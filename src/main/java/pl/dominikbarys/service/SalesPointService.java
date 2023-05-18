package pl.dominikbarys.service;

import org.springframework.stereotype.Service;
import pl.dominikbarys.dto.salespoint.CreateSalesPointDTO;
import pl.dominikbarys.dto.salespoint.SalesPointDTO;
import pl.dominikbarys.entity.Offer;
import pl.dominikbarys.entity.SalesPoint;
import pl.dominikbarys.exception.NotFoundException;
import pl.dominikbarys.exception.NotUniqueException;
import pl.dominikbarys.repository.OfferRepository;
import pl.dominikbarys.repository.ProductRepository;
import pl.dominikbarys.repository.SalesPointRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesPointService {

    private final SalesPointRepository salesPointRepository;

    private final OfferRepository offerRepository;

    public SalesPointService(SalesPointRepository salesPointRepository, OfferRepository offerRepository) {
        this.salesPointRepository = salesPointRepository;
        this.offerRepository = offerRepository;
    }

    public List<SalesPointDTO> findAllSalesPoints(){
        return salesPointRepository.findAll().stream()
                .map(this::convertSalesPointToSalesPointDTO)
                .collect(Collectors.toList());
    }

    public SalesPointDTO findSalesPointById(Integer id){

        SalesPoint salesPoint = salesPointRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sales point by id " + id + " was not found"));

        return convertSalesPointToSalesPointDTO(salesPoint);
    }

    public SalesPointDTO addSalesPoint(CreateSalesPointDTO createSalesPointDTO) {
        if(!salesPointRepository.existsByName(createSalesPointDTO.getName())) {

            List<Offer> offers = createSalesPointDTO.getOfferId().stream()
                    .map(id -> offerRepository.findById(id)
                            .orElseThrow(() -> new NotFoundException("Offer with id " + id + " was not found")))
                    .collect(Collectors.toList());

            SalesPoint newSalesPoint = new SalesPoint();
            newSalesPoint.setName(createSalesPointDTO.getName());
            newSalesPoint.setOffers(offers);

            salesPointRepository.save(newSalesPoint);

            return convertSalesPointToSalesPointDTO(newSalesPoint);

        } else {
            throw new NotUniqueException("SalesPoint with name " + createSalesPointDTO.getName() +" already exists");
        }
    }

    public SalesPointDTO addOffers(List<Integer> offersId, Integer id){
        SalesPoint salesPoint = salesPointRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Offer with id " + id + " was not found"));

        List<Integer> newOffers = offersId.stream()
                .filter(offerRepository::existsById)
                .collect(Collectors.toList());

        List<Offer> offers = offerRepository.findAllById(newOffers);

        salesPoint.getOffers().addAll(offers);
        salesPointRepository.save(salesPoint);

        return convertSalesPointToSalesPointDTO(salesPoint);
    }

    public SalesPointDTO removeOffers(List<Integer> offersId, Integer id){
        SalesPoint salesPoint = salesPointRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Offer with id " + id + " was not found"));

        List<Integer> offersToRemove = offersId.stream()
                        .filter(offerRepository::existsById)
                        .collect(Collectors.toList());

        salesPoint.getOffers().removeIf(offer -> offersToRemove.contains(offer.getId()));

        salesPointRepository.save(salesPoint);

        return convertSalesPointToSalesPointDTO(salesPoint);
    }

    public SalesPointDTO convertSalesPointToSalesPointDTO(SalesPoint salesPoint){
        SalesPointDTO salesPointDTO = new SalesPointDTO();
        salesPointDTO.setId(salesPoint.getId());
        salesPointDTO.setName(salesPoint.getName());

        List<Integer> offersId = salesPoint.getOffers().stream()
                .map(Offer::getId)
                .collect(Collectors.toList());

        salesPointDTO.setOffers(offersId);

        return salesPointDTO;
    }

}

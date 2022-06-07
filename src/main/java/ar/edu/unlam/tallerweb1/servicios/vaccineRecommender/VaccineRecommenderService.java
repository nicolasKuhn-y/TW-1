package ar.edu.unlam.tallerweb1.servicios.vaccineRecommender;

import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.repositorios.vaccineRecommender.VaccineRecommenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class VaccineRecommenderService implements IVaccineRecommenderService {
    private final VaccineRecommenderRepository countryRepository;

    @Autowired
    public VaccineRecommenderService(VaccineRecommenderRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Map<String, Set<Vaccine>> getVaccines(String countryCode) {
        Set<Vaccine> requiredVaccines = countryRepository.getRequiredVaccines(countryCode);
        Set<Vaccine> recommendedVaccines = countryRepository.getRecommendedVaccines(countryCode);

        Map<String, Set<Vaccine>> vaccines = new HashMap<>();

        vaccines.put("required", requiredVaccines);
        vaccines.put("recommended", recommendedVaccines);

        return vaccines;
    }

    @Override
    public List<Country> getCountries() {
        return countryRepository.getCountries();
    }
}

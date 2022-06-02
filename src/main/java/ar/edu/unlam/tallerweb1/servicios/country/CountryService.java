package ar.edu.unlam.tallerweb1.servicios.country;

import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.repositorios.country.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryService implements ICountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Vaccine> getVaccines(String countryName) {

        return countryRepository.getRequiredVaccines(countryName);
    }

    @Override
    public List<Country> getCountries() {
        return countryRepository.getCountries();
    }
}

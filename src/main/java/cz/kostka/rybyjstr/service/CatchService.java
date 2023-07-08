package cz.kostka.rybyjstr.service;

import cz.kostka.rybyjstr.repository.CatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatchService {

    private final CatchRepository catchRepository;

    @Autowired
    public CatchService(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }
}

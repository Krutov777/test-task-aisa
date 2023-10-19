package ru.coffeemachine.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coffeemachine.dto.request.AddCoffeeMachineRequest;
import ru.coffeemachine.dto.request.UpdateCoffeeMachineRequest;
import ru.coffeemachine.dto.response.CoffeeMachineResponse;
import ru.coffeemachine.exceptions.CoffeeMachineNotFoundException;
import ru.coffeemachine.models.CoffeeMachine;
import ru.coffeemachine.repositories.CoffeeMachineRepository;
import ru.coffeemachine.services.CoffeeMachineService;

import static ru.coffeemachine.constants.GlobalConstants.COFFEE_MACHINE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CoffeeMachineServiceImpl implements CoffeeMachineService {

    private final CoffeeMachineRepository coffeeMachineRepository;

    @Override
    public CoffeeMachineResponse addCoffeeMachine(AddCoffeeMachineRequest addCoffeeMachineRequest) {
        return CoffeeMachineResponse.from(
                coffeeMachineRepository.save(
                        CoffeeMachine.builder()
                                .name(addCoffeeMachineRequest.getName())
                                .state(CoffeeMachine.State.valueOf(addCoffeeMachineRequest.getState()))
                                .numberCupsCoffeePrepared(0)
                                .build()
                )
        );
    }

    @Override
    public CoffeeMachineResponse getCoffeeMachineById(Long coffeeMachineId) {
        return CoffeeMachineResponse.from(coffeeMachineRepository.findById(coffeeMachineId).orElseThrow(() -> new CoffeeMachineNotFoundException(COFFEE_MACHINE_NOT_FOUND)));
    }

    @Override
    public CoffeeMachineResponse updateCoffeeMachine(UpdateCoffeeMachineRequest updateCoffeeMachineRequest) {
        CoffeeMachine coffeeMachine = coffeeMachineRepository.findById(updateCoffeeMachineRequest.getId()).orElseThrow(() -> new CoffeeMachineNotFoundException(COFFEE_MACHINE_NOT_FOUND));
        coffeeMachine.setName(updateCoffeeMachineRequest.getName());
        coffeeMachine.setState(CoffeeMachine.State.valueOf(updateCoffeeMachineRequest.getState()));
        return CoffeeMachineResponse.from(coffeeMachineRepository.save(coffeeMachine));
    }
}

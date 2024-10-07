package com.bpm.engine.serviceImplement;

import com.bpm.engine.entitys.ReliefAssigned;
import com.bpm.engine.model.ReliefAssignedModel;
import com.bpm.engine.repository.ReliefAssignedRepository;
import com.bpm.engine.service.ReliefAssignedService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReliefAssignedServiceImpl implements ReliefAssignedService {

    @Autowired
    private ReliefAssignedRepository reliefAssignedRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ReliefAssignedModel createReliefAssigned(ReliefAssignedModel model) {
        try {
            ReliefAssigned entity = modelMapper.map(model, ReliefAssigned.class);
            ReliefAssigned savedEntity = reliefAssignedRepository.save(entity);
            return modelMapper.map(savedEntity, ReliefAssignedModel.class);
        } catch (Exception e) {
            throw new RuntimeException("Error creating ReliefAssigned", e);
        }
    }

    @Override
    @Transactional
    public ReliefAssignedModel updateReliefAssigned(ReliefAssignedModel model) {
        try {
            Optional<ReliefAssigned> optionalEntity = reliefAssignedRepository.findById(model.getIdRelief());
            if (optionalEntity.isPresent()) {
                ReliefAssigned entity = modelMapper.map(model, ReliefAssigned.class);
                ReliefAssigned updatedEntity = reliefAssignedRepository.save(entity);
                return modelMapper.map(updatedEntity, ReliefAssignedModel.class);
            } else {
                throw new RuntimeException("ReliefAssigned not found with id: " + model.getIdRelief());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating ReliefAssigned", e);
        }
    }

    @Override
    @Transactional
    public void updateTimeActive(Integer timeActive, Long idRelief) {
        try {
            reliefAssignedRepository.updateTimeActive(timeActive.toString(), idRelief);
        } catch (Exception e) {
            throw new RuntimeException("Error updating timeActive", e);
        }
    }

    @Override
    @Transactional
    public void updateActive(Boolean active, Long idRelief) {
        try {
            reliefAssignedRepository.updateActive(active.toString(), idRelief);
        } catch (Exception e) {
            throw new RuntimeException("Error updating active status", e);
        }
    }

    @Override
    public List<ReliefAssignedModel> findByActive(Boolean active) {
        try {
            return reliefAssignedRepository.findByActive(active).stream()
                    .map(entity -> modelMapper.map(entity, ReliefAssignedModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error finding ReliefAssigned by active", e);
        }
    }

    @Override
    public List<ReliefAssignedModel> findByUserCodeAndActive(String userCode, Boolean active) {
        try {
            return reliefAssignedRepository.findByUserCodeAndActive(userCode, active).stream()
                    .map(entity -> modelMapper.map(entity, ReliefAssignedModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error finding by userCode and active", e);
        }
    }

    @Override
    public List<ReliefAssignedModel> findByUserReliefCodeAndActive(String userReliefCode, Boolean active) {
        try {
            return reliefAssignedRepository.findByUserReliefCodeAndActive(userReliefCode, active).stream()
                    .map(entity -> modelMapper.map(entity, ReliefAssignedModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error finding by userReliefCode and active", e);
        }
    }

    @Override
    public List<ReliefAssignedModel> findByTemporaryAndActive(Boolean temporary, Boolean active) {
        try {
            return reliefAssignedRepository.findByTemporaryAndActive(temporary, active).stream()
                    .map(entity -> modelMapper.map(entity, ReliefAssignedModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error finding by temporary and active", e);
        }
    }

    @Override
    public List<ReliefAssignedModel> findByPermanentAndActive(Boolean permanent, Boolean active) {
        try {
            return reliefAssignedRepository.findByPermanentAndActive(permanent, active).stream()
                    .map(entity -> modelMapper.map(entity, ReliefAssignedModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error finding by permanent and active", e);
        }
    }

    @Override
    public List<ReliefAssignedModel> findByTemporaryAndReturnCommandAndActive(Boolean temporary, Boolean returnCommand, Boolean active) {
        try {
            return reliefAssignedRepository.findByTemporaryAndReturnCommandAndActive(temporary, returnCommand, active).stream()
                    .map(entity -> modelMapper.map(entity, ReliefAssignedModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error finding by temporary, returnCommand and active", e);
        }
    }

    @Override
    public List<ReliefAssignedModel> findByReturnCommandAndActive(Boolean returnCommand, Boolean active) {
        try {
            return reliefAssignedRepository.findByReturnCommandAndActive(returnCommand, active).stream()
                    .map(entity -> modelMapper.map(entity, ReliefAssignedModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error finding by returnCommand and active", e);
        }
    }

    @Override
    public List<ReliefAssignedModel> findByUserReliefCodeLike(String userReliefCode) {
        try {
            return reliefAssignedRepository.findByUserReliefCodeLike(userReliefCode).stream()
                    .map(entity -> modelMapper.map(entity, ReliefAssignedModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error finding by userReliefCode like", e);
        }
    }
}


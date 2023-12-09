package com.js.employee.serviceImplement;

import com.js.employee.entitys.ComputerSheet;
import com.js.employee.interfaces.IAssigned;
import com.js.employee.mapper.ComputerSheetMapper;
import com.js.employee.model.AssignedModel;
import com.js.employee.pojo.ComputerSheetPojo;
import com.js.employee.pojo.EmpleadoPojo;
import com.js.employee.repository.ComputerSheetRepository;
import com.js.employee.service.ComputerSheetService;
import com.js.employee.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ComputerSheetServiceImplement implements ComputerSheetService, IAssigned {

    @Autowired
    private ComputerSheetRepository computerSheetRepository;

    @Autowired
    private ComputerSheetMapper computerSheetMapper;

    @Autowired
    private EmpleadoService empleadoService;

    @Override
    public ComputerSheetPojo findByUserNameAndUserPassword(String userName, String userPassword) {
        ComputerSheetPojo comp = null;
        try{
           Optional<ComputerSheet> compOptional = computerSheetRepository.findByUserNameAndUserPassword(userName, userPassword);
           if(compOptional.isPresent()){
               comp =  computerSheetMapper.entityToPojo(compOptional.get());
           }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return comp;
    }

    @Override
    public List<ComputerSheetPojo> findByMail(String mail) {
        return computerSheetMapper.listEntityToListPojo(computerSheetRepository.findByMail(mail));
    }

    @Override
    public EmpleadoPojo findEmpledo(String userName, String userPassword) {
        ComputerSheetPojo computerSheetPojo = this.findByUserNameAndUserPassword(userName,userPassword);

        return null;
    }

    @Override
    public AssignedModel getEmployeeAssigned(String userName, String userPassword) {
        return this.generateAssigned(this.findEmpledo(userName,userPassword));
    }


}

package com.employee.mapper;

import com.employee.entitiy.Salary;
import com.employee.dto.SalaryDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    @Bean
    public ModelMapper getModelMapper(){
        PropertyMap<Salary, SalaryDTO> salaryMap = new PropertyMap<Salary, SalaryDTO>() {
            @Override
            protected void configure() {
                map().setFromDate(source.getSalaryID().getFromDate());
            }
        };


        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(salaryMap);
        return modelMapper;
    }
}

package pers.tanyong.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.tanyong.reggie.entity.Employee;
import pers.tanyong.reggie.mapper.EmployeeMapper;
import pers.tanyong.reggie.service.EmployeeService;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}

package com.example.LogisticCompany.dto.logisticCompany;

import com.example.LogisticCompany.dto.client.BaseClientDtoResponse;
import com.example.LogisticCompany.dto.client.ClientDtoResponse;
import com.example.LogisticCompany.dto.employee.BaseEmployeeDtoResponse;
import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.dto.income.BaseIncomeDtoResponse;
import com.example.LogisticCompany.dto.income.IncomeDtoResponse;
import com.example.LogisticCompany.dto.office.BaseOfficeDtoResponse;
import com.example.LogisticCompany.dto.office.OfficeDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogisticCompanyDtoResponse extends BaseLogisticCompanyDtoResponse {
    private Set<BaseOfficeDtoResponse> offices;

    private Set<BaseIncomeDtoResponse> incomes;

    private Set<BaseEmployeeDtoResponse> employees;

    private Set<BaseClientDtoResponse> clients;
}

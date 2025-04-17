package com.example.airbnb.dto.create;

import com.example.airbnb.model.domains.Country;
import com.example.airbnb.model.domains.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CreateHostDTO(
        String name,
        String surname,
        Long countryId
) {

    public Host toHost(Country country)
    {
        return new Host(name,surname,country);
    }
    public static CreateHostDTO from(Host host)
    {
        return  new CreateHostDTO(host.getName(), host.getSurname(), host.getCountry().getId());
    }
    public static List<CreateHostDTO> from(List<Host> hostList)
    {
        return hostList.stream().map(h->new CreateHostDTO(h.getName(), h.getSurname(), h.getCountry().getId())).toList();
    }

}

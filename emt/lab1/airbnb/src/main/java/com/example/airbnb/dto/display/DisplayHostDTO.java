package com.example.airbnb.dto.display;

import com.example.airbnb.dto.create.CreateHostDTO;
import com.example.airbnb.model.domains.Country;
import com.example.airbnb.model.domains.Host;

import java.util.List;

public record DisplayHostDTO(
        Long id,
        String name,
        String surname,
        Long countryId
) {
    public Host toHost(Country country)
    {
        return new Host(name,surname,country);
    }
    public static DisplayHostDTO from(Host host)
    {
        return  new DisplayHostDTO(host.getId(), host.getName(), host.getSurname(), host.getCountry().getId());
    }
    public static List<DisplayHostDTO> from(List<Host> hostList)
    {
        return hostList.stream().map(h->new DisplayHostDTO(h.getId(),h.getName(), h.getSurname(), h.getCountry().getId())).toList();
    }
}

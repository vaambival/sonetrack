package ru.vkurov.sonetrack.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vkurov.sonetrack.service.facade.RequestFacade;
import ru.vkurov.sonetrack.web.constants.Urls;
import ru.vkurov.sonetrack.web.dto.request.RequestDto;
import ru.vkurov.sonetrack.web.dto.request.RequestFilter;

@RestController
@RequiredArgsConstructor
@RequestMapping(Urls.Request.FULL)
public class RequestController {
    private final RequestFacade requestFacade;

    //TODO: добавить пагинацию
    @GetMapping
    public List<RequestDto> getAll(RequestFilter filter) {
        return requestFacade.getAll(filter);
    }

    @PatchMapping(Urls.Request.ById.FULL)
    public void ignoreRequest(@PathVariable("id") Long id) {
        requestFacade.ignoreRequest(id);
    }
}

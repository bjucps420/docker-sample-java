package edu.bju.todos.controllers;

import edu.bju.todos.dtos.TodoDto;
import edu.bju.todos.mapper.TodoMapper;
import edu.bju.todos.models.Todo;
import edu.bju.todos.services.TodoService;
import edu.bju.todos.utils.ApiResponse;
import edu.bju.todos.utils.SearchResponse;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todo/")
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper todoMapper;

    @GetMapping(path = "list", produces = "application/json")
    public ApiResponse<SearchResponse<TodoDto>> list(
        @RequestParam(value = "search", required = false, defaultValue = "") String search,
        @RequestParam(value = "groupBy", required = false, defaultValue = "") String groupByParam,
        @RequestParam(value = "groupDesc", required = false, defaultValue = "") String groupDescParam,
        @RequestParam(value = "sortBy", required = false, defaultValue = "title") String sortByParam,
        @RequestParam(value = "sortDesc", required = false, defaultValue = "false") String sortDescParam,
        @RequestParam(value = "page", required = false, defaultValue = "1") String pageParam,
        @RequestParam(value = "mustSort", required = false, defaultValue = "false") String mustSortParam,
        @RequestParam(value = "multiSort", required = false, defaultValue = "false") String multiSortParam,
        @RequestParam(value = "itemsPerPage", required = false, defaultValue = "10") String itemsPerPageParam
    ) {
        String[] groupBy = groupByParam.split(",");
        String[] groupByDesc = groupDescParam.split(",");
        String[] sortBy = sortByParam.split(",");
        String[] sortByDesc = sortDescParam.split(",");
        Integer page = Integer.parseInt(pageParam);
        Integer itemsPerPage = Integer.parseInt(itemsPerPageParam);
        Boolean mustSort = Boolean.parseBoolean(mustSortParam);
        Boolean multiSort = Boolean.parseBoolean(multiSortParam);

        var pageRequest = PageRequest.of(page - 1, itemsPerPage);
        if(sortBy.length > 0) {
            if(sortByDesc[0].equals("true")) {
                pageRequest = PageRequest.of(page - 1, itemsPerPage, Sort.Direction.DESC, sortBy[0]);
            } else {
                pageRequest = PageRequest.of(page - 1, itemsPerPage, Sort.Direction.ASC, sortBy[0]);
            }
        }

        Page<Todo> dataPage = null;
        if(StringUtils.isBlank(search)) {
            dataPage = todoService.findAll(
                pageRequest
            );
        } else {
            dataPage = todoService.findAllByTitleLike(
                search, pageRequest
            );
        }

        return ApiResponse.success(new SearchResponse<>(dataPage.getTotalElements(), todoMapper.from(dataPage.toList())));
    }
}

package com.ccsw.tutorial.prestamo.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

/**
 * @prestamo ccsw
 *
 */
public class PrestamoSearchDto {
    private PageableRequest pageable;
    private FiltersDto filterParams;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

    public FiltersDto getFilterParams() {
        return filterParams;
    }

    public void setFilterParams(FiltersDto filterDto) {
        this.filterParams = filterDto;
    }
}

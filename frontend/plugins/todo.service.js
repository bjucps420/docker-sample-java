export class TodoService {
  constructor (axios) {
    this.axios = axios
  }

  list(options) {
    const append = [];
    if(options.search) {
      append.push(`search=${options.search}`);
    }
    if(options.groupBy) {
      append.push(`groupBy=${options.groupBy.join(',')}`);
    }
    if(options.groupDesc) {
      append.push(`groupDesc=${options.groupDesc.join(',')}`);
    }
    if(options.sortBy) {
      append.push(`sortBy=${options.sortBy.join(',')}`);
    }
    if(options.sortDesc) {
      append.push(`sortDesc=${options.sortDesc.join(',')}`);
    }
    if(options.page) {
      append.push(`page=${options.page}`);
    }
    if(options.mustSort) {
      append.push(`mustSort=${options.mustSort}`);
    }
    if(options.multiSort) {
      append.push(`multiSort=${options.multiSort}`);
    }
    if(options.itemsPerPage) {
      append.push(`itemsPerPage=${options.itemsPerPage}`);
    }
    return this.axios.get(`/api/todo/list?${append.join('&')}`);
  }
}

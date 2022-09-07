import { TodoService } from './todo.service'

export default ({ app: { $axios } }, inject) => {
  const todoService = new TodoService($axios);

  inject('todoService', todoService);
}

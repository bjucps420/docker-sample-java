
<template>
  <v-row justify="center" align="center" class="my-lg-5">
    <v-col cols="12" lg="8">
      <v-card width="100%" :class="$vuetify.breakpoint.lgAndUp ? 'rounded-lg' : null" :tile="$vuetify.breakpoint.mdAndDown">
        <div class="secondary pa-6">
          <h2 class="font-weight-light white--text mb-n2">Todos</h2>
        </div>

        <div class="pa-8">
          <v-row align="end" class="pa-4">
            <v-spacer />
            <v-col cols="8" sm="4">
              <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                outlined
                hide-details
                clearable
                @change="getTodos"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-data-table
            :headers="headers"
            :items="todos"
            :options.sync="options"
            :server-items-length="total"
            :loading="loading"
            class="elevation-1"
            @update:options="getTodos"
          >
          </v-data-table>
        </div>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: 'IndexPage',
  data () {
    return {
      loading: true,
      search: '',
      total: 0,
      todos: [],
      options: {},
      headers: [
        { text: 'Title', value: 'title' },
      ],
    }
  },
  mounted() {
    this.getTodos();
  },
  methods: {
    refetch() {
      this.getTodos();
    },
    getTodos() {
      this.fetchTodos().then((data) => {
        if(data.success) {
          this.todos = data.response.items;
          this.total = data.response.total;
          this.loading = false;
        }
      });
    },
    fetchTodos() {
      return new Promise((resolve, reject) => {
        if(this.search) {
          this.options.search = this.search;
        } else {
          this.options.search = '';
        }
        this.$todoService.list(this.options).then((response) => {
          if(response.status === 200) {
            resolve(response.data);
          }
        });
      })
    },
  }
}
</script>

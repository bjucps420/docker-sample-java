<template>
  <v-app dark>
    <v-app-bar fixed app elevate-on-scroll>
      <v-btn text class="mr-2" color="secondary" @click="goHome">
        <span>Todos</span>
      </v-btn>

      <v-spacer />

      <v-toolbar-items>
        <v-btn text class="mr-2" color="secondary" @click="toggleTheme">
          <v-icon small left>mdi-brightness-6</v-icon>Theme
        </v-btn>
      </v-toolbar-items>
    </v-app-bar>

    <v-main>
      <Nuxt />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'DefaultLayout',
  data() {
    return {
    }
  },
  async mounted() {
    const theme = localStorage.getItem("useDarkTheme");
    if (theme) {
      if (theme === "true") {
        this.$vuetify.theme.dark = true;
      } else {
        this.$vuetify.theme.dark = false;
      }
    } else {
      this.$vuetify.theme.dark = false;
    }
  },
  methods: {
    toggleTheme() {
      this.$vuetify.theme.dark = !this.$vuetify.theme.dark;
      localStorage.setItem("useDarkTheme", this.$vuetify.theme.dark.toString())
    },
    goHome() {
      this.$router.push({path: "/"});
    },
  }
}
</script>

<template>
  <v-container>
    <v-row class="text-center">
      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">My Profile Page</h1>
        <CreateProfile/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
// @ is an alias to /src
import CreateProfile from '@/components/CreateProfile.vue'

export default {
  name: 'Home',
  components: {
    CreateProfile
  },
   data: () => ({
  }),
  methods: {
  }
}
</script>
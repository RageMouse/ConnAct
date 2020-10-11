<template>
  <v-container>
    <v-row class="text-center">
      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">My Events Page</h1>
        <CreateEvent/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
// @ is an alias to /src
import CreateEvent from '@/components/CreateEvent.vue'

export default {
  name: 'Home',
  components: {
    CreateEvent
  }
}
</script>
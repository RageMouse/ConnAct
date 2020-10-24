<template>
  <v-container>
    <v-row class="text-center">
      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">Events Page</h1>
        <Event/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
// @ is an alias to /src
import Event from '@/components/Event.vue'

export default {
  name: 'Home',
  components: {
    Event
  },
   data: () => ({
  }),
  methods: {
  }
}
</script>
<template>
  <v-container>
    <v-row class="text-center">
      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">Register</h1>
        <register/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
// @ is an alias to /src
import register from '@/components/Register.vue'

export default {
  name: 'test',
  components: {
    register
  }
}
</script>
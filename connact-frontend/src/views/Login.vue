<template>
  <v-container>
    <v-row class="text-center">
      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">login</h1>
        <login/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
// @ is an alias to /src
import login from '@/components/Login.vue'

export default {
  name: 'test',
  components: {
    login
  }
}
</script>
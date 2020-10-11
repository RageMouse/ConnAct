<template>
  <v-container>
    <v-card class="elevation-12">
      <v-toolbar dark color="primary">
        <v-toolbar-title>Create Event</v-toolbar-title>
      </v-toolbar>
      <v-card-text>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            id="eventName"
            v-model="eventName"
            :counter="25"
            :rules="nameRules"
            label="Name"
            required
          ></v-text-field>
        </v-form>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    eventName: "",
    description: "",
    dateStart: "",
    dateEnd: "",
    state: ""
  }),

  methods: {
  }
};
</script>
<template>
  <v-container>
    <v-card class="elevation-12 mx-auto" max-width="1000px">
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

          <v-textarea
            id="eventnDescription"
            v-model="eventDescription"
            :counter="255"
            :rules="[(v) => !!v || 'Description is required']"
            label="Description"
            required
          ></v-textarea>

          <v-menu
            v-model="menu2"
            :close-on-content-click="true"
            :nudge-right="40"
            transition="scale-transition"
            offset-y
            min-width="290px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="dateStart"
                label="Start Date for event"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
              v-model="dateStart"
              @input="menu2 = true"
              :min="getNowDate"
            ></v-date-picker>
          </v-menu>

          <v-menu
            v-model="menu2"
            :close-on-content-click="true"
            :nudge-right="40"
            transition="scale-transition"
            offset-y
            min-width="290px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="dateEnd"
                label="End Date for event"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
              v-model="dateEnd"
              @input="menu2 = true"
              :min="dateStart"
            ></v-date-picker>
          </v-menu>

          <v-btn
            id="validateButton"
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="validate"
            >Validate</v-btn
          >
        </v-form>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    eventName: "",
    nameRules: [
      (v) => !!v || "Name is required",
      (v) => (v && v.length <= 25) || "Name must be less than 25 characters",
    ],
    eventDescription: "",
    dateStart: "",
    dateEnd: "",
    state: "",
    selectedDate: null,
  }),

  methods: {},

  computed: {
    getNowDate() {
      var nowDate = new Date();
      return nowDate.toISOString().slice(0, 10);
    },
  },
};
</script>
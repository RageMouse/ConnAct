<template>
  <v-container>
    <v-card class="elevation-12 mx-auto" max-width="1000px">
      <v-toolbar dark color="primary">
        <v-toolbar-title>Create Event</v-toolbar-title>
      </v-toolbar>
      <v-card-text>
        <v-form ref="form" lazy-validation>
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
              :min="getNowDate"
            ></v-date-picker>
          </v-menu>

          <v-menu
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
            <v-date-picker v-model="dateEnd" :min="dateStart"></v-date-picker>
          </v-menu>
        </v-form>
      </v-card-text>
      <v-card-actions
        ><v-btn id="validateButton" color="success" class="mr-4" @click="send"
          >Sumbit</v-btn
        >
      </v-card-actions>
    </v-card>
    <br />
    <v-alert
      class="elevation-12 mx-auto"
      outlined
      type="success"
      text
      :value="alertSucces"
      max-width="1000px"
    >
      Event has succesfully been created!
    </v-alert>
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
    alertSucces: false,
  }),

  methods: {
    send: function () {
      this.axios
        .post("http://192.168.99.100:8089/event/", {
          eventName: this.eventName,
          eventDescription: this.eventDescription,
          dateStart: this.dateStart,
          dateEnd: this.dateEnd,
        })
        .then((response) => {
          console.log(response.status);
          if (response.status !== 204) {
            this.alertSucces = true;
          }
        })
        .catch((error) => {
          console.log(error.response);
        });
    },
  },

  computed: {
    getNowDate() {
      var nowDate = new Date();
      return nowDate.toISOString().slice(0, 10);
    },
  },
};
</script>
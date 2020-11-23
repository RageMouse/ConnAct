<template>
  <v-dialog
    width="500"
    :retain-focus="false"
    v-model="getDialog"
    @click:outside="closeDialog"
  >
    <v-card class="mx-auto" max-width="500">
      <v-img
        src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
        height="200"
      ></v-img>

      <v-card-title>
        {{ getEvent.eventName }}
      </v-card-title>

      <v-card-text v-if="!getEvent.active">
        event active: {{ getEvent.active }}
      </v-card-text>

      <v-card-text>
        {{ getEvent.description }}
      </v-card-text>

      <v-card-text>
        {{ getEvent.dateEnd }}
      </v-card-text>

      <v-card-text>
        {{ getEvent.dateStart }}
      </v-card-text>

      <v-btn
        v-if="getBtnText == 'join'"
        color="primary"
        text
        @click="joinEvent"
      >
        Join event
      </v-btn>

      <v-btn
        v-if="getBtnText == 'close'"
        color="primary"
        text
        @click="closeEvent"
      >
        Close event
      </v-btn>

      <v-alert
        class="elevation-12 mx-auto"
        outlined
        type="success"
        text
        :value="alert"
      >
        Event has been closed
      </v-alert>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  mounted() {
    this.hideAlert();
  },
  methods: {
    closeDialog: function () {
      this.$store.commit("updateEventDialog");
    },
    closeEvent: function () {
      this.$store.dispatch("closeEvent", this.getEvent.eventId);
    },
    joinEvent: function () {
      // join event here
    },
    alertTimer: function () {
      window.setInterval(() => {
        this.alert = false;
      }, 3000);
    },
    hideAlert: function () {
      if (alert) {
        this.alertTimer();
      }
    },
  },
  data: () => ({
    alert: false,
  }),
  computed: {
    getDialog: {
      get() {
        return this.$store.getters.eventDialog;
      },
      set(bool) {
        return bool;
      },
    },
    getEvent: function () {
      return this.$store.getters.eventDetail;
    },
    getBtnText: function () {
      return this.$store.getters.btnText;
    },
  },
};
</script>
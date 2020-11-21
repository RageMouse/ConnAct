<template>
<v-dialog v-model="show" max-width="500px">
  <v-card>
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
            id="eventDescription"
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
    <v-card-actions>
      <v-btn color="primary" @click.stop="show=false">Close</v-btn>
    </v-card-actions>
  </v-card>
</v-dialog>
</template>

<script>
export default {
  props: ['visible'],
  computed: {
    show: {
      get () {
        return this.visible
      },
      set (value) {
        if (!value) {
          this.$emit('close')
        }
      }
    },
    getNowDate() {
      var nowDate = new Date();
      return nowDate.toISOString().slice(0, 10);
    },
  },
  data: () => ({
    eventId: this.$store.getters.eventId,
    eventName: "",
    nameRules: [
      (v) => !!v || "Name is required",
      (v) => (v && v.length <= 25) || "Name must be less than 25 characters",
    ],
    eventDescription: "",
    dateStart: "",
    dateEnd: "",
    selectedDate: null,
  }),
}
</script>
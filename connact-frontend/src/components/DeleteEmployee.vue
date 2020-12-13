<template>
  <v-dialog v-model="show" max-width="500px">
    <v-card>
      <v-img
        class="white--text align-end mt-4"
        height="100px"
        src="https://picsum.photos/800"
      >
      </v-img>
        <v-card-title class="headline">
          Are you sure?
        </v-card-title>
        <v-card-text>There is no way to reverse this action.</v-card-text>
      <v-card-actions>
          <v-spacer></v-spacer>
        <v-btn color="error" text @click.stop="show = false">
          Disagree
        </v-btn>
        <v-btn color="primary" text @click="deleteEmployee" @click.stop="show = false">
          Agree
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: ["visible"],
  data() {
    return {
      dialog: false,
    };
  },
  computed: {
    show: {
      get() {
        return this.visible;
      },
      set(value) {
        if (!value) {
          this.$emit("close");
        }
      },
    },
  },
    methods: {
    deleteEmployee() {
      return this.$store.dispatch("deleteEmployee", this.$store.getters.userId);
    }
  },
};
</script>
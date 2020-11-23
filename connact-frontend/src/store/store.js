import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios"

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userid: '1',
        user: null,
        events: {},
        eventDetail: {},
        eventDialog: false,
        btnText: "",
        eventId: '',
        skills: [],
        interests: [],
        profile: {},
    },
    getters: {
        events(state) {
            return state.events
        },
        userId(state) {
            return state.userid
        },
        eventDetail(state) {
            return state.eventDetail
        },
        eventDialog(state) {
            return state.eventDialog
        },
        btnText(state) {
            return state.btnText
        },
        eventId(state) {
            return state.eventId
        },
        skills(state) {
            return state.skills
        },
        interests(state) {
            return state.interests
        },
        profile(state) {
            return state.profile
        },
    },
    mutations: {
        updateUserid(state, message) {
            state.userid = message
            console.log(this.userid + 'dsdsff')
        },
        setEvents(state, events) {
            state.events = events;
        },
        updateEventDetail(state, event) {
            state.eventDetail = event
        },
        updateEventDialog(state) {
            state.eventDialog = !state.eventDialog
        },
        updateBtnText(state, text) {
            state.btnText = text
        },
        setEventId(state, id) {
            state.eventId = id;
        },
        updateSkills(state, skills) {
            state.skills = skills
        },
        updateInterests(state, interests) {
            state.interests = interests
        },
        updateProfile(state, editProfileModel) {
            state.profile = editProfileModel
        }
    },
    actions: {
        getMyEvents(context, id) {
            return axios
                .get("http://192.168.178.20:8089/event/" + id)
                .then((response) => {
                    context.commit("setEvents", response.data);
                })
                .catch(error => {
                    throw new Error(error)
                });
        },
        closeEvent(context, id) {
            return axios
                .put("http://192.168.178.20:8089/event/" + id)
                .then((response) => {
                    console.log(response.status)
                })
                .catch((error) => {
                    console.log(error.response);
                });
        },
        setEventId(context, id) {
            context.commit("setEventId", id)
        },
        editEvent(context, data) {
            return axios
                .put("http://192.168.178.20:8089/event/", {
                    eventId: this.getters.eventId,
                    eventName: data.eventName,
                    eventDescription: data.eventDescription,
                    dateStart: data.dateStart,
                    dateEnd: data.dateEnd
                })
        },
        loadSkills(context) {
            return axios
                .get("http://192.168.178.20:8089/skill/")
                .then((response) => {
                    context.commit('updateSkills', response.data)
                });
        },
        loadInterests(context) {
            return axios
                .get("http://192.168.178.20:8089/interest/")
                .then((response) => {
                    context.commit('updateInterests', response.data)
                });
        },
        createProfile(context, profileCreateForm) {
            return axios
                .post("http://192.168.178.20:8089/profile/", {
                    displayName: profileCreateForm.displayName,
                    education: profileCreateForm.education,
                    skills: profileCreateForm.skills,
                    interests: profileCreateForm.interests,
                })
                .then((response) => {
                    console.log(response.status);
                })
                .catch((error) => {
                    console.log(error.response);
                });
        },
        loadProfile(context) { // later met userId
            return axios
                .get("http://192.168.178.20:8089/profile/")
                .then((response) => {
                    context.commit('updateProfile', response.data)
                })
        },
        editProfile(context, profileEditForm) {
            return axios
                .put("http://192.168.178.20:8089/profile/", {
                    profileId: profileEditForm.profileId,
                    displayName: profileEditForm.editedDisplayName,
                    education: profileEditForm.editedEducation,
                    skills: profileEditForm.editedSkills,
                    interests: profileEditForm.editedInterests,
                })
                .then((response) => {
                    context.commit('updateProfile', response.data)
                    console.log(response.status);
                })
                .catch((error) => {
                    console.log(error.response);
                });
        }

    },
})
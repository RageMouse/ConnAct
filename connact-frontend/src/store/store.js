import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios"

Vue.use(Vuex)

var apiUrl = "http://192.168.178.20:8089/"

export default new Vuex.Store({
    state: {
        userid: '0',
        user: {},
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
        user(state){
            return state.user
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
        updateUser(state, user){
            state.user = user
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
        updateProfile(state, profileModel) {
            state.profile = profileModel
        },
    },
    actions: {
        getMyEvents(context, id) {
            return axios
                .get(apiUrl + "event/" + id)
                .then((response) => {
                    context.commit("setEvents", response.data);
                })
                .catch(error => {
                    throw new Error(error)
                });
        },
        closeEvent(context, id) {
            return axios
                .put(apiUrl + "event/" + id)
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
                .put(apiUrl + "event/", {
                    eventId: this.getters.eventId,
                    eventName: data.eventName,
                    eventDescription: data.eventDescription,
                    dateStart: data.dateStart,
                    dateEnd: data.dateEnd
                })
        },
        loadSkills(context) {
            return axios
                .get(apiUrl + "skill/")
                .then((response) => {
                    context.commit('updateSkills', response.data)
                });
        },
        loadInterests(context) {
            return axios
                .get(apiUrl + "interest/")
                .then((response) => {
                    context.commit('updateInterests', response.data)
                });
        },
        createProfile(context, profileCreateForm) {
            return axios
                .post(apiUrl + "profile/", {
                    displayName: profileCreateForm.displayName,
                    education: profileCreateForm.education,
                    skills: profileCreateForm.skills,
                    interests: profileCreateForm.interests,
                    employeeId: profileCreateForm.userId,
                })
                .then((response) => {
                    context.commit('updateProfile', response.data)
                    console.log(response.status);
                })
                .catch((error) => {
                    console.log(error.response);
                });
        },
        loadProfile(context, employeeId) {
            return axios
                .get(apiUrl + "profile/" + employeeId)
                .then((response) => {
                    context.commit('updateProfile', response.data)
                })
        },
        editProfile(context, profileEditForm) {
            return axios
                .put(apiUrl + "profile/", {
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
        },
        getUserById(context, id){
            return axios
                .get(apiUrl + "employee/" + id)
                .then((response) => {
                    context.commit('updateUser', response.data)
                })
        },

        deleteEvent(context, id) {
            return axios
                .delete(apiUrl + "event/" + id)
                .then(() => {
                    this.getMyEvents
                })
                .catch(error => {
                    throw new Error(error)
                });
        },
        createEvent(context, data) {
            return axios
                .post(apiUrl + "event/", {
                    ownerId: this.getters.userId,
                    eventName: data.eventName,
                    eventDescription: data.eventDescription,
                    dateStart: data.dateStart,
                    dateEnd: data.dateEnd,
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
    }
})
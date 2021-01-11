import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios"

Vue.use(Vuex)

var apiUrl = "http://192.168.178.20:8089/"

export default new Vuex.Store({
    state: {
        userid: null,
        user: {},
        events: {},
        eventDetail: {},
        eventDialog: false,
        btnText: "",
        eventId: '',
        skills: [],
        interests: [],
        profile: {},
        requests: [],
        invites: [],
        users: [],
        deletedRequest: null,
    },
    getters: {
        requests(state) {
            return state.requests
        },
        invites(state) {
            return state.invites
        },
        users(state) {
            return state.users
        },
        events(state) {
            return state.events
        },
        user(state) {
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
        },
        updateUser(state, user) {
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
        setUsers(state, users) {
            state.users = users;
        },
        logout(state) {
            state.userid = null
        },
        setRequests(state, requests) {
            state.requests = requests
        },
        setInvites(state, invites) {
            state.invites = invites
        },
        addEvent(state, event) {
            state.events.push(event)
        },
        removeEvent(state, id) {
            state.events.splice(state.events.findIndex(event => event.eventId === id), 1)
        }
    },
    actions: {
        loadRequests(context, eventid) {
            return axios
                .get(apiUrl + "event/requests/" + eventid)
                .then((response) => {
                    context.commit("setRequests", response.data)
                });
        },
        acceptRequest(context, id) {
            return axios
                .put(
                    apiUrl + "event/accept",
                    {
                        id: id,
                        accepted: true,

                    })
                .then(response => {
                    console.log(response.status);
                })
                .catch(error => {
                    console.log(error.response)
                })
        },
        leaveEvent(context, userid) {
            return axios
                .put(
                    apiUrl + "event/leave/" + userid)
                .then(response => {
                    console.log(response.status);
                })
                .catch(error => {
                    console.log(error.response)
                })
        },
        loadInvites(context, employeeid) {
            return axios
                .get(apiUrl + "event/invites/" + employeeid)
                .then((response) => {
                    context.commit("setInvites", response.data)
                });
        },
        inviteEmployee(context, eventid) {
            return axios
                .post(apiUrl + "event/request", {
                    employeeId: this.$store.state.userid,
                    eventId: eventid,
                    requesttype: "uitnodiging",
                    accepted: false
                })
                .then((response) => {

                    if (response.status !== 204) {
                        this.alertSucces = true;
                    }
                })
                .catch((error) => {
                    console.log(error.response);
                });
        },
        removeRequest(context, requestid) {
            return axios
                .delete(apiUrl + "event/requests/kick/" + requestid)
                .then((response) => {

                    this.deletedRequest = response.data;
                });
        },
        loadUsers(context, eventid) {
            return axios
                .get(apiUrl + "event/users/" + eventid)
                .then((response) => {
                    context.commit("setUsers", response.data)
                });
        },
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
        createInvite(context, { employeeid, eventid }) {
            return axios
                .post(apiUrl + "event/request", {
                    employeeId: employeeid,
                    eventId: eventid,
                    requesttype: "uitnodiging",
                    accepted: false
                })
                .then((response) => {

                    if (response.status !== 204) {
                        this.alertSucces = true;
                    }
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
        /*
        getUserById(context, id){
            return axios
                .get(apiUrl + "employee/" + id)
                .then((response) => {
                    context.commit('updateUser', response.data)
                })
        },
        */
        deleteEvent(context, id) {
            return axios
                .delete(apiUrl + "event/" + id)
                .then(() => {
                    context.commit("removeEvent", id)
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
                    context.commit("addEvent", response)
                })
                .catch((error) => {
                    console.log(error.response);
                });
        },
        deleteEmployee(context, id) {
            var empty = {}
            return axios
                .delete(apiUrl + "employee/" + id)
                .then(context.commit('updateProfile', empty))
                .catch(error => {
                    throw new Error(error)
                });
        },
        loadEvents(context) {
            return axios
            .get(apiUrl + "event/")
            .then((response) => {
                context.commit("setEvents", response.data);
            })
        }
    }
})
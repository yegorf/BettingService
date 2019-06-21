<template>
    <div>
        <b>Registration</b><br>
        <b>Username</b><br>
        <input type="text" ref="usernameInput"><br>
        <b>Password</b><br>
        <input type="text" ref="passwordInput"><br>
        <b>Email</b><br>
        <input type="text" ref="emailInput"><br>
        <button @click="enter">Enter</button>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "Registration",
        methods: {
            enter() {
                const username = this.$refs.usernameInput.value;
                const password = this.$refs.passwordInput.value;
                const email = this.$refs.emailInput.value;

                axios.post('/users/addUser', null, {
                    params : {
                        username,
                        password,
                        email
                    }
                }).then(res => {
                    alert(res.data);
                });
            }
        }
    }
</script>

<style scoped>

</style>
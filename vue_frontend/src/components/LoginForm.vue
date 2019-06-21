<template>
    <div>
        <b>Registration</b><br>
        <b>Username</b><br>
        <input type="text" ref="usernameInput"><br>
        <b>Password</b><br>
        <input type="text" ref="passwordInput"><br>
        <button @click="enter">Enter</button>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "LoginForm",
        methods: {
            enter() {
                const username = this.$refs.usernameInput.value;
                const password = this.$refs.passwordInput.value;

                axios.post('/users/login', null, {
                    params : {
                        username,
                        password
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
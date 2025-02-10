<template>
  <a-row class="login">
    <a-col :span="8" :offset="8" class="login-main">
      <h1 style="text-align: center">&nbsp;Dora Train Reservation</h1>
      <a-form
          :model="loginForm"
          name="basic"

          autocomplete="off"
          @finish="onFinish"
          @finishFailed="onFinishFailed"
      >
        <a-form-item
            label=""
            name="mobile"
            :rules="[{ required: true, message: 'Please input your Phone Number!' }]"
        >
          <a-input v-model:value="loginForm.mobile" />
        </a-form-item>

        <a-form-item
            label=""
            name="code"
            :rules="[{ required: true, message: 'Please input your one time code!' }]"
        >
          <a-input v-model:value="loginForm.code" >
            <template #addonAfter>
              <a @click="sendCode">Request</a>
            </template>
          </a-input>

        </a-form-item>



        <a-form-item>
          <a-button type="primary" block @click="login">Login</a-button>
        </a-form-item>
      </a-form>
    </a-col>

  </a-row>
</template>
<script>
import { defineComponent, reactive } from 'vue';
import axios from 'axios';
import {notification} from "ant-design-vue";
import {useRouter} from "vue-router"
export default defineComponent({
  name: "login-view",

  setup() {

    const router = useRouter();

    const loginForm = reactive({
      mobile: '17182569875',
      code: '',
    });
    const onFinish = values => {
      console.log('Success:', values);
    };

    const onFinishFailed = errorInfo => {
      console.log('Failed:', errorInfo);
    };

    const sendCode = () => {
      axios.post("/member/member/send-code", {
        mobile: loginForm.mobile
      }).then(response => {
        console.log(response);
        let data = response.data;
        if(data.success) {
          notification.success({description:"Code have been sent"});
          // Router to main page after login success
          router.push("/");
        } else {
          notification.error({description:data.message});
        }
      })
    }
    const login = () => {
      axios.post("/member/member/login", {
        mobile: loginForm.mobile,
        code: loginForm.code
      }).then(response => {

        console.log(response);
        let data = response.data;
        if(data.success) {
          notification.success({description:"Successful Login"});
          console.log(response)
        } else{
          notification.error({description:data.error});
        }
      })
    }
    return {
      loginForm,
      onFinish,
      onFinishFailed,
      sendCode,
      login,
    };
  },
});
</script>

<style>
.login-main h1 {
  font-size: 25px;
  font-weight: bold;
}
.login-main {
  margin-top:100px;
  padding: 30px 30px 30px;
  border: 2px solid grey;
  border-radius: 10px;
  background-color: #fcfcfc;
}
</style>
import axios from "axios"
import { useState } from "react"

const Login = () => {
  const [userName, setUserName] = useState('')
  const [password, setPassword] = useState('')

  const onLogin = (e) => {
    e.preventDefault();
  }

  return (
    <div style={{height: '100vh', display: 'flex', marginTop: '150px', justifyContent: 'center'}}>
      <form style={{width: "450px"}}>
      
      <header style={{fontSize: '48px', color: '#0d6efd', fontWeight: 'bold', textAlign: 'center', marginBottom: '40px'}}>Login</header>
        <div class="form-outline mb-4">
          <input type="email" id="form2Example1" class="form-control" value={userName} onChange={e => setUserName(e.value)} />
          <label class="form-label" for="form2Example1">Username</label>
        </div>

        <div class="form-outline mb-4">
          <input type="password" id="form2Example2" class="form-control" value={password} onChange={e => setPassword(e.value)} />
          <label class="form-label" for="form2Example2">Password</label>
        </div>

        <div class="row mb-4">
          <div class="col d-flex justify-content-center">
            <div class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked />
              <label class="form-check-label" for="form2Example31"> Remember me </label>
            </div>
          </div>

          <div class="col">
            <a href="#!">Forgot password?</a>
          </div>
        </div>

        <button type="button" class="btn btn-primary btn-block mb-4" style={{width: '100%'}} onClick={() => onLogin()}>Sign in</button>

        <div class="text-center">
          <p>Not a member? <a href="#!">Register</a></p>
        </div>
      </form>
    </div>
  )
}

export default Login
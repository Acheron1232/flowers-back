

async function login(){

    let resp = await fetch("http://localhost:8080/api/v2/login",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({
            email:"asd",
            password:"asd"
        })
    })
    console.log(await resp.text())
}
async function registration(){

    let resp = await fetch("http://localhost:8080/api/v2/registration",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({
            firstName:"asd",
            lastName:"asd",
            email:"asd",
            password:"asd",
            phoneNumber: "123"
        })
    })
    console.log(await resp.text())
}
async function permit(){
    let resp = await fetch("http://localhost:8080/api/v1/permit")
}
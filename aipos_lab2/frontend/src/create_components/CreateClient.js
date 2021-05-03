import React, {Component} from "react";
import { Button, TextField } from '@material-ui/core';
import axios from 'axios';
import {Link, withRouter} from "react-router-dom";
const axiosPOSTconfig = {headers: {'Content-Type': 'application/json'}};


class CreateClient extends Component{

    constructor(props) {
        super(props)
        this.state = {
            name: "",
            phoneNumber: ""
        }
    }

    onChange = (event) => {
        this.setState({[event.target.id]: event.target.value});
    }

    onSubmit = (event) => {
        let {name, phoneNumber} = this.state;
        event.preventDefault();
        if(name === '' || phoneNumber === ''){
            alert('Enter all Fields');
        }
        else {
            axios.post('http://localhost:8080/client', JSON.stringify({
                'name': name,
                'phoneNumber': phoneNumber
            }), axiosPOSTconfig)
                .then((response) => {
                    this.setState({status: response.data.status});
                    alert('Creating completed');
                })
                .catch((error) => {console.log(error)});
        }
    }

    render() {
        let {name, phoneNumber} = this.state;
        return(
            <main>
                <div>
                    <form onSubmit={this.onSubmit}>
                        <TextField id="name" type="text" value={name} placeholder={"Name"} onChange={this.onChange}/><br/>
                        <TextField id="phoneNumber" type="text" value={phoneNumber} placeholder={"Phone number"} onChange={this.onChange}/><br/>

                        <br/><Button onClick={this.onSubmit} variant="contained" color="primary">Added client</Button><br/>
                        <br/><Button component={Link} to="/clients" variant="contained" color="primary">Client's Table</Button>
                    </form>
                </div>
            </main>
        );
    }
}
export default withRouter(CreateClient);

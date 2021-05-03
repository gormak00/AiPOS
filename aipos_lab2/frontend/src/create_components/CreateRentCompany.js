import React, {Component} from "react";
import { Button, TextField } from '@material-ui/core';
import axios from 'axios';
import {Link, withRouter} from "react-router-dom";
const axiosPOSTconfig = {headers: {'Content-Type': 'application/json'}};


class CreateRentCompany extends Component{

    constructor(props) {
        super(props)
        this.state = {
            name: ""
        }
    }

    onChange = (e) => {
        this.setState({[e.target.id]: e.target.value});
    }

    onSubmit = (event) => {
        let {name} = this.state;
        event.preventDefault();
        if(name === ''){
            alert('Enter all fields');
        }
        else{
            axios.post('http://localhost:8080/rentCompany', JSON.stringify({
                'name': name
            }), axiosPOSTconfig)
                .then((response) => {
                    this.setState({status: response.data.status});
                    alert('Creating completed');
                })
                .catch((error) => {console.log(error)});
        }
    }

    render() {
        let {name} = this.state;
        return(
            <main>
                <div>
                    <form onSubmit={this.onSubmit}>
                        <TextField id="name" type="text" value={name} placeholder={"Name"} onChange={this.onChange}/><br/>

                        <br/><Button onClick={this.onSubmit} variant="contained" color="primary">Added RentCompany</Button><br/>
                        <br/><Button component={Link} to="/rentCompanies" variant="contained" color="primary">RentCompany's Table</Button>
                    </form>
                </div>
            </main>
        );
    }
}
export default withRouter(CreateRentCompany);

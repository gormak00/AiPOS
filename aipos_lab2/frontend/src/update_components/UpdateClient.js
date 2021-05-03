import React, {Component } from "react";
import axios from "axios";
import {Link, withRouter} from "react-router-dom";
import Button from "@material-ui/core/Button";
import {Input} from "@material-ui/core";
const axiosPOSTconfig = {headers: {'Content-Type': 'application/json'}};



class UpdateClient extends Component{

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            phoneNumber: ''
        };
    }

    onChange = (event) => {
            this.setState({[event.target.id]: event.target.value});
    }

    onSubmit = (event) => {
        event.preventDefault();
        let {name, phoneNumber} = this.state;
        if((name === '') || (phoneNumber === '')){
            alert('You need Enter all Fields');
        }
        else{
            axios.post(`http://localhost:8080/client/` + this.props.match.params.id, JSON.stringify({
                'name': name,
                'phoneNumber': phoneNumber
            }), axiosPOSTconfig)
                .then((response) => {
                    alert('Update Completed');
                    this.setState({status: response.data.status});
                })
                .catch((error) => {console.log(error)});
        }

    }

    componentDidMount() {
        console.log(this.props);
        axios.get(`http://localhost:8080/client/`+this.props.match.params.id)
            .then((response) => {this.setState({name: response.data.data.name, phoneNumber: response.data.data.phoneNumber});})
            .catch((error) => {console.log(error); this.setState({ message: error.message.details })});
    }

    render() {
        let {name, phoneNumber} = this.state;
        return(
            <main role="main" className="container">
                <div>
                    <form onSubmit={this.onSubmit}>
                        <Input id="name" type="text" value={name} placeholder={"Name"} onChange={this.onChange}/><br/>
                        <Input id="phoneNumber" type="text" value={phoneNumber} placeholder={"Phone number"} onChange={this.onChange}/><br/>
                        <br/>
                        <Button onClick={this.onSubmit} variant="contained" color="primary">Update</Button><br/>
                        <br/><Button component={Link} to="/clients" variant="contained" color="primary">Client's Table</Button>
                    </form>
                </div>
            </main>
        );
    }
}

export default withRouter(UpdateClient);

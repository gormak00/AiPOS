import React, {Component} from "react";
import axios from "axios";
import {Link, withRouter} from "react-router-dom";
import Button from "@material-ui/core/Button";
import {TextField} from "@material-ui/core";
const axiosPOSTconfig = {headers: {'Content-Type': 'application/json'}};


class UpdateRentCompany extends Component{

    constructor(props) {
        super(props);
        this.state = {
            name: ''
        };
    }

    onChange = (event) => {
        this.setState({[event.target.id]: event.target.value});
    }

    onSubmit = (event) => {
        event.preventDefault();
        let {key, game} = this.state;
        if(name === '') {
            alert('Enter all Fields');
        }
        else{
            axios.post('http://localhost:8080/updateRentCompany/' + this.props.match.params.id, JSON.stringify({
                'name': name
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
        axios.get(`http://localhost:8080/rentCompany/`+this.props.match.params.id)
            .then((response) => {this.setState({name: response.data.name});})
            .catch((error) => {console.log(error); this.setState({ message: error.message })});
    }

    render() {
        let {name} = this.state;
        return(
            <main role="main" className="container">
                <div>
                    <form onSubmit={this.onSubmit}>
                        <TextField id="key" type="text" value={name} placeholder={"Name"} onChange={this.onChange}/><br/>

                        <br/><Button onClick={this.onSubmit} variant="contained" color="primary">Update Key</Button><br/>
                        <br/><Button component={Link} to="/rentCompanies" variant="contained" color="primary">RentCompany's Table</Button><br/>
                    </form>
                </div>
            </main>
        );
    }
}

export default withRouter(UpdateRentCompany);

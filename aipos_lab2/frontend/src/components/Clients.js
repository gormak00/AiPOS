import React, {Component} from "react";
import axios from "axios";
import {Link, withRouter} from "react-router-dom";
import Button from "@material-ui/core/Button";
import {Avatar} from "@material-ui/core";

class Clients extends Component{
    constructor(props) {
        super(props);
        this.state = {
            rows: []
        };
    }

    componentDidMount() {
        axios.get('http://localhost:8080/clients')
            .then((response) => {this.setState({rows: response.data});})
            .catch((error) => {console.log(error); this.setState({ message: error.message })});
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        this.componentDidMount();
    }

    render() {
        return (
            <main role="main" className="container">
                <div align="center">
                    {this.state.rows === null && <p>Loading menu...</p>}
                    <table className="clients">
                        <thead>
                            <tr>
                                <th width={50}>ID</th>
                                <th width={100}>Name</th>
                                <th width={300}>PhoneNumber</th>
                            </tr>
                        </thead>
                        {this.state.rows && this.state.rows.map(client => (
                            <tbody align="center">
                                <tr>
                                    <td width={50}>{client.id}</td>
                                    <td width={100}>{client.name}</td>
                                    <td width={300}>{client.phoneNumber}</td>
                                    <Button component={Link} to={'/client/delete/' + client.id} variant="contained" color="primary">Delete</Button>
                                    <Button component={Link} to={'/client/update/' + client.id} variant="contained" color="primary">Update</Button>
                                </tr>
                            </tbody>
                        ))
                        }
                    </table>
                    <div>
                        <Button component={Link} to="/client/add" variant="contained" color="primary">Add Client</Button>
                    </div>
                </div>
            </main>
        );
    }
}
export default withRouter(Clients);

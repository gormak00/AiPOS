import React, {Component} from "react";
import axios from "axios";
import {Link, withRouter} from "react-router-dom";
import Button from "@material-ui/core/Button";

class RentCompanies extends Component{
    constructor(props) {
        super(props);
        this.state = {
            rows: []
        };
    }

    componentDidMount() {
        axios.get('http://localhost:8080/rentCompanies')
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
                    <table className="rentCompanies">
                        <thead>
                            <tr>
                                <th width={50}>Id</th>
                                <th width={300}>Name</th>
                            </tr>
                        </thead>
                        {this.state.rows && this.state.rows.map(rentCompany => (
                            <tbody align="center">
                                <tr>
                                    <td width={50}>{rentCompany.id}</td>
                                    <td width={300}>{rentCompany.name}</td>

                                    <Button component={Link} to={'/rentCompany/delete/' + rentCompany.id} variant="contained" color="primary">Delete</Button>
                                    <Button component={Link} to={'/rentCompany/update/' + rentCompany.id} variant="contained" color="primary">Update</Button>
                                </tr>
                            </tbody>
                        ))
                        }
                    </table>
                    <div>
                        <Button component={Link} to="/rentCompany/add" variant="contained" color="primary">Add RentCompany</Button>
                    </div>
                </div>
            </main>
        );
    }
}
export default withRouter(RentCompanies);

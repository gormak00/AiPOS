import React, {Component} from "react";
import { Button } from '@material-ui/core';
import axios from 'axios';
import {Link, Redirect, withRouter} from "react-router-dom";
import * as PropTypes from "prop-types";

Redirect.propTypes = {to: PropTypes.string};

class DeleteCar extends Component{

    constructor(props) {
        super(props);
        this.state = {
            status: ''
        };
    }

    componentDidMount() {
        console.log(this.props);
        axios.delete('http://localhost:8080/deleteBooking/' + this.props.match.params.id)
            .then((response) => {
                this.setState({status: response.data.status});
            })
            .catch((error) => {
                console.log(error);
                this.setState({message: error.message})
            });
    }

    render() {
            return (
                <Redirect to={'/cars'}/>
            );
    }
}
export default withRouter(DeleteCar);

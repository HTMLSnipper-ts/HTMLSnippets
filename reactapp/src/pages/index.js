<<<<<<< Updated upstream
import React from 'react'
=======
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Home = ({ setCodeSnippData }) => {
  const [payloads, setPayloads] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {

    setCodeSnippData(null);

    // Fetch the payload data from the server
    const fetchData = async () => {
      try {
        const response = await fetch('https://3j51dwtcd5.execute-api.us-east-1.amazonaws.com/Cors/api/snippets'); // Replace with your actual API endpoint
        if (response.ok) {
          const data = await response.json();
          setPayloads(data);
        } else {
          console.error('Failed to fetch data');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchData();
  }, [setCodeSnippData]);

  console.log(payloads)

  const handleClick = (payload) => {
    // Set the editor data and navigate to the code editor
    setCodeSnippData(payload);
    navigate('/code-editor');
  };
>>>>>>> Stashed changes

const Home = () => {
  return (
    <div style = {{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '90vh'}}>
        <h1> Home</h1>
    </div>
  )
}

export default Home
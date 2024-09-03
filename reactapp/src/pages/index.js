import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Home = ({ setCodeSnippData }) => {
  const [payloads, setPayloads] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    setCodeSnippData(null)

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
  }, []);

  const handleClick = (payload) => {
    // Set the editor data and navigate to the code editor
    setCodeSnippData(payload);
    navigate('/code-editor');
  };

  return (
    <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'center', alignItems: 'center', height: '90vh' }}>
      {payloads.length > 0 ? (
        payloads.map((payload, index) => (
          <div
            key={index}
            onClick={() => handleClick(payload)}
            style={{
              border: '1px solid #ccc',
              borderRadius: '8px',
              padding: '20px',
              margin: '10px',
              cursor: 'pointer',
              width: '200px',
              textAlign: 'center',
              backgroundColor: 'blue',
            }}
          >
            <h3>{payload.title}</h3>
            <p>{payload.description}</p>
          </div>
        ))
      ) : (
        <h1>No Saved Codes</h1>
      )}
    </div>
  );
};

export default Home;
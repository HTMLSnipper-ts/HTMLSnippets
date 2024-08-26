import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const SearchResults = () => {
  const { query } = useParams();
  const [snippets, setSnippets] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchSnippets = async () => {
      try {
        const response = await fetch(`/api/snippets/search/${query}`);
        if (response.ok) {
          const data = await response.json();
          setSnippets(data);
        } else if (response.status === 204) {
          setSnippets([]);
        }
      } catch (error) {
        console.error('Error fetching snippets:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchSnippets();
  }, [query]);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>Search Results for "{query}"</h1>
      {snippets.length > 0 ? (
        <ul>
          {snippets.map((snippet) => (
            <li key={snippet.snippetID}>
              <h2>{snippet.title}</h2>
              <p>{snippet.description}</p>
            </li>
          ))}
        </ul>
      ) : (
        <p>No snippets found for "{query}".</p>
      )}
    </div>
  );
};

export default SearchResults;

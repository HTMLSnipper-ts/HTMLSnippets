import React, { useState, useEffect } from 'react';
import { signOut } from 'aws-amplify/auth';
import { useAuthenticator } from '@aws-amplify/ui-react';
import { fetchUserAttributes } from '@aws-amplify/auth'; // Import fetchUserAttributes
import { Nav, NavLink, Bars, NavMenu, NavBtn, SearchInput, SearchForm } from './NavbarElements';
import { useNavigate } from 'react-router-dom';

async function handleSignOut() {
  try {
    await signOut();
    window.location.href = '/signin';
  } catch (error) {
    console.log('error signing out: ', error);
  }
}

const Navbar = ({ setEditorData }) => {
  const { user } = useAuthenticator((context) => [context.user]);
  const [searchTerm, setSearchTerm] = useState('');
  const [userEmail, setUserEmail] = useState(''); // State to store user's email
  const navigate = useNavigate();

  useEffect(() => {
    // Function to fetch user attributes
    const fetchEmail = async () => {
      try {
        const userAttributes = await fetchUserAttributes();
        setUserEmail(userAttributes.email); // Set the user's email
      } catch (error) {
        console.log('Error fetching user attributes:', error);
      }
    };

    if (user) {
      fetchEmail(); // Fetch email only if the user is logged in
    }
  }, [user]);

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };

  const handleSearchSubmit = (e) => {
    e.preventDefault();
    if (searchTerm.trim()) {
      navigate(`/search/${searchTerm}`);
    }
  };

  return (
    <Nav>
      <NavLink to='/'>
        Logo
      </NavLink>
      <Bars />
      <NavMenu>
        <NavLink to='/code-editor' activeStyle>
          HTML
        </NavLink>
        <NavLink to='/services'>
          CSS
        </NavLink>
        <NavLink to='/contact-us'>
          JS
        </NavLink>
        <NavLink to='/sign-up'>
          Sign Up
        </NavLink>
        {user && (
          <>
            <NavLink to='/favorites'>
              Favorites
            </NavLink>
            <span style={{ marginLeft: '20px', color: 'white' }}>
              Hello, {userEmail || user.username}!
            </span>
          </>
        )}
        <SearchForm onSubmit={handleSearchSubmit}>
          <SearchInput
            type='text'
            placeholder='Search...'
            value={searchTerm}
            onChange={handleSearchChange}
          />
        </SearchForm>
      </NavMenu>
      <NavBtn>
        <button onClick={handleSignOut}>Sign Out</button>
      </NavBtn>
    </Nav>
  );
};

export default Navbar;

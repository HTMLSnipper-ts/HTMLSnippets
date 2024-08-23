import React, { useState } from 'react';
import { signOut } from 'aws-amplify/auth';
import { useAuthenticator } from '@aws-amplify/ui-react';
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

const Navbar = () => {
  const { user } = useAuthenticator((context) => [context.user]);
  const [searchTerm, setSearchTerm] = useState('');
  const navigate = useNavigate();

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
              Hello, {user.username}!
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

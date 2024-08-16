import React from 'react';
<<<<<<< Updated upstream
import { signOut } from 'aws-amplify/auth';
import { useAuthenticator } from '@aws-amplify/ui-react';
import { Nav, NavLink, Bars, NavMenu, NavBtn } from './NavbarElements';


async function handleSignOut() {
  try {
    await signOut();
    window.location.href = '/signin';
  } catch (error) {
    console.log('error signing out: ', error);
  }
}
=======
import { useAuthenticator } from '@aws-amplify/ui-react';
import {
  Nav,
  NavLink,
  Bars,
  NavMenu,
  NavBtn,
  NavBtnLink
} from './NavbarElements';
>>>>>>> Stashed changes

const Navbar = () => {
  const { user } = useAuthenticator((context) => [context.user]);

  return (
    <Nav>
      <NavLink to='/'>
        Logo
      </NavLink>
      <Bars />
      <NavMenu>
        <NavLink to='/about'>
          HTML
        </NavLink>
<<<<<<< Updated upstream
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
            </span>          </>
        )}
      </NavMenu>
      <NavBtn>
        <button onClick={handleSignOut}>Sign Out</button>
      </NavBtn>
    </Nav>
=======
        <Bars />
        <NavMenu>
          <NavLink to='/code-editor' activeStyle>
            HTML
          </NavLink>
          <NavLink to='/services' activeStyle>
            CSS
          </NavLink>
          <NavLink to='/contact-us' activeStyle>
            JS
          </NavLink>
          <NavLink to='/sign-up' activeStyle>
            Sign Up
          </NavLink>
          {user && (
            <span style={{ marginLeft: '20px', color: 'white' }}>
              Hello, {user.username}!
            </span>
          )}
        </NavMenu>
        <NavBtn>
          <NavBtnLink to='/signin'>Sign In</NavBtnLink>
        </NavBtn>
      </Nav>
    </>
>>>>>>> Stashed changes
  );
};

export default Navbar;

import type { MenuData, MenuItem } from '../types'
import { cn } from '@/lib/utils'

function getNavList(
  authenticated: boolean,
  clubList: MenuItem[],
  username?: string,
): MenuData {
  const baseClass =
    'text-[1rem] text-gray-700 font-semibold hover:underline hover:text-blue-800 transition-colors duration-200 capitalize'

  return [
    {
      groupName: null,
      key : "group-1",
      items: [
        {
          key: 'join-book-club',
          name: 'Join a book club',
          url: '/join-a-book-club',
          visible: true,
          className: cn(baseClass,"text-pink-800"),
        },
      ],
    },
    {
      groupName: 'Book - Find a book',
      key : "group-2",
      items: [
        {
          key: 'search-books',
          name: 'Search Books',
          url: '/books',
          visible: true,
          className: cn(baseClass),
        },
        {
          key: 'top-books',
          name: 'Top Books',
          url: '/best-book-club-books/this-month',
          visible: true,
          className: cn(baseClass),
        },
        {
          key: 'indie-reads',
          name: 'Great Indie Reads',
          url: '/great-indie-books',
          visible: true,
          className: cn(baseClass),
        },
      ],
    },
    {
      groupName: null,
      key : "group-3",
      items: [
        {
          key: 'discussion-guides',
          name: 'Discussion Guides',
          url: '/discussion-guides',
          visible: true,
          className: cn(baseClass),
        },
      ],
    },
    {
      groupName: username ?? 'My Clubs',
      key : "group-4",
      items: [
        {
          key: 'view-clubs',
          name: 'View All Club',
          url: '/clubs',
          visible: authenticated,
          className: cn(baseClass),
        },
        ...clubList.map((club) => ({
          key: club.key,
          name: club.name,
          url: club.url,
          visible: club.visible,
          className: cn(baseClass),
        })),
        {
          key: 'create-club',
          name: 'Create New Club',
          url: '/create-club',
          visible: authenticated,
          className: cn(baseClass),
        },
        {
          key: 'find-club',
          name: 'Find a club to join',
          url: '/join-a-book-club',
          visible: authenticated,
          className: cn(baseClass),
        },
      ],
    },
    {
      groupName: null,
      key : "group-5",
      items: [
        {
          key: 'sign-up',
          name: 'Sign up',
          url: '/sign-up',
          visible: authenticated,
          className: cn(baseClass),
        },
        {
          key: 'sign-in',
          name: 'Sign In',
          url: '/sign-in',
          visible: !authenticated,
          className: cn(baseClass),
        },
      ],
    },
  ]
}

function getClubList(clubnames: string[], authenticated: boolean): MenuItem[] {
  return clubnames.map((clubname, index) => ({
    key: `club-${index}-${clubname}`,
    name: clubname,
    url: `/${clubname}/members`,
    visible: authenticated,
    className: 'text-orange-600 hover:underline',
  }))
}

export { getNavList, getClubList }

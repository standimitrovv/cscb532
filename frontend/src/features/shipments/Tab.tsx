export const Tab: React.FunctionComponent<{
  text: string;
  isActive: boolean;
  onClick: () => void;
}> = ({ text, isActive, onClick }) => {
  return (
    <li>
      <a
        className={`inline-block p-4 border-b-2 rounded-t-lg ${
          isActive
            ? ' text-blue-600  border-blue-600 active dark:text-blue-500 dark:border-blue-500'
            : 'border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300 cursor-pointer'
        } `}
        onClick={onClick}
      >
        {text}
      </a>
    </li>
  );
};

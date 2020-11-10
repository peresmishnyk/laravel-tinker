/*<?php/*with-and-without-opening poliglot*/

define('LARAVEL_START', microtime(true));

echo "%%START-OUTPUT%%";

require __DIR__ . '/vendor/autoload.php';
$app = require_once __DIR__ . '/bootstrap/app.php';
$kernel = $app->make(\Illuminate\Contracts\Console\Kernel::class);

$code = array_reduce(
    token_get_all($argv[1]),
    function ($carry, $token) {
        if (is_string($token)) {
            return $carry . $token;
        }

        [$id, $text] = $token;

        if (in_array($id, [T_COMMENT, T_DOC_COMMENT, T_OPEN_TAG, T_OPEN_TAG_WITH_ECHO, T_CLOSE_TAG], true)) {
            $text = '';
        }

        return $carry . $text;
    },
    ''
);

$output = new \Symfony\Component\Console\Output\ConsoleOutput();
$kernel->handle(new \Symfony\Component\Console\Input\ArrayInput([
    'command' => 'tinker',
    '--execute' => $code
]), $output);

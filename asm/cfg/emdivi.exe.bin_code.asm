0x0041eec0:	call 0x00427645
0x00427645:	movl %edi, %edi
0x00427647:	pushl %ebp
0x00427648:	movl %ebp, %esp
0x0042764a:	subl %esp, $0x10<UINT8>
0x0042764d:	movl %eax, 0x44609c
0x00427652:	andl -8(%ebp), $0x0<UINT8>
0x00427656:	andl -4(%ebp), $0x0<UINT8>
0x0042765a:	pushl %ebx
0x0042765b:	pushl %edi
0x0042765c:	movl %edi, $0xbb40e64e<UINT32>
0x00427661:	movl %ebx, $0xffff0000<UINT32>
0x00427666:	cmpl %eax, %edi
0x00427668:	je 0x00427677
0x00427677:	pushl %esi
0x00427678:	leal %eax, -8(%ebp)
0x0042767b:	pushl %eax
0x0042767c:	call GetSystemTimeAsFileTime@kernel32.dll
GetSystemTimeAsFileTime@kernel32.dll: API Node	
0x00427682:	movl %esi, -4(%ebp)
0x00427685:	xorl %esi, -8(%ebp)
0x00427688:	call GetCurrentProcessId@kernel32.dll
GetCurrentProcessId@kernel32.dll: API Node	
0x0042768e:	xorl %esi, %eax
0x00427690:	call GetCurrentThreadId@kernel32.dll
GetCurrentThreadId@kernel32.dll: API Node	
0x00427696:	xorl %esi, %eax
0x00427698:	call GetTickCount@kernel32.dll
GetTickCount@kernel32.dll: API Node	
0x0042769e:	xorl %esi, %eax
0x004276a0:	leal %eax, -16(%ebp)
0x004276a3:	pushl %eax
0x004276a4:	call QueryPerformanceCounter@kernel32.dll
QueryPerformanceCounter@kernel32.dll: API Node	
0x004276aa:	movl %eax, -12(%ebp)
0x004276ad:	xorl %eax, -16(%ebp)
0x004276b0:	xorl %esi, %eax
0x004276b2:	cmpl %esi, %edi
0x004276b4:	jne 0x004276bd
0x004276bd:	testl %ebx, %esi
0x004276bf:	jne 0x004276c8
0x004276c8:	movl 0x44609c, %esi
0x004276ce:	notl %esi
0x004276d0:	movl 0x4460a0, %esi
0x004276d6:	popl %esi
0x004276d7:	popl %edi
0x004276d8:	popl %ebx
0x004276d9:	leave
0x004276da:	ret

0x0041eec5:	jmp 0x0041ed6e
0x0041ed6e:	pushl $0x14<UINT8>
0x0041ed70:	pushl $0x441470<UINT32>
0x0041ed75:	call 0x0041fad0
0x0041fad0:	pushl $0x41eff0<UINT32>
0x0041fad5:	pushl %fs:0
0x0041fadc:	movl %eax, 0x10(%esp)
0x0041fae0:	movl 0x10(%esp), %ebp
0x0041fae4:	leal %ebp, 0x10(%esp)
0x0041fae8:	subl %esp, %eax
0x0041faea:	pushl %ebx
0x0041faeb:	pushl %esi
0x0041faec:	pushl %edi
0x0041faed:	movl %eax, 0x44609c
0x0041faf2:	xorl -4(%ebp), %eax
0x0041faf5:	xorl %eax, %ebp
0x0041faf7:	pushl %eax
0x0041faf8:	movl -24(%ebp), %esp
0x0041fafb:	pushl -8(%ebp)
0x0041fafe:	movl %eax, -4(%ebp)
0x0041fb01:	movl -4(%ebp), $0xfffffffe<UINT32>
0x0041fb08:	movl -8(%ebp), %eax
0x0041fb0b:	leal %eax, -16(%ebp)
0x0041fb0e:	movl %fs:0, %eax
0x0041fb14:	ret

0x0041ed7a:	movl %eax, $0x5a4d<UINT32>
0x0041ed7f:	cmpw 0x400000, %ax
0x0041ed86:	jne 56
0x0041ed88:	movl %eax, 0x40003c
0x0041ed8d:	cmpl 0x400000(%eax), $0x4550<UINT32>
0x0041ed97:	jne 39
0x0041ed99:	movl %ecx, $0x10b<UINT32>
0x0041ed9e:	cmpw 0x400018(%eax), %cx
0x0041eda5:	jne 25
0x0041eda7:	cmpl 0x400074(%eax), $0xe<UINT8>
0x0041edae:	jbe 16
0x0041edb0:	xorl %ecx, %ecx
0x0041edb2:	cmpl 0x4000e8(%eax), %ecx
0x0041edb8:	setne %cl
0x0041edbb:	movl -28(%ebp), %ecx
0x0041edbe:	jmp 0x0041edc4
0x0041edc4:	pushl $0x1<UINT8>
0x0041edc6:	call 0x0042715b
0x0042715b:	movl %edi, %edi
0x0042715d:	pushl %ebp
0x0042715e:	movl %ebp, %esp
0x00427160:	xorl %eax, %eax
0x00427162:	cmpl 0x8(%ebp), %eax
0x00427165:	pushl $0x0<UINT8>
0x00427167:	sete %al
0x0042716a:	pushl $0x1000<UINT32>
0x0042716f:	pushl %eax
0x00427170:	call HeapCreate@kernel32.dll
HeapCreate@kernel32.dll: API Node	
0x00427176:	movl 0x4486cc, %eax
0x0042717b:	testl %eax, %eax
0x0042717d:	jne 0x00427181
0x00427181:	xorl %eax, %eax
0x00427183:	incl %eax
0x00427184:	movl 0x449a4c, %eax
0x00427189:	popl %ebp
0x0042718a:	ret

0x0041edcb:	popl %ecx
0x0041edcc:	testl %eax, %eax
0x0041edce:	jne 0x0041edd8
0x0041edd8:	call 0x00423074
0x00423074:	movl %edi, %edi
0x00423076:	pushl %esi
0x00423077:	pushl %edi
0x00423078:	movl %esi, $0x43b754<UINT32>
0x0042307d:	pushl %esi
0x0042307e:	call GetModuleHandleW@kernel32.dll
GetModuleHandleW@kernel32.dll: API Node	
0x00423084:	testl %eax, %eax
0x00423086:	jne 0x0042308f
0x0042308f:	movl %edi, %eax
0x00423091:	testl %edi, %edi
0x00423093:	je 350
0x00423099:	movl %esi, 0x43b008
0x0042309f:	pushl $0x43b7a0<UINT32>
0x004230a4:	pushl %edi
0x004230a5:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x004230a7:	pushl $0x43b794<UINT32>
0x004230ac:	pushl %edi
0x004230ad:	movl 0x448104, %eax
0x004230b2:	call GetProcAddress@kernel32.dll
0x004230b4:	pushl $0x43b788<UINT32>
0x004230b9:	pushl %edi
0x004230ba:	movl 0x448108, %eax
0x004230bf:	call GetProcAddress@kernel32.dll
0x004230c1:	pushl $0x43b780<UINT32>
0x004230c6:	pushl %edi
0x004230c7:	movl 0x44810c, %eax
0x004230cc:	call GetProcAddress@kernel32.dll
0x004230ce:	cmpl 0x448104, $0x0<UINT8>
0x004230d5:	movl %esi, 0x43b11c
0x004230db:	movl 0x448110, %eax
0x004230e0:	je 22
0x004230e2:	cmpl 0x448108, $0x0<UINT8>
0x004230e9:	je 13
0x004230eb:	cmpl 0x44810c, $0x0<UINT8>
0x004230f2:	je 4
0x004230f4:	testl %eax, %eax
0x004230f6:	jne 0x0042311c
0x0042311c:	call TlsAlloc@kernel32.dll
TlsAlloc@kernel32.dll: API Node	
0x00423122:	movl 0x4465f4, %eax
0x00423127:	cmpl %eax, $0xffffffff<UINT8>
0x0042312a:	je 204
0x00423130:	pushl 0x448108
0x00423136:	pushl %eax
0x00423137:	call TlsSetValue@kernel32.dll
TlsSetValue@kernel32.dll: API Node	
0x00423139:	testl %eax, %eax
0x0042313b:	je 187
0x00423141:	call 0x0041e4ef
0x0041e4ef:	movl %edi, %edi
0x0041e4f1:	pushl %esi
0x0041e4f2:	call 0x00422c2b
0x00422c2b:	pushl $0x0<UINT8>
0x00422c2d:	call 0x00422bb9
0x00422bb9:	movl %edi, %edi
0x00422bbb:	pushl %ebp
0x00422bbc:	movl %ebp, %esp
0x00422bbe:	pushl %esi
0x00422bbf:	pushl 0x4465f4
0x00422bc5:	movl %esi, 0x43b114
0x00422bcb:	call TlsGetValue@kernel32.dll
TlsGetValue@kernel32.dll: API Node	
0x00422bcd:	testl %eax, %eax
0x00422bcf:	je 33
0x00422bd1:	movl %eax, 0x4465f0
0x00422bd6:	cmpl %eax, $0xffffffff<UINT8>
0x00422bd9:	je 0x00422bf2
0x00422bf2:	movl %esi, $0x43b754<UINT32>
0x00422bf7:	pushl %esi
0x00422bf8:	call GetModuleHandleW@kernel32.dll
0x00422bfe:	testl %eax, %eax
0x00422c00:	jne 0x00422c0d
0x00422c0d:	pushl $0x43b744<UINT32>
0x00422c12:	pushl %eax
0x00422c13:	call GetProcAddress@kernel32.dll
0x00422c19:	testl %eax, %eax
0x00422c1b:	je 8
0x00422c1d:	pushl 0x8(%ebp)
0x00422c20:	call EncodePointer@KERNEL32.DLL
EncodePointer@KERNEL32.DLL: API Node	
0x00422c22:	movl 0x8(%ebp), %eax
0x00422c25:	movl %eax, 0x8(%ebp)
0x00422c28:	popl %esi
0x00422c29:	popl %ebp
0x00422c2a:	ret

0x00422c32:	popl %ecx
0x00422c33:	ret

0x0041e4f7:	movl %esi, %eax
0x0041e4f9:	pushl %esi
0x0041e4fa:	call 0x0042543a
0x0042543a:	movl %edi, %edi
0x0042543c:	pushl %ebp
0x0042543d:	movl %ebp, %esp
0x0042543f:	movl %eax, 0x8(%ebp)
0x00425442:	movl 0x4485f0, %eax
0x00425447:	popl %ebp
0x00425448:	ret

0x0041e4ff:	pushl %esi
0x0041e500:	call 0x004253cb
0x004253cb:	movl %edi, %edi
0x004253cd:	pushl %ebp
0x004253ce:	movl %ebp, %esp
0x004253d0:	movl %eax, 0x8(%ebp)
0x004253d3:	movl 0x4485ec, %eax
0x004253d8:	popl %ebp
0x004253d9:	ret

0x0041e505:	pushl %esi
0x0041e506:	call 0x0041f8e8
0x0041f8e8:	movl %edi, %edi
0x0041f8ea:	pushl %ebp
0x0041f8eb:	movl %ebp, %esp
0x0041f8ed:	movl %eax, 0x8(%ebp)
0x0041f8f0:	movl 0x448100, %eax
0x0041f8f5:	popl %ebp
0x0041f8f6:	ret

0x0041e50b:	pushl %esi
0x0041e50c:	call 0x004253bc
0x004253bc:	movl %edi, %edi
0x004253be:	pushl %ebp
0x004253bf:	movl %ebp, %esp
0x004253c1:	movl %eax, 0x8(%ebp)
0x004253c4:	movl 0x4485e8, %eax
0x004253c9:	popl %ebp
0x004253ca:	ret

0x0041e511:	pushl %esi
0x0041e512:	call 0x004253ad
0x004253ad:	movl %edi, %edi
0x004253af:	pushl %ebp
0x004253b0:	movl %ebp, %esp
0x004253b2:	movl %eax, 0x8(%ebp)
0x004253b5:	movl 0x4485dc, %eax
0x004253ba:	popl %ebp
0x004253bb:	ret

0x0041e517:	pushl %esi
0x0041e518:	call 0x0042519b
0x0042519b:	movl %edi, %edi
0x0042519d:	pushl %ebp
0x0042519e:	movl %ebp, %esp
0x004251a0:	movl %eax, 0x8(%ebp)
0x004251a3:	movl 0x4485c8, %eax
0x004251a8:	movl 0x4485cc, %eax
0x004251ad:	movl 0x4485d0, %eax
0x004251b2:	movl 0x4485d4, %eax
0x004251b7:	popl %ebp
0x004251b8:	ret

0x0041e51d:	pushl %esi
0x0041e51e:	call 0x0042335d
0x0042335d:	ret

0x0041e523:	pushl %esi
0x0041e524:	call 0x00424737
0x00424737:	pushl $0x4246b3<UINT32>
0x0042473c:	call 0x00422bb9
0x00424741:	popl %ecx
0x00424742:	movl 0x44815c, %eax
0x00424747:	ret

0x0041e529:	pushl $0x41e4bb<UINT32>
0x0041e52e:	call 0x00422bb9
0x0041e533:	addl %esp, $0x24<UINT8>
0x0041e536:	movl 0x446030, %eax
0x0041e53b:	popl %esi
0x0041e53c:	ret

0x00423146:	pushl 0x448104
0x0042314c:	call 0x00422bb9
0x00423151:	pushl 0x448108
0x00423157:	movl 0x448104, %eax
0x0042315c:	call 0x00422bb9
0x00423161:	pushl 0x44810c
0x00423167:	movl 0x448108, %eax
0x0042316c:	call 0x00422bb9
0x00423171:	pushl 0x448110
0x00423177:	movl 0x44810c, %eax
0x0042317c:	call 0x00422bb9
0x00423181:	addl %esp, $0x10<UINT8>
0x00423184:	movl 0x448110, %eax
0x00423189:	call 0x00424f7f
0x00424f7f:	movl %edi, %edi
0x00424f81:	pushl %esi
0x00424f82:	pushl %edi
0x00424f83:	xorl %esi, %esi
0x00424f85:	movl %edi, $0x448478<UINT32>
0x00424f8a:	cmpl 0x446cdc(,%esi,8), $0x1<UINT8>
0x00424f92:	jne 0x00424fb2
0x00424f94:	leal %eax, 0x446cd8(,%esi,8)
0x00424f9b:	movl (%eax), %edi
0x00424f9d:	pushl $0xfa0<UINT32>
0x00424fa2:	pushl (%eax)
0x00424fa4:	addl %edi, $0x18<UINT8>
0x00424fa7:	call 0x004253da
0x004253da:	pushl $0x10<UINT8>
0x004253dc:	pushl $0x4416e8<UINT32>
0x004253e1:	call 0x0041fad0
0x004253e6:	andl -4(%ebp), $0x0<UINT8>
0x004253ea:	pushl 0xc(%ebp)
0x004253ed:	pushl 0x8(%ebp)
0x004253f0:	call InitializeCriticalSectionAndSpinCount@kernel32.dll
InitializeCriticalSectionAndSpinCount@kernel32.dll: API Node	
0x004253f6:	movl -28(%ebp), %eax
0x004253f9:	jmp 0x0042542a
0x0042542a:	movl -4(%ebp), $0xfffffffe<UINT32>
0x00425431:	movl %eax, -28(%ebp)
0x00425434:	call 0x0041fb15
0x0041fb15:	movl %ecx, -16(%ebp)
0x0041fb18:	movl %fs:0, %ecx
0x0041fb1f:	popl %ecx
0x0041fb20:	popl %edi
0x0041fb21:	popl %edi
0x0041fb22:	popl %esi
0x0041fb23:	popl %ebx
0x0041fb24:	movl %esp, %ebp
0x0041fb26:	popl %ebp
0x0041fb27:	pushl %ecx
0x0041fb28:	ret

0x00425439:	ret

0x00424fac:	popl %ecx
0x00424fad:	popl %ecx
0x00424fae:	testl %eax, %eax
0x00424fb0:	je 12
0x00424fb2:	incl %esi
0x00424fb3:	cmpl %esi, $0x24<UINT8>
0x00424fb6:	jl 0x00424f8a
0x00424fb8:	xorl %eax, %eax
0x00424fba:	incl %eax
0x00424fbb:	popl %edi
0x00424fbc:	popl %esi
0x00424fbd:	ret

0x0042318e:	testl %eax, %eax
0x00423190:	je 101
0x00423192:	pushl $0x422ed7<UINT32>
0x00423197:	pushl 0x448104
0x0042319d:	call 0x00422c34
0x00422c34:	movl %edi, %edi
0x00422c36:	pushl %ebp
0x00422c37:	movl %ebp, %esp
0x00422c39:	pushl %esi
0x00422c3a:	pushl 0x4465f4
0x00422c40:	movl %esi, 0x43b114
0x00422c46:	call TlsGetValue@kernel32.dll
0x00422c48:	testl %eax, %eax
0x00422c4a:	je 33
0x00422c4c:	movl %eax, 0x4465f0
0x00422c51:	cmpl %eax, $0xffffffff<UINT8>
0x00422c54:	je 0x00422c6d
0x00422c6d:	movl %esi, $0x43b754<UINT32>
0x00422c72:	pushl %esi
0x00422c73:	call GetModuleHandleW@kernel32.dll
0x00422c79:	testl %eax, %eax
0x00422c7b:	jne 0x00422c88
0x00422c88:	pushl $0x43b770<UINT32>
0x00422c8d:	pushl %eax
0x00422c8e:	call GetProcAddress@kernel32.dll
0x00422c94:	testl %eax, %eax
0x00422c96:	je 8
0x00422c98:	pushl 0x8(%ebp)
0x00422c9b:	call DecodePointer@KERNEL32.DLL
DecodePointer@KERNEL32.DLL: API Node	
0x00422c9d:	movl 0x8(%ebp), %eax
0x00422ca0:	movl %eax, 0x8(%ebp)
0x00422ca3:	popl %esi
0x00422ca4:	popl %ebp
0x00422ca5:	ret

0x004231a2:	popl %ecx
0x004231a3:	call FlsAlloc@KERNEL32.DLL
FlsAlloc@KERNEL32.DLL: API Node	
0x004231a5:	movl 0x4465f0, %eax
0x004231aa:	cmpl %eax, $0xffffffff<UINT8>
0x004231ad:	je 72
0x004231af:	pushl $0x214<UINT32>
0x004231b4:	pushl $0x1<UINT8>
0x004231b6:	call 0x00423503
0x00423503:	movl %edi, %edi
0x00423505:	pushl %ebp
0x00423506:	movl %ebp, %esp
0x00423508:	pushl %esi
0x00423509:	pushl %edi
0x0042350a:	xorl %esi, %esi
0x0042350c:	pushl $0x0<UINT8>
0x0042350e:	pushl 0xc(%ebp)
0x00423511:	pushl 0x8(%ebp)
0x00423514:	call 0x0042ac64
0x0042ac64:	pushl $0xc<UINT8>
0x0042ac66:	pushl $0x441850<UINT32>
0x0042ac6b:	call 0x0041fad0
0x0042ac70:	movl %ecx, 0x8(%ebp)
0x0042ac73:	xorl %edi, %edi
0x0042ac75:	cmpl %ecx, %edi
0x0042ac77:	jbe 46
0x0042ac79:	pushl $0xffffffe0<UINT8>
0x0042ac7b:	popl %eax
0x0042ac7c:	xorl %edx, %edx
0x0042ac7e:	divl %eax, %ecx
0x0042ac80:	cmpl %eax, 0xc(%ebp)
0x0042ac83:	sbbl %eax, %eax
0x0042ac85:	incl %eax
0x0042ac86:	jne 0x0042aca7
0x0042aca7:	imull %ecx, 0xc(%ebp)
0x0042acab:	movl %esi, %ecx
0x0042acad:	movl 0x8(%ebp), %esi
0x0042acb0:	cmpl %esi, %edi
0x0042acb2:	jne 0x0042acb7
0x0042acb7:	xorl %ebx, %ebx
0x0042acb9:	movl -28(%ebp), %ebx
0x0042acbc:	cmpl %esi, $0xffffffe0<UINT8>
0x0042acbf:	ja 105
0x0042acc1:	cmpl 0x449a4c, $0x3<UINT8>
0x0042acc8:	jne 0x0042ad15
0x0042ad15:	cmpl %ebx, %edi
0x0042ad17:	jne 97
0x0042ad19:	pushl %esi
0x0042ad1a:	pushl $0x8<UINT8>
0x0042ad1c:	pushl 0x4486cc
0x0042ad22:	call HeapAlloc@kernel32.dll
HeapAlloc@kernel32.dll: API Node	
0x0042ad28:	movl %ebx, %eax
0x0042ad2a:	cmpl %ebx, %edi
0x0042ad2c:	jne 0x0042ad7a
0x0042ad7a:	movl %eax, %ebx
0x0042ad7c:	call 0x0041fb15
0x0042ad81:	ret

0x00423519:	movl %edi, %eax
0x0042351b:	addl %esp, $0xc<UINT8>
0x0042351e:	testl %edi, %edi
0x00423520:	jne 0x00423549
0x00423549:	movl %eax, %edi
0x0042354b:	popl %edi
0x0042354c:	popl %esi
0x0042354d:	popl %ebp
0x0042354e:	ret

0x004231bb:	movl %esi, %eax
0x004231bd:	popl %ecx
0x004231be:	popl %ecx
0x004231bf:	testl %esi, %esi
0x004231c1:	je 52
0x004231c3:	pushl %esi
0x004231c4:	pushl 0x4465f0
0x004231ca:	pushl 0x44810c
0x004231d0:	call 0x00422c34
0x00422c56:	pushl %eax
0x00422c57:	pushl 0x4465f4
0x00422c5d:	call TlsGetValue@kernel32.dll
0x00422c5f:	call FlsGetValue@KERNEL32.DLL
FlsGetValue@KERNEL32.DLL: API Node	
0x00422c61:	testl %eax, %eax
0x00422c63:	je 0x00422c6d
0x004231d5:	popl %ecx
0x004231d6:	call FlsSetValue@KERNEL32.DLL
FlsSetValue@KERNEL32.DLL: API Node	
0x004231d8:	testl %eax, %eax
0x004231da:	je 27
0x004231dc:	pushl $0x0<UINT8>
0x004231de:	pushl %esi
0x004231df:	call 0x00422d5d
0x00422d5d:	pushl $0xc<UINT8>
0x00422d5f:	pushl $0x441558<UINT32>
0x00422d64:	call 0x0041fad0
0x00422d69:	movl %esi, $0x43b754<UINT32>
0x00422d6e:	pushl %esi
0x00422d6f:	call GetModuleHandleW@kernel32.dll
0x00422d75:	testl %eax, %eax
0x00422d77:	jne 0x00422d80
0x00422d80:	movl -28(%ebp), %eax
0x00422d83:	movl %esi, 0x8(%ebp)
0x00422d86:	movl 0x5c(%esi), $0x43b7b0<UINT32>
0x00422d8d:	xorl %edi, %edi
0x00422d8f:	incl %edi
0x00422d90:	movl 0x14(%esi), %edi
0x00422d93:	testl %eax, %eax
0x00422d95:	je 36
0x00422d97:	pushl $0x43b744<UINT32>
0x00422d9c:	pushl %eax
0x00422d9d:	movl %ebx, 0x43b008
0x00422da3:	call GetProcAddress@kernel32.dll
0x00422da5:	movl 0x1f8(%esi), %eax
0x00422dab:	pushl $0x43b770<UINT32>
0x00422db0:	pushl -28(%ebp)
0x00422db3:	call GetProcAddress@kernel32.dll
0x00422db5:	movl 0x1fc(%esi), %eax
0x00422dbb:	movl 0x70(%esi), %edi
0x00422dbe:	movb 0xc8(%esi), $0x43<UINT8>
0x00422dc5:	movb 0x14b(%esi), $0x43<UINT8>
0x00422dcc:	movl 0x68(%esi), $0x446608<UINT32>
0x00422dd3:	pushl $0xd<UINT8>
0x00422dd5:	call 0x004250fb
0x004250fb:	movl %edi, %edi
0x004250fd:	pushl %ebp
0x004250fe:	movl %ebp, %esp
0x00425100:	movl %eax, 0x8(%ebp)
0x00425103:	pushl %esi
0x00425104:	leal %esi, 0x446cd8(,%eax,8)
0x0042510b:	cmpl (%esi), $0x0<UINT8>
0x0042510e:	jne 0x00425123
0x00425123:	pushl (%esi)
0x00425125:	call EnterCriticalSection@kernel32.dll
EnterCriticalSection@kernel32.dll: API Node	
0x0042512b:	popl %esi
0x0042512c:	popl %ebp
0x0042512d:	ret

0x00422dda:	popl %ecx
0x00422ddb:	andl -4(%ebp), $0x0<UINT8>
0x00422ddf:	pushl 0x68(%esi)
0x00422de2:	call InterlockedIncrement@kernel32.dll
InterlockedIncrement@kernel32.dll: API Node	
0x00422de8:	movl -4(%ebp), $0xfffffffe<UINT32>
0x00422def:	call 0x00422e32
0x00422e32:	pushl $0xd<UINT8>
0x00422e34:	call 0x00425021
0x00425021:	movl %edi, %edi
0x00425023:	pushl %ebp
0x00425024:	movl %ebp, %esp
0x00425026:	movl %eax, 0x8(%ebp)
0x00425029:	pushl 0x446cd8(,%eax,8)
0x00425030:	call LeaveCriticalSection@kernel32.dll
LeaveCriticalSection@kernel32.dll: API Node	
0x00425036:	popl %ebp
0x00425037:	ret

0x00422e39:	popl %ecx
0x00422e3a:	ret

0x00422df4:	pushl $0xc<UINT8>
0x00422df6:	call 0x004250fb
0x00422dfb:	popl %ecx
0x00422dfc:	movl -4(%ebp), %edi
0x00422dff:	movl %eax, 0xc(%ebp)
0x00422e02:	movl 0x6c(%esi), %eax
0x00422e05:	testl %eax, %eax
0x00422e07:	jne 8
0x00422e09:	movl %eax, 0x446c10
0x00422e0e:	movl 0x6c(%esi), %eax
0x00422e11:	pushl 0x6c(%esi)
0x00422e14:	call 0x00423e1b
0x00423e1b:	movl %edi, %edi
0x00423e1d:	pushl %ebp
0x00423e1e:	movl %ebp, %esp
0x00423e20:	pushl %ebx
0x00423e21:	pushl %esi
0x00423e22:	movl %esi, 0x43b124
0x00423e28:	pushl %edi
0x00423e29:	movl %edi, 0x8(%ebp)
0x00423e2c:	pushl %edi
0x00423e2d:	call InterlockedIncrement@kernel32.dll
0x00423e2f:	movl %eax, 0xb0(%edi)
0x00423e35:	testl %eax, %eax
0x00423e37:	je 0x00423e3c
0x00423e3c:	movl %eax, 0xb8(%edi)
0x00423e42:	testl %eax, %eax
0x00423e44:	je 0x00423e49
0x00423e49:	movl %eax, 0xb4(%edi)
0x00423e4f:	testl %eax, %eax
0x00423e51:	je 0x00423e56
0x00423e56:	movl %eax, 0xc0(%edi)
0x00423e5c:	testl %eax, %eax
0x00423e5e:	je 0x00423e63
0x00423e63:	leal %ebx, 0x50(%edi)
0x00423e66:	movl 0x8(%ebp), $0x6<UINT32>
0x00423e6d:	cmpl -8(%ebx), $0x446b30<UINT32>
0x00423e74:	je 0x00423e7f
0x00423e76:	movl %eax, (%ebx)
0x00423e78:	testl %eax, %eax
0x00423e7a:	je 0x00423e7f
0x00423e7f:	cmpl -4(%ebx), $0x0<UINT8>
0x00423e83:	je 0x00423e8f
0x00423e8f:	addl %ebx, $0x10<UINT8>
0x00423e92:	decl 0x8(%ebp)
0x00423e95:	jne 0x00423e6d
0x00423e97:	movl %eax, 0xd4(%edi)
0x00423e9d:	addl %eax, $0xb4<UINT32>
0x00423ea2:	pushl %eax
0x00423ea3:	call InterlockedIncrement@kernel32.dll
0x00423ea5:	popl %edi
0x00423ea6:	popl %esi
0x00423ea7:	popl %ebx
0x00423ea8:	popl %ebp
0x00423ea9:	ret

0x00422e19:	popl %ecx
0x00422e1a:	movl -4(%ebp), $0xfffffffe<UINT32>
0x00422e21:	call 0x00422e3b
0x00422e3b:	pushl $0xc<UINT8>
0x00422e3d:	call 0x00425021
0x00422e42:	popl %ecx
0x00422e43:	ret

0x00422e26:	call 0x0041fb15
0x00422e2b:	ret

0x004231e4:	popl %ecx
0x004231e5:	popl %ecx
0x004231e6:	call GetCurrentThreadId@kernel32.dll
0x004231ec:	orl 0x4(%esi), $0xffffffff<UINT8>
0x004231f0:	movl (%esi), %eax
0x004231f2:	xorl %eax, %eax
0x004231f4:	incl %eax
0x004231f5:	jmp 0x004231fe
0x004231fe:	popl %edi
0x004231ff:	popl %esi
0x00423200:	ret

0x0041eddd:	testl %eax, %eax
0x0041eddf:	jne 0x0041ede9
0x0041ede9:	call 0x0042512e
0x0042512e:	movl %edi, %edi
0x00425130:	pushl %esi
0x00425131:	movl %eax, $0x44127c<UINT32>
0x00425136:	movl %esi, $0x44127c<UINT32>
0x0042513b:	pushl %edi
0x0042513c:	movl %edi, %eax
0x0042513e:	cmpl %eax, %esi
0x00425140:	jae 0x00425151
0x00425151:	popl %edi
0x00425152:	popl %esi
0x00425153:	ret

0x0041edee:	andl -4(%ebp), $0x0<UINT8>
0x0041edf2:	call 0x0041feb9
0x0041feb9:	pushl $0x54<UINT8>
0x0041febb:	pushl $0x4414d8<UINT32>
0x0041fec0:	call 0x0041fad0
0x0041fec5:	xorl %edi, %edi
0x0041fec7:	movl -4(%ebp), %edi
0x0041feca:	leal %eax, -100(%ebp)
0x0041fecd:	pushl %eax
0x0041fece:	call GetStartupInfoA@kernel32.dll
GetStartupInfoA@kernel32.dll: API Node	
0x0041fed4:	movl -4(%ebp), $0xfffffffe<UINT32>
0x0041fedb:	pushl $0x40<UINT8>
0x0041fedd:	pushl $0x20<UINT8>
0x0041fedf:	popl %esi
0x0041fee0:	pushl %esi
0x0041fee1:	call 0x00423503
0x0041fee6:	popl %ecx
0x0041fee7:	popl %ecx
0x0041fee8:	cmpl %eax, %edi
0x0041feea:	je 532
0x0041fef0:	movl 0x449a80, %eax
0x0041fef5:	movl 0x449a6c, %esi
0x0041fefb:	leal %ecx, 0x800(%eax)
0x0041ff01:	jmp 0x0041ff33
0x0041ff33:	cmpl %eax, %ecx
0x0041ff35:	jb 0x0041ff03
0x0041ff03:	movb 0x4(%eax), $0x0<UINT8>
0x0041ff07:	orl (%eax), $0xffffffff<UINT8>
0x0041ff0a:	movb 0x5(%eax), $0xa<UINT8>
0x0041ff0e:	movl 0x8(%eax), %edi
0x0041ff11:	movb 0x24(%eax), $0x0<UINT8>
0x0041ff15:	movb 0x25(%eax), $0xa<UINT8>
0x0041ff19:	movb 0x26(%eax), $0xa<UINT8>
0x0041ff1d:	movl 0x38(%eax), %edi
0x0041ff20:	movb 0x34(%eax), $0x0<UINT8>
0x0041ff24:	addl %eax, $0x40<UINT8>
0x0041ff27:	movl %ecx, 0x449a80
0x0041ff2d:	addl %ecx, $0x800<UINT32>
0x0041ff37:	cmpw -50(%ebp), %di
0x0041ff3b:	je 0x0042004b
0x0042004b:	xorl %ebx, %ebx
0x0042004d:	movl %esi, %ebx
0x0042004f:	shll %esi, $0x6<UINT8>
0x00420052:	addl %esi, 0x449a80
0x00420058:	movl %eax, (%esi)
0x0042005a:	cmpl %eax, $0xffffffff<UINT8>
0x0042005d:	je 0x0042006a
0x0042006a:	movb 0x4(%esi), $0xffffff81<UINT8>
0x0042006e:	testl %ebx, %ebx
0x00420070:	jne 0x00420077
0x00420072:	pushl $0xfffffff6<UINT8>
0x00420074:	popl %eax
0x00420075:	jmp 0x00420081
0x00420081:	pushl %eax
0x00420082:	call GetStdHandle@kernel32.dll
GetStdHandle@kernel32.dll: API Node	
0x00420088:	movl %edi, %eax
0x0042008a:	cmpl %edi, $0xffffffff<UINT8>
0x0042008d:	je 67
0x0042008f:	testl %edi, %edi
0x00420091:	je 63
0x00420093:	pushl %edi
0x00420094:	call GetFileType@kernel32.dll
GetFileType@kernel32.dll: API Node	
0x0042009a:	testl %eax, %eax
0x0042009c:	je 52
0x0042009e:	movl (%esi), %edi
0x004200a0:	andl %eax, $0xff<UINT32>
0x004200a5:	cmpl %eax, $0x2<UINT8>
0x004200a8:	jne 6
0x004200aa:	orb 0x4(%esi), $0x40<UINT8>
0x004200ae:	jmp 0x004200b9
0x004200b9:	pushl $0xfa0<UINT32>
0x004200be:	leal %eax, 0xc(%esi)
0x004200c1:	pushl %eax
0x004200c2:	call 0x004253da
0x004200c7:	popl %ecx
0x004200c8:	popl %ecx
0x004200c9:	testl %eax, %eax
0x004200cb:	je 55
0x004200cd:	incl 0x8(%esi)
0x004200d0:	jmp 0x004200dc
0x004200dc:	incl %ebx
0x004200dd:	cmpl %ebx, $0x3<UINT8>
0x004200e0:	jl 0x0042004d
0x00420077:	movl %eax, %ebx
0x00420079:	decl %eax
0x0042007a:	negl %eax
0x0042007c:	sbbl %eax, %eax
0x0042007e:	addl %eax, $0xfffffff5<UINT8>
0x004200e6:	pushl 0x449a6c
0x004200ec:	call SetHandleCount@kernel32.dll
SetHandleCount@kernel32.dll: API Node	
0x004200f2:	xorl %eax, %eax
0x004200f4:	jmp 0x00420107
0x00420107:	call 0x0041fb15
0x0042010c:	ret

0x0041edf7:	testl %eax, %eax
0x0041edf9:	jnl 0x0041ee03
0x0041ee03:	call GetCommandLineA@kernel32.dll
GetCommandLineA@kernel32.dll: API Node	
0x0041ee09:	movl 0x44aba4, %eax
0x0041ee0e:	call 0x0042750e
0x0042750e:	movl %edi, %edi
0x00427510:	pushl %ebp
0x00427511:	movl %ebp, %esp
0x00427513:	movl %eax, 0x4487e0
0x00427518:	subl %esp, $0xc<UINT8>
0x0042751b:	pushl %ebx
0x0042751c:	pushl %esi
0x0042751d:	movl %esi, 0x43b16c
0x00427523:	pushl %edi
0x00427524:	xorl %ebx, %ebx
0x00427526:	xorl %edi, %edi
0x00427528:	cmpl %eax, %ebx
0x0042752a:	jne 46
0x0042752c:	call GetEnvironmentStringsW@kernel32.dll
GetEnvironmentStringsW@kernel32.dll: API Node	
0x0042752e:	movl %edi, %eax
0x00427530:	cmpl %edi, %ebx
0x00427532:	je 12
0x00427534:	movl 0x4487e0, $0x1<UINT32>
0x0042753e:	jmp 0x00427563
0x00427563:	cmpl %edi, %ebx
0x00427565:	jne 0x00427576
0x00427576:	movl %eax, %edi
0x00427578:	cmpw (%edi), %bx
0x0042757b:	je 14
0x0042757d:	incl %eax
0x0042757e:	incl %eax
0x0042757f:	cmpw (%eax), %bx
0x00427582:	jne 0x0042757d
0x00427584:	incl %eax
0x00427585:	incl %eax
0x00427586:	cmpw (%eax), %bx
0x00427589:	jne 0x0042757d
0x0042758b:	movl %esi, 0x43b088
0x00427591:	pushl %ebx
0x00427592:	pushl %ebx
0x00427593:	pushl %ebx
0x00427594:	subl %eax, %edi
0x00427596:	pushl %ebx
0x00427597:	sarl %eax
0x00427599:	incl %eax
0x0042759a:	pushl %eax
0x0042759b:	pushl %edi
0x0042759c:	pushl %ebx
0x0042759d:	pushl %ebx
0x0042759e:	movl -12(%ebp), %eax
0x004275a1:	call WideCharToMultiByte@kernel32.dll
WideCharToMultiByte@kernel32.dll: API Node	
0x004275a3:	movl -8(%ebp), %eax
0x004275a6:	cmpl %eax, %ebx
0x004275a8:	je 47
0x004275aa:	pushl %eax
0x004275ab:	call 0x004234be
0x004234be:	movl %edi, %edi
0x004234c0:	pushl %ebp
0x004234c1:	movl %ebp, %esp
0x004234c3:	pushl %esi
0x004234c4:	pushl %edi
0x004234c5:	xorl %esi, %esi
0x004234c7:	pushl 0x8(%ebp)
0x004234ca:	call 0x0041ebed
0x0041ebed:	movl %edi, %edi
0x0041ebef:	pushl %ebp
0x0041ebf0:	movl %ebp, %esp
0x0041ebf2:	pushl %esi
0x0041ebf3:	movl %esi, 0x8(%ebp)
0x0041ebf6:	cmpl %esi, $0xffffffe0<UINT8>
0x0041ebf9:	ja 161
0x0041ebff:	pushl %ebx
0x0041ec00:	pushl %edi
0x0041ec01:	movl %edi, 0x43b0d0
0x0041ec07:	cmpl 0x4486cc, $0x0<UINT8>
0x0041ec0e:	jne 0x0041ec28
0x0041ec28:	movl %eax, 0x449a4c
0x0041ec2d:	cmpl %eax, $0x1<UINT8>
0x0041ec30:	jne 14
0x0041ec32:	testl %esi, %esi
0x0041ec34:	je 4
0x0041ec36:	movl %eax, %esi
0x0041ec38:	jmp 0x0041ec3d
0x0041ec3d:	pushl %eax
0x0041ec3e:	jmp 0x0041ec5c
0x0041ec5c:	pushl $0x0<UINT8>
0x0041ec5e:	pushl 0x4486cc
0x0041ec64:	call HeapAlloc@kernel32.dll
0x0041ec66:	movl %ebx, %eax
0x0041ec68:	testl %ebx, %ebx
0x0041ec6a:	jne 0x0041ec9a
0x0041ec9a:	popl %edi
0x0041ec9b:	movl %eax, %ebx
0x0041ec9d:	popl %ebx
0x0041ec9e:	jmp 0x0041ecb4
0x0041ecb4:	popl %esi
0x0041ecb5:	popl %ebp
0x0041ecb6:	ret

0x004234cf:	movl %edi, %eax
0x004234d1:	popl %ecx
0x004234d2:	testl %edi, %edi
0x004234d4:	jne 0x004234fd
0x004234fd:	movl %eax, %edi
0x004234ff:	popl %edi
0x00423500:	popl %esi
0x00423501:	popl %ebp
0x00423502:	ret

0x004275b0:	popl %ecx
0x004275b1:	movl -4(%ebp), %eax
0x004275b4:	cmpl %eax, %ebx
0x004275b6:	je 33
0x004275b8:	pushl %ebx
0x004275b9:	pushl %ebx
0x004275ba:	pushl -8(%ebp)
0x004275bd:	pushl %eax
0x004275be:	pushl -12(%ebp)
0x004275c1:	pushl %edi
0x004275c2:	pushl %ebx
0x004275c3:	pushl %ebx
0x004275c4:	call WideCharToMultiByte@kernel32.dll
0x004275c6:	testl %eax, %eax
0x004275c8:	jne 0x004275d6
0x004275d6:	movl %ebx, -4(%ebp)
0x004275d9:	pushl %edi
0x004275da:	call FreeEnvironmentStringsW@kernel32.dll
FreeEnvironmentStringsW@kernel32.dll: API Node	
0x004275e0:	movl %eax, %ebx
0x004275e2:	jmp 0x00427640
0x00427640:	popl %edi
0x00427641:	popl %esi
0x00427642:	popl %ebx
0x00427643:	leave
0x00427644:	ret

0x0041ee13:	movl 0x447dc4, %eax
0x0041ee18:	call 0x00427453
0x00427453:	movl %edi, %edi
0x00427455:	pushl %ebp
0x00427456:	movl %ebp, %esp
0x00427458:	subl %esp, $0xc<UINT8>
0x0042745b:	pushl %ebx
0x0042745c:	xorl %ebx, %ebx
0x0042745e:	pushl %esi
0x0042745f:	pushl %edi
0x00427460:	cmpl 0x44abb4, %ebx
0x00427466:	jne 5
0x00427468:	call 0x00423cb4
0x00423cb4:	cmpl 0x44abb4, $0x0<UINT8>
0x00423cbb:	jne 0x00423ccf
0x00423cbd:	pushl $0xfffffffd<UINT8>
0x00423cbf:	call 0x00423b1a
0x00423b1a:	pushl $0x14<UINT8>
0x00423b1c:	pushl $0x4415e8<UINT32>
0x00423b21:	call 0x0041fad0
0x00423b26:	orl -32(%ebp), $0xffffffff<UINT8>
0x00423b2a:	call 0x00422ebd
0x00422ebd:	movl %edi, %edi
0x00422ebf:	pushl %esi
0x00422ec0:	call 0x00422e44
0x00422e44:	movl %edi, %edi
0x00422e46:	pushl %esi
0x00422e47:	pushl %edi
0x00422e48:	call GetLastError@kernel32.dll
GetLastError@kernel32.dll: API Node	
0x00422e4e:	pushl 0x4465f0
0x00422e54:	movl %edi, %eax
0x00422e56:	call 0x00422ccf
0x00422ccf:	movl %edi, %edi
0x00422cd1:	pushl %esi
0x00422cd2:	pushl 0x4465f4
0x00422cd8:	call TlsGetValue@kernel32.dll
0x00422cde:	movl %esi, %eax
0x00422ce0:	testl %esi, %esi
0x00422ce2:	jne 0x00422cff
0x00422cff:	movl %eax, %esi
0x00422d01:	popl %esi
0x00422d02:	ret

0x00422e5b:	call FlsGetValue@KERNEL32.DLL
0x00422e5d:	movl %esi, %eax
0x00422e5f:	testl %esi, %esi
0x00422e61:	jne 0x00422eb1
0x00422eb1:	pushl %edi
0x00422eb2:	call SetLastError@kernel32.dll
SetLastError@kernel32.dll: API Node	
0x00422eb8:	popl %edi
0x00422eb9:	movl %eax, %esi
0x00422ebb:	popl %esi
0x00422ebc:	ret

0x00422ec5:	movl %esi, %eax
0x00422ec7:	testl %esi, %esi
0x00422ec9:	jne 0x00422ed3
0x00422ed3:	movl %eax, %esi
0x00422ed5:	popl %esi
0x00422ed6:	ret

0x00423b2f:	movl %edi, %eax
0x00423b31:	movl -36(%ebp), %edi
0x00423b34:	call 0x00423815
0x00423815:	pushl $0xc<UINT8>
0x00423817:	pushl $0x4415c8<UINT32>
0x0042381c:	call 0x0041fad0
0x00423821:	call 0x00422ebd
0x00423826:	movl %edi, %eax
0x00423828:	movl %eax, 0x446b2c
0x0042382d:	testl 0x70(%edi), %eax
0x00423830:	je 0x0042384f
0x0042384f:	pushl $0xd<UINT8>
0x00423851:	call 0x004250fb
0x00423856:	popl %ecx
0x00423857:	andl -4(%ebp), $0x0<UINT8>
0x0042385b:	movl %esi, 0x68(%edi)
0x0042385e:	movl -28(%ebp), %esi
0x00423861:	cmpl %esi, 0x446a30
0x00423867:	je 0x0042389f
0x0042389f:	movl -4(%ebp), $0xfffffffe<UINT32>
0x004238a6:	call 0x004238b0
0x004238b0:	pushl $0xd<UINT8>
0x004238b2:	call 0x00425021
0x004238b7:	popl %ecx
0x004238b8:	ret

0x004238ab:	jmp 0x0042383b
0x0042383b:	testl %esi, %esi
0x0042383d:	jne 0x00423847
0x00423847:	movl %eax, %esi
0x00423849:	call 0x0041fb15
0x0042384e:	ret

0x00423b39:	movl %ebx, 0x68(%edi)
0x00423b3c:	movl %esi, 0x8(%ebp)
0x00423b3f:	call 0x004238b9
0x004238b9:	movl %edi, %edi
0x004238bb:	pushl %ebp
0x004238bc:	movl %ebp, %esp
0x004238be:	subl %esp, $0x10<UINT8>
0x004238c1:	pushl %ebx
0x004238c2:	xorl %ebx, %ebx
0x004238c4:	pushl %ebx
0x004238c5:	leal %ecx, -16(%ebp)
0x004238c8:	call 0x0041d900
0x0041d900:	movl %edi, %edi
0x0041d902:	pushl %ebp
0x0041d903:	movl %ebp, %esp
0x0041d905:	movl %eax, 0x8(%ebp)
0x0041d908:	pushl %esi
0x0041d909:	movl %esi, %ecx
0x0041d90b:	movb 0xc(%esi), $0x0<UINT8>
0x0041d90f:	testl %eax, %eax
0x0041d911:	jne 0x0041d976
0x0041d913:	call 0x00422ebd
0x0041d918:	movl 0x8(%esi), %eax
0x0041d91b:	movl %ecx, 0x6c(%eax)
0x0041d91e:	movl (%esi), %ecx
0x0041d920:	movl %ecx, 0x68(%eax)
0x0041d923:	movl 0x4(%esi), %ecx
0x0041d926:	movl %ecx, (%esi)
0x0041d928:	cmpl %ecx, 0x446c10
0x0041d92e:	je 0x0041d942
0x0041d942:	movl %eax, 0x4(%esi)
0x0041d945:	cmpl %eax, 0x446a30
0x0041d94b:	je 0x0041d963
0x0041d963:	movl %eax, 0x8(%esi)
0x0041d966:	testb 0x70(%eax), $0x2<UINT8>
0x0041d96a:	jne 20
0x0041d96c:	orl 0x70(%eax), $0x2<UINT8>
0x0041d970:	movb 0xc(%esi), $0x1<UINT8>
0x0041d974:	jmp 0x0041d980
0x0041d980:	movl %eax, %esi
0x0041d982:	popl %esi
0x0041d983:	popl %ebp
0x0041d984:	ret $0x4<UINT16>

0x004238cd:	movl 0x448118, %ebx
0x004238d3:	cmpl %esi, $0xfffffffe<UINT8>
0x004238d6:	jne 0x004238f6
0x004238f6:	cmpl %esi, $0xfffffffd<UINT8>
0x004238f9:	jne 0x0042390d
0x004238fb:	movl 0x448118, $0x1<UINT32>
0x00423905:	call GetACP@kernel32.dll
GetACP@kernel32.dll: API Node	
0x0042390b:	jmp 0x004238e8
0x004238e8:	cmpb -4(%ebp), %bl
0x004238eb:	je 69
0x004238ed:	movl %ecx, -8(%ebp)
0x004238f0:	andl 0x70(%ecx), $0xfffffffd<UINT8>
0x004238f4:	jmp 0x00423932
0x00423932:	popl %ebx
0x00423933:	leave
0x00423934:	ret

0x00423b44:	movl 0x8(%ebp), %eax
0x00423b47:	cmpl %eax, 0x4(%ebx)
0x00423b4a:	je 343
0x00423b50:	pushl $0x220<UINT32>
0x00423b55:	call 0x004234be
0x00423b5a:	popl %ecx
0x00423b5b:	movl %ebx, %eax
0x00423b5d:	testl %ebx, %ebx
0x00423b5f:	je 326
0x00423b65:	movl %ecx, $0x88<UINT32>
0x00423b6a:	movl %esi, 0x68(%edi)
0x00423b6d:	movl %edi, %ebx
0x00423b6f:	rep movsl %es:(%edi), %ds:(%esi)
0x00423b71:	andl (%ebx), $0x0<UINT8>
0x00423b74:	pushl %ebx
0x00423b75:	pushl 0x8(%ebp)
0x00423b78:	call 0x00423935
0x00423935:	movl %edi, %edi
0x00423937:	pushl %ebp
0x00423938:	movl %ebp, %esp
0x0042393a:	subl %esp, $0x20<UINT8>
0x0042393d:	movl %eax, 0x44609c
0x00423942:	xorl %eax, %ebp
0x00423944:	movl -4(%ebp), %eax
0x00423947:	pushl %ebx
0x00423948:	movl %ebx, 0xc(%ebp)
0x0042394b:	pushl %esi
0x0042394c:	movl %esi, 0x8(%ebp)
0x0042394f:	pushl %edi
0x00423950:	call 0x004238b9
0x0042390d:	cmpl %esi, $0xfffffffc<UINT8>
0x00423910:	jne 0x00423924
0x00423924:	cmpb -4(%ebp), %bl
0x00423927:	je 7
0x00423929:	movl %eax, -8(%ebp)
0x0042392c:	andl 0x70(%eax), $0xfffffffd<UINT8>
0x00423930:	movl %eax, %esi
0x00423955:	movl %edi, %eax
0x00423957:	xorl %esi, %esi
0x00423959:	movl 0x8(%ebp), %edi
0x0042395c:	cmpl %edi, %esi
0x0042395e:	jne 0x0042396e
0x0042396e:	movl -28(%ebp), %esi
0x00423971:	xorl %eax, %eax
0x00423973:	cmpl 0x446a38(%eax), %edi
0x00423979:	je 145
0x0042397f:	incl -28(%ebp)
0x00423982:	addl %eax, $0x30<UINT8>
0x00423985:	cmpl %eax, $0xf0<UINT32>
0x0042398a:	jb 0x00423973
0x0042398c:	cmpl %edi, $0xfde8<UINT32>
0x00423992:	je 368
0x00423998:	cmpl %edi, $0xfde9<UINT32>
0x0042399e:	je 356
0x004239a4:	movzwl %eax, %di
0x004239a7:	pushl %eax
0x004239a8:	call IsValidCodePage@kernel32.dll
IsValidCodePage@kernel32.dll: API Node	
0x004239ae:	testl %eax, %eax
0x004239b0:	je 338
0x004239b6:	leal %eax, -24(%ebp)
0x004239b9:	pushl %eax
0x004239ba:	pushl %edi
0x004239bb:	call GetCPInfo@kernel32.dll
GetCPInfo@kernel32.dll: API Node	
0x004239c1:	testl %eax, %eax
0x004239c3:	je 307
0x004239c9:	pushl $0x101<UINT32>
0x004239ce:	leal %eax, 0x1c(%ebx)
0x004239d1:	pushl %esi
0x004239d2:	pushl %eax
0x004239d3:	call 0x0041f180
0x0041f180:	movl %edx, 0xc(%esp)
0x0041f184:	movl %ecx, 0x4(%esp)
0x0041f188:	testl %edx, %edx
0x0041f18a:	je 105
0x0041f18c:	xorl %eax, %eax
0x0041f18e:	movb %al, 0x8(%esp)
0x0041f192:	testb %al, %al
0x0041f194:	jne 22
0x0041f196:	cmpl %edx, $0x100<UINT32>
0x0041f19c:	jb 0x0041f1ac
0x0041f19e:	cmpl 0x449a68, $0x0<UINT8>
0x0041f1a5:	je 0x0041f1ac
0x0041f1ac:	pushl %edi
0x0041f1ad:	movl %edi, %ecx
0x0041f1af:	cmpl %edx, $0x4<UINT8>
0x0041f1b2:	jb 49
0x0041f1b4:	negl %ecx
0x0041f1b6:	andl %ecx, $0x3<UINT8>
0x0041f1b9:	je 0x0041f1c7
0x0041f1c7:	movl %ecx, %eax
0x0041f1c9:	shll %eax, $0x8<UINT8>
0x0041f1cc:	addl %eax, %ecx
0x0041f1ce:	movl %ecx, %eax
0x0041f1d0:	shll %eax, $0x10<UINT8>
0x0041f1d3:	addl %eax, %ecx
0x0041f1d5:	movl %ecx, %edx
0x0041f1d7:	andl %edx, $0x3<UINT8>
0x0041f1da:	shrl %ecx, $0x2<UINT8>
0x0041f1dd:	je 6
0x0041f1df:	rep stosl %es:(%edi), %eax
0x0041f1e1:	testl %edx, %edx
0x0041f1e3:	je 0x0041f1ef
0x0041f1e5:	movb (%edi), %al
0x0041f1e7:	addl %edi, $0x1<UINT8>
0x0041f1ea:	subl %edx, $0x1<UINT8>
0x0041f1ed:	jne 0x0041f1e5
0x0041f1ef:	movl %eax, 0x8(%esp)
0x0041f1f3:	popl %edi
0x0041f1f4:	ret

0x004239d8:	xorl %edx, %edx
0x004239da:	incl %edx
0x004239db:	addl %esp, $0xc<UINT8>
0x004239de:	movl 0x4(%ebx), %edi
0x004239e1:	movl 0xc(%ebx), %esi
0x004239e4:	cmpl -24(%ebp), %edx
0x004239e7:	jbe 248
0x004239ed:	cmpb -18(%ebp), $0x0<UINT8>
0x004239f1:	je 0x00423ac6
0x00423ac6:	leal %eax, 0x1e(%ebx)
0x00423ac9:	movl %ecx, $0xfe<UINT32>
0x00423ace:	orb (%eax), $0x8<UINT8>
0x00423ad1:	incl %eax
0x00423ad2:	decl %ecx
0x00423ad3:	jne 0x00423ace
0x00423ad5:	movl %eax, 0x4(%ebx)
0x00423ad8:	call 0x004235ef
0x004235ef:	subl %eax, $0x3a4<UINT32>
0x004235f4:	je 34
0x004235f6:	subl %eax, $0x4<UINT8>
0x004235f9:	je 23
0x004235fb:	subl %eax, $0xd<UINT8>
0x004235fe:	je 12
0x00423600:	decl %eax
0x00423601:	je 3
0x00423603:	xorl %eax, %eax
0x00423605:	ret

0x00423add:	movl 0xc(%ebx), %eax
0x00423ae0:	movl 0x8(%ebx), %edx
0x00423ae3:	jmp 0x00423ae8
0x00423ae8:	xorl %eax, %eax
0x00423aea:	movzwl %ecx, %ax
0x00423aed:	movl %eax, %ecx
0x00423aef:	shll %ecx, $0x10<UINT8>
0x00423af2:	orl %eax, %ecx
0x00423af4:	leal %edi, 0x10(%ebx)
0x00423af7:	stosl %es:(%edi), %eax
0x00423af8:	stosl %es:(%edi), %eax
0x00423af9:	stosl %es:(%edi), %eax
0x00423afa:	jmp 0x00423aa4
0x00423aa4:	movl %esi, %ebx
0x00423aa6:	call 0x00423682
0x00423682:	movl %edi, %edi
0x00423684:	pushl %ebp
0x00423685:	movl %ebp, %esp
0x00423687:	subl %esp, $0x51c<UINT32>
0x0042368d:	movl %eax, 0x44609c
0x00423692:	xorl %eax, %ebp
0x00423694:	movl -4(%ebp), %eax
0x00423697:	pushl %ebx
0x00423698:	pushl %edi
0x00423699:	leal %eax, -1304(%ebp)
0x0042369f:	pushl %eax
0x004236a0:	pushl 0x4(%esi)
0x004236a3:	call GetCPInfo@kernel32.dll
0x004236a9:	movl %edi, $0x100<UINT32>
0x004236ae:	testl %eax, %eax
0x004236b0:	je 251
0x004236b6:	xorl %eax, %eax
0x004236b8:	movb -260(%ebp,%eax), %al
0x004236bf:	incl %eax
0x004236c0:	cmpl %eax, %edi
0x004236c2:	jb 0x004236b8
0x004236c4:	movb %al, -1298(%ebp)
0x004236ca:	movb -260(%ebp), $0x20<UINT8>
0x004236d1:	testb %al, %al
0x004236d3:	je 0x00423703
0x00423703:	pushl $0x0<UINT8>
0x00423705:	pushl 0xc(%esi)
0x00423708:	leal %eax, -1284(%ebp)
0x0042370e:	pushl 0x4(%esi)
0x00423711:	pushl %eax
0x00423712:	pushl %edi
0x00423713:	leal %eax, -260(%ebp)
0x00423719:	pushl %eax
0x0042371a:	pushl $0x1<UINT8>
0x0042371c:	pushl $0x0<UINT8>
0x0042371e:	call 0x0042b1d2
0x0042b1d2:	movl %edi, %edi
0x0042b1d4:	pushl %ebp
0x0042b1d5:	movl %ebp, %esp
0x0042b1d7:	subl %esp, $0x10<UINT8>
0x0042b1da:	pushl 0x8(%ebp)
0x0042b1dd:	leal %ecx, -16(%ebp)
0x0042b1e0:	call 0x0041d900
0x0042b1e5:	pushl 0x24(%ebp)
0x0042b1e8:	leal %ecx, -16(%ebp)
0x0042b1eb:	pushl 0x20(%ebp)
0x0042b1ee:	pushl 0x1c(%ebp)
0x0042b1f1:	pushl 0x18(%ebp)
0x0042b1f4:	pushl 0x14(%ebp)
0x0042b1f7:	pushl 0x10(%ebp)
0x0042b1fa:	pushl 0xc(%ebp)
0x0042b1fd:	call 0x0042b018
0x0042b018:	movl %edi, %edi
0x0042b01a:	pushl %ebp
0x0042b01b:	movl %ebp, %esp
0x0042b01d:	pushl %ecx
0x0042b01e:	pushl %ecx
0x0042b01f:	movl %eax, 0x44609c
0x0042b024:	xorl %eax, %ebp
0x0042b026:	movl -4(%ebp), %eax
0x0042b029:	movl %eax, 0x4487f4
0x0042b02e:	pushl %ebx
0x0042b02f:	pushl %esi
0x0042b030:	xorl %ebx, %ebx
0x0042b032:	pushl %edi
0x0042b033:	movl %edi, %ecx
0x0042b035:	cmpl %eax, %ebx
0x0042b037:	jne 58
0x0042b039:	leal %eax, -8(%ebp)
0x0042b03c:	pushl %eax
0x0042b03d:	xorl %esi, %esi
0x0042b03f:	incl %esi
0x0042b040:	pushl %esi
0x0042b041:	pushl $0x43b8a8<UINT32>
0x0042b046:	pushl %esi
0x0042b047:	call GetStringTypeW@kernel32.dll
GetStringTypeW@kernel32.dll: API Node	
0x0042b04d:	testl %eax, %eax
0x0042b04f:	je 8
0x0042b051:	movl 0x4487f4, %esi
0x0042b057:	jmp 0x0042b08d
0x0042b08d:	movl -8(%ebp), %ebx
0x0042b090:	cmpl 0x18(%ebp), %ebx
0x0042b093:	jne 0x0042b09d
0x0042b09d:	movl %esi, 0x43b08c
0x0042b0a3:	xorl %eax, %eax
0x0042b0a5:	cmpl 0x20(%ebp), %ebx
0x0042b0a8:	pushl %ebx
0x0042b0a9:	pushl %ebx
0x0042b0aa:	pushl 0x10(%ebp)
0x0042b0ad:	setne %al
0x0042b0b0:	pushl 0xc(%ebp)
0x0042b0b3:	leal %eax, 0x1(,%eax,8)
0x0042b0ba:	pushl %eax
0x0042b0bb:	pushl 0x18(%ebp)
0x0042b0be:	call MultiByteToWideChar@kernel32.dll
MultiByteToWideChar@kernel32.dll: API Node	
0x0042b0c0:	movl %edi, %eax
0x0042b0c2:	cmpl %edi, %ebx
0x0042b0c4:	je 171
0x0042b0ca:	jle 60
0x0042b0cc:	cmpl %edi, $0x7ffffff0<UINT32>
0x0042b0d2:	ja 52
0x0042b0d4:	leal %eax, 0x8(%edi,%edi)
0x0042b0d8:	cmpl %eax, $0x400<UINT32>
0x0042b0dd:	ja 19
0x0042b0df:	call 0x0041f200
0x0041f200:	pushl %ecx
0x0041f201:	leal %ecx, 0x8(%esp)
0x0041f205:	subl %ecx, %eax
0x0041f207:	andl %ecx, $0xf<UINT8>
0x0041f20a:	addl %eax, %ecx
0x0041f20c:	sbbl %ecx, %ecx
0x0041f20e:	orl %eax, %ecx
0x0041f210:	popl %ecx
0x0041f211:	jmp 0x00428430
0x00428430:	pushl %ecx
0x00428431:	leal %ecx, 0x4(%esp)
0x00428435:	subl %ecx, %eax
0x00428437:	sbbl %eax, %eax
0x00428439:	notl %eax
0x0042843b:	andl %ecx, %eax
0x0042843d:	movl %eax, %esp
0x0042843f:	andl %eax, $0xfffff000<UINT32>
0x00428444:	cmpl %ecx, %eax
0x00428446:	jb 10
0x00428448:	movl %eax, %ecx
0x0042844a:	popl %ecx
0x0042844b:	xchgl %esp, %eax
0x0042844c:	movl %eax, (%eax)
0x0042844e:	movl (%esp), %eax
0x00428451:	ret

0x0042b0e4:	movl %eax, %esp
0x0042b0e6:	cmpl %eax, %ebx
0x0042b0e8:	je 28
0x0042b0ea:	movl (%eax), $0xcccc<UINT32>
0x0042b0f0:	jmp 0x0042b103
0x0042b103:	addl %eax, $0x8<UINT8>
0x0042b106:	movl %ebx, %eax
0x0042b108:	testl %ebx, %ebx
0x0042b10a:	je 105
0x0042b10c:	leal %eax, (%edi,%edi)
0x0042b10f:	pushl %eax
0x0042b110:	pushl $0x0<UINT8>
0x0042b112:	pushl %ebx
0x0042b113:	call 0x0041f180
0x0042b118:	addl %esp, $0xc<UINT8>
0x0042b11b:	pushl %edi
0x0042b11c:	pushl %ebx
0x0042b11d:	pushl 0x10(%ebp)
0x0042b120:	pushl 0xc(%ebp)
0x0042b123:	pushl $0x1<UINT8>
0x0042b125:	pushl 0x18(%ebp)
0x0042b128:	call MultiByteToWideChar@kernel32.dll
0x0042b12a:	testl %eax, %eax
0x0042b12c:	je 17
0x0042b12e:	pushl 0x14(%ebp)
0x0042b131:	pushl %eax
0x0042b132:	pushl %ebx
0x0042b133:	pushl 0x8(%ebp)
0x0042b136:	call GetStringTypeW@kernel32.dll
0x0042b13c:	movl -8(%ebp), %eax
0x0042b13f:	pushl %ebx
0x0042b140:	call 0x0042414e
0x0042414e:	movl %edi, %edi
0x00424150:	pushl %ebp
0x00424151:	movl %ebp, %esp
0x00424153:	movl %eax, 0x8(%ebp)
0x00424156:	testl %eax, %eax
0x00424158:	je 18
0x0042415a:	subl %eax, $0x8<UINT8>
0x0042415d:	cmpl (%eax), $0xdddd<UINT32>
0x00424163:	jne 0x0042416c
0x0042416c:	popl %ebp
0x0042416d:	ret

0x0042b145:	movl %eax, -8(%ebp)
0x0042b148:	popl %ecx
0x0042b149:	jmp 0x0042b1c0
0x0042b1c0:	leal %esp, -20(%ebp)
0x0042b1c3:	popl %edi
0x0042b1c4:	popl %esi
0x0042b1c5:	popl %ebx
0x0042b1c6:	movl %ecx, -4(%ebp)
0x0042b1c9:	xorl %ecx, %ebp
0x0042b1cb:	call 0x0041d190
0x0041d190:	cmpl %ecx, 0x44609c
0x0041d196:	jne 0x0041d19a
0x0041d198:	rep ret

0x0042b1d0:	leave
0x0042b1d1:	ret

0x0042b202:	addl %esp, $0x1c<UINT8>
0x0042b205:	cmpb -4(%ebp), $0x0<UINT8>
0x0042b209:	je 7
0x0042b20b:	movl %ecx, -8(%ebp)
0x0042b20e:	andl 0x70(%ecx), $0xfffffffd<UINT8>
0x0042b212:	leave
0x0042b213:	ret

0x00423723:	xorl %ebx, %ebx
0x00423725:	pushl %ebx
0x00423726:	pushl 0x4(%esi)
0x00423729:	leal %eax, -516(%ebp)
0x0042372f:	pushl %edi
0x00423730:	pushl %eax
0x00423731:	pushl %edi
0x00423732:	leal %eax, -260(%ebp)
0x00423738:	pushl %eax
0x00423739:	pushl %edi
0x0042373a:	pushl 0xc(%esi)
0x0042373d:	pushl %ebx
0x0042373e:	call 0x00424513
0x00424513:	movl %edi, %edi
0x00424515:	pushl %ebp
0x00424516:	movl %ebp, %esp
0x00424518:	subl %esp, $0x10<UINT8>
0x0042451b:	pushl 0x8(%ebp)
0x0042451e:	leal %ecx, -16(%ebp)
0x00424521:	call 0x0041d900
0x00424526:	pushl 0x28(%ebp)
0x00424529:	leal %ecx, -16(%ebp)
0x0042452c:	pushl 0x24(%ebp)
0x0042452f:	pushl 0x20(%ebp)
0x00424532:	pushl 0x1c(%ebp)
0x00424535:	pushl 0x18(%ebp)
0x00424538:	pushl 0x14(%ebp)
0x0042453b:	pushl 0x10(%ebp)
0x0042453e:	pushl 0xc(%ebp)
0x00424541:	call 0x0042416e
0x0042416e:	movl %edi, %edi
0x00424170:	pushl %ebp
0x00424171:	movl %ebp, %esp
0x00424173:	subl %esp, $0x14<UINT8>
0x00424176:	movl %eax, 0x44609c
0x0042417b:	xorl %eax, %ebp
0x0042417d:	movl -4(%ebp), %eax
0x00424180:	pushl %ebx
0x00424181:	pushl %esi
0x00424182:	xorl %ebx, %ebx
0x00424184:	pushl %edi
0x00424185:	movl %esi, %ecx
0x00424187:	cmpl 0x448158, %ebx
0x0042418d:	jne 0x004241c7
0x0042418f:	pushl %ebx
0x00424190:	pushl %ebx
0x00424191:	xorl %edi, %edi
0x00424193:	incl %edi
0x00424194:	pushl %edi
0x00424195:	pushl $0x43b8a8<UINT32>
0x0042419a:	pushl $0x100<UINT32>
0x0042419f:	pushl %ebx
0x004241a0:	call LCMapStringW@kernel32.dll
LCMapStringW@kernel32.dll: API Node	
0x004241a6:	testl %eax, %eax
0x004241a8:	je 8
0x004241aa:	movl 0x448158, %edi
0x004241b0:	jmp 0x004241c7
0x004241c7:	cmpl 0x14(%ebp), %ebx
0x004241ca:	jle 0x004241ee
0x004241ee:	movl %eax, 0x448158
0x004241f3:	cmpl %eax, $0x2<UINT8>
0x004241f6:	je 428
0x004241fc:	cmpl %eax, %ebx
0x004241fe:	je 420
0x00424204:	cmpl %eax, $0x1<UINT8>
0x00424207:	jne 460
0x0042420d:	movl -8(%ebp), %ebx
0x00424210:	cmpl 0x20(%ebp), %ebx
0x00424213:	jne 0x0042421d
0x0042421d:	movl %esi, 0x43b08c
0x00424223:	xorl %eax, %eax
0x00424225:	cmpl 0x24(%ebp), %ebx
0x00424228:	pushl %ebx
0x00424229:	pushl %ebx
0x0042422a:	pushl 0x14(%ebp)
0x0042422d:	setne %al
0x00424230:	pushl 0x10(%ebp)
0x00424233:	leal %eax, 0x1(,%eax,8)
0x0042423a:	pushl %eax
0x0042423b:	pushl 0x20(%ebp)
0x0042423e:	call MultiByteToWideChar@kernel32.dll
0x00424240:	movl %edi, %eax
0x00424242:	cmpl %edi, %ebx
0x00424244:	je 0x004243d9
0x004243d9:	xorl %eax, %eax
0x004243db:	jmp 0x00424501
0x00424501:	leal %esp, -32(%ebp)
0x00424504:	popl %edi
0x00424505:	popl %esi
0x00424506:	popl %ebx
0x00424507:	movl %ecx, -4(%ebp)
0x0042450a:	xorl %ecx, %ebp
0x0042450c:	call 0x0041d190
0x00424511:	leave
0x00424512:	ret

0x00424546:	addl %esp, $0x20<UINT8>
0x00424549:	cmpb -4(%ebp), $0x0<UINT8>
0x0042454d:	je 7
0x0042454f:	movl %ecx, -8(%ebp)
0x00424552:	andl 0x70(%ecx), $0xfffffffd<UINT8>
0x00424556:	leave
0x00424557:	ret

0x00423743:	addl %esp, $0x44<UINT8>
0x00423746:	pushl %ebx
0x00423747:	pushl 0x4(%esi)
0x0042374a:	leal %eax, -772(%ebp)
0x00423750:	pushl %edi
0x00423751:	pushl %eax
0x00423752:	pushl %edi
0x00423753:	leal %eax, -260(%ebp)
0x00423759:	pushl %eax
0x0042375a:	pushl $0x200<UINT32>
0x0042375f:	pushl 0xc(%esi)
0x00423762:	pushl %ebx
0x00423763:	call 0x00424513
0x00423768:	addl %esp, $0x24<UINT8>
0x0042376b:	xorl %eax, %eax
0x0042376d:	movzwl %ecx, -1284(%ebp,%eax,2)
0x00423775:	testb %cl, $0x1<UINT8>
0x00423778:	je 0x00423788
0x00423788:	testb %cl, $0x2<UINT8>
0x0042378b:	je 0x004237a2
0x004237a2:	movb 0x11d(%esi,%eax), $0x0<UINT8>
0x004237aa:	incl %eax
0x004237ab:	cmpl %eax, %edi
0x004237ad:	jb -66
0x004237af:	jmp 0x00423807
0x00423807:	movl %ecx, -4(%ebp)
0x0042380a:	popl %edi
0x0042380b:	xorl %ecx, %ebp
0x0042380d:	popl %ebx
0x0042380e:	call 0x0041d190
0x00423813:	leave
0x00423814:	ret

0x00423aab:	jmp 0x00423967
0x00423967:	xorl %eax, %eax
0x00423969:	jmp 0x00423b0b
0x00423b0b:	movl %ecx, -4(%ebp)
0x00423b0e:	popl %edi
0x00423b0f:	popl %esi
0x00423b10:	xorl %ecx, %ebp
0x00423b12:	popl %ebx
0x00423b13:	call 0x0041d190
0x00423b18:	leave
0x00423b19:	ret

0x00423b7d:	popl %ecx
0x00423b7e:	popl %ecx
0x00423b7f:	movl -32(%ebp), %eax
0x00423b82:	testl %eax, %eax
0x00423b84:	jne 252
0x00423b8a:	movl %esi, -36(%ebp)
0x00423b8d:	pushl 0x68(%esi)
0x00423b90:	call InterlockedDecrement@kernel32.dll
InterlockedDecrement@kernel32.dll: API Node	
0x00423b96:	testl %eax, %eax
0x00423b98:	jne 17
0x00423b9a:	movl %eax, 0x68(%esi)
0x00423b9d:	cmpl %eax, $0x446608<UINT32>
0x00423ba2:	je 0x00423bab
0x00423bab:	movl 0x68(%esi), %ebx
0x00423bae:	pushl %ebx
0x00423baf:	movl %edi, 0x43b124
0x00423bb5:	call InterlockedIncrement@kernel32.dll
0x00423bb7:	testb 0x70(%esi), $0x2<UINT8>
0x00423bbb:	jne 234
0x00423bc1:	testb 0x446b2c, $0x1<UINT8>
0x00423bc8:	jne 221
0x00423bce:	pushl $0xd<UINT8>
0x00423bd0:	call 0x004250fb
0x00423bd5:	popl %ecx
0x00423bd6:	andl -4(%ebp), $0x0<UINT8>
0x00423bda:	movl %eax, 0x4(%ebx)
0x00423bdd:	movl 0x448128, %eax
0x00423be2:	movl %eax, 0x8(%ebx)
0x00423be5:	movl 0x44812c, %eax
0x00423bea:	movl %eax, 0xc(%ebx)
0x00423bed:	movl 0x448130, %eax
0x00423bf2:	xorl %eax, %eax
0x00423bf4:	movl -28(%ebp), %eax
0x00423bf7:	cmpl %eax, $0x5<UINT8>
0x00423bfa:	jnl 0x00423c0c
0x00423bfc:	movw %cx, 0x10(%ebx,%eax,2)
0x00423c01:	movw 0x44811c(,%eax,2), %cx
0x00423c09:	incl %eax
0x00423c0a:	jmp 0x00423bf4
0x00423c0c:	xorl %eax, %eax
0x00423c0e:	movl -28(%ebp), %eax
0x00423c11:	cmpl %eax, $0x101<UINT32>
0x00423c16:	jnl 0x00423c25
0x00423c18:	movb %cl, 0x1c(%eax,%ebx)
0x00423c1c:	movb 0x446828(%eax), %cl
0x00423c22:	incl %eax
0x00423c23:	jmp 0x00423c0e
0x00423c25:	xorl %eax, %eax
0x00423c27:	movl -28(%ebp), %eax
0x00423c2a:	cmpl %eax, $0x100<UINT32>
0x00423c2f:	jnl 0x00423c41
0x00423c31:	movb %cl, 0x11d(%eax,%ebx)
0x00423c38:	movb 0x446930(%eax), %cl
0x00423c3e:	incl %eax
0x00423c3f:	jmp 0x00423c27
0x00423c41:	pushl 0x446a30
0x00423c47:	call InterlockedDecrement@kernel32.dll
0x00423c4d:	testl %eax, %eax
0x00423c4f:	jne 0x00423c64
0x00423c64:	movl 0x446a30, %ebx
0x00423c6a:	pushl %ebx
0x00423c6b:	call InterlockedIncrement@kernel32.dll
0x00423c6d:	movl -4(%ebp), $0xfffffffe<UINT32>
0x00423c74:	call 0x00423c7b
0x00423c7b:	pushl $0xd<UINT8>
0x00423c7d:	call 0x00425021
0x00423c82:	popl %ecx
0x00423c83:	ret

0x00423c79:	jmp 0x00423cab
0x00423cab:	movl %eax, -32(%ebp)
0x00423cae:	call 0x0041fb15
0x00423cb3:	ret

0x00423cc4:	popl %ecx
0x00423cc5:	movl 0x44abb4, $0x1<UINT32>
0x00423ccf:	xorl %eax, %eax
0x00423cd1:	ret

0x0042746d:	pushl $0x104<UINT32>
0x00427472:	movl %esi, $0x4486d8<UINT32>
0x00427477:	pushl %esi
0x00427478:	pushl %ebx
0x00427479:	movb 0x4487dc, %bl
0x0042747f:	call GetModuleFileNameA@kernel32.dll
GetModuleFileNameA@kernel32.dll: API Node	
0x00427485:	movl %eax, 0x44aba4
0x0042748a:	movl 0x447da0, %esi
0x00427490:	cmpl %eax, %ebx
0x00427492:	je 7
0x00427494:	movl -4(%ebp), %eax
0x00427497:	cmpb (%eax), %bl
0x00427499:	jne 0x0042749e
0x0042749e:	movl %edx, -4(%ebp)
0x004274a1:	leal %eax, -8(%ebp)
0x004274a4:	pushl %eax
0x004274a5:	pushl %ebx
0x004274a6:	pushl %ebx
0x004274a7:	leal %edi, -12(%ebp)
0x004274aa:	call 0x004272b9
0x004272b9:	movl %edi, %edi
0x004272bb:	pushl %ebp
0x004272bc:	movl %ebp, %esp
0x004272be:	pushl %ecx
0x004272bf:	movl %ecx, 0x10(%ebp)
0x004272c2:	pushl %ebx
0x004272c3:	xorl %eax, %eax
0x004272c5:	pushl %esi
0x004272c6:	movl (%edi), %eax
0x004272c8:	movl %esi, %edx
0x004272ca:	movl %edx, 0xc(%ebp)
0x004272cd:	movl (%ecx), $0x1<UINT32>
0x004272d3:	cmpl 0x8(%ebp), %eax
0x004272d6:	je 0x004272e1
0x004272e1:	movl -4(%ebp), %eax
0x004272e4:	cmpb (%esi), $0x22<UINT8>
0x004272e7:	jne 0x004272f9
0x004272e9:	xorl %eax, %eax
0x004272eb:	cmpl -4(%ebp), %eax
0x004272ee:	movb %bl, $0x22<UINT8>
0x004272f0:	sete %al
0x004272f3:	incl %esi
0x004272f4:	movl -4(%ebp), %eax
0x004272f7:	jmp 0x00427335
0x00427335:	cmpl -4(%ebp), $0x0<UINT8>
0x00427339:	jne 0x004272e4
0x004272f9:	incl (%edi)
0x004272fb:	testl %edx, %edx
0x004272fd:	je 0x00427307
0x00427307:	movb %bl, (%esi)
0x00427309:	movzbl %eax, %bl
0x0042730c:	pushl %eax
0x0042730d:	incl %esi
0x0042730e:	call 0x0042bf54
0x0042bf54:	movl %edi, %edi
0x0042bf56:	pushl %ebp
0x0042bf57:	movl %ebp, %esp
0x0042bf59:	pushl $0x4<UINT8>
0x0042bf5b:	pushl $0x0<UINT8>
0x0042bf5d:	pushl 0x8(%ebp)
0x0042bf60:	pushl $0x0<UINT8>
0x0042bf62:	call 0x0042bf01
0x0042bf01:	movl %edi, %edi
0x0042bf03:	pushl %ebp
0x0042bf04:	movl %ebp, %esp
0x0042bf06:	subl %esp, $0x10<UINT8>
0x0042bf09:	pushl 0x8(%ebp)
0x0042bf0c:	leal %ecx, -16(%ebp)
0x0042bf0f:	call 0x0041d900
0x0042bf14:	movzbl %eax, 0xc(%ebp)
0x0042bf18:	movl %ecx, -12(%ebp)
0x0042bf1b:	movb %dl, 0x14(%ebp)
0x0042bf1e:	testb 0x1d(%ecx,%eax), %dl
0x0042bf22:	jne 30
0x0042bf24:	cmpl 0x10(%ebp), $0x0<UINT8>
0x0042bf28:	je 0x0042bf3c
0x0042bf3c:	xorl %eax, %eax
0x0042bf3e:	testl %eax, %eax
0x0042bf40:	je 0x0042bf45
0x0042bf45:	cmpb -4(%ebp), $0x0<UINT8>
0x0042bf49:	je 7
0x0042bf4b:	movl %ecx, -8(%ebp)
0x0042bf4e:	andl 0x70(%ecx), $0xfffffffd<UINT8>
0x0042bf52:	leave
0x0042bf53:	ret

0x0042bf67:	addl %esp, $0x10<UINT8>
0x0042bf6a:	popl %ebp
0x0042bf6b:	ret

0x00427313:	popl %ecx
0x00427314:	testl %eax, %eax
0x00427316:	je 0x0042732b
0x0042732b:	movl %edx, 0xc(%ebp)
0x0042732e:	movl %ecx, 0x10(%ebp)
0x00427331:	testb %bl, %bl
0x00427333:	je 0x00427367
0x0042733b:	cmpb %bl, $0x20<UINT8>
0x0042733e:	je 5
0x00427340:	cmpb %bl, $0x9<UINT8>
0x00427343:	jne 0x004272e4
0x00427367:	decl %esi
0x00427368:	jmp 0x0042734d
0x0042734d:	andl -4(%ebp), $0x0<UINT8>
0x00427351:	cmpb (%esi), $0x0<UINT8>
0x00427354:	je 0x00427443
0x00427443:	movl %eax, 0x8(%ebp)
0x00427446:	popl %esi
0x00427447:	popl %ebx
0x00427448:	testl %eax, %eax
0x0042744a:	je 0x0042744f
0x0042744f:	incl (%ecx)
0x00427451:	leave
0x00427452:	ret

0x004274af:	movl %eax, -8(%ebp)
0x004274b2:	addl %esp, $0xc<UINT8>
0x004274b5:	cmpl %eax, $0x3fffffff<UINT32>
0x004274ba:	jae 74
0x004274bc:	movl %ecx, -12(%ebp)
0x004274bf:	cmpl %ecx, $0xffffffff<UINT8>
0x004274c2:	jae 66
0x004274c4:	movl %edi, %eax
0x004274c6:	shll %edi, $0x2<UINT8>
0x004274c9:	leal %eax, (%edi,%ecx)
0x004274cc:	cmpl %eax, %ecx
0x004274ce:	jb 54
0x004274d0:	pushl %eax
0x004274d1:	call 0x004234be
0x004274d6:	movl %esi, %eax
0x004274d8:	popl %ecx
0x004274d9:	cmpl %esi, %ebx
0x004274db:	je 41
0x004274dd:	movl %edx, -4(%ebp)
0x004274e0:	leal %eax, -8(%ebp)
0x004274e3:	pushl %eax
0x004274e4:	addl %edi, %esi
0x004274e6:	pushl %edi
0x004274e7:	pushl %esi
0x004274e8:	leal %edi, -12(%ebp)
0x004274eb:	call 0x004272b9
0x004272d8:	movl %ebx, 0x8(%ebp)
0x004272db:	addl 0x8(%ebp), $0x4<UINT8>
0x004272df:	movl (%ebx), %edx
0x004272ff:	movb %al, (%esi)
0x00427301:	movb (%edx), %al
0x00427303:	incl %edx
0x00427304:	movl 0xc(%ebp), %edx
0x0042744c:	andl (%eax), $0x0<UINT8>
0x004274f0:	movl %eax, -8(%ebp)
0x004274f3:	addl %esp, $0xc<UINT8>
0x004274f6:	decl %eax
0x004274f7:	movl 0x447d84, %eax
0x004274fc:	movl 0x447d88, %esi
0x00427502:	xorl %eax, %eax
0x00427504:	jmp 0x00427509
0x00427509:	popl %edi
0x0042750a:	popl %esi
0x0042750b:	popl %ebx
0x0042750c:	leave
0x0042750d:	ret

0x0041ee1d:	testl %eax, %eax
0x0041ee1f:	jnl 0x0041ee29
0x0041ee29:	call 0x004271db
0x004271db:	cmpl 0x44abb4, $0x0<UINT8>
0x004271e2:	jne 0x004271e9
0x004271e9:	pushl %esi
0x004271ea:	movl %esi, 0x447dc4
0x004271f0:	pushl %edi
0x004271f1:	xorl %edi, %edi
0x004271f3:	testl %esi, %esi
0x004271f5:	jne 0x0042720f
0x0042720f:	movb %al, (%esi)
0x00427211:	testb %al, %al
0x00427213:	jne 0x004271ff
0x004271ff:	cmpb %al, $0x3d<UINT8>
0x00427201:	je 0x00427204
0x00427204:	pushl %esi
0x00427205:	call 0x00421940
0x00421940:	movl %ecx, 0x4(%esp)
0x00421944:	testl %ecx, $0x3<UINT32>
0x0042194a:	je 0x00421970
0x00421970:	movl %eax, (%ecx)
0x00421972:	movl %edx, $0x7efefeff<UINT32>
0x00421977:	addl %edx, %eax
0x00421979:	xorl %eax, $0xffffffff<UINT8>
0x0042197c:	xorl %eax, %edx
0x0042197e:	addl %ecx, $0x4<UINT8>
0x00421981:	testl %eax, $0x81010100<UINT32>
0x00421986:	je 0x00421970
0x00421988:	movl %eax, -4(%ecx)
0x0042198b:	testb %al, %al
0x0042198d:	je 0x004219c1
0x0042198f:	testb %ah, %ah
0x00421991:	je 36
0x00421993:	testl %eax, $0xff0000<UINT32>
0x00421998:	je 19
0x0042199a:	testl %eax, $0xff000000<UINT32>
0x0042199f:	je 0x004219a3
0x004219a3:	leal %eax, -1(%ecx)
0x004219a6:	movl %ecx, 0x4(%esp)
0x004219aa:	subl %eax, %ecx
0x004219ac:	ret

0x0042720a:	popl %ecx
0x0042720b:	leal %esi, 0x1(%esi,%eax)
0x00427215:	pushl $0x4<UINT8>
0x00427217:	incl %edi
0x00427218:	pushl %edi
0x00427219:	call 0x00423503
0x0042721e:	movl %edi, %eax
0x00427220:	popl %ecx
0x00427221:	popl %ecx
0x00427222:	movl 0x447d90, %edi
0x00427228:	testl %edi, %edi
0x0042722a:	je -53
0x0042722c:	movl %esi, 0x447dc4
0x00427232:	pushl %ebx
0x00427233:	jmp 0x00427277
0x00427277:	cmpb (%esi), $0x0<UINT8>
0x0042727a:	jne 0x00427235
0x00427235:	pushl %esi
0x00427236:	call 0x00421940
0x0042723b:	movl %ebx, %eax
0x0042723d:	incl %ebx
0x0042723e:	cmpb (%esi), $0x3d<UINT8>
0x00427241:	popl %ecx
0x00427242:	je 0x00427275
0x00427275:	addl %esi, %ebx
0x0042727c:	pushl 0x447dc4
0x00427282:	call 0x0041ecb7
0x0041ecb7:	pushl $0xc<UINT8>
0x0041ecb9:	pushl $0x441450<UINT32>
0x0041ecbe:	call 0x0041fad0
0x0041ecc3:	movl %esi, 0x8(%ebp)
0x0041ecc6:	testl %esi, %esi
0x0041ecc8:	je 117
0x0041ecca:	cmpl 0x449a4c, $0x3<UINT8>
0x0041ecd1:	jne 0x0041ed16
0x0041ed16:	pushl %esi
0x0041ed17:	pushl $0x0<UINT8>
0x0041ed19:	pushl 0x4486cc
0x0041ed1f:	call HeapFree@kernel32.dll
HeapFree@kernel32.dll: API Node	
0x0041ed25:	testl %eax, %eax
0x0041ed27:	jne 0x0041ed3f
0x0041ed3f:	call 0x0041fb15
0x0041ed44:	ret

0x00427287:	andl 0x447dc4, $0x0<UINT8>
0x0042728e:	andl (%edi), $0x0<UINT8>
0x00427291:	movl 0x44aba8, $0x1<UINT32>
0x0042729b:	xorl %eax, %eax
0x0042729d:	popl %ecx
0x0042729e:	popl %ebx
0x0042729f:	popl %edi
0x004272a0:	popl %esi
0x004272a1:	ret

0x0041ee2e:	testl %eax, %eax
0x0041ee30:	jnl 0x0041ee3a
0x0041ee3a:	pushl $0x1<UINT8>
0x0041ee3c:	call 0x0041e2f4
0x0041e2f4:	movl %edi, %edi
0x0041e2f6:	pushl %ebp
0x0041e2f7:	movl %ebp, %esp
0x0041e2f9:	cmpl 0x43b698, $0x0<UINT8>
0x0041e300:	je 25
0x0041e302:	pushl $0x43b698<UINT32>
0x0041e307:	call 0x004232a0
0x004232a0:	movl %edi, %edi
0x004232a2:	pushl %ebp
0x004232a3:	movl %ebp, %esp
0x004232a5:	pushl $0xfffffffe<UINT8>
0x004232a7:	pushl $0x4415a8<UINT32>
0x004232ac:	pushl $0x41eff0<UINT32>
0x004232b1:	movl %eax, %fs:0
0x004232b7:	pushl %eax
0x004232b8:	subl %esp, $0x8<UINT8>
0x004232bb:	pushl %ebx
0x004232bc:	pushl %esi
0x004232bd:	pushl %edi
0x004232be:	movl %eax, 0x44609c
0x004232c3:	xorl -8(%ebp), %eax
0x004232c6:	xorl %eax, %ebp
0x004232c8:	pushl %eax
0x004232c9:	leal %eax, -16(%ebp)
0x004232cc:	movl %fs:0, %eax
0x004232d2:	movl -24(%ebp), %esp
0x004232d5:	movl -4(%ebp), $0x0<UINT32>
0x004232dc:	pushl $0x400000<UINT32>
0x004232e1:	call 0x00423210
0x00423210:	movl %edi, %edi
0x00423212:	pushl %ebp
0x00423213:	movl %ebp, %esp
0x00423215:	movl %ecx, 0x8(%ebp)
0x00423218:	movl %eax, $0x5a4d<UINT32>
0x0042321d:	cmpw (%ecx), %ax
0x00423220:	je 0x00423226
0x00423226:	movl %eax, 0x3c(%ecx)
0x00423229:	addl %eax, %ecx
0x0042322b:	cmpl (%eax), $0x4550<UINT32>
0x00423231:	jne -17
0x00423233:	xorl %edx, %edx
0x00423235:	movl %ecx, $0x10b<UINT32>
0x0042323a:	cmpw 0x18(%eax), %cx
0x0042323e:	sete %dl
0x00423241:	movl %eax, %edx
0x00423243:	popl %ebp
0x00423244:	ret

0x004232e6:	addl %esp, $0x4<UINT8>
0x004232e9:	testl %eax, %eax
0x004232eb:	je 85
0x004232ed:	movl %eax, 0x8(%ebp)
0x004232f0:	subl %eax, $0x400000<UINT32>
0x004232f5:	pushl %eax
0x004232f6:	pushl $0x400000<UINT32>
0x004232fb:	call 0x00423250
0x00423250:	movl %edi, %edi
0x00423252:	pushl %ebp
0x00423253:	movl %ebp, %esp
0x00423255:	movl %eax, 0x8(%ebp)
0x00423258:	movl %ecx, 0x3c(%eax)
0x0042325b:	addl %ecx, %eax
0x0042325d:	movzwl %eax, 0x14(%ecx)
0x00423261:	pushl %ebx
0x00423262:	pushl %esi
0x00423263:	movzwl %esi, 0x6(%ecx)
0x00423267:	xorl %edx, %edx
0x00423269:	pushl %edi
0x0042326a:	leal %eax, 0x18(%eax,%ecx)
0x0042326e:	testl %esi, %esi
0x00423270:	jbe 27
0x00423272:	movl %edi, 0xc(%ebp)
0x00423275:	movl %ecx, 0xc(%eax)
0x00423278:	cmpl %edi, %ecx
0x0042327a:	jb 9
0x0042327c:	movl %ebx, 0x8(%eax)
0x0042327f:	addl %ebx, %ecx
0x00423281:	cmpl %edi, %ebx
0x00423283:	jb 0x0042328f
0x00423285:	incl %edx
0x00423286:	addl %eax, $0x28<UINT8>
0x00423289:	cmpl %edx, %esi
0x0042328b:	jb 0x00423275
0x0042328f:	popl %edi
0x00423290:	popl %esi
0x00423291:	popl %ebx
0x00423292:	popl %ebp
0x00423293:	ret

0x00423300:	addl %esp, $0x8<UINT8>
0x00423303:	testl %eax, %eax
0x00423305:	je 59
0x00423307:	movl %eax, 0x24(%eax)
0x0042330a:	shrl %eax, $0x1f<UINT8>
0x0042330d:	notl %eax
0x0042330f:	andl %eax, $0x1<UINT8>
0x00423312:	movl -4(%ebp), $0xfffffffe<UINT32>
0x00423319:	movl %ecx, -16(%ebp)
0x0042331c:	movl %fs:0, %ecx
0x00423323:	popl %ecx
0x00423324:	popl %edi
0x00423325:	popl %esi
0x00423326:	popl %ebx
0x00423327:	movl %esp, %ebp
0x00423329:	popl %ebp
0x0042332a:	ret

0x0041e30c:	popl %ecx
0x0041e30d:	testl %eax, %eax
0x0041e30f:	je 10
0x0041e311:	pushl 0x8(%ebp)
0x0041e314:	call 0x0041efc7
0x0041efc7:	movl %edi, %edi
0x0041efc9:	pushl %ebp
0x0041efca:	movl %ebp, %esp
0x0041efcc:	call 0x0041ef67
0x0041ef67:	movl %eax, $0x428290<UINT32>
0x0041ef6c:	movl 0x446df8, %eax
0x0041ef71:	movl 0x446dfc, $0x427977<UINT32>
0x0041ef7b:	movl 0x446e00, $0x42792b<UINT32>
0x0041ef85:	movl 0x446e04, $0x427964<UINT32>
0x0041ef8f:	movl 0x446e08, $0x4278cd<UINT32>
0x0041ef99:	movl 0x446e0c, %eax
0x0041ef9e:	movl 0x446e10, $0x428208<UINT32>
0x0041efa8:	movl 0x446e14, $0x4278e9<UINT32>
0x0041efb2:	movl 0x446e18, $0x42784b<UINT32>
0x0041efbc:	movl 0x446e1c, $0x4277d8<UINT32>
0x0041efc6:	ret

0x0041efd1:	call 0x0042831c
0x0042831c:	pushl $0x43bf04<UINT32>
0x00428321:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32.dll: API Node	
0x00428327:	testl %eax, %eax
0x00428329:	je 21
0x0042832b:	pushl $0x43bee8<UINT32>
0x00428330:	pushl %eax
0x00428331:	call GetProcAddress@kernel32.dll
0x00428337:	testl %eax, %eax
0x00428339:	je 5
0x0042833b:	pushl $0x0<UINT8>
0x0042833d:	call IsProcessorFeaturePresent@KERNEL32
IsProcessorFeaturePresent@KERNEL32: API Node	
0x0042833f:	ret

0x0041efd6:	cmpl 0x8(%ebp), $0x0<UINT8>
0x0041efda:	movl 0x447dd4, %eax
0x0041efdf:	je 5
0x0041efe1:	call 0x004282b3
0x004282b3:	movl %edi, %edi
0x004282b5:	pushl %esi
0x004282b6:	pushl $0x30000<UINT32>
0x004282bb:	pushl $0x10000<UINT32>
0x004282c0:	xorl %esi, %esi
0x004282c2:	pushl %esi
0x004282c3:	call 0x0042c3ff
0x0042c3ff:	movl %edi, %edi
0x0042c401:	pushl %ebp
0x0042c402:	movl %ebp, %esp
0x0042c404:	movl %eax, 0x10(%ebp)
0x0042c407:	movl %ecx, 0xc(%ebp)
0x0042c40a:	andl %eax, $0xfff7ffff<UINT32>
0x0042c40f:	andl %ecx, %eax
0x0042c411:	pushl %esi
0x0042c412:	testl %ecx, $0xfcf0fce0<UINT32>
0x0042c418:	je 0x0042c44b
0x0042c44b:	movl %esi, 0x8(%ebp)
0x0042c44e:	pushl %eax
0x0042c44f:	pushl 0xc(%ebp)
0x0042c452:	testl %esi, %esi
0x0042c454:	je 0x0042c45f
0x0042c45f:	call 0x0042e623
0x0042e623:	movl %edi, %edi
0x0042e625:	pushl %ebp
0x0042e626:	movl %ebp, %esp
0x0042e628:	subl %esp, $0x14<UINT8>
0x0042e62b:	pushl %ebx
0x0042e62c:	pushl %esi
0x0042e62d:	pushl %edi
0x0042e62e:	fwait
0x0042e62f:	fnstcw -8(%ebp)
0x0042e632:	movl %ebx, -8(%ebp)
0x0042e635:	xorl %edx, %edx
0x0042e637:	testb %bl, $0x1<UINT8>
0x0042e63a:	je 0x0042e63f
0x0042e63f:	testb %bl, $0x4<UINT8>
0x0042e642:	je 3
0x0042e644:	orl %edx, $0x8<UINT8>
0x0042e647:	testb %bl, $0x8<UINT8>
0x0042e64a:	je 3
0x0042e64c:	orl %edx, $0x4<UINT8>
0x0042e64f:	testb %bl, $0x10<UINT8>
0x0042e652:	je 3
0x0042e654:	orl %edx, $0x2<UINT8>
0x0042e657:	testb %bl, $0x20<UINT8>
0x0042e65a:	je 3
0x0042e65c:	orl %edx, $0x1<UINT8>
0x0042e65f:	testb %bl, $0x2<UINT8>
0x0042e662:	je 0x0042e66a
0x0042e66a:	movzwl %ecx, %bx
0x0042e66d:	movl %eax, %ecx
0x0042e66f:	movl %esi, $0xc00<UINT32>
0x0042e674:	andl %eax, %esi
0x0042e676:	movl %edi, $0x300<UINT32>
0x0042e67b:	je 36
0x0042e67d:	cmpl %eax, $0x400<UINT32>
0x0042e682:	je 23
0x0042e684:	cmpl %eax, $0x800<UINT32>
0x0042e689:	je 8
0x0042e68b:	cmpl %eax, %esi
0x0042e68d:	jne 18
0x0042e68f:	orl %edx, %edi
0x0042e691:	jmp 0x0042e6a1
0x0042e6a1:	andl %ecx, %edi
0x0042e6a3:	je 16
0x0042e6a5:	cmpl %ecx, $0x200<UINT32>
0x0042e6ab:	jne 0x0042e6bb
0x0042e6bb:	testl %ebx, $0x1000<UINT32>
0x0042e6c1:	je 6
0x0042e6c3:	orl %edx, $0x40000<UINT32>
0x0042e6c9:	movl %edi, 0xc(%ebp)
0x0042e6cc:	movl %ecx, 0x8(%ebp)
0x0042e6cf:	movl %eax, %edi
0x0042e6d1:	notl %eax
0x0042e6d3:	andl %eax, %edx
0x0042e6d5:	andl %ecx, %edi
0x0042e6d7:	orl %eax, %ecx
0x0042e6d9:	movl 0xc(%ebp), %eax
0x0042e6dc:	cmpl %eax, %edx
0x0042e6de:	je 174
0x0042e6e4:	movl %ebx, %eax
0x0042e6e6:	call 0x0042e4f5
0x0042e4f5:	xorl %eax, %eax
0x0042e4f7:	testb %bl, $0x10<UINT8>
0x0042e4fa:	je 0x0042e4fd
0x0042e4fd:	testb %bl, $0x8<UINT8>
0x0042e500:	je 3
0x0042e502:	orl %eax, $0x4<UINT8>
0x0042e505:	testb %bl, $0x4<UINT8>
0x0042e508:	je 3
0x0042e50a:	orl %eax, $0x8<UINT8>
0x0042e50d:	testb %bl, $0x2<UINT8>
0x0042e510:	je 3
0x0042e512:	orl %eax, $0x10<UINT8>
0x0042e515:	testb %bl, $0x1<UINT8>
0x0042e518:	je 3
0x0042e51a:	orl %eax, $0x20<UINT8>
0x0042e51d:	testl %ebx, $0x80000<UINT32>
0x0042e523:	je 0x0042e528
0x0042e528:	movl %ecx, %ebx
0x0042e52a:	movl %edx, $0x300<UINT32>
0x0042e52f:	andl %ecx, %edx
0x0042e531:	pushl %esi
0x0042e532:	movl %esi, $0x200<UINT32>
0x0042e537:	je 35
0x0042e539:	cmpl %ecx, $0x100<UINT32>
0x0042e53f:	je 22
0x0042e541:	cmpl %ecx, %esi
0x0042e543:	je 11
0x0042e545:	cmpl %ecx, %edx
0x0042e547:	jne 19
0x0042e549:	orl %eax, $0xc00<UINT32>
0x0042e54e:	jmp 0x0042e55c
0x0042e55c:	movl %ecx, %ebx
0x0042e55e:	andl %ecx, $0x30000<UINT32>
0x0042e564:	je 12
0x0042e566:	cmpl %ecx, $0x10000<UINT32>
0x0042e56c:	jne 6
0x0042e56e:	orl %eax, %esi
0x0042e570:	jmp 0x0042e574
0x0042e574:	popl %esi
0x0042e575:	testl %ebx, $0x40000<UINT32>
0x0042e57b:	je 5
0x0042e57d:	orl %eax, $0x1000<UINT32>
0x0042e582:	ret

0x0042e6eb:	movzwl %eax, %ax
0x0042e6ee:	movl -4(%ebp), %eax
0x0042e6f1:	fldcw -4(%ebp)
0x0042e6f4:	fwait
0x0042e6f5:	fnstcw -4(%ebp)
0x0042e6f8:	movl %ebx, -4(%ebp)
0x0042e6fb:	xorl %edx, %edx
0x0042e6fd:	testb %bl, $0x1<UINT8>
0x0042e700:	je 0x0042e705
0x0042e705:	testb %bl, $0x4<UINT8>
0x0042e708:	je 3
0x0042e70a:	orl %edx, $0x8<UINT8>
0x0042e70d:	testb %bl, $0x8<UINT8>
0x0042e710:	je 3
0x0042e712:	orl %edx, $0x4<UINT8>
0x0042e715:	testb %bl, $0x10<UINT8>
0x0042e718:	je 3
0x0042e71a:	orl %edx, $0x2<UINT8>
0x0042e71d:	testb %bl, $0x20<UINT8>
0x0042e720:	je 3
0x0042e722:	orl %edx, $0x1<UINT8>
0x0042e725:	testb %bl, $0x2<UINT8>
0x0042e728:	je 0x0042e730
0x0042e730:	movzwl %ecx, %bx
0x0042e733:	movl %eax, %ecx
0x0042e735:	andl %eax, %esi
0x0042e737:	je 40
0x0042e739:	cmpl %eax, $0x400<UINT32>
0x0042e73e:	je 27
0x0042e740:	cmpl %eax, $0x800<UINT32>
0x0042e745:	je 12
0x0042e747:	cmpl %eax, %esi
0x0042e749:	jne 22
0x0042e74b:	orl %edx, $0x300<UINT32>
0x0042e751:	jmp 0x0042e761
0x0042e761:	andl %ecx, $0x300<UINT32>
0x0042e767:	je 16
0x0042e769:	cmpl %ecx, $0x200<UINT32>
0x0042e76f:	jne 14
0x0042e771:	orl %edx, $0x10000<UINT32>
0x0042e777:	jmp 0x0042e77f
0x0042e77f:	testl %ebx, $0x1000<UINT32>
0x0042e785:	je 6
0x0042e787:	orl %edx, $0x40000<UINT32>
0x0042e78d:	movl 0xc(%ebp), %edx
0x0042e790:	movl %eax, %edx
0x0042e792:	xorl %esi, %esi
0x0042e794:	cmpl 0x449a68, %esi
0x0042e79a:	je 0x0042e92d
0x0042e92d:	popl %edi
0x0042e92e:	popl %esi
0x0042e92f:	popl %ebx
0x0042e930:	leave
0x0042e931:	ret

0x0042c464:	popl %ecx
0x0042c465:	popl %ecx
0x0042c466:	xorl %eax, %eax
0x0042c468:	popl %esi
0x0042c469:	popl %ebp
0x0042c46a:	ret

0x004282c8:	addl %esp, $0xc<UINT8>
0x004282cb:	testl %eax, %eax
0x004282cd:	je 0x004282dc
0x004282dc:	popl %esi
0x004282dd:	ret

0x0041efe6:	fnclex
0x0041efe8:	popl %ebp
0x0041efe9:	ret

0x0041e31a:	popl %ecx
0x0041e31b:	call 0x0042517a
0x0042517a:	movl %edi, %edi
0x0042517c:	pushl %esi
0x0042517d:	pushl %edi
0x0042517e:	xorl %edi, %edi
0x00425180:	leal %esi, 0x446df8(%edi)
0x00425186:	pushl (%esi)
0x00425188:	call 0x00422bb9
0x00422bdb:	pushl %eax
0x00422bdc:	pushl 0x4465f4
0x00422be2:	call TlsGetValue@kernel32.dll
0x00422be4:	call FlsGetValue@KERNEL32.DLL
0x00422be6:	testl %eax, %eax
0x00422be8:	je 8
0x00422bea:	movl %eax, 0x1f8(%eax)
0x00422bf0:	jmp 0x00422c19
0x0042518d:	addl %edi, $0x4<UINT8>
0x00425190:	popl %ecx
0x00425191:	movl (%esi), %eax
0x00425193:	cmpl %edi, $0x28<UINT8>
0x00425196:	jb 0x00425180
0x00425198:	popl %edi
0x00425199:	popl %esi
0x0042519a:	ret

0x0041e320:	pushl $0x43b5dc<UINT32>
0x0041e325:	pushl $0x43b5c4<UINT32>
0x0041e32a:	call 0x0041e2d0
0x0041e2d0:	movl %edi, %edi
0x0041e2d2:	pushl %ebp
0x0041e2d3:	movl %ebp, %esp
0x0041e2d5:	pushl %esi
0x0041e2d6:	movl %esi, 0x8(%ebp)
0x0041e2d9:	xorl %eax, %eax
0x0041e2db:	jmp 0x0041e2ec
0x0041e2ec:	cmpl %esi, 0xc(%ebp)
0x0041e2ef:	jb 0x0041e2dd
0x0041e2dd:	testl %eax, %eax
0x0041e2df:	jne 16
0x0041e2e1:	movl %ecx, (%esi)
0x0041e2e3:	testl %ecx, %ecx
0x0041e2e5:	je 0x0041e2e9
0x0041e2e9:	addl %esi, $0x4<UINT8>
0x0041e2e7:	call 0x004271cd
0x0041dc4b:	movl %edi, %edi
0x0041dc4d:	pushl %esi
0x0041dc4e:	pushl $0x4<UINT8>
0x0041dc50:	pushl $0x20<UINT8>
0x0041dc52:	call 0x00423503
0x0041dc57:	movl %esi, %eax
0x0041dc59:	pushl %esi
0x0041dc5a:	call 0x00422bb9
0x0041dc5f:	addl %esp, $0xc<UINT8>
0x0041dc62:	movl 0x44abb0, %eax
0x0041dc67:	movl 0x44abac, %eax
0x0041dc6c:	testl %esi, %esi
0x0041dc6e:	jne 0x0041dc75
0x0041dc75:	andl (%esi), $0x0<UINT8>
0x0041dc78:	xorl %eax, %eax
0x0041dc7a:	popl %esi
0x0041dc7b:	ret

0x0041f338:	movl %eax, 0x44aba0
0x0041f33d:	pushl %esi
0x0041f33e:	pushl $0x14<UINT8>
0x0041f340:	popl %esi
0x0041f341:	testl %eax, %eax
0x0041f343:	jne 7
0x0041f345:	movl %eax, $0x200<UINT32>
0x0041f34a:	jmp 0x0041f352
0x0041f352:	movl 0x44aba0, %eax
0x0041f357:	pushl $0x4<UINT8>
0x0041f359:	pushl %eax
0x0041f35a:	call 0x00423503
0x0041f35f:	popl %ecx
0x0041f360:	popl %ecx
0x0041f361:	movl 0x449b80, %eax
0x0041f366:	testl %eax, %eax
0x0041f368:	jne 0x0041f388
0x0041f388:	xorl %edx, %edx
0x0041f38a:	movl %ecx, $0x4460b0<UINT32>
0x0041f38f:	jmp 0x0041f396
0x0041f396:	movl (%edx,%eax), %ecx
0x0041f399:	addl %ecx, $0x20<UINT8>
0x0041f39c:	addl %edx, $0x4<UINT8>
0x0041f39f:	cmpl %ecx, $0x446330<UINT32>
0x0041f3a5:	jl 0x0041f391
0x0041f391:	movl %eax, 0x449b80
0x0041f3a7:	pushl $0xfffffffe<UINT8>
0x0041f3a9:	popl %esi
0x0041f3aa:	xorl %edx, %edx
0x0041f3ac:	movl %ecx, $0x4460c0<UINT32>
0x0041f3b1:	pushl %edi
0x0041f3b2:	movl %eax, %edx
0x0041f3b4:	sarl %eax, $0x5<UINT8>
0x0041f3b7:	movl %eax, 0x449a80(,%eax,4)
0x0041f3be:	movl %edi, %edx
0x0041f3c0:	andl %edi, $0x1f<UINT8>
0x0041f3c3:	shll %edi, $0x6<UINT8>
0x0041f3c6:	movl %eax, (%edi,%eax)
0x0041f3c9:	cmpl %eax, $0xffffffff<UINT8>
0x0041f3cc:	je 8
0x0041f3ce:	cmpl %eax, %esi
0x0041f3d0:	je 4
0x0041f3d2:	testl %eax, %eax
0x0041f3d4:	jne 0x0041f3d8
0x0041f3d8:	addl %ecx, $0x20<UINT8>
0x0041f3db:	incl %edx
0x0041f3dc:	cmpl %ecx, $0x446120<UINT32>
0x0041f3e2:	jl 0x0041f3b2
0x0041f3e4:	popl %edi
0x0041f3e5:	xorl %eax, %eax
0x0041f3e7:	popl %esi
0x0041f3e8:	ret

0x00424c2e:	call 0x00424bcc
0x00424bcc:	movl %edi, %edi
0x00424bce:	pushl %ebp
0x00424bcf:	movl %ebp, %esp
0x00424bd1:	subl %esp, $0x18<UINT8>
0x00424bd4:	xorl %eax, %eax
0x00424bd6:	pushl %ebx
0x00424bd7:	movl -4(%ebp), %eax
0x00424bda:	movl -12(%ebp), %eax
0x00424bdd:	movl -8(%ebp), %eax
0x00424be0:	pushl %ebx
0x00424be1:	pushfl
0x00424be2:	popl %eax
0x00424be3:	movl %ecx, %eax
0x00424be5:	xorl %eax, $0x200000<UINT32>
0x00424bea:	pushl %eax
0x00424beb:	popfl
0x00424bec:	pushfl
0x00424bed:	popl %edx
0x00424bee:	subl %edx, %ecx
0x00424bf0:	je 0x00424c11
0x00424c11:	popl %ebx
0x00424c12:	testl -4(%ebp), $0x4000000<UINT32>
0x00424c19:	je 0x00424c29
0x00424c29:	xorl %eax, %eax
0x00424c2b:	popl %ebx
0x00424c2c:	leave
0x00424c2d:	ret

0x00424c33:	movl 0x449a68, %eax
0x00424c38:	xorl %eax, %eax
0x00424c3a:	ret

0x004271cd:	pushl $0x42718b<UINT32>
0x004271d2:	call SetUnhandledExceptionFilter@kernel32.dll
SetUnhandledExceptionFilter@kernel32.dll: API Node	
0x004271d8:	xorl %eax, %eax
0x004271da:	ret

0x0041e2f1:	popl %esi
0x0041e2f2:	popl %ebp
0x0041e2f3:	ret

0x0041e32f:	popl %ecx
0x0041e330:	popl %ecx
0x0041e331:	testl %eax, %eax
0x0041e333:	jne 66
0x0041e335:	pushl $0x425154<UINT32>
0x0041e33a:	call 0x0041dcb8
0x0041dcb8:	movl %edi, %edi
0x0041dcba:	pushl %ebp
0x0041dcbb:	movl %ebp, %esp
0x0041dcbd:	pushl 0x8(%ebp)
0x0041dcc0:	call 0x0041dc7c
0x0041dc7c:	pushl $0xc<UINT8>
0x0041dc7e:	pushl $0x441340<UINT32>
0x0041dc83:	call 0x0041fad0
0x0041dc88:	call 0x0041e2a1
0x0041e2a1:	pushl $0x8<UINT8>
0x0041e2a3:	call 0x004250fb
0x0041e2a8:	popl %ecx
0x0041e2a9:	ret

0x0041dc8d:	andl -4(%ebp), $0x0<UINT8>
0x0041dc91:	pushl 0x8(%ebp)
0x0041dc94:	call 0x0041db91
0x0041db91:	movl %edi, %edi
0x0041db93:	pushl %ebp
0x0041db94:	movl %ebp, %esp
0x0041db96:	pushl %ecx
0x0041db97:	pushl %ebx
0x0041db98:	pushl %esi
0x0041db99:	pushl %edi
0x0041db9a:	pushl 0x44abb0
0x0041dba0:	call 0x00422c34
0x00422c65:	movl %eax, 0x1fc(%eax)
0x00422c6b:	jmp 0x00422c94
0x0041dba5:	pushl 0x44abac
0x0041dbab:	movl %edi, %eax
0x0041dbad:	movl -4(%ebp), %edi
0x0041dbb0:	call 0x00422c34
0x0041dbb5:	movl %esi, %eax
0x0041dbb7:	popl %ecx
0x0041dbb8:	popl %ecx
0x0041dbb9:	cmpl %esi, %edi
0x0041dbbb:	jb 131
0x0041dbc1:	movl %ebx, %esi
0x0041dbc3:	subl %ebx, %edi
0x0041dbc5:	leal %eax, 0x4(%ebx)
0x0041dbc8:	cmpl %eax, $0x4<UINT8>
0x0041dbcb:	jb 119
0x0041dbcd:	pushl %edi
0x0041dbce:	call 0x00424610
0x00424610:	pushl $0x10<UINT8>
0x00424612:	pushl $0x441628<UINT32>
0x00424617:	call 0x0041fad0
0x0042461c:	xorl %eax, %eax
0x0042461e:	movl %ebx, 0x8(%ebp)
0x00424621:	xorl %edi, %edi
0x00424623:	cmpl %ebx, %edi
0x00424625:	setne %al
0x00424628:	cmpl %eax, %edi
0x0042462a:	jne 0x00424649
0x00424649:	cmpl 0x449a4c, $0x3<UINT8>
0x00424650:	jne 0x0042468a
0x0042468a:	pushl %ebx
0x0042468b:	pushl %edi
0x0042468c:	pushl 0x4486cc
0x00424692:	call HeapSize@kernel32.dll
HeapSize@kernel32.dll: API Node	
0x00424698:	movl %esi, %eax
0x0042469a:	movl %eax, %esi
0x0042469c:	call 0x0041fb15
0x004246a1:	ret

0x0041dbd3:	movl %edi, %eax
0x0041dbd5:	leal %eax, 0x4(%ebx)
0x0041dbd8:	popl %ecx
0x0041dbd9:	cmpl %edi, %eax
0x0041dbdb:	jae 0x0041dc25
0x0041dc25:	pushl 0x8(%ebp)
0x0041dc28:	call 0x00422bb9
0x0041dc2d:	movl (%esi), %eax
0x0041dc2f:	addl %esi, $0x4<UINT8>
0x0041dc32:	pushl %esi
0x0041dc33:	call 0x00422bb9
0x0041dc38:	popl %ecx
0x0041dc39:	movl 0x44abac, %eax
0x0041dc3e:	movl %eax, 0x8(%ebp)
0x0041dc41:	popl %ecx
0x0041dc42:	jmp 0x0041dc46
0x0041dc46:	popl %edi
0x0041dc47:	popl %esi
0x0041dc48:	popl %ebx
0x0041dc49:	leave
0x0041dc4a:	ret

0x0041dc99:	popl %ecx
0x0041dc9a:	movl -28(%ebp), %eax
0x0041dc9d:	movl -4(%ebp), $0xfffffffe<UINT32>
0x0041dca4:	call 0x0041dcb2
0x0041dcb2:	call 0x0041e2aa
0x0041e2aa:	pushl $0x8<UINT8>
0x0041e2ac:	call 0x00425021
0x0041e2b1:	popl %ecx
0x0041e2b2:	ret

0x0041dcb7:	ret

0x0041dca9:	movl %eax, -28(%ebp)
0x0041dcac:	call 0x0041fb15
0x0041dcb1:	ret

0x0041dcc5:	negl %eax
0x0041dcc7:	sbbl %eax, %eax
0x0041dcc9:	negl %eax
0x0041dccb:	popl %ecx
0x0041dccc:	decl %eax
0x0041dccd:	popl %ebp
0x0041dcce:	ret

0x0041e33f:	movl %eax, $0x43b1c4<UINT32>
0x0041e344:	movl (%esp), $0x43b5c0<UINT32>
0x0041e34b:	call 0x0041e2b3
0x0041e2b3:	movl %edi, %edi
0x0041e2b5:	pushl %ebp
0x0041e2b6:	movl %ebp, %esp
0x0041e2b8:	pushl %esi
0x0041e2b9:	movl %esi, %eax
0x0041e2bb:	jmp 0x0041e2c8
0x0041e2c8:	cmpl %esi, 0x8(%ebp)
0x0041e2cb:	jb 0x0041e2bd
0x0041e2bd:	movl %eax, (%esi)
0x0041e2bf:	testl %eax, %eax
0x0041e2c1:	je 0x0041e2c5
0x0041e2c5:	addl %esi, $0x4<UINT8>
0x0041e2c3:	call 0x004390b0
0x00434c90:	pushl %ebp
0x00434c91:	movl %ebp, %esp
0x00434c93:	pushl $0xffffffff<UINT8>
0x00434c95:	pushl $0x433762<UINT32>
0x00434c9a:	movl %eax, %fs:0
0x00434ca0:	pushl %eax
0x00434ca1:	pushl %ebx
0x00434ca2:	pushl %esi
0x00434ca3:	pushl %edi
0x00434ca4:	movl %eax, 0x44609c
0x00434ca9:	xorl %eax, %ebp
0x00434cab:	pushl %eax
0x00434cac:	leal %eax, -12(%ebp)
0x00434caf:	movl %fs:0, %eax
0x00434cb5:	xorl %eax, %eax
0x00434cb7:	movl -4(%ebp), %eax
0x00434cba:	leal %ebx, 0x48(%eax)
0x00434cbd:	movl %esi, $0x4473f8<UINT32>
0x00434cc2:	movb 0x448860, %al
0x00434cc7:	call 0x004034c0
0x004034c0:	movl %eax, 0xc(%esi)
0x004034c3:	addl %eax, %ebx
0x004034c5:	pushl %edi
0x004034c6:	pushl %eax
0x004034c7:	call 0x0041cfd3
0x0041cfd3:	movl %edi, %edi
0x0041cfd5:	pushl %ebp
0x0041cfd6:	movl %ebp, %esp
0x0041cfd8:	popl %ebp
0x0041cfd9:	jmp 0x0041e695
0x0041e695:	movl %edi, %edi
0x0041e697:	pushl %ebp
0x0041e698:	movl %ebp, %esp
0x0041e69a:	subl %esp, $0xc<UINT8>
0x0041e69d:	jmp 0x0041e6ac
0x0041e6ac:	pushl 0x8(%ebp)
0x0041e6af:	call 0x0041ebed
0x0041e6b4:	popl %ecx
0x0041e6b5:	testl %eax, %eax
0x0041e6b7:	je -26
0x0041e6b9:	leave
0x0041e6ba:	ret

0x004034cc:	movl %edi, %eax
0x004034ce:	addl %esp, $0x4<UINT8>
0x004034d1:	testl %edi, %edi
0x004034d3:	je 97
0x004034d5:	cmpl %ebx, 0x4(%esi)
0x004034d8:	jae 0x004034dd
0x004034dd:	cmpl (%esi), $0x0<UINT8>
0x004034e0:	je 0x00403509
0x00403509:	movl %ecx, 0xc(%esi)
0x0040350c:	movl %eax, 0x4(%esi)
0x0040350f:	addl %ecx, %ebx
0x00403511:	movl (%esi), %edi
0x00403513:	movl 0x8(%esi), %ebx
0x00403516:	cmpl %eax, %ecx
0x00403518:	jae 28
0x0040351a:	leal %ebx, (%ebx)
0x00403520:	movl %edx, (%esi)
0x00403522:	movb %cl, 0x448860
0x00403528:	movb (%eax,%edx), %cl
0x0040352b:	movl %edx, 0x8(%esi)
0x0040352e:	addl %edx, 0xc(%esi)
0x00403531:	incl %eax
0x00403532:	cmpl %eax, %edx
0x00403534:	jb 0x00403520
0x00403536:	popl %edi
0x00403537:	ret

0x00434ccc:	movl %eax, 0x4473f8
0x00434cd1:	movl %ecx, 0x4473fc
0x00434cd7:	pushl $0x38<UINT8>
0x00434cd9:	addl %ecx, %eax
0x00434cdb:	pushl $0x43fc10<UINT32>
0x00434ce0:	pushl %ecx
0x00434ce1:	call 0x00420c20
0x00420c20:	pushl %ebp
0x00420c21:	movl %ebp, %esp
0x00420c23:	pushl %edi
0x00420c24:	pushl %esi
0x00420c25:	movl %esi, 0xc(%ebp)
0x00420c28:	movl %ecx, 0x10(%ebp)
0x00420c2b:	movl %edi, 0x8(%ebp)
0x00420c2e:	movl %eax, %ecx
0x00420c30:	movl %edx, %ecx
0x00420c32:	addl %eax, %esi
0x00420c34:	cmpl %edi, %esi
0x00420c36:	jbe 0x00420c40
0x00420c38:	cmpl %edi, %eax
0x00420c3a:	jb 420
0x00420c40:	cmpl %ecx, $0x100<UINT32>
0x00420c46:	jb 0x00420c67
0x00420c67:	testl %edi, $0x3<UINT32>
0x00420c6d:	jne 0x00420c84
0x00420c6f:	shrl %ecx, $0x2<UINT8>
0x00420c72:	andl %edx, $0x3<UINT8>
0x00420c75:	cmpl %ecx, $0x8<UINT8>
0x00420c78:	jb 0x00420ca4
0x00420c7a:	rep movsl %es:(%edi), %ds:(%esi)
0x00420c7c:	jmp 0x00420db8
0x00420da4:	movl %eax, 0x8(%ebp)
0x00420da7:	popl %esi
0x00420da8:	popl %edi
0x00420da9:	leave
0x00420daa:	ret

0x00434ce6:	addl 0x4473fc, $0x38<UINT8>
0x00434ced:	addl %esp, $0xc<UINT8>
0x00434cf0:	xorl %eax, %eax
0x00434cf2:	movl %edi, $0x1<UINT32>
0x00434cf7:	movl -4(%ebp), %edi
0x00434cfa:	movl 0x447408, %eax
0x00434cff:	movl 0x44740c, $0x1582<UINT32>
0x00434d09:	movl 0x447410, %eax
0x00434d0e:	movl 0x447414, %eax
0x00434d13:	movl 0x447418, %eax
0x00434d18:	movl 0x44741c, %eax
0x00434d1d:	movb -4(%ebp), $0x2<UINT8>
0x00434d21:	movl %esi, $0x447414<UINT32>
0x00434d26:	movl 0x447420, %edi
0x00434d2c:	movb 0x448860, %al
0x00434d31:	call 0x004034c0
0x00434d36:	movl %edx, 0x447418
0x00434d3c:	movl %eax, 0x447414
0x00434d41:	pushl $0x38<UINT8>
0x00434d43:	addl %edx, %eax
0x00434d45:	pushl $0x43fbd4<UINT32>
0x00434d4a:	pushl %edx
0x00434d4b:	call 0x00420c20
0x00434d50:	addl 0x447418, $0x38<UINT8>
0x00434d57:	addl %esp, $0xc<UINT8>
0x00434d5a:	xorl %eax, %eax
0x00434d5c:	movl 0x447424, %eax
0x00434d61:	movl 0x447428, $0x1ab5<UINT32>
0x00434d6b:	movl 0x44742c, %edi
0x00434d71:	movl 0x447430, %eax
0x00434d76:	movl 0x447434, %eax
0x00434d7b:	movl 0x447438, %eax
0x00434d80:	movb -4(%ebp), $0x4<UINT8>
0x00434d84:	leal %ebx, 0x3b(%edi)
0x00434d87:	movl %esi, $0x447430<UINT32>
0x00434d8c:	movl 0x44743c, %edi
0x00434d92:	movb 0x448860, %al
0x00434d97:	call 0x004034c0
0x00434d9c:	movl %ecx, 0x447434
0x00434da2:	movl %edx, 0x447430
0x00434da8:	pushl $0x2c<UINT8>
0x00434daa:	addl %ecx, %edx
0x00434dac:	pushl $0x43fba4<UINT32>
0x00434db1:	pushl %ecx
0x00434db2:	call 0x00420c20
0x00434db7:	addl 0x447434, $0x2c<UINT8>
0x00434dbe:	pushl $0x439510<UINT32>
0x00434dc3:	movl 0x447440, $0x0<UINT32>
0x00434dcd:	call 0x0041dcb8
0x00434dd2:	addl %esp, $0x10<UINT8>
0x00434dd5:	movl %ecx, -12(%ebp)
0x00434dd8:	movl %fs:0, %ecx
0x00434ddf:	popl %ecx
0x00434de0:	popl %edi
0x00434de1:	popl %esi
0x00434de2:	popl %ebx
0x00434de3:	movl %esp, %ebp
0x00434de5:	popl %ebp
0x00434de6:	ret

0x00434fb0:	pushl %ebp
0x00434fb1:	movl %ebp, %esp
0x00434fb3:	pushl $0xffffffff<UINT8>
0x00434fb5:	pushl $0x43378a<UINT32>
0x00434fba:	movl %eax, %fs:0
0x00434fc0:	pushl %eax
0x00434fc1:	pushl %ebx
0x00434fc2:	pushl %esi
0x00434fc3:	movl %eax, 0x44609c
0x00434fc8:	xorl %eax, %ebp
0x00434fca:	pushl %eax
0x00434fcb:	leal %eax, -12(%ebp)
0x00434fce:	movl %fs:0, %eax
0x00434fd4:	xorl %eax, %eax
0x00434fd6:	movl -4(%ebp), %eax
0x00434fd9:	leal %ebx, 0x5e(%eax)
0x00434fdc:	movl %esi, $0x447444<UINT32>
0x00434fe1:	movb 0x448860, %al
0x00434fe6:	call 0x004034c0
0x00434feb:	movl %eax, 0x447448
0x00434ff0:	movl %ecx, 0x447444
0x00434ff6:	pushl $0x4e<UINT8>
0x00434ff8:	addl %eax, %ecx
0x00434ffa:	pushl $0x43fc50<UINT32>
0x00434fff:	pushl %eax
0x00435000:	call 0x00420c20
0x00420db8:	movb %al, (%esi)
0x00420dba:	movb (%edi), %al
0x00420dbc:	movb %al, 0x1(%esi)
0x00420dbf:	movb 0x1(%edi), %al
0x00420dc2:	movl %eax, 0x8(%ebp)
0x00420dc5:	popl %esi
0x00420dc6:	popl %edi
0x00420dc7:	leave
0x00420dc8:	ret

0x00435005:	addl 0x447448, $0x4e<UINT8>
0x0043500c:	pushl $0x4395f0<UINT32>
0x00435011:	call 0x0041dcb8
0x00435016:	addl %esp, $0x10<UINT8>
0x00435019:	movl %ecx, -12(%ebp)
0x0043501c:	movl %fs:0, %ecx
0x00435023:	popl %ecx
0x00435024:	popl %esi
0x00435025:	popl %ebx
0x00435026:	movl %esp, %ebp
0x00435028:	popl %ebp
0x00435029:	ret

0x00435830:	pushl %ebp
0x00435831:	movl %ebp, %esp
0x00435833:	pushl $0xffffffff<UINT8>
0x00435835:	pushl $0x4337ba<UINT32>
0x0043583a:	movl %eax, %fs:0
0x00435840:	pushl %eax
0x00435841:	pushl %ebx
0x00435842:	pushl %esi
0x00435843:	movl %eax, 0x44609c
0x00435848:	xorl %eax, %ebp
0x0043584a:	pushl %eax
0x0043584b:	leal %eax, -12(%ebp)
0x0043584e:	movl %fs:0, %eax
0x00435854:	xorl %eax, %eax
0x00435856:	movl -4(%ebp), %eax
0x00435859:	leal %ebx, 0x48(%eax)
0x0043585c:	movl %esi, $0x447454<UINT32>
0x00435861:	movb 0x448860, %al
0x00435866:	call 0x004034c0
0x0043586b:	movl %eax, 0x447458
0x00435870:	movl %ecx, 0x447454
0x00435876:	pushl $0x38<UINT8>
0x00435878:	addl %eax, %ecx
0x0043587a:	pushl $0x43fca0<UINT32>
0x0043587f:	pushl %eax
0x00435880:	call 0x00420c20
0x00435885:	addl 0x447458, $0x38<UINT8>
0x0043588c:	pushl $0x4399f0<UINT32>
0x00435891:	call 0x0041dcb8
0x00435896:	addl %esp, $0x10<UINT8>
0x00435899:	movl %ecx, -12(%ebp)
0x0043589c:	movl %fs:0, %ecx
0x004358a3:	popl %ecx
0x004358a4:	popl %esi
0x004358a5:	popl %ebx
0x004358a6:	movl %esp, %ebp
0x004358a8:	popl %ebp
0x004358a9:	ret

0x004357b0:	pushl %ebp
0x004357b1:	movl %ebp, %esp
0x004357b3:	pushl $0xffffffff<UINT8>
0x004357b5:	pushl $0x4337ea<UINT32>
0x004357ba:	movl %eax, %fs:0
0x004357c0:	pushl %eax
0x004357c1:	pushl %ebx
0x004357c2:	pushl %esi
0x004357c3:	movl %eax, 0x44609c
0x004357c8:	xorl %eax, %ebp
0x004357ca:	pushl %eax
0x004357cb:	leal %eax, -12(%ebp)
0x004357ce:	movl %fs:0, %eax
0x004357d4:	xorl %eax, %eax
0x004357d6:	movl -4(%ebp), %eax
0x004357d9:	leal %ebx, 0x48(%eax)
0x004357dc:	movl %esi, $0x447464<UINT32>
0x004357e1:	movb 0x448860, %al
0x004357e6:	call 0x004034c0
0x004357eb:	movl %eax, 0x447468
0x004357f0:	movl %ecx, 0x447464
0x004357f6:	pushl $0x38<UINT8>
0x004357f8:	addl %eax, %ecx
0x004357fa:	pushl $0x43fcdc<UINT32>
0x004357ff:	pushl %eax
0x00435800:	call 0x00420c20
0x00435805:	addl 0x447468, $0x38<UINT8>
0x0043580c:	pushl $0x4399b0<UINT32>
0x00435811:	call 0x0041dcb8
0x00435816:	addl %esp, $0x10<UINT8>
0x00435819:	movl %ecx, -12(%ebp)
0x0043581c:	movl %fs:0, %ecx
0x00435823:	popl %ecx
0x00435824:	popl %esi
0x00435825:	popl %ebx
0x00435826:	movl %esp, %ebp
0x00435828:	popl %ebp
0x00435829:	ret

0x00435730:	pushl %ebp
0x00435731:	movl %ebp, %esp
0x00435733:	pushl $0xffffffff<UINT8>
0x00435735:	pushl $0x43381a<UINT32>
0x0043573a:	movl %eax, %fs:0
0x00435740:	pushl %eax
0x00435741:	pushl %ebx
0x00435742:	pushl %esi
0x00435743:	movl %eax, 0x44609c
0x00435748:	xorl %eax, %ebp
0x0043574a:	pushl %eax
0x0043574b:	leal %eax, -12(%ebp)
0x0043574e:	movl %fs:0, %eax
0x00435754:	xorl %eax, %eax
0x00435756:	movl -4(%ebp), %eax
0x00435759:	leal %ebx, 0x48(%eax)
0x0043575c:	movl %esi, $0x447474<UINT32>
0x00435761:	movb 0x448860, %al
0x00435766:	call 0x004034c0
0x0043576b:	movl %eax, 0x447478
0x00435770:	movl %ecx, 0x447474
0x00435776:	pushl $0x38<UINT8>
0x00435778:	addl %eax, %ecx
0x0043577a:	pushl $0x43fd18<UINT32>
0x0043577f:	pushl %eax
0x00435780:	call 0x00420c20
0x00435785:	addl 0x447478, $0x38<UINT8>
0x0043578c:	pushl $0x439970<UINT32>
0x00435791:	call 0x0041dcb8
0x00435796:	addl %esp, $0x10<UINT8>
0x00435799:	movl %ecx, -12(%ebp)
0x0043579c:	movl %fs:0, %ecx
0x004357a3:	popl %ecx
0x004357a4:	popl %esi
0x004357a5:	popl %ebx
0x004357a6:	movl %esp, %ebp
0x004357a8:	popl %ebp
0x004357a9:	ret

0x004356b0:	pushl %ebp
0x004356b1:	movl %ebp, %esp
0x004356b3:	pushl $0xffffffff<UINT8>
0x004356b5:	pushl $0x43384a<UINT32>
0x004356ba:	movl %eax, %fs:0
0x004356c0:	pushl %eax
0x004356c1:	pushl %ebx
0x004356c2:	pushl %esi
0x004356c3:	movl %eax, 0x44609c
0x004356c8:	xorl %eax, %ebp
0x004356ca:	pushl %eax
0x004356cb:	leal %eax, -12(%ebp)
0x004356ce:	movl %fs:0, %eax
0x004356d4:	xorl %eax, %eax
0x004356d6:	movl -4(%ebp), %eax
0x004356d9:	leal %ebx, 0x48(%eax)
0x004356dc:	movl %esi, $0x447484<UINT32>
0x004356e1:	movb 0x448860, %al
0x004356e6:	call 0x004034c0
0x004356eb:	movl %eax, 0x447488
0x004356f0:	movl %ecx, 0x447484
0x004356f6:	pushl $0x38<UINT8>
0x004356f8:	addl %eax, %ecx
0x004356fa:	pushl $0x43fd54<UINT32>
0x004356ff:	pushl %eax
0x00435700:	call 0x00420c20
0x00435705:	addl 0x447488, $0x38<UINT8>
0x0043570c:	pushl $0x439930<UINT32>
0x00435711:	call 0x0041dcb8
0x00435716:	addl %esp, $0x10<UINT8>
0x00435719:	movl %ecx, -12(%ebp)
0x0043571c:	movl %fs:0, %ecx
0x00435723:	popl %ecx
0x00435724:	popl %esi
0x00435725:	popl %ebx
0x00435726:	movl %esp, %ebp
0x00435728:	popl %ebp
0x00435729:	ret

0x00435630:	pushl %ebp
0x00435631:	movl %ebp, %esp
0x00435633:	pushl $0xffffffff<UINT8>
0x00435635:	pushl $0x43387a<UINT32>
0x0043563a:	movl %eax, %fs:0
0x00435640:	pushl %eax
0x00435641:	pushl %ebx
0x00435642:	pushl %esi
0x00435643:	movl %eax, 0x44609c
0x00435648:	xorl %eax, %ebp
0x0043564a:	pushl %eax
0x0043564b:	leal %eax, -12(%ebp)
0x0043564e:	movl %fs:0, %eax
0x00435654:	xorl %eax, %eax
0x00435656:	movl -4(%ebp), %eax
0x00435659:	leal %ebx, 0x48(%eax)
0x0043565c:	movl %esi, $0x447494<UINT32>
0x00435661:	movb 0x448860, %al
0x00435666:	call 0x004034c0
0x0043566b:	movl %eax, 0x447498
0x00435670:	movl %ecx, 0x447494
0x00435676:	pushl $0x38<UINT8>
0x00435678:	addl %eax, %ecx
0x0043567a:	pushl $0x43fd90<UINT32>
0x0043567f:	pushl %eax
0x00435680:	call 0x00420c20
0x00435685:	addl 0x447498, $0x38<UINT8>
0x0043568c:	pushl $0x4398f0<UINT32>
0x00435691:	call 0x0041dcb8
0x00435696:	addl %esp, $0x10<UINT8>
0x00435699:	movl %ecx, -12(%ebp)
0x0043569c:	movl %fs:0, %ecx
0x004356a3:	popl %ecx
0x004356a4:	popl %esi
0x004356a5:	popl %ebx
0x004356a6:	movl %esp, %ebp
0x004356a8:	popl %ebp
0x004356a9:	ret

0x004355b0:	pushl %ebp
0x004355b1:	movl %ebp, %esp
0x004355b3:	pushl $0xffffffff<UINT8>
0x004355b5:	pushl $0x4338aa<UINT32>
0x004355ba:	movl %eax, %fs:0
0x004355c0:	pushl %eax
0x004355c1:	pushl %ebx
0x004355c2:	pushl %esi
0x004355c3:	movl %eax, 0x44609c
0x004355c8:	xorl %eax, %ebp
0x004355ca:	pushl %eax
0x004355cb:	leal %eax, -12(%ebp)
0x004355ce:	movl %fs:0, %eax
0x004355d4:	xorl %eax, %eax
0x004355d6:	movl -4(%ebp), %eax
0x004355d9:	leal %ebx, 0x48(%eax)
0x004355dc:	movl %esi, $0x4474a4<UINT32>
0x004355e1:	movb 0x448860, %al
0x004355e6:	call 0x004034c0
0x004355eb:	movl %eax, 0x4474a8
0x004355f0:	movl %ecx, 0x4474a4
0x004355f6:	pushl $0x38<UINT8>
0x004355f8:	addl %eax, %ecx
0x004355fa:	pushl $0x43fdcc<UINT32>
0x004355ff:	pushl %eax
0x00435600:	call 0x00420c20
0x00435605:	addl 0x4474a8, $0x38<UINT8>
0x0043560c:	pushl $0x4398b0<UINT32>
0x00435611:	call 0x0041dcb8
0x00435616:	addl %esp, $0x10<UINT8>
0x00435619:	movl %ecx, -12(%ebp)
0x0043561c:	movl %fs:0, %ecx
0x00435623:	popl %ecx
0x00435624:	popl %esi
0x00435625:	popl %ebx
0x00435626:	movl %esp, %ebp
0x00435628:	popl %ebp
0x00435629:	ret

0x00435530:	pushl %ebp
0x00435531:	movl %ebp, %esp
0x00435533:	pushl $0xffffffff<UINT8>
0x00435535:	pushl $0x4338da<UINT32>
0x0043553a:	movl %eax, %fs:0
0x00435540:	pushl %eax
0x00435541:	pushl %ebx
0x00435542:	pushl %esi
0x00435543:	movl %eax, 0x44609c
0x00435548:	xorl %eax, %ebp
0x0043554a:	pushl %eax
0x0043554b:	leal %eax, -12(%ebp)
0x0043554e:	movl %fs:0, %eax
0x00435554:	xorl %eax, %eax
0x00435556:	movl -4(%ebp), %eax
0x00435559:	leal %ebx, 0x48(%eax)
0x0043555c:	movl %esi, $0x4474b4<UINT32>
0x00435561:	movb 0x448860, %al
0x00435566:	call 0x004034c0
0x0043556b:	movl %eax, 0x4474b8
0x00435570:	movl %ecx, 0x4474b4
0x00435576:	pushl $0x38<UINT8>
0x00435578:	addl %eax, %ecx
0x0043557a:	pushl $0x43fe08<UINT32>
0x0043557f:	pushl %eax
0x00435580:	call 0x00420c20
0x00435585:	addl 0x4474b8, $0x38<UINT8>
0x0043558c:	pushl $0x439870<UINT32>
0x00435591:	call 0x0041dcb8
0x00435596:	addl %esp, $0x10<UINT8>
0x00435599:	movl %ecx, -12(%ebp)
0x0043559c:	movl %fs:0, %ecx
0x004355a3:	popl %ecx
0x004355a4:	popl %esi
0x004355a5:	popl %ebx
0x004355a6:	movl %esp, %ebp
0x004355a8:	popl %ebp
0x004355a9:	ret

0x004354b0:	pushl %ebp
0x004354b1:	movl %ebp, %esp
0x004354b3:	pushl $0xffffffff<UINT8>
0x004354b5:	pushl $0x43390a<UINT32>
0x004354ba:	movl %eax, %fs:0
0x004354c0:	pushl %eax
0x004354c1:	pushl %ebx
0x004354c2:	pushl %esi
0x004354c3:	movl %eax, 0x44609c
0x004354c8:	xorl %eax, %ebp
0x004354ca:	pushl %eax
0x004354cb:	leal %eax, -12(%ebp)
0x004354ce:	movl %fs:0, %eax
0x004354d4:	xorl %eax, %eax
0x004354d6:	movl -4(%ebp), %eax
0x004354d9:	leal %ebx, 0x48(%eax)
0x004354dc:	movl %esi, $0x4474c4<UINT32>
0x004354e1:	movb 0x448860, %al
0x004354e6:	call 0x004034c0
0x004354eb:	movl %eax, 0x4474c8
0x004354f0:	movl %ecx, 0x4474c4
0x004354f6:	pushl $0x38<UINT8>
0x004354f8:	addl %eax, %ecx
0x004354fa:	pushl $0x43fe44<UINT32>
0x004354ff:	pushl %eax
0x00435500:	call 0x00420c20
0x00435505:	addl 0x4474c8, $0x38<UINT8>
0x0043550c:	pushl $0x439830<UINT32>
0x00435511:	call 0x0041dcb8
0x00435516:	addl %esp, $0x10<UINT8>
0x00435519:	movl %ecx, -12(%ebp)
0x0043551c:	movl %fs:0, %ecx
0x00435523:	popl %ecx
0x00435524:	popl %esi
0x00435525:	popl %ebx
0x00435526:	movl %esp, %ebp
0x00435528:	popl %ebp
0x00435529:	ret

0x00435430:	pushl %ebp
0x00435431:	movl %ebp, %esp
0x00435433:	pushl $0xffffffff<UINT8>
0x00435435:	pushl $0x43393a<UINT32>
0x0043543a:	movl %eax, %fs:0
0x00435440:	pushl %eax
0x00435441:	pushl %ebx
0x00435442:	pushl %esi
0x00435443:	movl %eax, 0x44609c
0x00435448:	xorl %eax, %ebp
0x0043544a:	pushl %eax
0x0043544b:	leal %eax, -12(%ebp)
0x0043544e:	movl %fs:0, %eax
0x00435454:	xorl %eax, %eax
0x00435456:	movl -4(%ebp), %eax
0x00435459:	leal %ebx, 0x48(%eax)
0x0043545c:	movl %esi, $0x4474d4<UINT32>
0x00435461:	movb 0x448860, %al
0x00435466:	call 0x004034c0
0x0043546b:	movl %eax, 0x4474d8
0x00435470:	movl %ecx, 0x4474d4
0x00435476:	pushl $0x38<UINT8>
0x00435478:	addl %eax, %ecx
0x0043547a:	pushl $0x43fe80<UINT32>
0x0043547f:	pushl %eax
0x00435480:	call 0x00420c20
0x00435485:	addl 0x4474d8, $0x38<UINT8>
0x0043548c:	pushl $0x4397f0<UINT32>
0x00435491:	call 0x0041dcb8
0x00435496:	addl %esp, $0x10<UINT8>
0x00435499:	movl %ecx, -12(%ebp)
0x0043549c:	movl %fs:0, %ecx
0x004354a3:	popl %ecx
0x004354a4:	popl %esi
0x004354a5:	popl %ebx
0x004354a6:	movl %esp, %ebp
0x004354a8:	popl %ebp
0x004354a9:	ret

0x004350b0:	pushl %ebp
0x004350b1:	movl %ebp, %esp
0x004350b3:	pushl $0xffffffff<UINT8>
0x004350b5:	pushl $0x43396a<UINT32>
0x004350ba:	movl %eax, %fs:0
0x004350c0:	pushl %eax
0x004350c1:	pushl %ebx
0x004350c2:	pushl %esi
0x004350c3:	movl %eax, 0x44609c
0x004350c8:	xorl %eax, %ebp
0x004350ca:	pushl %eax
0x004350cb:	leal %eax, -12(%ebp)
0x004350ce:	movl %fs:0, %eax
0x004350d4:	xorl %eax, %eax
0x004350d6:	movl -4(%ebp), %eax
0x004350d9:	leal %ebx, 0x17(%eax)
0x004350dc:	movl %esi, $0x4474e4<UINT32>
0x004350e1:	movb 0x448860, %al
0x004350e6:	call 0x004034c0
0x004350eb:	movl %eax, 0x4474e8
0x004350f0:	movl %ecx, 0x4474e4
0x004350f6:	movl %edx, 0x43febc
0x004350fc:	addl %eax, %ecx
0x004350fe:	movl (%eax), %edx
0x00435100:	movw %cx, 0x43fec0
0x00435107:	movw 0x4(%eax), %cx
0x0043510b:	movb %dl, 0x43fec2
0x00435111:	movb 0x6(%eax), %dl
0x00435114:	addl 0x4474e8, $0x7<UINT8>
0x0043511b:	pushl $0x439670<UINT32>
0x00435120:	call 0x0041dcb8
0x00435125:	addl %esp, $0x4<UINT8>
0x00435128:	movl %ecx, -12(%ebp)
0x0043512b:	movl %fs:0, %ecx
0x00435132:	popl %ecx
0x00435133:	popl %esi
0x00435134:	popl %ebx
0x00435135:	movl %esp, %ebp
0x00435137:	popl %ebp
0x00435138:	ret

0x004353a0:	pushl %ebp
0x004353a1:	movl %ebp, %esp
0x004353a3:	pushl $0xffffffff<UINT8>
0x004353a5:	pushl $0x43399a<UINT32>
0x004353aa:	movl %eax, %fs:0
0x004353b0:	pushl %eax
0x004353b1:	pushl %ebx
0x004353b2:	pushl %esi
0x004353b3:	movl %eax, 0x44609c
0x004353b8:	xorl %eax, %ebp
0x004353ba:	pushl %eax
0x004353bb:	leal %eax, -12(%ebp)
0x004353be:	movl %fs:0, %eax
0x004353c4:	xorl %eax, %eax
0x004353c6:	movl -4(%ebp), %eax
0x004353c9:	leal %ebx, 0x1c(%eax)
0x004353cc:	movl %esi, $0x4474f4<UINT32>
0x004353d1:	movb 0x448860, %al
0x004353d6:	call 0x004034c0
0x004353db:	movl %eax, 0x4474f8
0x004353e0:	movl %ecx, 0x4474f4
0x004353e6:	movl %edx, 0x43fec4
0x004353ec:	addl %eax, %ecx
0x004353ee:	movl (%eax), %edx
0x004353f0:	movl %ecx, 0x43fec8
0x004353f6:	movl 0x4(%eax), %ecx
0x004353f9:	movl %edx, 0x43fecc
0x004353ff:	movl 0x8(%eax), %edx
0x00435402:	addl 0x4474f8, $0xc<UINT8>
0x00435409:	pushl $0x4397b0<UINT32>
0x0043540e:	call 0x0041dcb8
0x00435413:	addl %esp, $0x4<UINT8>
0x00435416:	movl %ecx, -12(%ebp)
0x00435419:	movl %fs:0, %ecx
0x00435420:	popl %ecx
0x00435421:	popl %esi
0x00435422:	popl %ebx
0x00435423:	movl %esp, %ebp
0x00435425:	popl %ebp
0x00435426:	ret

0x004351c0:	pushl %ebp
0x004351c1:	movl %ebp, %esp
0x004351c3:	pushl $0xffffffff<UINT8>
0x004351c5:	pushl $0x4339ca<UINT32>
0x004351ca:	movl %eax, %fs:0
0x004351d0:	pushl %eax
0x004351d1:	pushl %ebx
0x004351d2:	pushl %esi
0x004351d3:	movl %eax, 0x44609c
0x004351d8:	xorl %eax, %ebp
0x004351da:	pushl %eax
0x004351db:	leal %eax, -12(%ebp)
0x004351de:	movl %fs:0, %eax
0x004351e4:	xorl %eax, %eax
0x004351e6:	movl -4(%ebp), %eax
0x004351e9:	leal %ebx, 0x1a(%eax)
0x004351ec:	movl %esi, $0x447504<UINT32>
0x004351f1:	movb 0x448860, %al
0x004351f6:	call 0x004034c0
0x004351fb:	movl %eax, 0x447508
0x00435200:	movl %ecx, 0x447504
0x00435206:	movl %edx, 0x43fed4
0x0043520c:	addl %eax, %ecx
0x0043520e:	movl (%eax), %edx
0x00435210:	movl %ecx, 0x43fed8
0x00435216:	movl 0x4(%eax), %ecx
0x00435219:	movw %dx, 0x43fedc
0x00435220:	movw 0x8(%eax), %dx
0x00435224:	addl 0x447508, $0xa<UINT8>
0x0043522b:	pushl $0x4396f0<UINT32>
0x00435230:	call 0x0041dcb8
0x00435235:	addl %esp, $0x4<UINT8>
0x00435238:	movl %ecx, -12(%ebp)
0x0043523b:	movl %fs:0, %ecx
0x00435242:	popl %ecx
0x00435243:	popl %esi
0x00435244:	popl %ebx
0x00435245:	movl %esp, %ebp
0x00435247:	popl %ebp
0x00435248:	ret

0x00435030:	pushl %ebp
0x00435031:	movl %ebp, %esp
0x00435033:	pushl $0xffffffff<UINT8>
0x00435035:	pushl $0x4339fa<UINT32>
0x0043503a:	movl %eax, %fs:0
0x00435040:	pushl %eax
0x00435041:	pushl %ebx
0x00435042:	pushl %esi
0x00435043:	movl %eax, 0x44609c
0x00435048:	xorl %eax, %ebp
0x0043504a:	pushl %eax
0x0043504b:	leal %eax, -12(%ebp)
0x0043504e:	movl %fs:0, %eax
0x00435054:	xorl %eax, %eax
0x00435056:	movl -4(%ebp), %eax
0x00435059:	leal %ebx, 0x18(%eax)
0x0043505c:	movl %esi, $0x447514<UINT32>
0x00435061:	movb 0x448860, %al
0x00435066:	call 0x004034c0
0x0043506b:	movl %eax, 0x447518
0x00435070:	movl %ecx, 0x447514
0x00435076:	movl %edx, 0x43fee0
0x0043507c:	addl %eax, %ecx
0x0043507e:	movl (%eax), %edx
0x00435080:	movl %ecx, 0x43fee4
0x00435086:	movl 0x4(%eax), %ecx
0x00435089:	addl 0x447518, $0x8<UINT8>
0x00435090:	pushl $0x439630<UINT32>
0x00435095:	call 0x0041dcb8
0x0043509a:	addl %esp, $0x4<UINT8>
0x0043509d:	movl %ecx, -12(%ebp)
0x004350a0:	movl %fs:0, %ecx
0x004350a7:	popl %ecx
0x004350a8:	popl %esi
0x004350a9:	popl %ebx
0x004350aa:	movl %esp, %ebp
0x004350ac:	popl %ebp
0x004350ad:	ret

0x00435140:	pushl %ebp
0x00435141:	movl %ebp, %esp
0x00435143:	pushl $0xffffffff<UINT8>
0x00435145:	pushl $0x433a2a<UINT32>
0x0043514a:	movl %eax, %fs:0
0x00435150:	pushl %eax
0x00435151:	pushl %ebx
0x00435152:	pushl %esi
0x00435153:	movl %eax, 0x44609c
0x00435158:	xorl %eax, %ebp
0x0043515a:	pushl %eax
0x0043515b:	leal %eax, -12(%ebp)
0x0043515e:	movl %fs:0, %eax
0x00435164:	xorl %eax, %eax
0x00435166:	movl -4(%ebp), %eax
0x00435169:	leal %ebx, 0x16(%eax)
0x0043516c:	movl %esi, $0x447524<UINT32>
0x00435171:	movb 0x448860, %al
0x00435176:	call 0x004034c0
0x0043517b:	movl %eax, 0x447528
0x00435180:	movl %ecx, 0x447524
0x00435186:	movl %edx, 0x43feec
0x0043518c:	addl %eax, %ecx
0x0043518e:	movl (%eax), %edx
0x00435190:	movw %cx, 0x43fef0
0x00435197:	movw 0x4(%eax), %cx
0x0043519b:	addl 0x447528, $0x6<UINT8>
0x004351a2:	pushl $0x4396b0<UINT32>
0x004351a7:	call 0x0041dcb8
0x004351ac:	addl %esp, $0x4<UINT8>
0x004351af:	movl %ecx, -12(%ebp)
0x004351b2:	movl %fs:0, %ecx
0x004351b9:	popl %ecx
0x004351ba:	popl %esi
0x004351bb:	popl %ebx
0x004351bc:	movl %esp, %ebp
0x004351be:	popl %ebp
0x004351bf:	ret

0x00434df0:	pushl %ebp
0x00434df1:	movl %ebp, %esp
0x00434df3:	pushl $0xffffffff<UINT8>
0x00434df5:	pushl $0x433a5a<UINT32>
0x00434dfa:	movl %eax, %fs:0
0x00434e00:	pushl %eax
0x00434e01:	pushl %ebx
0x00434e02:	pushl %esi
0x00434e03:	movl %eax, 0x44609c
0x00434e08:	xorl %eax, %ebp
0x00434e0a:	pushl %eax
0x00434e0b:	leal %eax, -12(%ebp)
0x00434e0e:	movl %fs:0, %eax
0x00434e14:	xorl %eax, %eax
0x00434e16:	movl -4(%ebp), %eax
0x00434e19:	leal %ebx, 0x1b(%eax)
0x00434e1c:	movl %esi, $0x447534<UINT32>
0x00434e21:	movb 0x448860, %al
0x00434e26:	call 0x004034c0
0x00434e2b:	movl %eax, 0x447538
0x00434e30:	movl %ecx, 0x447534
0x00434e36:	movl %edx, 0x43fef4
0x00434e3c:	addl %eax, %ecx
0x00434e3e:	movl (%eax), %edx
0x00434e40:	movl %ecx, 0x43fef8
0x00434e46:	movl 0x4(%eax), %ecx
0x00434e49:	movw %dx, 0x43fefc
0x00434e50:	movw 0x8(%eax), %dx
0x00434e54:	movb %cl, 0x43fefe
0x00434e5a:	movb 0xa(%eax), %cl
0x00434e5d:	addl 0x447538, $0xb<UINT8>
0x00434e64:	pushl $0x439530<UINT32>
0x00434e69:	call 0x0041dcb8
0x00434e6e:	addl %esp, $0x4<UINT8>
0x00434e71:	movl %ecx, -12(%ebp)
0x00434e74:	movl %fs:0, %ecx
0x00434e7b:	popl %ecx
0x00434e7c:	popl %esi
0x00434e7d:	popl %ebx
0x00434e7e:	movl %esp, %ebp
0x00434e80:	popl %ebp
0x00434e81:	ret

0x00435250:	pushl %ebp
0x00435251:	movl %ebp, %esp
0x00435253:	pushl $0xffffffff<UINT8>
0x00435255:	pushl $0x433a8a<UINT32>
0x0043525a:	movl %eax, %fs:0
0x00435260:	pushl %eax
0x00435261:	pushl %ebx
0x00435262:	pushl %esi
0x00435263:	movl %eax, 0x44609c
0x00435268:	xorl %eax, %ebp
0x0043526a:	pushl %eax
0x0043526b:	leal %eax, -12(%ebp)
0x0043526e:	movl %fs:0, %eax
0x00435274:	xorl %eax, %eax
0x00435276:	movl -4(%ebp), %eax
0x00435279:	leal %ebx, 0x23(%eax)
0x0043527c:	movl %esi, $0x447544<UINT32>
0x00435281:	movb 0x448860, %al
0x00435286:	call 0x004034c0
0x0043528b:	movl %ecx, 0x447548
0x00435291:	movl %eax, 0x447544
0x00435296:	movl %edx, 0x43ff00
0x0043529c:	addl %eax, %ecx
0x0043529e:	movl (%eax), %edx
0x004352a0:	movl %ecx, 0x43ff04
0x004352a6:	movl 0x4(%eax), %ecx
0x004352a9:	movl %edx, 0x43ff08
0x004352af:	movl 0x8(%eax), %edx
0x004352b2:	movl %ecx, 0x43ff0c
0x004352b8:	movl 0xc(%eax), %ecx
0x004352bb:	movw %dx, 0x43ff10
0x004352c2:	movw 0x10(%eax), %dx
0x004352c6:	movb %cl, 0x43ff12
0x004352cc:	movb 0x12(%eax), %cl
0x004352cf:	addl 0x447548, $0x13<UINT8>
0x004352d6:	pushl $0x439730<UINT32>
0x004352db:	call 0x0041dcb8
0x004352e0:	addl %esp, $0x4<UINT8>
0x004352e3:	movl %ecx, -12(%ebp)
0x004352e6:	movl %fs:0, %ecx
0x004352ed:	popl %ecx
0x004352ee:	popl %esi
0x004352ef:	popl %ebx
0x004352f0:	movl %esp, %ebp
0x004352f2:	popl %ebp
0x004352f3:	ret

0x00435300:	pushl %ebp
0x00435301:	movl %ebp, %esp
0x00435303:	pushl $0xffffffff<UINT8>
0x00435305:	pushl $0x433aba<UINT32>
0x0043530a:	movl %eax, %fs:0
0x00435310:	pushl %eax
0x00435311:	pushl %ebx
0x00435312:	pushl %esi
0x00435313:	movl %eax, 0x44609c
0x00435318:	xorl %eax, %ebp
0x0043531a:	pushl %eax
0x0043531b:	leal %eax, -12(%ebp)
0x0043531e:	movl %fs:0, %eax
0x00435324:	xorl %eax, %eax
0x00435326:	movl -4(%ebp), %eax
0x00435329:	leal %ebx, 0x1e(%eax)
0x0043532c:	movl %esi, $0x447554<UINT32>
0x00435331:	movb 0x448860, %al
0x00435336:	call 0x004034c0
0x0043533b:	movl %eax, 0x447558
0x00435340:	movl %ecx, 0x447554
0x00435346:	movl %edx, 0x43ff14
0x0043534c:	addl %eax, %ecx
0x0043534e:	movl (%eax), %edx
0x00435350:	movl %ecx, 0x43ff18
0x00435356:	movl 0x4(%eax), %ecx
0x00435359:	movl %edx, 0x43ff1c
0x0043535f:	movl 0x8(%eax), %edx
0x00435362:	movw %cx, 0x43ff20
0x00435369:	movw 0xc(%eax), %cx
0x0043536d:	addl 0x447558, $0xe<UINT8>
0x00435374:	pushl $0x439770<UINT32>
0x00435379:	call 0x0041dcb8
0x0043537e:	addl %esp, $0x4<UINT8>
0x00435381:	movl %ecx, -12(%ebp)
0x00435384:	movl %fs:0, %ecx
0x0043538b:	popl %ecx
0x0043538c:	popl %esi
0x0043538d:	popl %ebx
0x0043538e:	movl %esp, %ebp
0x00435390:	popl %ebp
0x00435391:	ret

0x00434e90:	pushl %ebp
0x00434e91:	movl %ebp, %esp
0x00434e93:	pushl $0xffffffff<UINT8>
0x00434e95:	pushl $0x433aea<UINT32>
0x00434e9a:	movl %eax, %fs:0
0x00434ea0:	pushl %eax
0x00434ea1:	pushl %ebx
0x00434ea2:	pushl %esi
0x00434ea3:	movl %eax, 0x44609c
0x00434ea8:	xorl %eax, %ebp
0x00434eaa:	pushl %eax
0x00434eab:	leal %eax, -12(%ebp)
0x00434eae:	movl %fs:0, %eax
0x00434eb4:	xorl %eax, %eax
0x00434eb6:	movl -4(%ebp), %eax
0x00434eb9:	leal %ebx, 0x18(%eax)
0x00434ebc:	movl %esi, $0x447564<UINT32>
0x00434ec1:	movb 0x448860, %al
0x00434ec6:	call 0x004034c0
0x00434ecb:	movl %eax, 0x447568
0x00434ed0:	movl %ecx, 0x447564
0x00434ed6:	movl %edx, 0x43ff24
0x00434edc:	addl %eax, %ecx
0x00434ede:	movl (%eax), %edx
0x00434ee0:	movl %ecx, 0x43ff28
0x00434ee6:	movl 0x4(%eax), %ecx
0x00434ee9:	addl 0x447568, $0x8<UINT8>
0x00434ef0:	pushl $0x439570<UINT32>
0x00434ef5:	call 0x0041dcb8
0x00434efa:	addl %esp, $0x4<UINT8>
0x00434efd:	movl %ecx, -12(%ebp)
0x00434f00:	movl %fs:0, %ecx
0x00434f07:	popl %ecx
0x00434f08:	popl %esi
0x00434f09:	popl %ebx
0x00434f0a:	movl %esp, %ebp
0x00434f0c:	popl %ebp
0x00434f0d:	ret

0x00434f10:	pushl %ebp
0x00434f11:	movl %ebp, %esp
0x00434f13:	pushl $0xffffffff<UINT8>
0x00434f15:	pushl $0x433b1a<UINT32>
0x00434f1a:	movl %eax, %fs:0
0x00434f20:	pushl %eax
0x00434f21:	pushl %ebx
0x00434f22:	pushl %esi
0x00434f23:	movl %eax, 0x44609c
0x00434f28:	xorl %eax, %ebp
0x00434f2a:	pushl %eax
0x00434f2b:	leal %eax, -12(%ebp)
0x00434f2e:	movl %fs:0, %eax
0x00434f34:	xorl %eax, %eax
0x00434f36:	movl -4(%ebp), %eax
0x00434f39:	leal %ebx, 0x1b(%eax)
0x00434f3c:	movl %esi, $0x447574<UINT32>
0x00434f41:	movb 0x448860, %al
0x00434f46:	call 0x004034c0
0x00434f4b:	movl %eax, 0x447578
0x00434f50:	movl %ecx, 0x447574
0x00434f56:	movl %edx, 0x43ff30
0x00434f5c:	addl %eax, %ecx
0x00434f5e:	movl (%eax), %edx
0x00434f60:	movl %ecx, 0x43ff34
0x00434f66:	movl 0x4(%eax), %ecx
0x00434f69:	movw %dx, 0x43ff38
0x00434f70:	movw 0x8(%eax), %dx
0x00434f74:	movb %cl, 0x43ff3a
0x00434f7a:	movb 0xa(%eax), %cl
0x00434f7d:	addl 0x447578, $0xb<UINT8>
0x00434f84:	pushl $0x4395b0<UINT32>
0x00434f89:	call 0x0041dcb8
0x00434f8e:	addl %esp, $0x4<UINT8>
0x00434f91:	movl %ecx, -12(%ebp)
0x00434f94:	movl %fs:0, %ecx
0x00434f9b:	popl %ecx
0x00434f9c:	popl %esi
0x00434f9d:	popl %ebx
0x00434f9e:	movl %esp, %ebp
0x00434fa0:	popl %ebp
0x00434fa1:	ret

0x00435ba0:	pushl %ebp
0x00435ba1:	movl %ebp, %esp
0x00435ba3:	pushl $0xffffffff<UINT8>
0x00435ba5:	pushl $0x433b72<UINT32>
0x00435baa:	movl %eax, %fs:0
0x00435bb0:	pushl %eax
0x00435bb1:	pushl %ebx
0x00435bb2:	pushl %esi
0x00435bb3:	pushl %edi
0x00435bb4:	movl %eax, 0x44609c
0x00435bb9:	xorl %eax, %ebp
0x00435bbb:	pushl %eax
0x00435bbc:	leal %eax, -12(%ebp)
0x00435bbf:	movl %fs:0, %eax
0x00435bc5:	xorl %eax, %eax
0x00435bc7:	movl -4(%ebp), %eax
0x00435bca:	leal %ebx, 0x48(%eax)
0x00435bcd:	movl %esi, $0x447590<UINT32>
0x00435bd2:	movb 0x448860, %al
0x00435bd7:	call 0x004034c0
0x00435bdc:	movl %eax, 0x447590
0x00435be1:	movl %ecx, 0x447594
0x00435be7:	pushl $0x38<UINT8>
0x00435be9:	addl %ecx, %eax
0x00435beb:	pushl $0x43fc10<UINT32>
0x00435bf0:	pushl %ecx
0x00435bf1:	call 0x00420c20
0x00435bf6:	addl 0x447594, $0x38<UINT8>
0x00435bfd:	addl %esp, $0xc<UINT8>
0x00435c00:	xorl %eax, %eax
0x00435c02:	movl %edi, $0x1<UINT32>
0x00435c07:	movl -4(%ebp), %edi
0x00435c0a:	movl 0x4475a0, %eax
0x00435c0f:	movl 0x4475a4, $0x1582<UINT32>
0x00435c19:	movl 0x4475a8, %eax
0x00435c1e:	movl 0x4475ac, %eax
0x00435c23:	movl 0x4475b0, %eax
0x00435c28:	movl 0x4475b4, %eax
0x00435c2d:	movb -4(%ebp), $0x2<UINT8>
0x00435c31:	movl %esi, $0x4475ac<UINT32>
0x00435c36:	movl 0x4475b8, %edi
0x00435c3c:	movb 0x448860, %al
0x00435c41:	call 0x004034c0
0x00435c46:	movl %edx, 0x4475b0
0x00435c4c:	movl %eax, 0x4475ac
0x00435c51:	pushl $0x38<UINT8>
0x00435c53:	addl %edx, %eax
0x00435c55:	pushl $0x43fbd4<UINT32>
0x00435c5a:	pushl %edx
0x00435c5b:	call 0x00420c20
0x00435c60:	addl 0x4475b0, $0x38<UINT8>
0x00435c67:	addl %esp, $0xc<UINT8>
0x00435c6a:	xorl %eax, %eax
0x00435c6c:	movl 0x4475bc, %eax
0x00435c71:	movl 0x4475c0, $0x1ab5<UINT32>
0x00435c7b:	movl 0x4475c4, %edi
0x00435c81:	movl 0x4475c8, %eax
0x00435c86:	movl 0x4475cc, %eax
0x00435c8b:	movl 0x4475d0, %eax
0x00435c90:	movb -4(%ebp), $0x4<UINT8>
0x00435c94:	leal %ebx, 0x3b(%edi)
0x00435c97:	movl %esi, $0x4475c8<UINT32>
0x00435c9c:	movl 0x4475d4, %edi
0x00435ca2:	movb 0x448860, %al
0x00435ca7:	call 0x004034c0
0x00435cac:	movl %ecx, 0x4475cc
0x00435cb2:	movl %edx, 0x4475c8
0x00435cb8:	pushl $0x2c<UINT8>
0x00435cba:	addl %ecx, %edx
0x00435cbc:	pushl $0x43fba4<UINT32>
0x00435cc1:	pushl %ecx
0x00435cc2:	call 0x00420c20
0x00435cc7:	addl 0x4475cc, $0x2c<UINT8>
0x00435cce:	pushl $0x439a50<UINT32>
0x00435cd3:	movl 0x4475d8, $0x0<UINT32>
0x00435cdd:	call 0x0041dcb8
0x00435ce2:	addl %esp, $0x10<UINT8>
0x00435ce5:	movl %ecx, -12(%ebp)
0x00435ce8:	movl %fs:0, %ecx
0x00435cef:	popl %ecx
0x00435cf0:	popl %edi
0x00435cf1:	popl %esi
0x00435cf2:	popl %ebx
0x00435cf3:	movl %esp, %ebp
0x00435cf5:	popl %ebp
0x00435cf6:	ret

0x004358b0:	pushl %ebp
0x004358b1:	movl %ebp, %esp
0x004358b3:	pushl $0xffffffff<UINT8>
0x004358b5:	pushl $0x433bfe<UINT32>
0x004358ba:	movl %eax, %fs:0
0x004358c0:	pushl %eax
0x004358c1:	pushl %ebx
0x004358c2:	pushl %esi
0x004358c3:	pushl %edi
0x004358c4:	movl %eax, 0x44609c
0x004358c9:	xorl %eax, %ebp
0x004358cb:	pushl %eax
0x004358cc:	leal %eax, -12(%ebp)
0x004358cf:	movl %fs:0, %eax
0x004358d5:	xorl %eax, %eax
0x004358d7:	movl -4(%ebp), %eax
0x004358da:	leal %ebx, 0x28(%eax)
0x004358dd:	movl %esi, $0x4475e0<UINT32>
0x004358e2:	movb 0x448860, %al
0x004358e7:	call 0x004034c0
0x004358ec:	movl %ecx, 0x4475e4
0x004358f2:	movl %eax, 0x4475e0
0x004358f7:	movl %edx, 0x440048
0x004358fd:	addl %eax, %ecx
0x004358ff:	movl (%eax), %edx
0x00435901:	movl %ecx, 0x44004c
0x00435907:	movl 0x4(%eax), %ecx
0x0043590a:	movl %edx, 0x440050
0x00435910:	movl 0x8(%eax), %edx
0x00435913:	movl %ecx, 0x440054
0x00435919:	movl 0xc(%eax), %ecx
0x0043591c:	movl %edx, 0x440058
0x00435922:	movl 0x10(%eax), %edx
0x00435925:	movl %ecx, 0x44005c
0x0043592b:	movl 0x14(%eax), %ecx
0x0043592e:	addl 0x4475e4, $0x18<UINT8>
0x00435935:	xorl %eax, %eax
0x00435937:	movl %edi, $0x1<UINT32>
0x0043593c:	movl -4(%ebp), %edi
0x0043593f:	movl 0x4475f0, %eax
0x00435944:	movl 0x4475f4, %eax
0x00435949:	movl 0x4475f8, %eax
0x0043594e:	movb -4(%ebp), $0x2<UINT8>
0x00435952:	movl %esi, $0x4475f0<UINT32>
0x00435957:	movl 0x4475fc, %edi
0x0043595d:	movb 0x448860, %al
0x00435962:	call 0x004034c0
0x00435967:	movl %edx, 0x4475f4
0x0043596d:	movl %eax, 0x4475f0
0x00435972:	movl %ecx, 0x44002c
0x00435978:	addl %eax, %edx
0x0043597a:	movl (%eax), %ecx
0x0043597c:	movl %edx, 0x440030
0x00435982:	movl 0x4(%eax), %edx
0x00435985:	movl %ecx, 0x440034
0x0043598b:	movl 0x8(%eax), %ecx
0x0043598e:	movl %edx, 0x440038
0x00435994:	movl 0xc(%eax), %edx
0x00435997:	movl %ecx, 0x44003c
0x0043599d:	movl 0x10(%eax), %ecx
0x004359a0:	movl %edx, 0x440040
0x004359a6:	movl 0x14(%eax), %edx
0x004359a9:	addl 0x4475f4, $0x18<UINT8>
0x004359b0:	xorl %eax, %eax
0x004359b2:	movl 0x447600, %eax
0x004359b7:	movl 0x447604, %eax
0x004359bc:	movl 0x447608, %eax
0x004359c1:	movb -4(%ebp), $0x4<UINT8>
0x004359c5:	movl %esi, $0x447600<UINT32>
0x004359ca:	movl 0x44760c, %edi
0x004359d0:	movb 0x448860, %al
0x004359d5:	call 0x004034c0
0x004359da:	movl %eax, 0x447604
0x004359df:	movl %ecx, 0x447600
0x004359e5:	movl %edx, 0x440010
0x004359eb:	addl %eax, %ecx
0x004359ed:	movl (%eax), %edx
0x004359ef:	movl %ecx, 0x440014
0x004359f5:	movl 0x4(%eax), %ecx
0x004359f8:	movl %edx, 0x440018
0x004359fe:	movl 0x8(%eax), %edx
0x00435a01:	movl %ecx, 0x44001c
0x00435a07:	movl 0xc(%eax), %ecx
0x00435a0a:	movl %edx, 0x440020
0x00435a10:	movl 0x10(%eax), %edx
0x00435a13:	movl %ecx, 0x440024
0x00435a19:	movl 0x14(%eax), %ecx
0x00435a1c:	addl 0x447604, $0x18<UINT8>
0x00435a23:	xorl %eax, %eax
0x00435a25:	movl 0x447610, %eax
0x00435a2a:	movl 0x447614, %eax
0x00435a2f:	movl 0x447618, %eax
0x00435a34:	movb -4(%ebp), $0x6<UINT8>
0x00435a38:	movl %esi, $0x447610<UINT32>
0x00435a3d:	movl 0x44761c, %edi
0x00435a43:	movb 0x448860, %al
0x00435a48:	call 0x004034c0
0x00435a4d:	movl %edx, 0x447610
0x00435a53:	movl %eax, 0x447614
0x00435a58:	movl %ecx, 0x43fff4
0x00435a5e:	addl %eax, %edx
0x00435a60:	movl (%eax), %ecx
0x00435a62:	movl %edx, 0x43fff8
0x00435a68:	movl 0x4(%eax), %edx
0x00435a6b:	movl %ecx, 0x43fffc
0x00435a71:	movl 0x8(%eax), %ecx
0x00435a74:	movl %edx, 0x440000
0x00435a7a:	movl 0xc(%eax), %edx
0x00435a7d:	movl %ecx, 0x440004
0x00435a83:	movl 0x10(%eax), %ecx
0x00435a86:	movl %edx, 0x440008
0x00435a8c:	movl 0x14(%eax), %edx
0x00435a8f:	addl 0x447614, $0x18<UINT8>
0x00435a96:	xorl %eax, %eax
0x00435a98:	movl 0x447620, %eax
0x00435a9d:	movl 0x447624, %eax
0x00435aa2:	movl 0x447628, %eax
0x00435aa7:	movb -4(%ebp), $0x8<UINT8>
0x00435aab:	movl %esi, $0x447620<UINT32>
0x00435ab0:	movl 0x44762c, %edi
0x00435ab6:	movb 0x448860, %al
0x00435abb:	call 0x004034c0
0x00435ac0:	movl %ecx, 0x447624
0x00435ac6:	movl %eax, 0x447620
0x00435acb:	movl %edx, 0x43ffd8
0x00435ad1:	addl %eax, %ecx
0x00435ad3:	movl (%eax), %edx
0x00435ad5:	movl %ecx, 0x43ffdc
0x00435adb:	movl 0x4(%eax), %ecx
0x00435ade:	movl %edx, 0x43ffe0
0x00435ae4:	movl 0x8(%eax), %edx
0x00435ae7:	movl %ecx, 0x43ffe4
0x00435aed:	movl 0xc(%eax), %ecx
0x00435af0:	movl %edx, 0x43ffe8
0x00435af6:	movl 0x10(%eax), %edx
0x00435af9:	movl %ecx, 0x43ffec
0x00435aff:	movl 0x14(%eax), %ecx
0x00435b02:	addl 0x447624, $0x18<UINT8>
0x00435b09:	xorl %eax, %eax
0x00435b0b:	movl 0x447630, %eax
0x00435b10:	movl 0x447634, %eax
0x00435b15:	movl 0x447638, %eax
0x00435b1a:	movb -4(%ebp), $0xa<UINT8>
0x00435b1e:	movl %esi, $0x447630<UINT32>
0x00435b23:	movl 0x44763c, %edi
0x00435b29:	movb 0x448860, %al
0x00435b2e:	call 0x004034c0
0x00435b33:	movl %edx, 0x447630
0x00435b39:	movl %eax, 0x447634
0x00435b3e:	movl %ecx, 0x43ffbc
0x00435b44:	addl %eax, %edx
0x00435b46:	movl (%eax), %ecx
0x00435b48:	movl %edx, 0x43ffc0
0x00435b4e:	movl 0x4(%eax), %edx
0x00435b51:	movl %ecx, 0x43ffc4
0x00435b57:	movl 0x8(%eax), %ecx
0x00435b5a:	movl %edx, 0x43ffc8
0x00435b60:	movl 0xc(%eax), %edx
0x00435b63:	movl %ecx, 0x43ffcc
0x00435b69:	movl 0x10(%eax), %ecx
0x00435b6c:	movl %edx, 0x43ffd0
0x00435b72:	movl 0x14(%eax), %edx
0x00435b75:	addl 0x447634, $0x18<UINT8>
0x00435b7c:	pushl $0x439a30<UINT32>
0x00435b81:	call 0x0041dcb8
0x00435b86:	addl %esp, $0x4<UINT8>
0x00435b89:	movl %ecx, -12(%ebp)
0x00435b8c:	movl %fs:0, %ecx
0x00435b93:	popl %ecx
0x00435b94:	popl %edi
0x00435b95:	popl %esi
0x00435b96:	popl %ebx
0x00435b97:	movl %esp, %ebp
0x00435b99:	popl %ebp
0x00435b9a:	ret

0x00435d60:	pushl %ebp
0x00435d61:	movl %ebp, %esp
0x00435d63:	pushl $0xffffffff<UINT8>
0x00435d65:	pushl $0x433c2a<UINT32>
0x00435d6a:	movl %eax, %fs:0
0x00435d70:	pushl %eax
0x00435d71:	pushl %ebx
0x00435d72:	pushl %esi
0x00435d73:	movl %eax, 0x44609c
0x00435d78:	xorl %eax, %ebp
0x00435d7a:	pushl %eax
0x00435d7b:	leal %eax, -12(%ebp)
0x00435d7e:	movl %fs:0, %eax
0x00435d84:	xorl %eax, %eax
0x00435d86:	movl -4(%ebp), %eax
0x00435d89:	leal %ebx, 0x48(%eax)
0x00435d8c:	movl %esi, $0x447640<UINT32>
0x00435d91:	movb 0x448860, %al
0x00435d96:	call 0x004034c0
0x00435d9b:	movl %eax, 0x447644
0x00435da0:	movl %ecx, 0x447640
0x00435da6:	pushl $0x38<UINT8>
0x00435da8:	addl %eax, %ecx
0x00435daa:	pushl $0x4401b0<UINT32>
0x00435daf:	pushl %eax
0x00435db0:	call 0x00420c20
0x00435db5:	addl 0x447644, $0x38<UINT8>
0x00435dbc:	pushl $0x439ab0<UINT32>
0x00435dc1:	call 0x0041dcb8
0x00435dc6:	addl %esp, $0x10<UINT8>
0x00435dc9:	movl %ecx, -12(%ebp)
0x00435dcc:	movl %fs:0, %ecx
0x00435dd3:	popl %ecx
0x00435dd4:	popl %esi
0x00435dd5:	popl %ebx
0x00435dd6:	movl %esp, %ebp
0x00435dd8:	popl %ebp
0x00435dd9:	ret

0x00435d00:	pushl %ebp
0x00435d01:	movl %ebp, %esp
0x00435d03:	pushl $0xffffffff<UINT8>
0x00435d05:	pushl $0x433c5a<UINT32>
0x00435d0a:	movl %eax, %fs:0
0x00435d10:	pushl %eax
0x00435d11:	pushl %ebx
0x00435d12:	pushl %esi
0x00435d13:	movl %eax, 0x44609c
0x00435d18:	xorl %eax, %ebp
0x00435d1a:	pushl %eax
0x00435d1b:	leal %eax, -12(%ebp)
0x00435d1e:	movl %fs:0, %eax
0x00435d24:	xorl %eax, %eax
0x00435d26:	movl -4(%ebp), $0x0<UINT32>
0x00435d2d:	leal %ebx, 0x10(%eax)
0x00435d30:	movl %esi, $0x447d30<UINT32>
0x00435d35:	movw 0x448a0c, %ax
0x00435d3b:	call 0x00406000
0x00406000:	movl %eax, 0xc(%esi)
0x00406003:	xorl %ecx, %ecx
0x00406005:	addl %eax, %ebx
0x00406007:	movl %edx, $0x2<UINT32>
0x0040600c:	mull %eax, %edx
0x0040600e:	seto %cl
0x00406011:	pushl %edi
0x00406012:	negl %ecx
0x00406014:	orl %ecx, %eax
0x00406016:	pushl %ecx
0x00406017:	call 0x0041cfd3
0x0040601c:	movl %edi, %eax
0x0040601e:	addl %esp, $0x4<UINT8>
0x00406021:	testl %edi, %edi
0x00406023:	je 99
0x00406025:	cmpl %ebx, 0x4(%esi)
0x00406028:	jae 0x0040602d
0x0040602d:	cmpl (%esi), $0x0<UINT8>
0x00406030:	je 0x0040605b
0x0040605b:	movl %ecx, 0xc(%esi)
0x0040605e:	movl %eax, 0x4(%esi)
0x00406061:	addl %ecx, %ebx
0x00406063:	movl (%esi), %edi
0x00406065:	movl 0x8(%esi), %ebx
0x00406068:	cmpl %eax, %ecx
0x0040606a:	jae 28
0x0040606c:	leal %esp, (%esp)
0x00406070:	movl %edx, (%esi)
0x00406072:	movw %cx, 0x448a0c
0x00406079:	movw (%edx,%eax,2), %cx
0x0040607d:	movl %edx, 0x8(%esi)
0x00406080:	addl %edx, 0xc(%esi)
0x00406083:	incl %eax
0x00406084:	cmpl %eax, %edx
0x00406086:	jb 0x00406070
0x00406088:	popl %edi
0x00406089:	ret

0x00435d40:	pushl $0x439a70<UINT32>
0x00435d45:	call 0x0041dcb8
0x00435d4a:	addl %esp, $0x4<UINT8>
0x00435d4d:	movl %ecx, -12(%ebp)
0x00435d50:	movl %fs:0, %ecx
0x00435d57:	popl %ecx
0x00435d58:	popl %esi
0x00435d59:	popl %ebx
0x00435d5a:	movl %esp, %ebp
0x00435d5c:	popl %ebp
0x00435d5d:	ret

0x004365f0:	pushl %ebp
0x004365f1:	movl %ebp, %esp
0x004365f3:	pushl $0xffffffff<UINT8>
0x004365f5:	pushl $0x433cb2<UINT32>
0x004365fa:	movl %eax, %fs:0
0x00436600:	pushl %eax
0x00436601:	pushl %ebx
0x00436602:	pushl %esi
0x00436603:	pushl %edi
0x00436604:	movl %eax, 0x44609c
0x00436609:	xorl %eax, %ebp
0x0043660b:	pushl %eax
0x0043660c:	leal %eax, -12(%ebp)
0x0043660f:	movl %fs:0, %eax
0x00436615:	xorl %eax, %eax
0x00436617:	movl -4(%ebp), %eax
0x0043661a:	leal %ebx, 0x48(%eax)
0x0043661d:	movl %esi, $0x447658<UINT32>
0x00436622:	movb 0x448860, %al
0x00436627:	call 0x004034c0
0x0043662c:	movl %eax, 0x447658
0x00436631:	movl %ecx, 0x44765c
0x00436637:	pushl $0x38<UINT8>
0x00436639:	addl %ecx, %eax
0x0043663b:	pushl $0x43fc10<UINT32>
0x00436640:	pushl %ecx
0x00436641:	call 0x00420c20
0x00436646:	addl 0x44765c, $0x38<UINT8>
0x0043664d:	addl %esp, $0xc<UINT8>
0x00436650:	xorl %eax, %eax
0x00436652:	movl %edi, $0x1<UINT32>
0x00436657:	movl -4(%ebp), %edi
0x0043665a:	movl 0x447668, %eax
0x0043665f:	movl 0x44766c, $0x1582<UINT32>
0x00436669:	movl 0x447670, %eax
0x0043666e:	movl 0x447674, %eax
0x00436673:	movl 0x447678, %eax
0x00436678:	movl 0x44767c, %eax
0x0043667d:	movb -4(%ebp), $0x2<UINT8>
0x00436681:	movl %esi, $0x447674<UINT32>
0x00436686:	movl 0x447680, %edi
0x0043668c:	movb 0x448860, %al
0x00436691:	call 0x004034c0
0x00436696:	movl %edx, 0x447678
0x0043669c:	movl %eax, 0x447674
0x004366a1:	pushl $0x38<UINT8>
0x004366a3:	addl %edx, %eax
0x004366a5:	pushl $0x43fbd4<UINT32>
0x004366aa:	pushl %edx
0x004366ab:	call 0x00420c20
0x004366b0:	addl 0x447678, $0x38<UINT8>
0x004366b7:	addl %esp, $0xc<UINT8>
0x004366ba:	xorl %eax, %eax
0x004366bc:	movl 0x447684, %eax
0x004366c1:	movl 0x447688, $0x1ab5<UINT32>
0x004366cb:	movl 0x44768c, %edi
0x004366d1:	movl 0x447690, %eax
0x004366d6:	movl 0x447694, %eax
0x004366db:	movl 0x447698, %eax
0x004366e0:	movb -4(%ebp), $0x4<UINT8>
0x004366e4:	leal %ebx, 0x3b(%edi)
0x004366e7:	movl %esi, $0x447690<UINT32>
0x004366ec:	movl 0x44769c, %edi
0x004366f2:	movb 0x448860, %al
0x004366f7:	call 0x004034c0
0x004366fc:	movl %ecx, 0x447694
0x00436702:	movl %edx, 0x447690
0x00436708:	pushl $0x2c<UINT8>
0x0043670a:	addl %ecx, %edx
0x0043670c:	pushl $0x43fba4<UINT32>
0x00436711:	pushl %ecx
0x00436712:	call 0x00420c20
0x00436717:	addl 0x447694, $0x2c<UINT8>
0x0043671e:	pushl $0x439ea0<UINT32>
0x00436723:	movl 0x4476a0, $0x0<UINT32>
0x0043672d:	call 0x0041dcb8
0x00436732:	addl %esp, $0x10<UINT8>
0x00436735:	movl %ecx, -12(%ebp)
0x00436738:	movl %fs:0, %ecx
0x0043673f:	popl %ecx
0x00436740:	popl %edi
0x00436741:	popl %esi
0x00436742:	popl %ebx
0x00436743:	movl %esp, %ebp
0x00436745:	popl %ebp
0x00436746:	ret

0x00435ff0:	pushl %ebp
0x00435ff1:	movl %ebp, %esp
0x00435ff3:	pushl $0xffffffff<UINT8>
0x00435ff5:	pushl $0x433cda<UINT32>
0x00435ffa:	movl %eax, %fs:0
0x00436000:	pushl %eax
0x00436001:	pushl %ebx
0x00436002:	pushl %esi
0x00436003:	movl %eax, 0x44609c
0x00436008:	xorl %eax, %ebp
0x0043600a:	pushl %eax
0x0043600b:	leal %eax, -12(%ebp)
0x0043600e:	movl %fs:0, %eax
0x00436014:	xorl %eax, %eax
0x00436016:	movl -4(%ebp), %eax
0x00436019:	leal %ebx, 0x5e(%eax)
0x0043601c:	movl %esi, $0x4476a4<UINT32>
0x00436021:	movb 0x448860, %al
0x00436026:	call 0x004034c0
0x0043602b:	movl %eax, 0x4476a8
0x00436030:	movl %ecx, 0x4476a4
0x00436036:	pushl $0x4e<UINT8>
0x00436038:	addl %eax, %ecx
0x0043603a:	pushl $0x43fc50<UINT32>
0x0043603f:	pushl %eax
0x00436040:	call 0x00420c20
0x00436045:	addl 0x4476a8, $0x4e<UINT8>
0x0043604c:	pushl $0x439be0<UINT32>
0x00436051:	call 0x0041dcb8
0x00436056:	addl %esp, $0x10<UINT8>
0x00436059:	movl %ecx, -12(%ebp)
0x0043605c:	movl %fs:0, %ecx
0x00436063:	popl %ecx
0x00436064:	popl %esi
0x00436065:	popl %ebx
0x00436066:	movl %esp, %ebp
0x00436068:	popl %ebp
0x00436069:	ret

0x00436070:	pushl %ebp
0x00436071:	movl %ebp, %esp
0x00436073:	pushl $0xffffffff<UINT8>
0x00436075:	pushl $0x433d0a<UINT32>
0x0043607a:	movl %eax, %fs:0
0x00436080:	pushl %eax
0x00436081:	pushl %ebx
0x00436082:	pushl %esi
0x00436083:	movl %eax, 0x44609c
0x00436088:	xorl %eax, %ebp
0x0043608a:	pushl %eax
0x0043608b:	leal %eax, -12(%ebp)
0x0043608e:	movl %fs:0, %eax
0x00436094:	xorl %eax, %eax
0x00436096:	movl -4(%ebp), %eax
0x00436099:	leal %ebx, 0x3f(%eax)
0x0043609c:	movl %esi, $0x4476b4<UINT32>
0x004360a1:	movb 0x448860, %al
0x004360a6:	call 0x004034c0
0x004360ab:	movl %eax, 0x4476b4
0x004360b0:	movl %ecx, 0x4476b8
0x004360b6:	pushl $0x2f<UINT8>
0x004360b8:	addl %ecx, %eax
0x004360ba:	pushl $0x440594<UINT32>
0x004360bf:	pushl %ecx
0x004360c0:	call 0x00420c20
0x00420dcc:	movb %al, (%esi)
0x00420dce:	movb (%edi), %al
0x00420dd0:	movb %al, 0x1(%esi)
0x00420dd3:	movb 0x1(%edi), %al
0x00420dd6:	movb %al, 0x2(%esi)
0x00420dd9:	movb 0x2(%edi), %al
0x00420ddc:	movl %eax, 0x8(%ebp)
0x00420ddf:	popl %esi
0x00420de0:	popl %edi
0x00420de1:	leave
0x00420de2:	ret

0x004360c5:	addl 0x4476b8, $0x2f<UINT8>
0x004360cc:	pushl $0x439c20<UINT32>
0x004360d1:	call 0x0041dcb8
0x004360d6:	addl %esp, $0x10<UINT8>
0x004360d9:	movl %ecx, -12(%ebp)
0x004360dc:	movl %fs:0, %ecx
0x004360e3:	popl %ecx
0x004360e4:	popl %esi
0x004360e5:	popl %ebx
0x004360e6:	movl %esp, %ebp
0x004360e8:	popl %ebp
0x004360e9:	ret

0x004360f0:	pushl %ebp
0x004360f1:	movl %ebp, %esp
0x004360f3:	pushl $0xffffffff<UINT8>
0x004360f5:	pushl $0x433d3a<UINT32>
0x004360fa:	movl %eax, %fs:0
0x00436100:	pushl %eax
0x00436101:	pushl %ebx
0x00436102:	pushl %esi
0x00436103:	movl %eax, 0x44609c
0x00436108:	xorl %eax, %ebp
0x0043610a:	pushl %eax
0x0043610b:	leal %eax, -12(%ebp)
0x0043610e:	movl %fs:0, %eax
0x00436114:	xorl %eax, %eax
0x00436116:	movl -4(%ebp), %eax
0x00436119:	leal %ebx, 0x1a(%eax)
0x0043611c:	movl %esi, $0x4476c4<UINT32>
0x00436121:	movb 0x448860, %al
0x00436126:	call 0x004034c0
0x0043612b:	movl %eax, 0x4476c8
0x00436130:	movl %ecx, 0x4476c4
0x00436136:	movl %edx, 0x43fed4
0x0043613c:	addl %eax, %ecx
0x0043613e:	movl (%eax), %edx
0x00436140:	movl %ecx, 0x43fed8
0x00436146:	movl 0x4(%eax), %ecx
0x00436149:	movw %dx, 0x43fedc
0x00436150:	movw 0x8(%eax), %dx
0x00436154:	addl 0x4476c8, $0xa<UINT8>
0x0043615b:	pushl $0x439c60<UINT32>
0x00436160:	call 0x0041dcb8
0x00436165:	addl %esp, $0x4<UINT8>
0x00436168:	movl %ecx, -12(%ebp)
0x0043616b:	movl %fs:0, %ecx
0x00436172:	popl %ecx
0x00436173:	popl %esi
0x00436174:	popl %ebx
0x00436175:	movl %esp, %ebp
0x00436177:	popl %ebp
0x00436178:	ret

0x00436290:	pushl %ebp
0x00436291:	movl %ebp, %esp
0x00436293:	pushl $0xffffffff<UINT8>
0x00436295:	pushl $0x433d6a<UINT32>
0x0043629a:	movl %eax, %fs:0
0x004362a0:	pushl %eax
0x004362a1:	pushl %ebx
0x004362a2:	pushl %esi
0x004362a3:	movl %eax, 0x44609c
0x004362a8:	xorl %eax, %ebp
0x004362aa:	pushl %eax
0x004362ab:	leal %eax, -12(%ebp)
0x004362ae:	movl %fs:0, %eax
0x004362b4:	xorl %eax, %eax
0x004362b6:	movl -4(%ebp), %eax
0x004362b9:	leal %ebx, 0x18(%eax)
0x004362bc:	movl %esi, $0x4476d4<UINT32>
0x004362c1:	movb 0x448860, %al
0x004362c6:	call 0x004034c0
0x004362cb:	movl %eax, 0x4476d8
0x004362d0:	movl %ecx, 0x4476d4
0x004362d6:	movl %edx, 0x43fee0
0x004362dc:	addl %eax, %ecx
0x004362de:	movl (%eax), %edx
0x004362e0:	movl %ecx, 0x43fee4
0x004362e6:	movl 0x4(%eax), %ecx
0x004362e9:	addl 0x4476d8, $0x8<UINT8>
0x004362f0:	pushl $0x439d20<UINT32>
0x004362f5:	call 0x0041dcb8
0x004362fa:	addl %esp, $0x4<UINT8>
0x004362fd:	movl %ecx, -12(%ebp)
0x00436300:	movl %fs:0, %ecx
0x00436307:	popl %ecx
0x00436308:	popl %esi
0x00436309:	popl %ebx
0x0043630a:	movl %esp, %ebp
0x0043630c:	popl %ebp
0x0043630d:	ret

0x004364f0:	pushl %ebp
0x004364f1:	movl %ebp, %esp
0x004364f3:	pushl $0xffffffff<UINT8>
0x004364f5:	pushl $0x433d9a<UINT32>
0x004364fa:	movl %eax, %fs:0
0x00436500:	pushl %eax
0x00436501:	pushl %ebx
0x00436502:	pushl %esi
0x00436503:	movl %eax, 0x44609c
0x00436508:	xorl %eax, %ebp
0x0043650a:	pushl %eax
0x0043650b:	leal %eax, -12(%ebp)
0x0043650e:	movl %fs:0, %eax
0x00436514:	xorl %eax, %eax
0x00436516:	movl -4(%ebp), %eax
0x00436519:	leal %ebx, 0x16(%eax)
0x0043651c:	movl %esi, $0x4476e4<UINT32>
0x00436521:	movb 0x448860, %al
0x00436526:	call 0x004034c0
0x0043652b:	movl %eax, 0x4476e8
0x00436530:	movl %ecx, 0x4476e4
0x00436536:	movl %edx, 0x43feec
0x0043653c:	addl %eax, %ecx
0x0043653e:	movl (%eax), %edx
0x00436540:	movw %cx, 0x43fef0
0x00436547:	movw 0x4(%eax), %cx
0x0043654b:	addl 0x4476e8, $0x6<UINT8>
0x00436552:	pushl $0x439e20<UINT32>
0x00436557:	call 0x0041dcb8
0x0043655c:	addl %esp, $0x4<UINT8>
0x0043655f:	movl %ecx, -12(%ebp)
0x00436562:	movl %fs:0, %ecx
0x00436569:	popl %ecx
0x0043656a:	popl %esi
0x0043656b:	popl %ebx
0x0043656c:	movl %esp, %ebp
0x0043656e:	popl %ebp
0x0043656f:	ret

0x00435ea0:	pushl %ebp
0x00435ea1:	movl %ebp, %esp
0x00435ea3:	pushl $0xffffffff<UINT8>
0x00435ea5:	pushl $0x433dca<UINT32>
0x00435eaa:	movl %eax, %fs:0
0x00435eb0:	pushl %eax
0x00435eb1:	pushl %ebx
0x00435eb2:	pushl %esi
0x00435eb3:	movl %eax, 0x44609c
0x00435eb8:	xorl %eax, %ebp
0x00435eba:	pushl %eax
0x00435ebb:	leal %eax, -12(%ebp)
0x00435ebe:	movl %fs:0, %eax
0x00435ec4:	xorl %eax, %eax
0x00435ec6:	movl -4(%ebp), %eax
0x00435ec9:	leal %ebx, 0x23(%eax)
0x00435ecc:	movl %esi, $0x4476f4<UINT32>
0x00435ed1:	movb 0x448860, %al
0x00435ed6:	call 0x004034c0
0x00435edb:	movl %ecx, 0x4476f8
0x00435ee1:	movl %eax, 0x4476f4
0x00435ee6:	movl %edx, 0x43ff00
0x00435eec:	addl %eax, %ecx
0x00435eee:	movl (%eax), %edx
0x00435ef0:	movl %ecx, 0x43ff04
0x00435ef6:	movl 0x4(%eax), %ecx
0x00435ef9:	movl %edx, 0x43ff08
0x00435eff:	movl 0x8(%eax), %edx
0x00435f02:	movl %ecx, 0x43ff0c
0x00435f08:	movl 0xc(%eax), %ecx
0x00435f0b:	movw %dx, 0x43ff10
0x00435f12:	movw 0x10(%eax), %dx
0x00435f16:	movb %cl, 0x43ff12
0x00435f1c:	movb 0x12(%eax), %cl
0x00435f1f:	addl 0x4476f8, $0x13<UINT8>
0x00435f26:	pushl $0x439b60<UINT32>
0x00435f2b:	call 0x0041dcb8
0x0041dbdd:	movl %eax, $0x800<UINT32>
0x0041dbe2:	cmpl %edi, %eax
0x0041dbe4:	jae 2
0x0041dbe6:	movl %eax, %edi
0x0041dbe8:	addl %eax, %edi
0x0041dbea:	cmpl %eax, %edi
0x0041dbec:	jb 15
0x0041dbee:	pushl %eax
0x0041dbef:	pushl -4(%ebp)
0x0041dbf2:	call 0x0042354f
0x0042354f:	movl %edi, %edi
0x00423551:	pushl %ebp
0x00423552:	movl %ebp, %esp
0x00423554:	pushl %esi
0x00423555:	pushl %edi
0x00423556:	xorl %esi, %esi
0x00423558:	pushl 0xc(%ebp)
0x0042355b:	pushl 0x8(%ebp)
0x0042355e:	call 0x0042ad82
0x0042ad82:	pushl $0x10<UINT8>
0x0042ad84:	pushl $0x441870<UINT32>
0x0042ad89:	call 0x0041fad0
0x0042ad8e:	movl %ebx, 0x8(%ebp)
0x0042ad91:	testl %ebx, %ebx
0x0042ad93:	jne 0x0042ada3
0x0042ada3:	movl %esi, 0xc(%ebp)
0x0042ada6:	testl %esi, %esi
0x0042ada8:	jne 0x0042adb6
0x0042adb6:	cmpl 0x449a4c, $0x3<UINT8>
0x0042adbd:	jne 0x0042af56
0x0042af56:	cmpl %esi, $0xffffffe0<UINT8>
0x0042af59:	jbe 0x0042af28
0x0042af28:	testl %esi, %esi
0x0042af2a:	jne 0x0042af2d
0x0042af2d:	pushl %esi
0x0042af2e:	pushl %ebx
0x0042af2f:	pushl $0x0<UINT8>
0x0042af31:	pushl 0x4486cc
0x0042af37:	call HeapReAlloc@kernel32.dll
HeapReAlloc@kernel32.dll: API Node	
0x0042af3d:	movl %edi, %eax
0x0042af3f:	testl %edi, %edi
0x0042af41:	jne 0x0042af99
0x0042af99:	movl %eax, %edi
0x0042af9b:	jmp 0x0042af6f
0x0042af6f:	call 0x0041fb15
0x0042af74:	ret

0x00423563:	movl %edi, %eax
0x00423565:	popl %ecx
0x00423566:	popl %ecx
0x00423567:	testl %edi, %edi
0x00423569:	jne 0x00423597
0x00423597:	movl %eax, %edi
0x00423599:	popl %edi
0x0042359a:	popl %esi
0x0042359b:	popl %ebp
0x0042359c:	ret

0x0041dbf7:	popl %ecx
0x0041dbf8:	popl %ecx
0x0041dbf9:	testl %eax, %eax
0x0041dbfb:	jne 0x0041dc13
0x0041dc13:	sarl %ebx, $0x2<UINT8>
0x0041dc16:	pushl %eax
0x0041dc17:	leal %esi, (%eax,%ebx,4)
0x0041dc1a:	call 0x00422bb9
0x0041dc1f:	popl %ecx
0x0041dc20:	movl 0x44abb0, %eax
0x0041eff0:	movl %edi, %edi
0x0041eff2:	pushl %ebp
0x0041eff3:	movl %ebp, %esp
0x0041eff5:	subl %esp, $0x18<UINT8>
0x0041eff8:	pushl %ebx
0x0041eff9:	movl %ebx, 0xc(%ebp)
0x0041effc:	pushl %esi
0x0041effd:	movl %esi, 0x8(%ebx)
0x0041f000:	xorl %esi, 0x44609c
0x0041f006:	pushl %edi
0x0041f007:	movl %eax, (%esi)
0x0041f009:	movb -1(%ebp), $0x0<UINT8>
0x0041f00d:	movl -12(%ebp), $0x1<UINT32>
0x0041f014:	leal %edi, 0x10(%ebx)
0x0041f017:	cmpl %eax, $0xfffffffe<UINT8>
0x0041f01a:	je 0x0041f029
0x0041f029:	movl %ecx, 0xc(%esi)
0x0041f02c:	movl %eax, 0x8(%esi)
0x0041f02f:	addl %ecx, %edi
0x0041f031:	xorl %ecx, (%eax,%edi)
0x0041f034:	call 0x0041d190
0x0041f039:	movl %eax, 0x8(%ebp)
0x0041f03c:	testb 0x4(%eax), $0x66<UINT8>
0x0041f040:	jne 278
0x0041f046:	movl %ecx, 0x10(%ebp)
0x0041f049:	leal %edx, -24(%ebp)
0x0041f04c:	movl -4(%ebx), %edx
0x0041f04f:	movl %ebx, 0xc(%ebx)
0x0041f052:	movl -24(%ebp), %eax
0x0041f055:	movl -20(%ebp), %ecx
0x0041f058:	cmpl %ebx, $0xfffffffe<UINT8>
0x0041f05b:	je 95
0x0041f05d:	leal %ecx, (%ecx)
0x0041f060:	leal %eax, (%ebx,%ebx,2)
0x0041f063:	movl %ecx, 0x14(%esi,%eax,4)
0x0041f067:	leal %eax, 0x10(%esi,%eax,4)
0x0041f06b:	movl -16(%ebp), %eax
0x0041f06e:	movl %eax, (%eax)
0x0041f070:	movl -8(%ebp), %eax
0x0041f073:	testl %ecx, %ecx
0x0041f075:	je 0x0041f08b
0x0041f08b:	movl %ebx, %eax
0x0041f08d:	cmpl %eax, $0xfffffffe<UINT8>
0x0041f090:	jne -50
0x0041f092:	cmpb -1(%ebp), $0x0<UINT8>
0x0041f096:	je 0x0041f0bc
0x0041f0bc:	movl %eax, -12(%ebp)
0x0041f0bf:	popl %edi
0x0041f0c0:	popl %esi
0x0041f0c1:	popl %ebx
0x0041f0c2:	movl %esp, %ebp
0x0041f0c4:	popl %ebp
0x0041f0c5:	ret

0x00435f30:	addl %esp, $0x4<UINT8>
0x00435f33:	movl %ecx, -12(%ebp)
0x00435f36:	movl %fs:0, %ecx
0x00435f3d:	popl %ecx
0x00435f3e:	popl %esi
0x00435f3f:	popl %ebx
0x00435f40:	movl %esp, %ebp
0x00435f42:	popl %ebp
0x00435f43:	ret

0x00436310:	pushl %ebp
0x00436311:	movl %ebp, %esp
0x00436313:	pushl $0xffffffff<UINT8>
0x00436315:	pushl $0x433dfa<UINT32>
0x0043631a:	movl %eax, %fs:0
0x00436320:	pushl %eax
0x00436321:	pushl %ebx
0x00436322:	pushl %esi
0x00436323:	movl %eax, 0x44609c
0x00436328:	xorl %eax, %ebp
0x0043632a:	pushl %eax
0x0043632b:	leal %eax, -12(%ebp)
0x0043632e:	movl %fs:0, %eax
0x00436334:	xorl %eax, %eax
0x00436336:	movl -4(%ebp), %eax
0x00436339:	leal %ebx, 0x24(%eax)
0x0043633c:	movl %esi, $0x447704<UINT32>
0x00436341:	movb 0x448860, %al
0x00436346:	call 0x004034c0
0x0043634b:	movl %ecx, 0x447708
0x00436351:	movl %eax, 0x447704
0x00436356:	movl %edx, 0x4405c4
0x0043635c:	addl %eax, %ecx
0x0043635e:	movl (%eax), %edx
0x00436360:	movl %ecx, 0x4405c8
0x00436366:	movl 0x4(%eax), %ecx
0x00436369:	movl %edx, 0x4405cc
0x0043636f:	movl 0x8(%eax), %edx
0x00436372:	movl %ecx, 0x4405d0
0x00436378:	movl 0xc(%eax), %ecx
0x0043637b:	movl %edx, 0x4405d4
0x00436381:	movl 0x10(%eax), %edx
0x00436384:	addl 0x447708, $0x14<UINT8>
0x0043638b:	pushl $0x439d60<UINT32>
0x00436390:	call 0x0041dcb8
0x00436395:	addl %esp, $0x4<UINT8>
0x00436398:	movl %ecx, -12(%ebp)
0x0043639b:	movl %fs:0, %ecx
0x004363a2:	popl %ecx
0x004363a3:	popl %esi
0x004363a4:	popl %ebx
0x004363a5:	movl %esp, %ebp
0x004363a7:	popl %ebp
0x004363a8:	ret

0x004363b0:	pushl %ebp
0x004363b1:	movl %ebp, %esp
0x004363b3:	pushl $0xffffffff<UINT8>
0x004363b5:	pushl $0x433e2a<UINT32>
0x004363ba:	movl %eax, %fs:0
0x004363c0:	pushl %eax
0x004363c1:	pushl %ebx
0x004363c2:	pushl %esi
0x004363c3:	movl %eax, 0x44609c
0x004363c8:	xorl %eax, %ebp
0x004363ca:	pushl %eax
0x004363cb:	leal %eax, -12(%ebp)
0x004363ce:	movl %fs:0, %eax
0x004363d4:	xorl %eax, %eax
0x004363d6:	movl -4(%ebp), %eax
0x004363d9:	leal %ebx, 0x21(%eax)
0x004363dc:	movl %esi, $0x447714<UINT32>
0x004363e1:	movb 0x448860, %al
0x004363e6:	call 0x004034c0
0x004363eb:	movl %ecx, 0x447718
0x004363f1:	movl %eax, 0x447714
0x004363f6:	movl %edx, 0x4405dc
0x004363fc:	addl %eax, %ecx
0x004363fe:	movl (%eax), %edx
0x00436400:	movl %ecx, 0x4405e0
0x00436406:	movl 0x4(%eax), %ecx
0x00436409:	movl %edx, 0x4405e4
0x0043640f:	movl 0x8(%eax), %edx
0x00436412:	movl %ecx, 0x4405e8
0x00436418:	movl 0xc(%eax), %ecx
0x0043641b:	movb %dl, 0x4405ec
0x00436421:	movb 0x10(%eax), %dl
0x00436424:	addl 0x447718, $0x11<UINT8>
0x0043642b:	pushl $0x439da0<UINT32>
0x00436430:	call 0x0041dcb8
0x00436435:	addl %esp, $0x4<UINT8>
0x00436438:	movl %ecx, -12(%ebp)
0x0043643b:	movl %fs:0, %ecx
0x00436442:	popl %ecx
0x00436443:	popl %esi
0x00436444:	popl %ebx
0x00436445:	movl %esp, %ebp
0x00436447:	popl %ebp
0x00436448:	ret

0x00435e00:	pushl %ebp
0x00435e01:	movl %ebp, %esp
0x00435e03:	pushl $0xffffffff<UINT8>
0x00435e05:	pushl $0x433e5a<UINT32>
0x00435e0a:	movl %eax, %fs:0
0x00435e10:	pushl %eax
0x00435e11:	pushl %ebx
0x00435e12:	pushl %esi
0x00435e13:	movl %eax, 0x44609c
0x00435e18:	xorl %eax, %ebp
0x00435e1a:	pushl %eax
0x00435e1b:	leal %eax, -12(%ebp)
0x00435e1e:	movl %fs:0, %eax
0x00435e24:	xorl %eax, %eax
0x00435e26:	movl -4(%ebp), %eax
0x00435e29:	leal %ebx, 0x1e(%eax)
0x00435e2c:	movl %esi, $0x447724<UINT32>
0x00435e31:	movb 0x448860, %al
0x00435e36:	call 0x004034c0
0x00435e3b:	movl %eax, 0x447728
0x00435e40:	movl %ecx, 0x447724
0x00435e46:	movl %edx, 0x43ff14
0x00435e4c:	addl %eax, %ecx
0x00435e4e:	movl (%eax), %edx
0x00435e50:	movl %ecx, 0x43ff18
0x00435e56:	movl 0x4(%eax), %ecx
0x00435e59:	movl %edx, 0x43ff1c
0x00435e5f:	movl 0x8(%eax), %edx
0x00435e62:	movw %cx, 0x43ff20
0x00435e69:	movw 0xc(%eax), %cx
0x00435e6d:	addl 0x447728, $0xe<UINT8>
0x00435e74:	pushl $0x439b20<UINT32>
0x00435e79:	call 0x0041dcb8
0x00435e7e:	addl %esp, $0x4<UINT8>
0x00435e81:	movl %ecx, -12(%ebp)
0x00435e84:	movl %fs:0, %ecx
0x00435e8b:	popl %ecx
0x00435e8c:	popl %esi
0x00435e8d:	popl %ebx
0x00435e8e:	movl %esp, %ebp
0x00435e90:	popl %ebp
0x00435e91:	ret

0x00436450:	pushl %ebp
0x00436451:	movl %ebp, %esp
0x00436453:	pushl $0xffffffff<UINT8>
0x00436455:	pushl $0x433e8a<UINT32>
0x0043645a:	movl %eax, %fs:0
0x00436460:	pushl %eax
0x00436461:	pushl %ebx
0x00436462:	pushl %esi
0x00436463:	movl %eax, 0x44609c
0x00436468:	xorl %eax, %ebp
0x0043646a:	pushl %eax
0x0043646b:	leal %eax, -12(%ebp)
0x0043646e:	movl %fs:0, %eax
0x00436474:	xorl %eax, %eax
0x00436476:	movl -4(%ebp), %eax
0x00436479:	leal %ebx, 0x1e(%eax)
0x0043647c:	movl %esi, $0x447734<UINT32>
0x00436481:	movb 0x448860, %al
0x00436486:	call 0x004034c0
0x0043648b:	movl %eax, 0x447738
0x00436490:	movl %ecx, 0x447734
0x00436496:	movl %edx, 0x4405f0
0x0043649c:	addl %eax, %ecx
0x0043649e:	movl (%eax), %edx
0x004364a0:	movl %ecx, 0x4405f4
0x004364a6:	movl 0x4(%eax), %ecx
0x004364a9:	movl %edx, 0x4405f8
0x004364af:	movl 0x8(%eax), %edx
0x004364b2:	movw %cx, 0x4405fc
0x004364b9:	movw 0xc(%eax), %cx
0x004364bd:	addl 0x447738, $0xe<UINT8>
0x004364c4:	pushl $0x439de0<UINT32>
0x004364c9:	call 0x0041dcb8
0x004364ce:	addl %esp, $0x4<UINT8>
0x004364d1:	movl %ecx, -12(%ebp)
0x004364d4:	movl %fs:0, %ecx
0x004364db:	popl %ecx
0x004364dc:	popl %esi
0x004364dd:	popl %ebx
0x004364de:	movl %esp, %ebp
0x004364e0:	popl %ebp
0x004364e1:	ret

0x00436570:	pushl %ebp
0x00436571:	movl %ebp, %esp
0x00436573:	pushl $0xffffffff<UINT8>
0x00436575:	pushl $0x433eba<UINT32>
0x0043657a:	movl %eax, %fs:0
0x00436580:	pushl %eax
0x00436581:	pushl %ebx
0x00436582:	pushl %esi
0x00436583:	movl %eax, 0x44609c
0x00436588:	xorl %eax, %ebp
0x0043658a:	pushl %eax
0x0043658b:	leal %eax, -12(%ebp)
0x0043658e:	movl %fs:0, %eax
0x00436594:	xorl %eax, %eax
0x00436596:	movl -4(%ebp), %eax
0x00436599:	leal %ebx, 0x15(%eax)
0x0043659c:	movl %esi, $0x447744<UINT32>
0x004365a1:	movb 0x448860, %al
0x004365a6:	call 0x004034c0
0x004365ab:	movl %eax, 0x447748
0x004365b0:	movl %ecx, 0x447744
0x004365b6:	movl %edx, 0x440600
0x004365bc:	addl %eax, %ecx
0x004365be:	movl (%eax), %edx
0x004365c0:	movb %cl, 0x440604
0x004365c6:	movb 0x4(%eax), %cl
0x004365c9:	addl 0x447748, $0x5<UINT8>
0x004365d0:	pushl $0x439e60<UINT32>
0x004365d5:	call 0x0041dcb8
0x004365da:	addl %esp, $0x4<UINT8>
0x004365dd:	movl %ecx, -12(%ebp)
0x004365e0:	movl %fs:0, %ecx
0x004365e7:	popl %ecx
0x004365e8:	popl %esi
0x004365e9:	popl %ebx
0x004365ea:	movl %esp, %ebp
0x004365ec:	popl %ebp
0x004365ed:	ret

0x00436180:	pushl %ebp
0x00436181:	movl %ebp, %esp
0x00436183:	pushl $0xffffffff<UINT8>
0x00436185:	pushl $0x433eea<UINT32>
0x0043618a:	movl %eax, %fs:0
0x00436190:	pushl %eax
0x00436191:	pushl %ebx
0x00436192:	pushl %esi
0x00436193:	movl %eax, 0x44609c
0x00436198:	xorl %eax, %ebp
0x0043619a:	pushl %eax
0x0043619b:	leal %eax, -12(%ebp)
0x0043619e:	movl %fs:0, %eax
0x004361a4:	xorl %eax, %eax
0x004361a6:	movl -4(%ebp), %eax
0x004361a9:	leal %ebx, 0x15(%eax)
0x004361ac:	movl %esi, $0x447754<UINT32>
0x004361b1:	movb 0x448860, %al
0x004361b6:	call 0x004034c0
0x004361bb:	movl %eax, 0x447758
0x004361c0:	movl %ecx, 0x447754
0x004361c6:	movl %edx, 0x440608
0x004361cc:	addl %eax, %ecx
0x004361ce:	movl (%eax), %edx
0x004361d0:	movb %cl, 0x44060c
0x004361d6:	movb 0x4(%eax), %cl
0x004361d9:	addl 0x447758, $0x5<UINT8>
0x004361e0:	pushl $0x439ca0<UINT32>
0x004361e5:	call 0x0041dcb8
0x004361ea:	addl %esp, $0x4<UINT8>
0x004361ed:	movl %ecx, -12(%ebp)
0x004361f0:	movl %fs:0, %ecx
0x004361f7:	popl %ecx
0x004361f8:	popl %esi
0x004361f9:	popl %ebx
0x004361fa:	movl %esp, %ebp
0x004361fc:	popl %ebp
0x004361fd:	ret

0x00436200:	pushl %ebp
0x00436201:	movl %ebp, %esp
0x00436203:	pushl $0xffffffff<UINT8>
0x00436205:	pushl $0x433f1a<UINT32>
0x0043620a:	movl %eax, %fs:0
0x00436210:	pushl %eax
0x00436211:	pushl %ebx
0x00436212:	pushl %esi
0x00436213:	movl %eax, 0x44609c
0x00436218:	xorl %eax, %ebp
0x0043621a:	pushl %eax
0x0043621b:	leal %eax, -12(%ebp)
0x0043621e:	movl %fs:0, %eax
0x00436224:	xorl %eax, %eax
0x00436226:	movl -4(%ebp), %eax
0x00436229:	leal %ebx, 0x1c(%eax)
0x0043622c:	movl %esi, $0x447764<UINT32>
0x00436231:	movb 0x448860, %al
0x00436236:	call 0x004034c0
0x0043623b:	movl %eax, 0x447768
0x00436240:	movl %ecx, 0x447764
0x00436246:	movl %edx, 0x440610
0x0043624c:	addl %eax, %ecx
0x0043624e:	movl (%eax), %edx
0x00436250:	movl %ecx, 0x440614
0x00436256:	movl 0x4(%eax), %ecx
0x00436259:	movl %edx, 0x440618
0x0043625f:	movl 0x8(%eax), %edx
0x00436262:	addl 0x447768, $0xc<UINT8>
0x00436269:	pushl $0x439ce0<UINT32>
0x0043626e:	call 0x0041dcb8
0x00436273:	addl %esp, $0x4<UINT8>
0x00436276:	movl %ecx, -12(%ebp)
0x00436279:	movl %fs:0, %ecx
0x00436280:	popl %ecx
0x00436281:	popl %esi
0x00436282:	popl %ebx
0x00436283:	movl %esp, %ebp
0x00436285:	popl %ebp
0x00436286:	ret

0x00435f50:	pushl %ebp
0x00435f51:	movl %ebp, %esp
0x00435f53:	pushl $0xffffffff<UINT8>
0x00435f55:	pushl $0x433f4a<UINT32>
0x00435f5a:	movl %eax, %fs:0
0x00435f60:	pushl %eax
0x00435f61:	pushl %ebx
0x00435f62:	pushl %esi
0x00435f63:	movl %eax, 0x44609c
0x00435f68:	xorl %eax, %ebp
0x00435f6a:	pushl %eax
0x00435f6b:	leal %eax, -12(%ebp)
0x00435f6e:	movl %fs:0, %eax
0x00435f74:	xorl %eax, %eax
0x00435f76:	movl -4(%ebp), %eax
0x00435f79:	leal %ebx, 0x1e(%eax)
0x00435f7c:	movl %esi, $0x447774<UINT32>
0x00435f81:	movb 0x448860, %al
0x00435f86:	call 0x004034c0
0x00435f8b:	movl %eax, 0x447778
0x00435f90:	movl %ecx, 0x447774
0x00435f96:	movl %edx, 0x440620
0x00435f9c:	addl %eax, %ecx
0x00435f9e:	movl (%eax), %edx
0x00435fa0:	movl %ecx, 0x440624
0x00435fa6:	movl 0x4(%eax), %ecx
0x00435fa9:	movl %edx, 0x440628
0x00435faf:	movl 0x8(%eax), %edx
0x00435fb2:	movw %cx, 0x44062c
0x00435fb9:	movw 0xc(%eax), %cx
0x00435fbd:	addl 0x447778, $0xe<UINT8>
0x00435fc4:	pushl $0x439ba0<UINT32>
0x00435fc9:	call 0x0041dcb8
0x00435fce:	addl %esp, $0x4<UINT8>
0x00435fd1:	movl %ecx, -12(%ebp)
0x00435fd4:	movl %fs:0, %ecx
0x00435fdb:	popl %ecx
0x00435fdc:	popl %esi
0x00435fdd:	popl %ebx
0x00435fde:	movl %esp, %ebp
0x00435fe0:	popl %ebp
0x00435fe1:	ret

0x00435de0:	movl %ecx, $0x4499f8<UINT32>
0x00435de5:	call 0x00408720
0x00408720:	pushl %ebp
0x00408721:	movl %ebp, %esp
0x00408723:	pushl $0xffffffff<UINT8>
0x00408725:	pushl $0x430b69<UINT32>
0x0040872a:	movl %eax, %fs:0
0x00408730:	pushl %eax
0x00408731:	subl %esp, $0xc<UINT8>
0x00408734:	pushl %ebx
0x00408735:	pushl %esi
0x00408736:	pushl %edi
0x00408737:	movl %eax, 0x44609c
0x0040873c:	xorl %eax, %ebp
0x0040873e:	pushl %eax
0x0040873f:	leal %eax, -12(%ebp)
0x00408742:	movl %fs:0, %eax
0x00408748:	movl %edi, %ecx
0x0040874a:	movl -20(%ebp), %edi
0x0040874d:	xorl %ebx, %ebx
0x0040874f:	leal %esi, 0xc(%edi)
0x00408752:	movl (%edi), %ebx
0x00408754:	movl %eax, $0x1<UINT32>
0x00408759:	movl 0x4(%edi), %eax
0x0040875c:	movl 0x8(%edi), %ebx
0x0040875f:	movl -24(%ebp), %esi
0x00408762:	movl (%esi), %ebx
0x00408764:	movl 0x4(%esi), %ebx
0x00408767:	movl 0x8(%esi), %ebx
0x0040876a:	movl -4(%ebp), %ebx
0x0040876d:	movl 0xc(%esi), %eax
0x00408770:	xorl %eax, %eax
0x00408772:	movw 0x448a0c, %ax
0x00408778:	cmpl (%esi), $0x43ffac<UINT32>
0x0040877e:	movl %eax, 0x8(%esi)
0x00408781:	sete -13(%ebp)
0x00408785:	cmpl %eax, $0x6<UINT8>
0x00408788:	jb 0x00408794
0x00408794:	leal %ecx, 0x16(%eax)
0x00408797:	cmpl %ecx, %eax
0x00408799:	jbe 9
0x0040879b:	movl %ebx, %ecx
0x0040879d:	call 0x00406000
0x004087a2:	xorl %ebx, %ebx
0x004087a4:	cmpb -13(%ebp), %bl
0x004087a7:	je 0x004087ad
0x004087ad:	movl %ecx, $0x43ffac<UINT32>
0x004087b2:	movl %edx, 0x4(%esi)
0x004087b5:	movl %eax, (%esi)
0x004087b7:	leal %eax, (%eax,%edx,2)
0x004087ba:	movl %edx, (%ecx)
0x004087bc:	movl (%eax), %edx
0x004087be:	movl %edx, 0x4(%ecx)
0x004087c1:	movl 0x4(%eax), %edx
0x004087c4:	movl %ecx, 0x8(%ecx)
0x004087c7:	movl 0x8(%eax), %ecx
0x004087ca:	addl 0x4(%esi), $0x6<UINT8>
0x004087ce:	leal %ecx, 0x1c(%edi)
0x004087d1:	movl -4(%ebp), $0x1<UINT32>
0x004087d8:	call 0x004086b0
0x004086b0:	pushl %ebp
0x004086b1:	movl %ebp, %esp
0x004086b3:	pushl $0xffffffff<UINT8>
0x004086b5:	pushl $0x430738<UINT32>
0x004086ba:	movl %eax, %fs:0
0x004086c0:	pushl %eax
0x004086c1:	pushl %ecx
0x004086c2:	pushl %ebx
0x004086c3:	pushl %esi
0x004086c4:	movl %eax, 0x44609c
0x004086c9:	xorl %eax, %ebp
0x004086cb:	pushl %eax
0x004086cc:	leal %eax, -12(%ebp)
0x004086cf:	movl %fs:0, %eax
0x004086d5:	movl %esi, %ecx
0x004086d7:	movl -16(%ebp), %esi
0x004086da:	xorl %eax, %eax
0x004086dc:	movl (%esi), %eax
0x004086de:	movl 0x4(%esi), %eax
0x004086e1:	movl 0x8(%esi), %eax
0x004086e4:	movl -4(%ebp), %eax
0x004086e7:	movl 0xc(%esi), $0x1<UINT32>
0x004086ee:	movl %ebx, $0x10<UINT32>
0x004086f3:	movw 0x448a0c, %ax
0x004086f9:	cmpl 0x8(%esi), %ebx
0x004086fc:	jae 5
0x004086fe:	call 0x00406000
0x00408703:	movl %eax, %esi
0x00408705:	movl %ecx, -12(%ebp)
0x00408708:	movl %fs:0, %ecx
0x0040870f:	popl %ecx
0x00408710:	popl %esi
0x00408711:	popl %ebx
0x00408712:	movl %esp, %ebp
0x00408714:	popl %ebp
0x00408715:	ret

0x004087dd:	leal %ecx, 0x2c(%edi)
0x004087e0:	movb -4(%ebp), $0x2<UINT8>
0x004087e4:	call 0x004086b0
0x004087e9:	leal %ecx, 0x3c(%edi)
0x004087ec:	movb -4(%ebp), $0x3<UINT8>
0x004087f0:	call 0x004086b0
0x004087f5:	movl 0x4c(%edi), %ebx
0x004087f8:	movl %eax, %edi
0x004087fa:	movl %ecx, -12(%ebp)
0x004087fd:	movl %fs:0, %ecx
0x00408804:	popl %ecx
0x00408805:	popl %edi
0x00408806:	popl %esi
0x00408807:	popl %ebx
0x00408808:	movl %esp, %ebp
0x0040880a:	popl %ebp
0x0040880b:	ret

0x00435dea:	pushl $0x439b10<UINT32>
0x00435def:	call 0x0041dcb8
0x00435df4:	popl %ecx
0x00435df5:	ret

0x00436750:	pushl %ebp
0x00436751:	movl %ebp, %esp
0x00436753:	pushl $0xffffffff<UINT8>
0x00436755:	pushl $0x433f7a<UINT32>
0x0043675a:	movl %eax, %fs:0
0x00436760:	pushl %eax
0x00436761:	pushl %ebx
0x00436762:	pushl %esi
0x00436763:	movl %eax, 0x44609c
0x00436768:	xorl %eax, %ebp
0x0043676a:	pushl %eax
0x0043676b:	leal %eax, -12(%ebp)
0x0043676e:	movl %fs:0, %eax
0x00436774:	xorl %eax, %eax
0x00436776:	movl -4(%ebp), %eax
0x00436779:	leal %ebx, 0x68(%eax)
0x0043677c:	movl %esi, $0x447784<UINT32>
0x00436781:	movb 0x448860, %al
0x00436786:	call 0x004034c0
0x0043678b:	movl %eax, 0x447784
0x00436790:	movl %ecx, 0x447788
0x00436796:	pushl $0x58<UINT8>
0x00436798:	addl %ecx, %eax
0x0043679a:	pushl $0x440830<UINT32>
0x0043679f:	pushl %ecx
0x004367a0:	call 0x00420c20
0x004367a5:	addl 0x447788, $0x58<UINT8>
0x004367ac:	pushl $0x439ec0<UINT32>
0x004367b1:	call 0x0041dcb8
0x004367b6:	addl %esp, $0x10<UINT8>
0x004367b9:	movl %ecx, -12(%ebp)
0x004367bc:	movl %fs:0, %ecx
0x004367c3:	popl %ecx
0x004367c4:	popl %esi
0x004367c5:	popl %ebx
0x004367c6:	movl %esp, %ebp
0x004367c8:	popl %ebp
0x004367c9:	ret

0x004367d0:	pushl %ebp
0x004367d1:	movl %ebp, %esp
0x004367d3:	pushl $0xffffffff<UINT8>
0x004367d5:	pushl $0x433faa<UINT32>
0x004367da:	movl %eax, %fs:0
0x004367e0:	pushl %eax
0x004367e1:	pushl %ebx
0x004367e2:	pushl %esi
0x004367e3:	movl %eax, 0x44609c
0x004367e8:	xorl %eax, %ebp
0x004367ea:	pushl %eax
0x004367eb:	leal %eax, -12(%ebp)
0x004367ee:	movl %fs:0, %eax
0x004367f4:	xorl %eax, %eax
0x004367f6:	movl -4(%ebp), %eax
0x004367f9:	leal %ebx, 0x28(%eax)
0x004367fc:	movl %esi, $0x447794<UINT32>
0x00436801:	movb 0x448860, %al
0x00436806:	call 0x004034c0
0x0043680b:	movl %ecx, 0x447798
0x00436811:	movl %eax, 0x447794
0x00436816:	movl %edx, 0x44088c
0x0043681c:	addl %eax, %ecx
0x0043681e:	movl (%eax), %edx
0x00436820:	movl %ecx, 0x440890
0x00436826:	movl 0x4(%eax), %ecx
0x00436829:	movl %edx, 0x440894
0x0043682f:	movl 0x8(%eax), %edx
0x00436832:	movl %ecx, 0x440898
0x00436838:	movl 0xc(%eax), %ecx
0x0043683b:	movl %edx, 0x44089c
0x00436841:	movl 0x10(%eax), %edx
0x00436844:	movl %ecx, 0x4408a0
0x0043684a:	movl 0x14(%eax), %ecx
0x0043684d:	addl 0x447798, $0x18<UINT8>
0x00436854:	pushl $0x439f00<UINT32>
0x00436859:	call 0x0041dcb8
0x0043685e:	addl %esp, $0x4<UINT8>
0x00436861:	movl %ecx, -12(%ebp)
0x00436864:	movl %fs:0, %ecx
0x0043686b:	popl %ecx
0x0043686c:	popl %esi
0x0043686d:	popl %ebx
0x0043686e:	movl %esp, %ebp
0x00436870:	popl %ebp
0x00436871:	ret

0x00436930:	pushl %ebp
0x00436931:	movl %ebp, %esp
0x00436933:	pushl $0xffffffff<UINT8>
0x00436935:	pushl $0x433fda<UINT32>
0x0043693a:	movl %eax, %fs:0
0x00436940:	pushl %eax
0x00436941:	pushl %ebx
0x00436942:	pushl %esi
0x00436943:	movl %eax, 0x44609c
0x00436948:	xorl %eax, %ebp
0x0043694a:	pushl %eax
0x0043694b:	leal %eax, -12(%ebp)
0x0043694e:	movl %fs:0, %eax
0x00436954:	xorl %eax, %eax
0x00436956:	movl -4(%ebp), %eax
0x00436959:	leal %ebx, 0x28(%eax)
0x0043695c:	movl %esi, $0x4477a4<UINT32>
0x00436961:	movb 0x448860, %al
0x00436966:	call 0x004034c0
0x0043696b:	movl %ecx, 0x4477a8
0x00436971:	movl %eax, 0x4477a4
0x00436976:	movl %edx, 0x4408a8
0x0043697c:	addl %eax, %ecx
0x0043697e:	movl (%eax), %edx
0x00436980:	movl %ecx, 0x4408ac
0x00436986:	movl 0x4(%eax), %ecx
0x00436989:	movl %edx, 0x4408b0
0x0043698f:	movl 0x8(%eax), %edx
0x00436992:	movl %ecx, 0x4408b4
0x00436998:	movl 0xc(%eax), %ecx
0x0043699b:	movl %edx, 0x4408b8
0x004369a1:	movl 0x10(%eax), %edx
0x004369a4:	movl %ecx, 0x4408bc
0x004369aa:	movl 0x14(%eax), %ecx
0x004369ad:	addl 0x4477a8, $0x18<UINT8>
0x004369b4:	pushl $0x439f80<UINT32>
0x004369b9:	call 0x0041dcb8
0x004369be:	addl %esp, $0x4<UINT8>
0x004369c1:	movl %ecx, -12(%ebp)
0x004369c4:	movl %fs:0, %ecx
0x004369cb:	popl %ecx
0x004369cc:	popl %esi
0x004369cd:	popl %ebx
0x004369ce:	movl %esp, %ebp
0x004369d0:	popl %ebp
0x004369d1:	ret

0x00436880:	pushl %ebp
0x00436881:	movl %ebp, %esp
0x00436883:	pushl $0xffffffff<UINT8>
0x00436885:	pushl $0x43400a<UINT32>
0x0043688a:	movl %eax, %fs:0
0x00436890:	pushl %eax
0x00436891:	pushl %ebx
0x00436892:	pushl %esi
0x00436893:	movl %eax, 0x44609c
0x00436898:	xorl %eax, %ebp
0x0043689a:	pushl %eax
0x0043689b:	leal %eax, -12(%ebp)
0x0043689e:	movl %fs:0, %eax
0x004368a4:	xorl %eax, %eax
0x004368a6:	movl -4(%ebp), %eax
0x004368a9:	leal %ebx, 0x28(%eax)
0x004368ac:	movl %esi, $0x4477b4<UINT32>
0x004368b1:	movb 0x448860, %al
0x004368b6:	call 0x004034c0
0x004368bb:	movl %ecx, 0x4477b8
0x004368c1:	movl %eax, 0x4477b4
0x004368c6:	movl %edx, 0x4408c4
0x004368cc:	addl %eax, %ecx
0x004368ce:	movl (%eax), %edx
0x004368d0:	movl %ecx, 0x4408c8
0x004368d6:	movl 0x4(%eax), %ecx
0x004368d9:	movl %edx, 0x4408cc
0x004368df:	movl 0x8(%eax), %edx
0x004368e2:	movl %ecx, 0x4408d0
0x004368e8:	movl 0xc(%eax), %ecx
0x004368eb:	movl %edx, 0x4408d4
0x004368f1:	movl 0x10(%eax), %edx
0x004368f4:	movl %ecx, 0x4408d8
0x004368fa:	movl 0x14(%eax), %ecx
0x004368fd:	addl 0x4477b8, $0x18<UINT8>
0x00436904:	pushl $0x439f40<UINT32>
0x00436909:	call 0x0041dcb8
0x0043690e:	addl %esp, $0x4<UINT8>
0x00436911:	movl %ecx, -12(%ebp)
0x00436914:	movl %fs:0, %ecx
0x0043691b:	popl %ecx
0x0043691c:	popl %esi
0x0043691d:	popl %ebx
0x0043691e:	movl %esp, %ebp
0x00436920:	popl %ebp
0x00436921:	ret

0x004369e0:	pushl %ebp
0x004369e1:	movl %ebp, %esp
0x004369e3:	pushl $0xffffffff<UINT8>
0x004369e5:	pushl $0x43403a<UINT32>
0x004369ea:	movl %eax, %fs:0
0x004369f0:	pushl %eax
0x004369f1:	pushl %ebx
0x004369f2:	pushl %esi
0x004369f3:	movl %eax, 0x44609c
0x004369f8:	xorl %eax, %ebp
0x004369fa:	pushl %eax
0x004369fb:	leal %eax, -12(%ebp)
0x004369fe:	movl %fs:0, %eax
0x00436a04:	xorl %eax, %eax
0x00436a06:	movl -4(%ebp), %eax
0x00436a09:	leal %ebx, 0x70(%eax)
0x00436a0c:	movl %esi, $0x4477c4<UINT32>
0x00436a11:	movb 0x448860, %al
0x00436a16:	call 0x004034c0
0x00436a1b:	movl %eax, 0x4477c4
0x00436a20:	movl %ecx, 0x4477c8
0x00436a26:	pushl $0x60<UINT8>
0x00436a28:	addl %ecx, %eax
0x00436a2a:	pushl $0x4408e0<UINT32>
0x00436a2f:	pushl %ecx
0x00436a30:	call 0x00420c20
0x00436a35:	addl 0x4477c8, $0x60<UINT8>
0x00436a3c:	pushl $0x439fc0<UINT32>
0x00436a41:	call 0x0041dcb8
0x00436a46:	addl %esp, $0x10<UINT8>
0x00436a49:	movl %ecx, -12(%ebp)
0x00436a4c:	movl %fs:0, %ecx
0x00436a53:	popl %ecx
0x00436a54:	popl %esi
0x00436a55:	popl %ebx
0x00436a56:	movl %esp, %ebp
0x00436a58:	popl %ebp
0x00436a59:	ret

0x00436a60:	pushl %ebp
0x00436a61:	movl %ebp, %esp
0x00436a63:	pushl $0xffffffff<UINT8>
0x00436a65:	pushl $0x43406a<UINT32>
0x00436a6a:	movl %eax, %fs:0
0x00436a70:	pushl %eax
0x00436a71:	pushl %ebx
0x00436a72:	pushl %esi
0x00436a73:	movl %eax, 0x44609c
0x00436a78:	xorl %eax, %ebp
0x00436a7a:	pushl %eax
0x00436a7b:	leal %eax, -12(%ebp)
0x00436a7e:	movl %fs:0, %eax
0x00436a84:	xorl %eax, %eax
0x00436a86:	movl -4(%ebp), %eax
0x00436a89:	leal %ebx, 0x28(%eax)
0x00436a8c:	movl %esi, $0x4477d4<UINT32>
0x00436a91:	movb 0x448860, %al
0x00436a96:	call 0x004034c0
0x00436a9b:	movl %ecx, 0x4477d8
0x00436aa1:	movl %eax, 0x4477d4
0x00436aa6:	movl %edx, 0x440944
0x00436aac:	addl %eax, %ecx
0x00436aae:	movl (%eax), %edx
0x00436ab0:	movl %ecx, 0x440948
0x00436ab6:	movl 0x4(%eax), %ecx
0x00436ab9:	movl %edx, 0x44094c
0x00436abf:	movl 0x8(%eax), %edx
0x00436ac2:	movl %ecx, 0x440950
0x00436ac8:	movl 0xc(%eax), %ecx
0x00436acb:	movl %edx, 0x440954
0x00436ad1:	movl 0x10(%eax), %edx
0x00436ad4:	movl %ecx, 0x440958
0x00436ada:	movl 0x14(%eax), %ecx
0x00436add:	addl 0x4477d8, $0x18<UINT8>
0x00436ae4:	pushl $0x43a000<UINT32>
0x00436ae9:	call 0x0041dcb8
0x00436aee:	addl %esp, $0x4<UINT8>
0x00436af1:	movl %ecx, -12(%ebp)
0x00436af4:	movl %fs:0, %ecx
0x00436afb:	popl %ecx
0x00436afc:	popl %esi
0x00436afd:	popl %ebx
0x00436afe:	movl %esp, %ebp
0x00436b00:	popl %ebp
0x00436b01:	ret

0x00436b10:	pushl %ebp
0x00436b11:	movl %ebp, %esp
0x00436b13:	pushl $0xffffffff<UINT8>
0x00436b15:	pushl $0x4340c2<UINT32>
0x00436b1a:	movl %eax, %fs:0
0x00436b20:	pushl %eax
0x00436b21:	pushl %ebx
0x00436b22:	pushl %esi
0x00436b23:	pushl %edi
0x00436b24:	movl %eax, 0x44609c
0x00436b29:	xorl %eax, %ebp
0x00436b2b:	pushl %eax
0x00436b2c:	leal %eax, -12(%ebp)
0x00436b2f:	movl %fs:0, %eax
0x00436b35:	xorl %eax, %eax
0x00436b37:	movl -4(%ebp), %eax
0x00436b3a:	leal %ebx, 0x48(%eax)
0x00436b3d:	movl %esi, $0x4477f0<UINT32>
0x00436b42:	movb 0x448860, %al
0x00436b47:	call 0x004034c0
0x00436b4c:	movl %eax, 0x4477f0
0x00436b51:	movl %ecx, 0x4477f4
0x00436b57:	pushl $0x38<UINT8>
0x00436b59:	addl %ecx, %eax
0x00436b5b:	pushl $0x43fc10<UINT32>
0x00436b60:	pushl %ecx
0x00436b61:	call 0x00420c20
0x00436b66:	addl 0x4477f4, $0x38<UINT8>
0x00436b6d:	addl %esp, $0xc<UINT8>
0x00436b70:	xorl %eax, %eax
0x00436b72:	movl %edi, $0x1<UINT32>
0x00436b77:	movl -4(%ebp), %edi
0x00436b7a:	movl 0x447800, %eax
0x00436b7f:	movl 0x447804, $0x1582<UINT32>
0x00436b89:	movl 0x447808, %eax
0x00436b8e:	movl 0x44780c, %eax
0x00436b93:	movl 0x447810, %eax
0x00436b98:	movl 0x447814, %eax
0x00436b9d:	movb -4(%ebp), $0x2<UINT8>
0x00436ba1:	movl %esi, $0x44780c<UINT32>
0x00436ba6:	movl 0x447818, %edi
0x00436bac:	movb 0x448860, %al
0x00436bb1:	call 0x004034c0
0x00436bb6:	movl %edx, 0x447810
0x00436bbc:	movl %eax, 0x44780c
0x00436bc1:	pushl $0x38<UINT8>
0x00436bc3:	addl %edx, %eax
0x00436bc5:	pushl $0x43fbd4<UINT32>
0x00436bca:	pushl %edx
0x00436bcb:	call 0x00420c20
0x00436bd0:	addl 0x447810, $0x38<UINT8>
0x00436bd7:	addl %esp, $0xc<UINT8>
0x00436bda:	xorl %eax, %eax
0x00436bdc:	movl 0x44781c, %eax
0x00436be1:	movl 0x447820, $0x1ab5<UINT32>
0x00436beb:	movl 0x447824, %edi
0x00436bf1:	movl 0x447828, %eax
0x00436bf6:	movl 0x44782c, %eax
0x00436bfb:	movl 0x447830, %eax
0x00436c00:	movb -4(%ebp), $0x4<UINT8>
0x00436c04:	leal %ebx, 0x3b(%edi)
0x00436c07:	movl %esi, $0x447828<UINT32>
0x00436c0c:	movl 0x447834, %edi
0x00436c12:	movb 0x448860, %al
0x00436c17:	call 0x004034c0
0x00436c1c:	movl %ecx, 0x44782c
0x00436c22:	movl %edx, 0x447828
0x00436c28:	pushl $0x2c<UINT8>
0x00436c2a:	addl %ecx, %edx
0x00436c2c:	pushl $0x43fba4<UINT32>
0x00436c31:	pushl %ecx
0x00436c32:	call 0x00420c20
0x00436c37:	addl 0x44782c, $0x2c<UINT8>
0x00436c3e:	pushl $0x43a040<UINT32>
0x00436c43:	movl 0x447838, $0x0<UINT32>
0x00436c4d:	call 0x0041dcb8
0x00436c52:	addl %esp, $0x10<UINT8>
0x00436c55:	movl %ecx, -12(%ebp)
0x00436c58:	movl %fs:0, %ecx
0x00436c5f:	popl %ecx
0x00436c60:	popl %edi
0x00436c61:	popl %esi
0x00436c62:	popl %ebx
0x00436c63:	movl %esp, %ebp
0x00436c65:	popl %ebp
0x00436c66:	ret

0x00436c70:	pushl %ebp
0x00436c71:	movl %ebp, %esp
0x00436c73:	pushl $0xffffffff<UINT8>
0x00436c75:	pushl $0x434162<UINT32>
0x00436c7a:	movl %eax, %fs:0
0x00436c80:	pushl %eax
0x00436c81:	pushl %ebx
0x00436c82:	pushl %esi
0x00436c83:	pushl %edi
0x00436c84:	movl %eax, 0x44609c
0x00436c89:	xorl %eax, %ebp
0x00436c8b:	pushl %eax
0x00436c8c:	leal %eax, -12(%ebp)
0x00436c8f:	movl %fs:0, %eax
0x00436c95:	xorl %eax, %eax
0x00436c97:	movl -4(%ebp), %eax
0x00436c9a:	leal %ebx, 0x1c(%eax)
0x00436c9d:	movl %esi, $0x447840<UINT32>
0x00436ca2:	movb 0x448860, %al
0x00436ca7:	call 0x004034c0
0x00436cac:	movl %eax, 0x447844
0x00436cb1:	movl %ecx, 0x447840
0x00436cb7:	movl %edx, 0x440a64
0x00436cbd:	addl %eax, %ecx
0x00436cbf:	movl (%eax), %edx
0x00436cc1:	movl %ecx, 0x440a68
0x00436cc7:	movl 0x4(%eax), %ecx
0x00436cca:	movl %edx, 0x440a6c
0x00436cd0:	movl 0x8(%eax), %edx
0x00436cd3:	addl 0x447844, $0xc<UINT8>
0x00436cda:	xorl %eax, %eax
0x00436cdc:	movl %edi, $0x1<UINT32>
0x00436ce1:	movl -4(%ebp), %edi
0x00436ce4:	movl 0x447850, %eax
0x00436ce9:	movl 0x447854, %eax
0x00436cee:	movl 0x447858, %eax
0x00436cf3:	movb -4(%ebp), $0x2<UINT8>
0x00436cf7:	movl %esi, $0x447850<UINT32>
0x00436cfc:	movl 0x44785c, %edi
0x00436d02:	movb 0x448860, %al
0x00436d07:	call 0x004034c0
0x00436d0c:	movl %ecx, 0x447854
0x00436d12:	movl %eax, 0x447850
0x00436d17:	movl %edx, 0x440a54
0x00436d1d:	addl %eax, %ecx
0x00436d1f:	movl (%eax), %edx
0x00436d21:	movl %ecx, 0x440a58
0x00436d27:	movl 0x4(%eax), %ecx
0x00436d2a:	movl %edx, 0x440a5c
0x00436d30:	movl 0x8(%eax), %edx
0x00436d33:	addl 0x447854, $0xc<UINT8>
0x00436d3a:	xorl %eax, %eax
0x00436d3c:	movl 0x447860, %eax
0x00436d41:	movl 0x447864, %eax
0x00436d46:	movl 0x447868, %eax
0x00436d4b:	movb -4(%ebp), $0x4<UINT8>
0x00436d4f:	leal %ebx, 0x27(%edi)
0x00436d52:	movl %esi, $0x447860<UINT32>
0x00436d57:	movl 0x44786c, %edi
0x00436d5d:	movb 0x448860, %al
0x00436d62:	call 0x004034c0
0x00436d67:	movl %eax, 0x447864
0x00436d6c:	movl %ecx, 0x447860
0x00436d72:	movl %edx, 0x440a38
0x00436d78:	addl %eax, %ecx
0x00436d7a:	movl (%eax), %edx
0x00436d7c:	movl %ecx, 0x440a3c
0x00436d82:	movl 0x4(%eax), %ecx
0x00436d85:	movl %edx, 0x440a40
0x00436d8b:	movl 0x8(%eax), %edx
0x00436d8e:	movl %ecx, 0x440a44
0x00436d94:	movl 0xc(%eax), %ecx
0x00436d97:	movl %edx, 0x440a48
0x00436d9d:	movl 0x10(%eax), %edx
0x00436da0:	movl %ecx, 0x440a4c
0x00436da6:	movl 0x14(%eax), %ecx
0x00436da9:	addl 0x447864, $0x18<UINT8>
0x00436db0:	xorl %eax, %eax
0x00436db2:	movl 0x447870, %eax
0x00436db7:	movl 0x447874, %eax
0x00436dbc:	movl 0x447878, %eax
0x00436dc1:	movb -4(%ebp), $0x6<UINT8>
0x00436dc5:	leal %ebx, 0x1b(%edi)
0x00436dc8:	movl %esi, $0x447870<UINT32>
0x00436dcd:	movl 0x44787c, %edi
0x00436dd3:	movb 0x448860, %al
0x00436dd8:	call 0x004034c0
0x00436ddd:	movl %edx, 0x447874
0x00436de3:	movl %eax, 0x447870
0x00436de8:	movl %ecx, 0x440a28
0x00436dee:	addl %eax, %edx
0x00436df0:	movl (%eax), %ecx
0x00436df2:	movl %edx, 0x440a2c
0x00436df8:	movl 0x4(%eax), %edx
0x00436dfb:	movl %ecx, 0x440a30
0x00436e01:	movl 0x8(%eax), %ecx
0x00436e04:	addl 0x447874, $0xc<UINT8>
0x00436e0b:	xorl %eax, %eax
0x00436e0d:	movl 0x447880, %eax
0x00436e12:	movl 0x447884, %eax
0x00436e17:	movl 0x447888, %eax
0x00436e1c:	movb -4(%ebp), $0x8<UINT8>
0x00436e20:	leal %ebx, 0x2f(%edi)
0x00436e23:	movl %esi, $0x447880<UINT32>
0x00436e28:	movl 0x44788c, %edi
0x00436e2e:	movb 0x448860, %al
0x00436e33:	call 0x004034c0
0x00436e38:	movl %edx, 0x447880
0x00436e3e:	movl %eax, 0x447884
0x00436e43:	pushl $0x20<UINT8>
0x00436e45:	addl %eax, %edx
0x00436e47:	pushl $0x440a04<UINT32>
0x00436e4c:	pushl %eax
0x00436e4d:	call 0x00420c20
0x00436e52:	addl 0x447884, $0x20<UINT8>
0x00436e59:	addl %esp, $0xc<UINT8>
0x00436e5c:	xorl %eax, %eax
0x00436e5e:	movl 0x447890, %eax
0x00436e63:	movl 0x447894, %eax
0x00436e68:	movl 0x447898, %eax
0x00436e6d:	movb -4(%ebp), $0xa<UINT8>
0x00436e71:	leal %ebx, 0x27(%edi)
0x00436e74:	movl %esi, $0x447890<UINT32>
0x00436e79:	movl 0x44789c, %edi
0x00436e7f:	movb 0x448860, %al
0x00436e84:	call 0x004034c0
0x00436e89:	movl %edx, 0x447894
0x00436e8f:	movl %ecx, 0x447890
0x00436e95:	leal %eax, (%edx,%ecx)
0x00436e98:	movl %ecx, 0x4409e8
0x00436e9e:	movl (%eax), %ecx
0x00436ea0:	movl %edx, 0x4409ec
0x00436ea6:	movl 0x4(%eax), %edx
0x00436ea9:	movl %ecx, 0x4409f0
0x00436eaf:	movl 0x8(%eax), %ecx
0x00436eb2:	movl %edx, 0x4409f4
0x00436eb8:	movl 0xc(%eax), %edx
0x00436ebb:	movl %ecx, 0x4409f8
0x00436ec1:	movl 0x10(%eax), %ecx
0x00436ec4:	movl %edx, 0x4409fc
0x00436eca:	movl 0x14(%eax), %edx
0x00436ecd:	addl 0x447894, $0x18<UINT8>
0x00436ed4:	xorl %eax, %eax
0x00436ed6:	movl 0x4478a0, %eax
0x00436edb:	movl 0x4478a4, %eax
0x00436ee0:	movl 0x4478a8, %eax
0x00436ee5:	movb -4(%ebp), $0xc<UINT8>
0x00436ee9:	movl %esi, $0x4478a0<UINT32>
0x00436eee:	movl 0x4478ac, %edi
0x00436ef4:	movb 0x448860, %al
0x00436ef9:	call 0x004034c0
0x00436efe:	movl %ecx, 0x4478a4
0x00436f04:	movl %eax, 0x4478a0
0x00436f09:	movl %edx, 0x4409cc
0x00436f0f:	addl %eax, %ecx
0x00436f11:	movl (%eax), %edx
0x00436f13:	movl %ecx, 0x4409d0
0x00436f19:	movl 0x4(%eax), %ecx
0x00436f1c:	movl %edx, 0x4409d4
0x00436f22:	movl 0x8(%eax), %edx
0x00436f25:	movl %ecx, 0x4409d8
0x00436f2b:	movl 0xc(%eax), %ecx
0x00436f2e:	movl %edx, 0x4409dc
0x00436f34:	movl 0x10(%eax), %edx
0x00436f37:	movl %ecx, 0x4409e0
0x00436f3d:	movl 0x14(%eax), %ecx
0x00436f40:	addl 0x4478a4, $0x18<UINT8>
0x00436f47:	pushl $0x43a060<UINT32>
0x00436f4c:	call 0x0041dcb8
0x00436f51:	addl %esp, $0x4<UINT8>
0x00436f54:	movl %ecx, -12(%ebp)
0x00436f57:	movl %fs:0, %ecx
0x00436f5e:	popl %ecx
0x00436f5f:	popl %edi
0x00436f60:	popl %esi
0x00436f61:	popl %ebx
0x00436f62:	movl %esp, %ebp
0x00436f64:	popl %ebp
0x00436f65:	ret

0x00436f70:	pushl %ebp
0x00436f71:	movl %ebp, %esp
0x00436f73:	pushl $0xffffffff<UINT8>
0x00436f75:	pushl $0x4341ee<UINT32>
0x00436f7a:	movl %eax, %fs:0
0x00436f80:	pushl %eax
0x00436f81:	pushl %ebx
0x00436f82:	pushl %esi
0x00436f83:	pushl %edi
0x00436f84:	movl %eax, 0x44609c
0x00436f89:	xorl %eax, %ebp
0x00436f8b:	pushl %eax
0x00436f8c:	leal %eax, -12(%ebp)
0x00436f8f:	movl %fs:0, %eax
0x00436f95:	xorl %eax, %eax
0x00436f97:	movl -4(%ebp), %eax
0x00436f9a:	leal %ebx, 0x28(%eax)
0x00436f9d:	movl %esi, $0x4478b0<UINT32>
0x00436fa2:	movb 0x448860, %al
0x00436fa7:	call 0x004034c0
0x00436fac:	movl %ecx, 0x4478b4
0x00436fb2:	movl %eax, 0x4478b0
0x00436fb7:	movl %edx, 0x440048
0x00436fbd:	addl %eax, %ecx
0x00436fbf:	movl (%eax), %edx
0x00436fc1:	movl %ecx, 0x44004c
0x00436fc7:	movl 0x4(%eax), %ecx
0x00436fca:	movl %edx, 0x440050
0x00436fd0:	movl 0x8(%eax), %edx
0x00436fd3:	movl %ecx, 0x440054
0x00436fd9:	movl 0xc(%eax), %ecx
0x00436fdc:	movl %edx, 0x440058
0x00436fe2:	movl 0x10(%eax), %edx
0x00436fe5:	movl %ecx, 0x44005c
0x00436feb:	movl 0x14(%eax), %ecx
0x00436fee:	addl 0x4478b4, $0x18<UINT8>
0x00436ff5:	xorl %eax, %eax
0x00436ff7:	movl %edi, $0x1<UINT32>
0x00436ffc:	movl -4(%ebp), %edi
0x00436fff:	movl 0x4478c0, %eax
0x00437004:	movl 0x4478c4, %eax
0x00437009:	movl 0x4478c8, %eax
0x0043700e:	movb -4(%ebp), $0x2<UINT8>
0x00437012:	movl %esi, $0x4478c0<UINT32>
0x00437017:	movl 0x4478cc, %edi
0x0043701d:	movb 0x448860, %al
0x00437022:	call 0x004034c0
0x00437027:	movl %edx, 0x4478c4
0x0043702d:	movl %eax, 0x4478c0
0x00437032:	movl %ecx, 0x44002c
0x00437038:	addl %eax, %edx
0x0043703a:	movl (%eax), %ecx
0x0043703c:	movl %edx, 0x440030
0x00437042:	movl 0x4(%eax), %edx
0x00437045:	movl %ecx, 0x440034
0x0043704b:	movl 0x8(%eax), %ecx
0x0043704e:	movl %edx, 0x440038
0x00437054:	movl 0xc(%eax), %edx
0x00437057:	movl %ecx, 0x44003c
0x0043705d:	movl 0x10(%eax), %ecx
0x00437060:	movl %edx, 0x440040
0x00437066:	movl 0x14(%eax), %edx
0x00437069:	addl 0x4478c4, $0x18<UINT8>
0x00437070:	xorl %eax, %eax
0x00437072:	movl 0x4478d0, %eax
0x00437077:	movl 0x4478d4, %eax
0x0043707c:	movl 0x4478d8, %eax
0x00437081:	movb -4(%ebp), $0x4<UINT8>
0x00437085:	movl %esi, $0x4478d0<UINT32>
0x0043708a:	movl 0x4478dc, %edi
0x00437090:	movb 0x448860, %al
0x00437095:	call 0x004034c0
0x0043709a:	movl %eax, 0x4478d4
0x0043709f:	movl %ecx, 0x4478d0
0x004370a5:	movl %edx, 0x440010
0x004370ab:	addl %eax, %ecx
0x004370ad:	movl (%eax), %edx
0x004370af:	movl %ecx, 0x440014
0x004370b5:	movl 0x4(%eax), %ecx
0x004370b8:	movl %edx, 0x440018
0x004370be:	movl 0x8(%eax), %edx
0x004370c1:	movl %ecx, 0x44001c
0x004370c7:	movl 0xc(%eax), %ecx
0x004370ca:	movl %edx, 0x440020
0x004370d0:	movl 0x10(%eax), %edx
0x004370d3:	movl %ecx, 0x440024
0x004370d9:	movl 0x14(%eax), %ecx
0x004370dc:	addl 0x4478d4, $0x18<UINT8>
0x004370e3:	xorl %eax, %eax
0x004370e5:	movl 0x4478e0, %eax
0x004370ea:	movl 0x4478e4, %eax
0x004370ef:	movl 0x4478e8, %eax
0x004370f4:	movb -4(%ebp), $0x6<UINT8>
0x004370f8:	movl %esi, $0x4478e0<UINT32>
0x004370fd:	movl 0x4478ec, %edi
0x00437103:	movb 0x448860, %al
0x00437108:	call 0x004034c0
0x0043710d:	movl %edx, 0x4478e0
0x00437113:	movl %eax, 0x4478e4
0x00437118:	movl %ecx, 0x43fff4
0x0043711e:	addl %eax, %edx
0x00437120:	movl (%eax), %ecx
0x00437122:	movl %edx, 0x43fff8
0x00437128:	movl 0x4(%eax), %edx
0x0043712b:	movl %ecx, 0x43fffc
0x00437131:	movl 0x8(%eax), %ecx
0x00437134:	movl %edx, 0x440000
0x0043713a:	movl 0xc(%eax), %edx
0x0043713d:	movl %ecx, 0x440004
0x00437143:	movl 0x10(%eax), %ecx
0x00437146:	movl %edx, 0x440008
0x0043714c:	movl 0x14(%eax), %edx
0x0043714f:	addl 0x4478e4, $0x18<UINT8>
0x00437156:	xorl %eax, %eax
0x00437158:	movl 0x4478f0, %eax
0x0043715d:	movl 0x4478f4, %eax
0x00437162:	movl 0x4478f8, %eax
0x00437167:	movb -4(%ebp), $0x8<UINT8>
0x0043716b:	movl %esi, $0x4478f0<UINT32>
0x00437170:	movl 0x4478fc, %edi
0x00437176:	movb 0x448860, %al
0x0043717b:	call 0x004034c0
0x00437180:	movl %ecx, 0x4478f4
0x00437186:	movl %eax, 0x4478f0
0x0043718b:	movl %edx, 0x43ffd8
0x00437191:	addl %eax, %ecx
0x00437193:	movl (%eax), %edx
0x00437195:	movl %ecx, 0x43ffdc
0x0043719b:	movl 0x4(%eax), %ecx
0x0043719e:	movl %edx, 0x43ffe0
0x004371a4:	movl 0x8(%eax), %edx
0x004371a7:	movl %ecx, 0x43ffe4
0x004371ad:	movl 0xc(%eax), %ecx
0x004371b0:	movl %edx, 0x43ffe8
0x004371b6:	movl 0x10(%eax), %edx
0x004371b9:	movl %ecx, 0x43ffec
0x004371bf:	movl 0x14(%eax), %ecx
0x004371c2:	addl 0x4478f4, $0x18<UINT8>
0x004371c9:	xorl %eax, %eax
0x004371cb:	movl 0x447900, %eax
0x004371d0:	movl 0x447904, %eax
0x004371d5:	movl 0x447908, %eax
0x004371da:	movb -4(%ebp), $0xa<UINT8>
0x004371de:	movl %esi, $0x447900<UINT32>
0x004371e3:	movl 0x44790c, %edi
0x004371e9:	movb 0x448860, %al
0x004371ee:	call 0x004034c0
0x004371f3:	movl %edx, 0x447900
0x004371f9:	movl %eax, 0x447904
0x004371fe:	movl %ecx, 0x43ffbc
0x00437204:	addl %eax, %edx
0x00437206:	movl (%eax), %ecx
0x00437208:	movl %edx, 0x43ffc0
0x0043720e:	movl 0x4(%eax), %edx
0x00437211:	movl %ecx, 0x43ffc4
0x00437217:	movl 0x8(%eax), %ecx
0x0043721a:	movl %edx, 0x43ffc8
0x00437220:	movl 0xc(%eax), %edx
0x00437223:	movl %ecx, 0x43ffcc
0x00437229:	movl 0x10(%eax), %ecx
0x0043722c:	movl %edx, 0x43ffd0
0x00437232:	movl 0x14(%eax), %edx
0x00437235:	addl 0x447904, $0x18<UINT8>
0x0043723c:	pushl $0x43a080<UINT32>
0x00437241:	call 0x0041dcb8
0x00437246:	addl %esp, $0x4<UINT8>
0x00437249:	movl %ecx, -12(%ebp)
0x0043724c:	movl %fs:0, %ecx
0x00437253:	popl %ecx
0x00437254:	popl %edi
0x00437255:	popl %esi
0x00437256:	popl %ebx
0x00437257:	movl %esp, %ebp
0x00437259:	popl %ebp
0x0043725a:	ret

0x00437260:	pushl %ebp
0x00437261:	movl %ebp, %esp
0x00437263:	pushl $0xffffffff<UINT8>
0x00437265:	pushl $0x434242<UINT32>
0x0043726a:	movl %eax, %fs:0
0x00437270:	pushl %eax
0x00437271:	pushl %ebx
0x00437272:	pushl %esi
0x00437273:	movl %eax, 0x44609c
0x00437278:	xorl %eax, %ebp
0x0043727a:	pushl %eax
0x0043727b:	leal %eax, -12(%ebp)
0x0043727e:	movl %fs:0, %eax
0x00437284:	xorl %eax, %eax
0x00437286:	movl -4(%ebp), %eax
0x00437289:	leal %ebx, 0x26(%eax)
0x0043728c:	movl %esi, $0x447910<UINT32>
0x00437291:	movb 0x448860, %al
0x00437296:	call 0x004034c0
0x0043729b:	movl %ecx, 0x447914
0x004372a1:	movl %eax, 0x447910
0x004372a6:	movl %edx, 0x440aa8
0x004372ac:	addl %eax, %ecx
0x004372ae:	movl (%eax), %edx
0x004372b0:	movl %ecx, 0x440aac
0x004372b6:	movl 0x4(%eax), %ecx
0x004372b9:	movl %edx, 0x440ab0
0x004372bf:	movl 0x8(%eax), %edx
0x004372c2:	movl %ecx, 0x440ab4
0x004372c8:	movl 0xc(%eax), %ecx
0x004372cb:	movl %edx, 0x440ab8
0x004372d1:	movl 0x10(%eax), %edx
0x004372d4:	movw %cx, 0x440abc
0x004372db:	movw 0x14(%eax), %cx
0x004372df:	addl 0x447914, $0x16<UINT8>
0x004372e6:	xorl %eax, %eax
0x004372e8:	movl -4(%ebp), $0x1<UINT32>
0x004372ef:	movl 0x447920, %eax
0x004372f4:	movl 0x447924, %eax
0x004372f9:	movl 0x447928, %eax
0x004372fe:	movb -4(%ebp), $0x2<UINT8>
0x00437302:	leal %ebx, 0x27(%eax)
0x00437305:	movl %esi, $0x447920<UINT32>
0x0043730a:	movl 0x44792c, $0x1<UINT32>
0x00437314:	movb 0x448860, %al
0x00437319:	call 0x004034c0
0x0043731e:	movl %edx, 0x447920
0x00437324:	movl %eax, 0x447924
0x00437329:	pushl $0x17<UINT8>
0x0043732b:	addl %eax, %edx
0x0043732d:	pushl $0x440a90<UINT32>
0x00437332:	pushl %eax
0x00437333:	call 0x00420c20
0x00420ca4:	jmp 0x00420d68
0x00420d58:	movl %eax, -20(%esi,%ecx,4)
0x00420d5c:	movl -20(%edi,%ecx,4), %eax
0x00420d60:	movl %eax, -16(%esi,%ecx,4)
0x00420d64:	movl -16(%edi,%ecx,4), %eax
0x00420d68:	movl %eax, -12(%esi,%ecx,4)
0x00420d6c:	movl -12(%edi,%ecx,4), %eax
0x00420d70:	movl %eax, -8(%esi,%ecx,4)
0x00420d74:	movl -8(%edi,%ecx,4), %eax
0x00420d78:	movl %eax, -4(%esi,%ecx,4)
0x00420d7c:	movl -4(%edi,%ecx,4), %eax
0x00420d80:	leal %eax, (,%ecx,4)
0x00420d87:	addl %esi, %eax
0x00420d89:	addl %edi, %eax
0x00420d8b:	jmp 0x00420da4
0x00437338:	addl 0x447924, $0x17<UINT8>
0x0043733f:	addl %esp, $0xc<UINT8>
0x00437342:	xorl %eax, %eax
0x00437344:	movl 0x447930, %eax
0x00437349:	movl 0x447934, %eax
0x0043734e:	movl 0x447938, %eax
0x00437353:	movb -4(%ebp), $0x4<UINT8>
0x00437357:	leal %ebx, 0x28(%eax)
0x0043735a:	movl %esi, $0x447930<UINT32>
0x0043735f:	movl 0x44793c, $0x1<UINT32>
0x00437369:	movb 0x448860, %al
0x0043736e:	call 0x004034c0
0x00437373:	movl %edx, 0x447934
0x00437379:	movl %ecx, 0x447930
0x0043737f:	leal %eax, (%edx,%ecx)
0x00437382:	movl %ecx, 0x440a74
0x00437388:	movl (%eax), %ecx
0x0043738a:	movl %edx, 0x440a78
0x00437390:	movl 0x4(%eax), %edx
0x00437393:	movl %ecx, 0x440a7c
0x00437399:	movl 0x8(%eax), %ecx
0x0043739c:	movl %edx, 0x440a80
0x004373a2:	movl 0xc(%eax), %edx
0x004373a5:	movl %ecx, 0x440a84
0x004373ab:	movl 0x10(%eax), %ecx
0x004373ae:	movl %edx, 0x440a88
0x004373b4:	movl 0x14(%eax), %edx
0x004373b7:	addl 0x447934, $0x18<UINT8>
0x004373be:	pushl $0x43a0a0<UINT32>
0x004373c3:	call 0x0041dcb8
0x004373c8:	addl %esp, $0x4<UINT8>
0x004373cb:	movl %ecx, -12(%ebp)
0x004373ce:	movl %fs:0, %ecx
0x004373d5:	popl %ecx
0x004373d6:	popl %esi
0x004373d7:	popl %ebx
0x004373d8:	movl %esp, %ebp
0x004373da:	popl %ebp
0x004373db:	ret

0x004373e0:	pushl %ebp
0x004373e1:	movl %ebp, %esp
0x004373e3:	pushl $0xffffffff<UINT8>
0x004373e5:	pushl $0x434292<UINT32>
0x004373ea:	movl %eax, %fs:0
0x004373f0:	pushl %eax
0x004373f1:	pushl %ebx
0x004373f2:	pushl %esi
0x004373f3:	movl %eax, 0x44609c
0x004373f8:	xorl %eax, %ebp
0x004373fa:	pushl %eax
0x004373fb:	leal %eax, -12(%ebp)
0x004373fe:	movl %fs:0, %eax
0x00437404:	xorl %eax, %eax
0x00437406:	movl -4(%ebp), %eax
0x00437409:	leal %ebx, 0x10(%eax)
0x0043740c:	movl %esi, $0x447940<UINT32>
0x00437411:	movb 0x448860, %al
0x00437416:	call 0x004034c0
0x0043741b:	movl %eax, 0x447944
0x00437420:	movl %ecx, 0x447940
0x00437426:	xorl %ebx, %ebx
0x00437428:	pushl %ebx
0x00437429:	addl %eax, %ecx
0x0043742b:	pushl $0x43fad1<UINT32>
0x00437430:	pushl %eax
0x00437431:	call 0x00420c20
0x00437436:	addl %esp, $0xc<UINT8>
0x00437439:	movl -4(%ebp), $0x1<UINT32>
0x00437440:	movl 0x447950, %ebx
0x00437446:	movl 0x447954, %ebx
0x0043744c:	movl 0x447958, %ebx
0x00437452:	movb -4(%ebp), $0x2<UINT8>
0x00437456:	movb 0x448860, %bl
0x0043745c:	movl %ebx, $0x19<UINT32>
0x00437461:	movl %esi, $0x447950<UINT32>
0x00437466:	movl 0x44795c, $0x1<UINT32>
0x00437470:	call 0x004034c0
0x00437475:	movl %edx, 0x447954
0x0043747b:	movl %eax, 0x447950
0x00437480:	movl %ecx, 0x440ad4
0x00437486:	addl %eax, %edx
0x00437488:	movl (%eax), %ecx
0x0043748a:	movl %edx, 0x440ad8
0x00437490:	movl 0x4(%eax), %edx
0x00437493:	movb %cl, 0x440adc
0x00437499:	movb 0x8(%eax), %cl
0x0043749c:	addl 0x447954, $0x9<UINT8>
0x004374a3:	xorl %eax, %eax
0x004374a5:	movl 0x447960, %eax
0x004374aa:	movl 0x447964, %eax
0x004374af:	movl 0x447968, %eax
0x004374b4:	movb -4(%ebp), $0x4<UINT8>
0x004374b8:	leal %ebx, 0x23(%eax)
0x004374bb:	movl %esi, $0x447960<UINT32>
0x004374c0:	movl 0x44796c, $0x1<UINT32>
0x004374ca:	movb 0x448860, %al
0x004374cf:	call 0x004034c0
0x004374d4:	movl %edx, 0x447964
0x004374da:	movl %eax, 0x447960
0x004374df:	movl %ecx, 0x440ac0
0x004374e5:	addl %eax, %edx
0x004374e7:	movl (%eax), %ecx
0x004374e9:	movl %edx, 0x440ac4
0x004374ef:	movl 0x4(%eax), %edx
0x004374f2:	movl %ecx, 0x440ac8
0x004374f8:	movl 0x8(%eax), %ecx
0x004374fb:	movl %edx, 0x440acc
0x00437501:	movl 0xc(%eax), %edx
0x00437504:	movw %cx, 0x440ad0
0x0043750b:	movw 0x10(%eax), %cx
0x0043750f:	movb %dl, 0x440ad2
0x00437515:	movb 0x12(%eax), %dl
0x00437518:	addl 0x447964, $0x13<UINT8>
0x0043751f:	pushl $0x43a0c0<UINT32>
0x00437524:	call 0x0041dcb8
0x00437529:	addl %esp, $0x4<UINT8>
0x0043752c:	movl %ecx, -12(%ebp)
0x0043752f:	movl %fs:0, %ecx
0x00437536:	popl %ecx
0x00437537:	popl %esi
0x00437538:	popl %ebx
0x00437539:	movl %esp, %ebp
0x0043753b:	popl %ebp
0x0043753c:	ret

0x00437540:	pushl %ebp
0x00437541:	movl %ebp, %esp
0x00437543:	pushl $0xffffffff<UINT8>
0x00437545:	pushl $0x4342e2<UINT32>
0x0043754a:	movl %eax, %fs:0
0x00437550:	pushl %eax
0x00437551:	pushl %ebx
0x00437552:	pushl %esi
0x00437553:	movl %eax, 0x44609c
0x00437558:	xorl %eax, %ebp
0x0043755a:	pushl %eax
0x0043755b:	leal %eax, -12(%ebp)
0x0043755e:	movl %fs:0, %eax
0x00437564:	xorl %eax, %eax
0x00437566:	movl -4(%ebp), %eax
0x00437569:	leal %ebx, 0x15(%eax)
0x0043756c:	movl %esi, $0x447970<UINT32>
0x00437571:	movb 0x448860, %al
0x00437576:	call 0x004034c0
0x0043757b:	movl %ecx, 0x447974
0x00437581:	movl %eax, 0x447970
0x00437586:	movl %edx, 0x440aec
0x0043758c:	addl %eax, %ecx
0x0043758e:	movl (%eax), %edx
0x00437590:	movzbl %ecx, 0x440af0
0x00437597:	movb 0x4(%eax), %cl
0x0043759a:	addl 0x447974, $0x5<UINT8>
0x004375a1:	xorl %eax, %eax
0x004375a3:	movl -4(%ebp), $0x1<UINT32>
0x004375aa:	movl 0x447980, %eax
0x004375af:	movl 0x447984, %eax
0x004375b4:	movl 0x447988, %eax
0x004375b9:	movb -4(%ebp), $0x2<UINT8>
0x004375bd:	leal %ebx, 0x19(%eax)
0x004375c0:	movl %esi, $0x447980<UINT32>
0x004375c5:	movl 0x44798c, $0x1<UINT32>
0x004375cf:	movb 0x448860, %al
0x004375d4:	call 0x004034c0
0x004375d9:	movl %edx, 0x447984
0x004375df:	movl %eax, 0x447980
0x004375e4:	movl %ecx, 0x440ae0
0x004375ea:	addl %eax, %edx
0x004375ec:	movl (%eax), %ecx
0x004375ee:	movl %edx, 0x440ae4
0x004375f4:	movl 0x4(%eax), %edx
0x004375f7:	movzbl %ecx, 0x440ae8
0x004375fe:	movb 0x8(%eax), %cl
0x00437601:	addl 0x447984, $0x9<UINT8>
0x00437608:	xorl %eax, %eax
0x0043760a:	movl 0x447990, %eax
0x0043760f:	movl 0x447994, %eax
0x00437614:	movl 0x447998, %eax
0x00437619:	movb -4(%ebp), $0x4<UINT8>
0x0043761d:	movl %esi, $0x447990<UINT32>
0x00437622:	movl 0x44799c, $0x1<UINT32>
0x0043762c:	movb 0x448860, %al
0x00437631:	call 0x004034c0
0x00437636:	movl %edx, 0x447990
0x0043763c:	movl %eax, 0x447994
0x00437641:	movl %ecx, 0x440ae0
0x00437647:	addl %eax, %edx
0x00437649:	movl (%eax), %ecx
0x0043764b:	movl %edx, 0x440ae4
0x00437651:	movl 0x4(%eax), %edx
0x00437654:	movzbl %ecx, 0x440ae8
0x0043765b:	movb 0x8(%eax), %cl
0x0043765e:	addl 0x447994, $0x9<UINT8>
0x00437665:	pushl $0x43a0e0<UINT32>
0x0043766a:	call 0x0041dcb8
0x0043766f:	addl %esp, $0x4<UINT8>
0x00437672:	movl %ecx, -12(%ebp)
0x00437675:	movl %fs:0, %ecx
0x0043767c:	popl %ecx
0x0043767d:	popl %esi
0x0043767e:	popl %ebx
0x0043767f:	movl %esp, %ebp
0x00437681:	popl %ebp
0x00437682:	ret

0x00437690:	pushl %ebp
0x00437691:	movl %ebp, %esp
0x00437693:	pushl $0xffffffff<UINT8>
0x00437695:	pushl $0x43430a<UINT32>
0x0043769a:	movl %eax, %fs:0
0x004376a0:	pushl %eax
0x004376a1:	pushl %ebx
0x004376a2:	pushl %esi
0x004376a3:	movl %eax, 0x44609c
0x004376a8:	xorl %eax, %ebp
0x004376aa:	pushl %eax
0x004376ab:	leal %eax, -12(%ebp)
0x004376ae:	movl %fs:0, %eax
0x004376b4:	xorl %eax, %eax
0x004376b6:	movl -4(%ebp), %eax
0x004376b9:	leal %ebx, 0x5e(%eax)
0x004376bc:	movl %esi, $0x4479a0<UINT32>
0x004376c1:	movb 0x448860, %al
0x004376c6:	call 0x004034c0
0x004376cb:	movl %eax, 0x4479a4
0x004376d0:	movl %ecx, 0x4479a0
0x004376d6:	pushl $0x4e<UINT8>
0x004376d8:	addl %eax, %ecx
0x004376da:	pushl $0x43fc50<UINT32>
0x004376df:	pushl %eax
0x004376e0:	call 0x00420c20
0x004376e5:	addl 0x4479a4, $0x4e<UINT8>
0x004376ec:	pushl $0x43a100<UINT32>
0x004376f1:	call 0x0041dcb8
0x004376f6:	addl %esp, $0x10<UINT8>
0x004376f9:	movl %ecx, -12(%ebp)
0x004376fc:	movl %fs:0, %ecx
0x00437703:	popl %ecx
0x00437704:	popl %esi
0x00437705:	popl %ebx
0x00437706:	movl %esp, %ebp
0x00437708:	popl %ebp
0x00437709:	ret

0x00437710:	pushl %ebp
0x00437711:	movl %ebp, %esp
0x00437713:	pushl $0xffffffff<UINT8>
0x00437715:	pushl $0x43433a<UINT32>
0x0043771a:	movl %eax, %fs:0
0x00437720:	pushl %eax
0x00437721:	pushl %ebx
0x00437722:	pushl %esi
0x00437723:	movl %eax, 0x44609c
0x00437728:	xorl %eax, %ebp
0x0043772a:	pushl %eax
0x0043772b:	leal %eax, -12(%ebp)
0x0043772e:	movl %fs:0, %eax
0x00437734:	xorl %eax, %eax
0x00437736:	movl -4(%ebp), %eax
0x00437739:	leal %ebx, 0x3f(%eax)
0x0043773c:	movl %esi, $0x4479b0<UINT32>
0x00437741:	movb 0x448860, %al
0x00437746:	call 0x004034c0
0x0043774b:	movl %eax, 0x4479b0
0x00437750:	movl %ecx, 0x4479b4
0x00437756:	pushl $0x2f<UINT8>
0x00437758:	addl %ecx, %eax
0x0043775a:	pushl $0x440594<UINT32>
0x0043775f:	pushl %ecx
0x00437760:	call 0x00420c20
0x00437765:	addl 0x4479b4, $0x2f<UINT8>
0x0043776c:	pushl $0x43a140<UINT32>
0x00437771:	call 0x0041dcb8
0x00437776:	addl %esp, $0x10<UINT8>
0x00437779:	movl %ecx, -12(%ebp)
0x0043777c:	movl %fs:0, %ecx
0x00437783:	popl %ecx
0x00437784:	popl %esi
0x00437785:	popl %ebx
0x00437786:	movl %esp, %ebp
0x00437788:	popl %ebp
0x00437789:	ret

0x00437790:	pushl %ebp
0x00437791:	movl %ebp, %esp
0x00437793:	pushl $0xffffffff<UINT8>
0x00437795:	pushl $0x43436a<UINT32>
0x0043779a:	movl %eax, %fs:0
0x004377a0:	pushl %eax
0x004377a1:	pushl %ebx
0x004377a2:	pushl %esi
0x004377a3:	movl %eax, 0x44609c
0x004377a8:	xorl %eax, %ebp
0x004377aa:	pushl %eax
0x004377ab:	leal %eax, -12(%ebp)
0x004377ae:	movl %fs:0, %eax
0x004377b4:	xorl %eax, %eax
0x004377b6:	movl -4(%ebp), %eax
0x004377b9:	leal %ebx, 0x48(%eax)
0x004377bc:	movl %esi, $0x4479c0<UINT32>
0x004377c1:	movb 0x448860, %al
0x004377c6:	call 0x004034c0
0x004377cb:	movl %eax, 0x4479c4
0x004377d0:	movl %ecx, 0x4479c0
0x004377d6:	pushl $0x38<UINT8>
0x004377d8:	addl %eax, %ecx
0x004377da:	pushl $0x4401b0<UINT32>
0x004377df:	pushl %eax
0x004377e0:	call 0x00420c20
0x004377e5:	addl 0x4479c4, $0x38<UINT8>
0x004377ec:	pushl $0x43a180<UINT32>
0x004377f1:	call 0x0041dcb8
0x004377f6:	addl %esp, $0x10<UINT8>
0x004377f9:	movl %ecx, -12(%ebp)
0x004377fc:	movl %fs:0, %ecx
0x00437803:	popl %ecx
0x00437804:	popl %esi
0x00437805:	popl %ebx
0x00437806:	movl %esp, %ebp
0x00437808:	popl %ebp
0x00437809:	ret

0x00437810:	pushl %ebp
0x00437811:	movl %ebp, %esp
0x00437813:	pushl $0xffffffff<UINT8>
0x00437815:	pushl $0x43439a<UINT32>
0x0043781a:	movl %eax, %fs:0
0x00437820:	pushl %eax
0x00437821:	pushl %ebx
0x00437822:	pushl %esi
0x00437823:	movl %eax, 0x44609c
0x00437828:	xorl %eax, %ebp
0x0043782a:	pushl %eax
0x0043782b:	leal %eax, -12(%ebp)
0x0043782e:	movl %fs:0, %eax
0x00437834:	xorl %eax, %eax
0x00437836:	movl -4(%ebp), %eax
0x00437839:	leal %ebx, 0x68(%eax)
0x0043783c:	movl %esi, $0x4479d0<UINT32>
0x00437841:	movb 0x448860, %al
0x00437846:	call 0x004034c0
0x0043784b:	movl %eax, 0x4479d0
0x00437850:	movl %ecx, 0x4479d4
0x00437856:	pushl $0x58<UINT8>
0x00437858:	addl %ecx, %eax
0x0043785a:	pushl $0x440830<UINT32>
0x0043785f:	pushl %ecx
0x00437860:	call 0x00420c20
0x00437865:	addl 0x4479d4, $0x58<UINT8>
0x0043786c:	pushl $0x43a1c0<UINT32>
0x00437871:	call 0x0041dcb8
0x00437876:	addl %esp, $0x10<UINT8>
0x00437879:	movl %ecx, -12(%ebp)
0x0043787c:	movl %fs:0, %ecx
0x00437883:	popl %ecx
0x00437884:	popl %esi
0x00437885:	popl %ebx
0x00437886:	movl %esp, %ebp
0x00437888:	popl %ebp
0x00437889:	ret

0x00437890:	pushl %ebp
0x00437891:	movl %ebp, %esp
0x00437893:	pushl $0xffffffff<UINT8>
0x00437895:	pushl $0x4343ca<UINT32>
0x0043789a:	movl %eax, %fs:0
0x004378a0:	pushl %eax
0x004378a1:	pushl %ebx
0x004378a2:	pushl %esi
0x004378a3:	movl %eax, 0x44609c
0x004378a8:	xorl %eax, %ebp
0x004378aa:	pushl %eax
0x004378ab:	leal %eax, -12(%ebp)
0x004378ae:	movl %fs:0, %eax
0x004378b4:	xorl %eax, %eax
0x004378b6:	movl -4(%ebp), %eax
0x004378b9:	leal %ebx, 0x28(%eax)
0x004378bc:	movl %esi, $0x4479e0<UINT32>
0x004378c1:	movb 0x448860, %al
0x004378c6:	call 0x004034c0
0x004378cb:	movl %ecx, 0x4479e4
0x004378d1:	movl %eax, 0x4479e0
0x004378d6:	movl %edx, 0x44088c
0x004378dc:	addl %eax, %ecx
0x004378de:	movl (%eax), %edx
0x004378e0:	movl %ecx, 0x440890
0x004378e6:	movl 0x4(%eax), %ecx
0x004378e9:	movl %edx, 0x440894
0x004378ef:	movl 0x8(%eax), %edx
0x004378f2:	movl %ecx, 0x440898
0x004378f8:	movl 0xc(%eax), %ecx
0x004378fb:	movl %edx, 0x44089c
0x00437901:	movl 0x10(%eax), %edx
0x00437904:	movl %ecx, 0x4408a0
0x0043790a:	movl 0x14(%eax), %ecx
0x0043790d:	addl 0x4479e4, $0x18<UINT8>
0x00437914:	pushl $0x43a200<UINT32>
0x00437919:	call 0x0041dcb8
0x0043791e:	addl %esp, $0x4<UINT8>
0x00437921:	movl %ecx, -12(%ebp)
0x00437924:	movl %fs:0, %ecx
0x0043792b:	popl %ecx
0x0043792c:	popl %esi
0x0043792d:	popl %ebx
0x0043792e:	movl %esp, %ebp
0x00437930:	popl %ebp
0x00437931:	ret

0x00437940:	pushl %ebp
0x00437941:	movl %ebp, %esp
0x00437943:	pushl $0xffffffff<UINT8>
0x00437945:	pushl $0x4343fa<UINT32>
0x0043794a:	movl %eax, %fs:0
0x00437950:	pushl %eax
0x00437951:	pushl %ebx
0x00437952:	pushl %esi
0x00437953:	movl %eax, 0x44609c
0x00437958:	xorl %eax, %ebp
0x0043795a:	pushl %eax
0x0043795b:	leal %eax, -12(%ebp)
0x0043795e:	movl %fs:0, %eax
0x00437964:	xorl %eax, %eax
0x00437966:	movl -4(%ebp), %eax
0x00437969:	leal %ebx, 0x28(%eax)
0x0043796c:	movl %esi, $0x4479f0<UINT32>
0x00437971:	movb 0x448860, %al
0x00437976:	call 0x004034c0
0x0043797b:	movl %ecx, 0x4479f4
0x00437981:	movl %eax, 0x4479f0
0x00437986:	movl %edx, 0x4408a8
0x0043798c:	addl %eax, %ecx
0x0043798e:	movl (%eax), %edx
0x00437990:	movl %ecx, 0x4408ac
0x00437996:	movl 0x4(%eax), %ecx
0x00437999:	movl %edx, 0x4408b0
0x0043799f:	movl 0x8(%eax), %edx
0x004379a2:	movl %ecx, 0x4408b4
0x004379a8:	movl 0xc(%eax), %ecx
0x004379ab:	movl %edx, 0x4408b8
0x004379b1:	movl 0x10(%eax), %edx
0x004379b4:	movl %ecx, 0x4408bc
0x004379ba:	movl 0x14(%eax), %ecx
0x004379bd:	addl 0x4479f4, $0x18<UINT8>
0x004379c4:	pushl $0x43a240<UINT32>
0x004379c9:	call 0x0041dcb8
0x004379ce:	addl %esp, $0x4<UINT8>
0x004379d1:	movl %ecx, -12(%ebp)
0x004379d4:	movl %fs:0, %ecx
0x004379db:	popl %ecx
0x004379dc:	popl %esi
0x004379dd:	popl %ebx
0x004379de:	movl %esp, %ebp
0x004379e0:	popl %ebp
0x004379e1:	ret

0x004379f0:	pushl %ebp
0x004379f1:	movl %ebp, %esp
0x004379f3:	pushl $0xffffffff<UINT8>
0x004379f5:	pushl $0x43442a<UINT32>
0x004379fa:	movl %eax, %fs:0
0x00437a00:	pushl %eax
0x00437a01:	pushl %ebx
0x00437a02:	pushl %esi
0x00437a03:	movl %eax, 0x44609c
0x00437a08:	xorl %eax, %ebp
0x00437a0a:	pushl %eax
0x00437a0b:	leal %eax, -12(%ebp)
0x00437a0e:	movl %fs:0, %eax
0x00437a14:	xorl %eax, %eax
0x00437a16:	movl -4(%ebp), %eax
0x00437a19:	leal %ebx, 0x28(%eax)
0x00437a1c:	movl %esi, $0x447a00<UINT32>
0x00437a21:	movb 0x448860, %al
0x00437a26:	call 0x004034c0
0x00437a2b:	movl %ecx, 0x447a04
0x00437a31:	movl %eax, 0x447a00
0x00437a36:	movl %edx, 0x4408c4
0x00437a3c:	addl %eax, %ecx
0x00437a3e:	movl (%eax), %edx
0x00437a40:	movl %ecx, 0x4408c8
0x00437a46:	movl 0x4(%eax), %ecx
0x00437a49:	movl %edx, 0x4408cc
0x00437a4f:	movl 0x8(%eax), %edx
0x00437a52:	movl %ecx, 0x4408d0
0x00437a58:	movl 0xc(%eax), %ecx
0x00437a5b:	movl %edx, 0x4408d4
0x00437a61:	movl 0x10(%eax), %edx
0x00437a64:	movl %ecx, 0x4408d8
0x00437a6a:	movl 0x14(%eax), %ecx
0x00437a6d:	addl 0x447a04, $0x18<UINT8>
0x00437a74:	pushl $0x43a280<UINT32>
0x00437a79:	call 0x0041dcb8
0x00437a7e:	addl %esp, $0x4<UINT8>
0x00437a81:	movl %ecx, -12(%ebp)
0x00437a84:	movl %fs:0, %ecx
0x00437a8b:	popl %ecx
0x00437a8c:	popl %esi
0x00437a8d:	popl %ebx
0x00437a8e:	movl %esp, %ebp
0x00437a90:	popl %ebp
0x00437a91:	ret

0x00437aa0:	pushl %ebp
0x00437aa1:	movl %ebp, %esp
0x00437aa3:	pushl $0xffffffff<UINT8>
0x00437aa5:	pushl $0x43445a<UINT32>
0x00437aaa:	movl %eax, %fs:0
0x00437ab0:	pushl %eax
0x00437ab1:	pushl %ebx
0x00437ab2:	pushl %esi
0x00437ab3:	movl %eax, 0x44609c
0x00437ab8:	xorl %eax, %ebp
0x00437aba:	pushl %eax
0x00437abb:	leal %eax, -12(%ebp)
0x00437abe:	movl %fs:0, %eax
0x00437ac4:	xorl %eax, %eax
0x00437ac6:	movl -4(%ebp), %eax
0x00437ac9:	leal %ebx, 0x70(%eax)
0x00437acc:	movl %esi, $0x447a10<UINT32>
0x00437ad1:	movb 0x448860, %al
0x00437ad6:	call 0x004034c0
0x00437adb:	movl %eax, 0x447a10
0x00437ae0:	movl %ecx, 0x447a14
0x00437ae6:	pushl $0x60<UINT8>
0x00437ae8:	addl %ecx, %eax
0x00437aea:	pushl $0x4408e0<UINT32>
0x00437aef:	pushl %ecx
0x00437af0:	call 0x00420c20
0x00437af5:	addl 0x447a14, $0x60<UINT8>
0x00437afc:	pushl $0x43a2c0<UINT32>
0x00437b01:	call 0x0041dcb8
0x00437b06:	addl %esp, $0x10<UINT8>
0x00437b09:	movl %ecx, -12(%ebp)
0x00437b0c:	movl %fs:0, %ecx
0x00437b13:	popl %ecx
0x00437b14:	popl %esi
0x00437b15:	popl %ebx
0x00437b16:	movl %esp, %ebp
0x00437b18:	popl %ebp
0x00437b19:	ret

0x00437b20:	pushl %ebp
0x00437b21:	movl %ebp, %esp
0x00437b23:	pushl $0xffffffff<UINT8>
0x00437b25:	pushl $0x43448a<UINT32>
0x00437b2a:	movl %eax, %fs:0
0x00437b30:	pushl %eax
0x00437b31:	pushl %ebx
0x00437b32:	pushl %esi
0x00437b33:	movl %eax, 0x44609c
0x00437b38:	xorl %eax, %ebp
0x00437b3a:	pushl %eax
0x00437b3b:	leal %eax, -12(%ebp)
0x00437b3e:	movl %fs:0, %eax
0x00437b44:	xorl %eax, %eax
0x00437b46:	movl -4(%ebp), %eax
0x00437b49:	leal %ebx, 0x28(%eax)
0x00437b4c:	movl %esi, $0x447a20<UINT32>
0x00437b51:	movb 0x448860, %al
0x00437b56:	call 0x004034c0
0x00437b5b:	movl %ecx, 0x447a24
0x00437b61:	movl %eax, 0x447a20
0x00437b66:	movl %edx, 0x440944
0x00437b6c:	addl %eax, %ecx
0x00437b6e:	movl (%eax), %edx
0x00437b70:	movl %ecx, 0x440948
0x00437b76:	movl 0x4(%eax), %ecx
0x00437b79:	movl %edx, 0x44094c
0x00437b7f:	movl 0x8(%eax), %edx
0x00437b82:	movl %ecx, 0x440950
0x00437b88:	movl 0xc(%eax), %ecx
0x00437b8b:	movl %edx, 0x440954
0x00437b91:	movl 0x10(%eax), %edx
0x00437b94:	movl %ecx, 0x440958
0x00437b9a:	movl 0x14(%eax), %ecx
0x00437b9d:	addl 0x447a24, $0x18<UINT8>
0x00437ba4:	pushl $0x43a300<UINT32>
0x00437ba9:	call 0x0041dcb8
0x00437bae:	addl %esp, $0x4<UINT8>
0x00437bb1:	movl %ecx, -12(%ebp)
0x00437bb4:	movl %fs:0, %ecx
0x00437bbb:	popl %ecx
0x00437bbc:	popl %esi
0x00437bbd:	popl %ebx
0x00437bbe:	movl %esp, %ebp
0x00437bc0:	popl %ebp
0x00437bc1:	ret

0x00437bd0:	pushl %ebp
0x00437bd1:	movl %ebp, %esp
0x00437bd3:	pushl $0xffffffff<UINT8>
0x00437bd5:	pushl $0x4344ba<UINT32>
0x00437bda:	movl %eax, %fs:0
0x00437be0:	pushl %eax
0x00437be1:	pushl %ebx
0x00437be2:	pushl %esi
0x00437be3:	movl %eax, 0x44609c
0x00437be8:	xorl %eax, %ebp
0x00437bea:	pushl %eax
0x00437beb:	leal %eax, -12(%ebp)
0x00437bee:	movl %fs:0, %eax
0x00437bf4:	xorl %eax, %eax
0x00437bf6:	movl -4(%ebp), %eax
0x00437bf9:	leal %ebx, 0x48(%eax)
0x00437bfc:	movl %esi, $0x447a30<UINT32>
0x00437c01:	movb 0x448860, %al
0x00437c06:	call 0x004034c0
0x00437c0b:	movl %eax, 0x447a34
0x00437c10:	movl %ecx, 0x447a30
0x00437c16:	pushl $0x38<UINT8>
0x00437c18:	addl %eax, %ecx
0x00437c1a:	pushl $0x43fca0<UINT32>
0x00437c1f:	pushl %eax
0x00437c20:	call 0x00420c20
0x00437c25:	addl 0x447a34, $0x38<UINT8>
0x00437c2c:	pushl $0x43a340<UINT32>
0x00437c31:	call 0x0041dcb8
0x00437c36:	addl %esp, $0x10<UINT8>
0x00437c39:	movl %ecx, -12(%ebp)
0x00437c3c:	movl %fs:0, %ecx
0x00437c43:	popl %ecx
0x00437c44:	popl %esi
0x00437c45:	popl %ebx
0x00437c46:	movl %esp, %ebp
0x00437c48:	popl %ebp
0x00437c49:	ret

0x00437c50:	pushl %ebp
0x00437c51:	movl %ebp, %esp
0x00437c53:	pushl $0xffffffff<UINT8>
0x00437c55:	pushl $0x4344ea<UINT32>
0x00437c5a:	movl %eax, %fs:0
0x00437c60:	pushl %eax
0x00437c61:	pushl %ebx
0x00437c62:	pushl %esi
0x00437c63:	movl %eax, 0x44609c
0x00437c68:	xorl %eax, %ebp
0x00437c6a:	pushl %eax
0x00437c6b:	leal %eax, -12(%ebp)
0x00437c6e:	movl %fs:0, %eax
0x00437c74:	xorl %eax, %eax
0x00437c76:	movl -4(%ebp), %eax
0x00437c79:	leal %ebx, 0x48(%eax)
0x00437c7c:	movl %esi, $0x447a40<UINT32>
0x00437c81:	movb 0x448860, %al
0x00437c86:	call 0x004034c0
0x00437c8b:	movl %eax, 0x447a44
0x00437c90:	movl %ecx, 0x447a40
0x00437c96:	pushl $0x38<UINT8>
0x00437c98:	addl %eax, %ecx
0x00437c9a:	pushl $0x43fcdc<UINT32>
0x00437c9f:	pushl %eax
0x00437ca0:	call 0x00420c20
0x00437ca5:	addl 0x447a44, $0x38<UINT8>
0x00437cac:	pushl $0x43a380<UINT32>
0x00437cb1:	call 0x0041dcb8
0x00437cb6:	addl %esp, $0x10<UINT8>
0x00437cb9:	movl %ecx, -12(%ebp)
0x00437cbc:	movl %fs:0, %ecx
0x00437cc3:	popl %ecx
0x00437cc4:	popl %esi
0x00437cc5:	popl %ebx
0x00437cc6:	movl %esp, %ebp
0x00437cc8:	popl %ebp
0x00437cc9:	ret

0x00437cd0:	pushl %ebp
0x00437cd1:	movl %ebp, %esp
0x00437cd3:	pushl $0xffffffff<UINT8>
0x00437cd5:	pushl $0x43451a<UINT32>
0x00437cda:	movl %eax, %fs:0
0x00437ce0:	pushl %eax
0x00437ce1:	pushl %ebx
0x00437ce2:	pushl %esi
0x00437ce3:	movl %eax, 0x44609c
0x00437ce8:	xorl %eax, %ebp
0x00437cea:	pushl %eax
0x00437ceb:	leal %eax, -12(%ebp)
0x00437cee:	movl %fs:0, %eax
0x00437cf4:	xorl %eax, %eax
0x00437cf6:	movl -4(%ebp), %eax
0x00437cf9:	leal %ebx, 0x48(%eax)
0x00437cfc:	movl %esi, $0x447a50<UINT32>
0x00437d01:	movb 0x448860, %al
0x00437d06:	call 0x004034c0
0x00437d0b:	movl %eax, 0x447a54
0x00437d10:	movl %ecx, 0x447a50
0x00437d16:	pushl $0x38<UINT8>
0x00437d18:	addl %eax, %ecx
0x00437d1a:	pushl $0x43fd18<UINT32>
0x00437d1f:	pushl %eax
0x00437d20:	call 0x00420c20
0x00437d25:	addl 0x447a54, $0x38<UINT8>
0x00437d2c:	pushl $0x43a3c0<UINT32>
0x00437d31:	call 0x0041dcb8
0x00437d36:	addl %esp, $0x10<UINT8>
0x00437d39:	movl %ecx, -12(%ebp)
0x00437d3c:	movl %fs:0, %ecx
0x00437d43:	popl %ecx
0x00437d44:	popl %esi
0x00437d45:	popl %ebx
0x00437d46:	movl %esp, %ebp
0x00437d48:	popl %ebp
0x00437d49:	ret

0x00437d50:	pushl %ebp
0x00437d51:	movl %ebp, %esp
0x00437d53:	pushl $0xffffffff<UINT8>
0x00437d55:	pushl $0x43454a<UINT32>
0x00437d5a:	movl %eax, %fs:0
0x00437d60:	pushl %eax
0x00437d61:	pushl %ebx
0x00437d62:	pushl %esi
0x00437d63:	movl %eax, 0x44609c
0x00437d68:	xorl %eax, %ebp
0x00437d6a:	pushl %eax
0x00437d6b:	leal %eax, -12(%ebp)
0x00437d6e:	movl %fs:0, %eax
0x00437d74:	xorl %eax, %eax
0x00437d76:	movl -4(%ebp), %eax
0x00437d79:	leal %ebx, 0x48(%eax)
0x00437d7c:	movl %esi, $0x447a60<UINT32>
0x00437d81:	movb 0x448860, %al
0x00437d86:	call 0x004034c0
0x00437d8b:	movl %eax, 0x447a64
0x00437d90:	movl %ecx, 0x447a60
0x00437d96:	pushl $0x38<UINT8>
0x00437d98:	addl %eax, %ecx
0x00437d9a:	pushl $0x43fd54<UINT32>
0x00437d9f:	pushl %eax
0x00437da0:	call 0x00420c20
0x00437da5:	addl 0x447a64, $0x38<UINT8>
0x00437dac:	pushl $0x43a400<UINT32>
0x00437db1:	call 0x0041dcb8
0x00437db6:	addl %esp, $0x10<UINT8>
0x00437db9:	movl %ecx, -12(%ebp)
0x00437dbc:	movl %fs:0, %ecx
0x00437dc3:	popl %ecx
0x00437dc4:	popl %esi
0x00437dc5:	popl %ebx
0x00437dc6:	movl %esp, %ebp
0x00437dc8:	popl %ebp
0x00437dc9:	ret

0x00437dd0:	pushl %ebp
0x00437dd1:	movl %ebp, %esp
0x00437dd3:	pushl $0xffffffff<UINT8>
0x00437dd5:	pushl $0x43457a<UINT32>
0x00437dda:	movl %eax, %fs:0
0x00437de0:	pushl %eax
0x00437de1:	pushl %ebx
0x00437de2:	pushl %esi
0x00437de3:	movl %eax, 0x44609c
0x00437de8:	xorl %eax, %ebp
0x00437dea:	pushl %eax
0x00437deb:	leal %eax, -12(%ebp)
0x00437dee:	movl %fs:0, %eax
0x00437df4:	xorl %eax, %eax
0x00437df6:	movl -4(%ebp), %eax
0x00437df9:	leal %ebx, 0x48(%eax)
0x00437dfc:	movl %esi, $0x447a70<UINT32>
0x00437e01:	movb 0x448860, %al
0x00437e06:	call 0x004034c0
0x00437e0b:	movl %eax, 0x447a74
0x00437e10:	movl %ecx, 0x447a70
0x00437e16:	pushl $0x38<UINT8>
0x00437e18:	addl %eax, %ecx
0x00437e1a:	pushl $0x43fd90<UINT32>
0x00437e1f:	pushl %eax
0x00437e20:	call 0x00420c20
0x00437e25:	addl 0x447a74, $0x38<UINT8>
0x00437e2c:	pushl $0x43a440<UINT32>
0x00437e31:	call 0x0041dcb8
0x00437e36:	addl %esp, $0x10<UINT8>
0x00437e39:	movl %ecx, -12(%ebp)
0x00437e3c:	movl %fs:0, %ecx
0x00437e43:	popl %ecx
0x00437e44:	popl %esi
0x00437e45:	popl %ebx
0x00437e46:	movl %esp, %ebp
0x00437e48:	popl %ebp
0x00437e49:	ret

0x00437e50:	pushl %ebp
0x00437e51:	movl %ebp, %esp
0x00437e53:	pushl $0xffffffff<UINT8>
0x00437e55:	pushl $0x4345aa<UINT32>
0x00437e5a:	movl %eax, %fs:0
0x00437e60:	pushl %eax
0x00437e61:	pushl %ebx
0x00437e62:	pushl %esi
0x00437e63:	movl %eax, 0x44609c
0x00437e68:	xorl %eax, %ebp
0x00437e6a:	pushl %eax
0x00437e6b:	leal %eax, -12(%ebp)
0x00437e6e:	movl %fs:0, %eax
0x00437e74:	xorl %eax, %eax
0x00437e76:	movl -4(%ebp), %eax
0x00437e79:	leal %ebx, 0x48(%eax)
0x00437e7c:	movl %esi, $0x447a80<UINT32>
0x00437e81:	movb 0x448860, %al
0x00437e86:	call 0x004034c0
0x00437e8b:	movl %eax, 0x447a84
0x00437e90:	movl %ecx, 0x447a80
0x00437e96:	pushl $0x38<UINT8>
0x00437e98:	addl %eax, %ecx
0x00437e9a:	pushl $0x43fdcc<UINT32>
0x00437e9f:	pushl %eax
0x00437ea0:	call 0x00420c20
0x00437ea5:	addl 0x447a84, $0x38<UINT8>
0x00437eac:	pushl $0x43a480<UINT32>
0x00437eb1:	call 0x0041dcb8
0x00437eb6:	addl %esp, $0x10<UINT8>
0x00437eb9:	movl %ecx, -12(%ebp)
0x00437ebc:	movl %fs:0, %ecx
0x00437ec3:	popl %ecx
0x00437ec4:	popl %esi
0x00437ec5:	popl %ebx
0x00437ec6:	movl %esp, %ebp
0x00437ec8:	popl %ebp
0x00437ec9:	ret

0x00437ed0:	pushl %ebp
0x00437ed1:	movl %ebp, %esp
0x00437ed3:	pushl $0xffffffff<UINT8>
0x00437ed5:	pushl $0x4345da<UINT32>
0x00437eda:	movl %eax, %fs:0
0x00437ee0:	pushl %eax
0x00437ee1:	pushl %ebx
0x00437ee2:	pushl %esi
0x00437ee3:	movl %eax, 0x44609c
0x00437ee8:	xorl %eax, %ebp
0x00437eea:	pushl %eax
0x00437eeb:	leal %eax, -12(%ebp)
0x00437eee:	movl %fs:0, %eax
0x00437ef4:	xorl %eax, %eax
0x00437ef6:	movl -4(%ebp), %eax
0x00437ef9:	leal %ebx, 0x48(%eax)
0x00437efc:	movl %esi, $0x447a90<UINT32>
0x00437f01:	movb 0x448860, %al
0x00437f06:	call 0x004034c0
0x00437f0b:	movl %eax, 0x447a94
0x00437f10:	movl %ecx, 0x447a90
0x00437f16:	pushl $0x38<UINT8>
0x00437f18:	addl %eax, %ecx
0x00437f1a:	pushl $0x43fe08<UINT32>
0x00437f1f:	pushl %eax
0x00437f20:	call 0x00420c20
0x00437f25:	addl 0x447a94, $0x38<UINT8>
0x00437f2c:	pushl $0x43a4c0<UINT32>
0x00437f31:	call 0x0041dcb8
0x00437f36:	addl %esp, $0x10<UINT8>
0x00437f39:	movl %ecx, -12(%ebp)
0x00437f3c:	movl %fs:0, %ecx
0x00437f43:	popl %ecx
0x00437f44:	popl %esi
0x00437f45:	popl %ebx
0x00437f46:	movl %esp, %ebp
0x00437f48:	popl %ebp
0x00437f49:	ret

0x00437f50:	pushl %ebp
0x00437f51:	movl %ebp, %esp
0x00437f53:	pushl $0xffffffff<UINT8>
0x00437f55:	pushl $0x43460a<UINT32>
0x00437f5a:	movl %eax, %fs:0
0x00437f60:	pushl %eax
0x00437f61:	pushl %ebx
0x00437f62:	pushl %esi
0x00437f63:	movl %eax, 0x44609c
0x00437f68:	xorl %eax, %ebp
0x00437f6a:	pushl %eax
0x00437f6b:	leal %eax, -12(%ebp)
0x00437f6e:	movl %fs:0, %eax
0x00437f74:	xorl %eax, %eax
0x00437f76:	movl -4(%ebp), %eax
0x00437f79:	leal %ebx, 0x48(%eax)
0x00437f7c:	movl %esi, $0x447aa0<UINT32>
0x00437f81:	movb 0x448860, %al
0x00437f86:	call 0x004034c0
0x00437f8b:	movl %eax, 0x447aa4
0x00437f90:	movl %ecx, 0x447aa0
0x00437f96:	pushl $0x38<UINT8>
0x00437f98:	addl %eax, %ecx
0x00437f9a:	pushl $0x43fe44<UINT32>
0x00437f9f:	pushl %eax
0x00437fa0:	call 0x00420c20
0x00437fa5:	addl 0x447aa4, $0x38<UINT8>
0x00437fac:	pushl $0x43a500<UINT32>
0x00437fb1:	call 0x0041dcb8
0x00437fb6:	addl %esp, $0x10<UINT8>
0x00437fb9:	movl %ecx, -12(%ebp)
0x00437fbc:	movl %fs:0, %ecx
0x00437fc3:	popl %ecx
0x00437fc4:	popl %esi
0x00437fc5:	popl %ebx
0x00437fc6:	movl %esp, %ebp
0x00437fc8:	popl %ebp
0x00437fc9:	ret

0x00437fd0:	pushl %ebp
0x00437fd1:	movl %ebp, %esp
0x00437fd3:	pushl $0xffffffff<UINT8>
0x00437fd5:	pushl $0x43463a<UINT32>
0x00437fda:	movl %eax, %fs:0
0x00437fe0:	pushl %eax
0x00437fe1:	pushl %ebx
0x00437fe2:	pushl %esi
0x00437fe3:	movl %eax, 0x44609c
0x00437fe8:	xorl %eax, %ebp
0x00437fea:	pushl %eax
0x00437feb:	leal %eax, -12(%ebp)
0x00437fee:	movl %fs:0, %eax
0x00437ff4:	xorl %eax, %eax
0x00437ff6:	movl -4(%ebp), %eax
0x00437ff9:	leal %ebx, 0x48(%eax)
0x00437ffc:	movl %esi, $0x447ab0<UINT32>
0x00438001:	movb 0x448860, %al
0x00438006:	call 0x004034c0
0x0043800b:	movl %eax, 0x447ab4
0x00438010:	movl %ecx, 0x447ab0
0x00438016:	pushl $0x38<UINT8>
0x00438018:	addl %eax, %ecx
0x0043801a:	pushl $0x43fe80<UINT32>
0x0043801f:	pushl %eax
0x00438020:	call 0x00420c20
0x00438025:	addl 0x447ab4, $0x38<UINT8>
0x0043802c:	pushl $0x43a540<UINT32>
0x00438031:	call 0x0041dcb8
0x00438036:	addl %esp, $0x10<UINT8>
0x00438039:	movl %ecx, -12(%ebp)
0x0043803c:	movl %fs:0, %ecx
0x00438043:	popl %ecx
0x00438044:	popl %esi
0x00438045:	popl %ebx
0x00438046:	movl %esp, %ebp
0x00438048:	popl %ebp
0x00438049:	ret

0x00438050:	pushl %ebp
0x00438051:	movl %ebp, %esp
0x00438053:	pushl $0xffffffff<UINT8>
0x00438055:	pushl $0x43466a<UINT32>
0x0043805a:	movl %eax, %fs:0
0x00438060:	pushl %eax
0x00438061:	pushl %ebx
0x00438062:	pushl %esi
0x00438063:	movl %eax, 0x44609c
0x00438068:	xorl %eax, %ebp
0x0043806a:	pushl %eax
0x0043806b:	leal %eax, -12(%ebp)
0x0043806e:	movl %fs:0, %eax
0x00438074:	xorl %eax, %eax
0x00438076:	movl -4(%ebp), %eax
0x00438079:	leal %ebx, 0x17(%eax)
0x0043807c:	movl %esi, $0x447ac0<UINT32>
0x00438081:	movb 0x448860, %al
0x00438086:	call 0x004034c0
0x0043808b:	movl %eax, 0x447ac4
0x00438090:	movl %ecx, 0x447ac0
0x00438096:	movl %edx, 0x43febc
0x0043809c:	addl %eax, %ecx
0x0043809e:	movl (%eax), %edx
0x004380a0:	movw %cx, 0x43fec0
0x004380a7:	movw 0x4(%eax), %cx
0x004380ab:	movb %dl, 0x43fec2
0x004380b1:	movb 0x6(%eax), %dl
0x004380b4:	addl 0x447ac4, $0x7<UINT8>
0x004380bb:	pushl $0x43a580<UINT32>
0x004380c0:	call 0x0041dcb8
0x004380c5:	addl %esp, $0x4<UINT8>
0x004380c8:	movl %ecx, -12(%ebp)
0x004380cb:	movl %fs:0, %ecx
0x004380d2:	popl %ecx
0x004380d3:	popl %esi
0x004380d4:	popl %ebx
0x004380d5:	movl %esp, %ebp
0x004380d7:	popl %ebp
0x004380d8:	ret

0x004380e0:	pushl %ebp
0x004380e1:	movl %ebp, %esp
0x004380e3:	pushl $0xffffffff<UINT8>
0x004380e5:	pushl $0x43469a<UINT32>
0x004380ea:	movl %eax, %fs:0
0x004380f0:	pushl %eax
0x004380f1:	pushl %ebx
0x004380f2:	pushl %esi
0x004380f3:	movl %eax, 0x44609c
0x004380f8:	xorl %eax, %ebp
0x004380fa:	pushl %eax
0x004380fb:	leal %eax, -12(%ebp)
0x004380fe:	movl %fs:0, %eax
0x00438104:	xorl %eax, %eax
0x00438106:	movl -4(%ebp), %eax
0x00438109:	leal %ebx, 0x1c(%eax)
0x0043810c:	movl %esi, $0x447ad0<UINT32>
0x00438111:	movb 0x448860, %al
0x00438116:	call 0x004034c0
0x0043811b:	movl %eax, 0x447ad4
0x00438120:	movl %ecx, 0x447ad0
0x00438126:	movl %edx, 0x43fec4
0x0043812c:	addl %eax, %ecx
0x0043812e:	movl (%eax), %edx
0x00438130:	movl %ecx, 0x43fec8
0x00438136:	movl 0x4(%eax), %ecx
0x00438139:	movl %edx, 0x43fecc
0x0043813f:	movl 0x8(%eax), %edx
0x00438142:	addl 0x447ad4, $0xc<UINT8>
0x00438149:	pushl $0x43a5c0<UINT32>
0x0043814e:	call 0x0041dcb8
0x00438153:	addl %esp, $0x4<UINT8>
0x00438156:	movl %ecx, -12(%ebp)
0x00438159:	movl %fs:0, %ecx
0x00438160:	popl %ecx
0x00438161:	popl %esi
0x00438162:	popl %ebx
0x00438163:	movl %esp, %ebp
0x00438165:	popl %ebp
0x00438166:	ret

0x00438170:	pushl %ebp
0x00438171:	movl %ebp, %esp
0x00438173:	pushl $0xffffffff<UINT8>
0x00438175:	pushl $0x4346ca<UINT32>
0x0043817a:	movl %eax, %fs:0
0x00438180:	pushl %eax
0x00438181:	pushl %ebx
0x00438182:	pushl %esi
0x00438183:	movl %eax, 0x44609c
0x00438188:	xorl %eax, %ebp
0x0043818a:	pushl %eax
0x0043818b:	leal %eax, -12(%ebp)
0x0043818e:	movl %fs:0, %eax
0x00438194:	xorl %eax, %eax
0x00438196:	movl -4(%ebp), %eax
0x00438199:	leal %ebx, 0x1a(%eax)
0x0043819c:	movl %esi, $0x447ae0<UINT32>
0x004381a1:	movb 0x448860, %al
0x004381a6:	call 0x004034c0
0x004381ab:	movl %eax, 0x447ae4
0x004381b0:	movl %ecx, 0x447ae0
0x004381b6:	movl %edx, 0x43fed4
0x004381bc:	addl %eax, %ecx
0x004381be:	movl (%eax), %edx
0x004381c0:	movl %ecx, 0x43fed8
0x004381c6:	movl 0x4(%eax), %ecx
0x004381c9:	movw %dx, 0x43fedc
0x004381d0:	movw 0x8(%eax), %dx
0x004381d4:	addl 0x447ae4, $0xa<UINT8>
0x004381db:	pushl $0x43a600<UINT32>
0x004381e0:	call 0x0041dcb8
0x004381e5:	addl %esp, $0x4<UINT8>
0x004381e8:	movl %ecx, -12(%ebp)
0x004381eb:	movl %fs:0, %ecx
0x004381f2:	popl %ecx
0x004381f3:	popl %esi
0x004381f4:	popl %ebx
0x004381f5:	movl %esp, %ebp
0x004381f7:	popl %ebp
0x004381f8:	ret

0x00438200:	pushl %ebp
0x00438201:	movl %ebp, %esp
0x00438203:	pushl $0xffffffff<UINT8>
0x00438205:	pushl $0x4346fa<UINT32>
0x0043820a:	movl %eax, %fs:0
0x00438210:	pushl %eax
0x00438211:	pushl %ebx
0x00438212:	pushl %esi
0x00438213:	movl %eax, 0x44609c
0x00438218:	xorl %eax, %ebp
0x0043821a:	pushl %eax
0x0043821b:	leal %eax, -12(%ebp)
0x0043821e:	movl %fs:0, %eax
0x00438224:	xorl %eax, %eax
0x00438226:	movl -4(%ebp), %eax
0x00438229:	leal %ebx, 0x18(%eax)
0x0043822c:	movl %esi, $0x447af0<UINT32>
0x00438231:	movb 0x448860, %al
0x00438236:	call 0x004034c0
0x0043823b:	movl %eax, 0x447af4
0x00438240:	movl %ecx, 0x447af0
0x00438246:	movl %edx, 0x43fee0
0x0043824c:	addl %eax, %ecx
0x0043824e:	movl (%eax), %edx
0x00438250:	movl %ecx, 0x43fee4
0x00438256:	movl 0x4(%eax), %ecx
0x00438259:	addl 0x447af4, $0x8<UINT8>
0x00438260:	pushl $0x43a640<UINT32>
0x00438265:	call 0x0041dcb8
0x0043826a:	addl %esp, $0x4<UINT8>
0x0043826d:	movl %ecx, -12(%ebp)
0x00438270:	movl %fs:0, %ecx
0x00438277:	popl %ecx
0x00438278:	popl %esi
0x00438279:	popl %ebx
0x0043827a:	movl %esp, %ebp
0x0043827c:	popl %ebp
0x0043827d:	ret

0x00438280:	pushl %ebp
0x00438281:	movl %ebp, %esp
0x00438283:	pushl $0xffffffff<UINT8>
0x00438285:	pushl $0x43472a<UINT32>
0x0043828a:	movl %eax, %fs:0
0x00438290:	pushl %eax
0x00438291:	pushl %ebx
0x00438292:	pushl %esi
0x00438293:	movl %eax, 0x44609c
0x00438298:	xorl %eax, %ebp
0x0043829a:	pushl %eax
0x0043829b:	leal %eax, -12(%ebp)
0x0043829e:	movl %fs:0, %eax
0x004382a4:	xorl %eax, %eax
0x004382a6:	movl -4(%ebp), %eax
0x004382a9:	leal %ebx, 0x16(%eax)
0x004382ac:	movl %esi, $0x447b00<UINT32>
0x004382b1:	movb 0x448860, %al
0x004382b6:	call 0x004034c0
0x004382bb:	movl %eax, 0x447b04
0x004382c0:	movl %ecx, 0x447b00
0x004382c6:	movl %edx, 0x43feec
0x004382cc:	addl %eax, %ecx
0x004382ce:	movl (%eax), %edx
0x004382d0:	movw %cx, 0x43fef0
0x004382d7:	movw 0x4(%eax), %cx
0x004382db:	addl 0x447b04, $0x6<UINT8>
0x004382e2:	pushl $0x43a680<UINT32>
0x004382e7:	call 0x0041dcb8
0x004382ec:	addl %esp, $0x4<UINT8>
0x004382ef:	movl %ecx, -12(%ebp)
0x004382f2:	movl %fs:0, %ecx
0x004382f9:	popl %ecx
0x004382fa:	popl %esi
0x004382fb:	popl %ebx
0x004382fc:	movl %esp, %ebp
0x004382fe:	popl %ebp
0x004382ff:	ret

0x00438300:	pushl %ebp
0x00438301:	movl %ebp, %esp
0x00438303:	pushl $0xffffffff<UINT8>
0x00438305:	pushl $0x43475a<UINT32>
0x0043830a:	movl %eax, %fs:0
0x00438310:	pushl %eax
0x00438311:	pushl %ebx
0x00438312:	pushl %esi
0x00438313:	movl %eax, 0x44609c
0x00438318:	xorl %eax, %ebp
0x0043831a:	pushl %eax
0x0043831b:	leal %eax, -12(%ebp)
0x0043831e:	movl %fs:0, %eax
0x00438324:	xorl %eax, %eax
0x00438326:	movl -4(%ebp), %eax
0x00438329:	leal %ebx, 0x1b(%eax)
0x0043832c:	movl %esi, $0x447b10<UINT32>
0x00438331:	movb 0x448860, %al
0x00438336:	call 0x004034c0
0x0043833b:	movl %eax, 0x447b14
0x00438340:	movl %ecx, 0x447b10
0x00438346:	movl %edx, 0x43fef4
0x0043834c:	addl %eax, %ecx
0x0043834e:	movl (%eax), %edx
0x00438350:	movl %ecx, 0x43fef8
0x00438356:	movl 0x4(%eax), %ecx
0x00438359:	movw %dx, 0x43fefc
0x00438360:	movw 0x8(%eax), %dx
0x00438364:	movb %cl, 0x43fefe
0x0043836a:	movb 0xa(%eax), %cl
0x0043836d:	addl 0x447b14, $0xb<UINT8>
0x00438374:	pushl $0x43a6c0<UINT32>
0x00438379:	call 0x0041dcb8
0x0043837e:	addl %esp, $0x4<UINT8>
0x00438381:	movl %ecx, -12(%ebp)
0x00438384:	movl %fs:0, %ecx
0x0043838b:	popl %ecx
0x0043838c:	popl %esi
0x0043838d:	popl %ebx
0x0043838e:	movl %esp, %ebp
0x00438390:	popl %ebp
0x00438391:	ret

0x004383a0:	pushl %ebp
0x004383a1:	movl %ebp, %esp
0x004383a3:	pushl $0xffffffff<UINT8>
0x004383a5:	pushl $0x43478a<UINT32>
0x004383aa:	movl %eax, %fs:0
0x004383b0:	pushl %eax
0x004383b1:	pushl %ebx
0x004383b2:	pushl %esi
0x004383b3:	movl %eax, 0x44609c
0x004383b8:	xorl %eax, %ebp
0x004383ba:	pushl %eax
0x004383bb:	leal %eax, -12(%ebp)
0x004383be:	movl %fs:0, %eax
0x004383c4:	xorl %eax, %eax
0x004383c6:	movl -4(%ebp), %eax
0x004383c9:	leal %ebx, 0x23(%eax)
0x004383cc:	movl %esi, $0x447b20<UINT32>
0x004383d1:	movb 0x448860, %al
0x004383d6:	call 0x004034c0
0x004383db:	movl %ecx, 0x447b24
0x004383e1:	movl %eax, 0x447b20
0x004383e6:	movl %edx, 0x43ff00
0x004383ec:	addl %eax, %ecx
0x004383ee:	movl (%eax), %edx
0x004383f0:	movl %ecx, 0x43ff04
0x004383f6:	movl 0x4(%eax), %ecx
0x004383f9:	movl %edx, 0x43ff08
0x004383ff:	movl 0x8(%eax), %edx
0x00438402:	movl %ecx, 0x43ff0c
0x00438408:	movl 0xc(%eax), %ecx
0x0043840b:	movw %dx, 0x43ff10
0x00438412:	movw 0x10(%eax), %dx
0x00438416:	movb %cl, 0x43ff12
0x0043841c:	movb 0x12(%eax), %cl
0x0043841f:	addl 0x447b24, $0x13<UINT8>
0x00438426:	pushl $0x43a700<UINT32>
0x0043842b:	call 0x0041dcb8
0x00438430:	addl %esp, $0x4<UINT8>
0x00438433:	movl %ecx, -12(%ebp)
0x00438436:	movl %fs:0, %ecx
0x0043843d:	popl %ecx
0x0043843e:	popl %esi
0x0043843f:	popl %ebx
0x00438440:	movl %esp, %ebp
0x00438442:	popl %ebp
0x00438443:	ret

0x00438450:	pushl %ebp
0x00438451:	movl %ebp, %esp
0x00438453:	pushl $0xffffffff<UINT8>
0x00438455:	pushl $0x4347ba<UINT32>
0x0043845a:	movl %eax, %fs:0
0x00438460:	pushl %eax
0x00438461:	pushl %ebx
0x00438462:	pushl %esi
0x00438463:	movl %eax, 0x44609c
0x00438468:	xorl %eax, %ebp
0x0043846a:	pushl %eax
0x0043846b:	leal %eax, -12(%ebp)
0x0043846e:	movl %fs:0, %eax
0x00438474:	xorl %eax, %eax
0x00438476:	movl -4(%ebp), %eax
0x00438479:	leal %ebx, 0x24(%eax)
0x0043847c:	movl %esi, $0x447b30<UINT32>
0x00438481:	movb 0x448860, %al
0x00438486:	call 0x004034c0
0x0043848b:	movl %ecx, 0x447b34
0x00438491:	movl %eax, 0x447b30
0x00438496:	movl %edx, 0x4405c4
0x0043849c:	addl %eax, %ecx
0x0043849e:	movl (%eax), %edx
0x004384a0:	movl %ecx, 0x4405c8
0x004384a6:	movl 0x4(%eax), %ecx
0x004384a9:	movl %edx, 0x4405cc
0x004384af:	movl 0x8(%eax), %edx
0x004384b2:	movl %ecx, 0x4405d0
0x004384b8:	movl 0xc(%eax), %ecx
0x004384bb:	movl %edx, 0x4405d4
0x004384c1:	movl 0x10(%eax), %edx
0x004384c4:	addl 0x447b34, $0x14<UINT8>
0x004384cb:	pushl $0x43a740<UINT32>
0x004384d0:	call 0x0041dcb8
0x004384d5:	addl %esp, $0x4<UINT8>
0x004384d8:	movl %ecx, -12(%ebp)
0x004384db:	movl %fs:0, %ecx
0x004384e2:	popl %ecx
0x004384e3:	popl %esi
0x004384e4:	popl %ebx
0x004384e5:	movl %esp, %ebp
0x004384e7:	popl %ebp
0x004384e8:	ret

0x004384f0:	pushl %ebp
0x004384f1:	movl %ebp, %esp
0x004384f3:	pushl $0xffffffff<UINT8>
0x004384f5:	pushl $0x4347ea<UINT32>
0x004384fa:	movl %eax, %fs:0
0x00438500:	pushl %eax
0x00438501:	pushl %ebx
0x00438502:	pushl %esi
0x00438503:	movl %eax, 0x44609c
0x00438508:	xorl %eax, %ebp
0x0043850a:	pushl %eax
0x0043850b:	leal %eax, -12(%ebp)
0x0043850e:	movl %fs:0, %eax
0x00438514:	xorl %eax, %eax
0x00438516:	movl -4(%ebp), %eax
0x00438519:	leal %ebx, 0x21(%eax)
0x0043851c:	movl %esi, $0x447b40<UINT32>
0x00438521:	movb 0x448860, %al
0x00438526:	call 0x004034c0
0x0043852b:	movl %ecx, 0x447b44
0x00438531:	movl %eax, 0x447b40
0x00438536:	movl %edx, 0x4405dc
0x0043853c:	addl %eax, %ecx
0x0043853e:	movl (%eax), %edx
0x00438540:	movl %ecx, 0x4405e0
0x00438546:	movl 0x4(%eax), %ecx
0x00438549:	movl %edx, 0x4405e4
0x0043854f:	movl 0x8(%eax), %edx
0x00438552:	movl %ecx, 0x4405e8
0x00438558:	movl 0xc(%eax), %ecx
0x0043855b:	movb %dl, 0x4405ec
0x00438561:	movb 0x10(%eax), %dl
0x00438564:	addl 0x447b44, $0x11<UINT8>
0x0043856b:	pushl $0x43a780<UINT32>
0x00438570:	call 0x0041dcb8
0x00438575:	addl %esp, $0x4<UINT8>
0x00438578:	movl %ecx, -12(%ebp)
0x0043857b:	movl %fs:0, %ecx
0x00438582:	popl %ecx
0x00438583:	popl %esi
0x00438584:	popl %ebx
0x00438585:	movl %esp, %ebp
0x00438587:	popl %ebp
0x00438588:	ret

0x00438590:	pushl %ebp
0x00438591:	movl %ebp, %esp
0x00438593:	pushl $0xffffffff<UINT8>
0x00438595:	pushl $0x43481a<UINT32>
0x0043859a:	movl %eax, %fs:0
0x004385a0:	pushl %eax
0x004385a1:	pushl %ebx
0x004385a2:	pushl %esi
0x004385a3:	movl %eax, 0x44609c
0x004385a8:	xorl %eax, %ebp
0x004385aa:	pushl %eax
0x004385ab:	leal %eax, -12(%ebp)
0x004385ae:	movl %fs:0, %eax
0x004385b4:	xorl %eax, %eax
0x004385b6:	movl -4(%ebp), %eax
0x004385b9:	leal %ebx, 0x1e(%eax)
0x004385bc:	movl %esi, $0x447b50<UINT32>
0x004385c1:	movb 0x448860, %al
0x004385c6:	call 0x004034c0
0x004385cb:	movl %eax, 0x447b54
0x004385d0:	movl %ecx, 0x447b50
0x004385d6:	movl %edx, 0x43ff14
0x004385dc:	addl %eax, %ecx
0x004385de:	movl (%eax), %edx
0x004385e0:	movl %ecx, 0x43ff18
0x004385e6:	movl 0x4(%eax), %ecx
0x004385e9:	movl %edx, 0x43ff1c
0x004385ef:	movl 0x8(%eax), %edx
0x004385f2:	movw %cx, 0x43ff20
0x004385f9:	movw 0xc(%eax), %cx
0x004385fd:	addl 0x447b54, $0xe<UINT8>
0x00438604:	pushl $0x43a7c0<UINT32>
0x00438609:	call 0x0041dcb8
0x0043860e:	addl %esp, $0x4<UINT8>
0x00438611:	movl %ecx, -12(%ebp)
0x00438614:	movl %fs:0, %ecx
0x0043861b:	popl %ecx
0x0043861c:	popl %esi
0x0043861d:	popl %ebx
0x0043861e:	movl %esp, %ebp
0x00438620:	popl %ebp
0x00438621:	ret

0x00438630:	pushl %ebp
0x00438631:	movl %ebp, %esp
0x00438633:	pushl $0xffffffff<UINT8>
0x00438635:	pushl $0x43484a<UINT32>
0x0043863a:	movl %eax, %fs:0
0x00438640:	pushl %eax
0x00438641:	pushl %ebx
0x00438642:	pushl %esi
0x00438643:	movl %eax, 0x44609c
0x00438648:	xorl %eax, %ebp
0x0043864a:	pushl %eax
0x0043864b:	leal %eax, -12(%ebp)
0x0043864e:	movl %fs:0, %eax
0x00438654:	xorl %eax, %eax
0x00438656:	movl -4(%ebp), %eax
0x00438659:	leal %ebx, 0x1e(%eax)
0x0043865c:	movl %esi, $0x447b60<UINT32>
0x00438661:	movb 0x448860, %al
0x00438666:	call 0x004034c0
0x0043866b:	movl %eax, 0x447b64
0x00438670:	movl %ecx, 0x447b60
0x00438676:	movl %edx, 0x4405f0
0x0043867c:	addl %eax, %ecx
0x0043867e:	movl (%eax), %edx
0x00438680:	movl %ecx, 0x4405f4
0x00438686:	movl 0x4(%eax), %ecx
0x00438689:	movl %edx, 0x4405f8
0x0043868f:	movl 0x8(%eax), %edx
0x00438692:	movw %cx, 0x4405fc
0x00438699:	movw 0xc(%eax), %cx
0x0043869d:	addl 0x447b64, $0xe<UINT8>
0x004386a4:	pushl $0x43a800<UINT32>
0x004386a9:	call 0x0041dcb8
0x004386ae:	addl %esp, $0x4<UINT8>
0x004386b1:	movl %ecx, -12(%ebp)
0x004386b4:	movl %fs:0, %ecx
0x004386bb:	popl %ecx
0x004386bc:	popl %esi
0x004386bd:	popl %ebx
0x004386be:	movl %esp, %ebp
0x004386c0:	popl %ebp
0x004386c1:	ret

0x004386d0:	pushl %ebp
0x004386d1:	movl %ebp, %esp
0x004386d3:	pushl $0xffffffff<UINT8>
0x004386d5:	pushl $0x43487a<UINT32>
0x004386da:	movl %eax, %fs:0
0x004386e0:	pushl %eax
0x004386e1:	pushl %ebx
0x004386e2:	pushl %esi
0x004386e3:	movl %eax, 0x44609c
0x004386e8:	xorl %eax, %ebp
0x004386ea:	pushl %eax
0x004386eb:	leal %eax, -12(%ebp)
0x004386ee:	movl %fs:0, %eax
0x004386f4:	xorl %eax, %eax
0x004386f6:	movl -4(%ebp), %eax
0x004386f9:	leal %ebx, 0x15(%eax)
0x004386fc:	movl %esi, $0x447b70<UINT32>
0x00438701:	movb 0x448860, %al
0x00438706:	call 0x004034c0
0x0043870b:	movl %eax, 0x447b74
0x00438710:	movl %ecx, 0x447b70
0x00438716:	movl %edx, 0x440600
0x0043871c:	addl %eax, %ecx
0x0043871e:	movl (%eax), %edx
0x00438720:	movb %cl, 0x440604
0x00438726:	movb 0x4(%eax), %cl
0x00438729:	addl 0x447b74, $0x5<UINT8>
0x00438730:	pushl $0x43a840<UINT32>
0x00438735:	call 0x0041dcb8
0x0043873a:	addl %esp, $0x4<UINT8>
0x0043873d:	movl %ecx, -12(%ebp)
0x00438740:	movl %fs:0, %ecx
0x00438747:	popl %ecx
0x00438748:	popl %esi
0x00438749:	popl %ebx
0x0043874a:	movl %esp, %ebp
0x0043874c:	popl %ebp
0x0043874d:	ret

0x00438750:	pushl %ebp
0x00438751:	movl %ebp, %esp
0x00438753:	pushl $0xffffffff<UINT8>
0x00438755:	pushl $0x4348aa<UINT32>
0x0043875a:	movl %eax, %fs:0
0x00438760:	pushl %eax
0x00438761:	pushl %ebx
0x00438762:	pushl %esi
0x00438763:	movl %eax, 0x44609c
0x00438768:	xorl %eax, %ebp
0x0043876a:	pushl %eax
0x0043876b:	leal %eax, -12(%ebp)
0x0043876e:	movl %fs:0, %eax
0x00438774:	xorl %eax, %eax
0x00438776:	movl -4(%ebp), %eax
0x00438779:	leal %ebx, 0x18(%eax)
0x0043877c:	movl %esi, $0x447b80<UINT32>
0x00438781:	movb 0x448860, %al
0x00438786:	call 0x004034c0
0x0043878b:	movl %eax, 0x447b84
0x00438790:	movl %ecx, 0x447b80
0x00438796:	movl %edx, 0x440af4
0x0043879c:	addl %eax, %ecx
0x0043879e:	movl (%eax), %edx
0x004387a0:	movl %ecx, 0x440af8
0x004387a6:	movl 0x4(%eax), %ecx
0x004387a9:	addl 0x447b84, $0x8<UINT8>
0x004387b0:	pushl $0x43a880<UINT32>
0x004387b5:	call 0x0041dcb8
0x004387ba:	addl %esp, $0x4<UINT8>
0x004387bd:	movl %ecx, -12(%ebp)
0x004387c0:	movl %fs:0, %ecx
0x004387c7:	popl %ecx
0x004387c8:	popl %esi
0x004387c9:	popl %ebx
0x004387ca:	movl %esp, %ebp
0x004387cc:	popl %ebp
0x004387cd:	ret

0x004387d0:	pushl %ebp
0x004387d1:	movl %ebp, %esp
0x004387d3:	pushl $0xffffffff<UINT8>
0x004387d5:	pushl $0x4348da<UINT32>
0x004387da:	movl %eax, %fs:0
0x004387e0:	pushl %eax
0x004387e1:	pushl %ebx
0x004387e2:	pushl %esi
0x004387e3:	movl %eax, 0x44609c
0x004387e8:	xorl %eax, %ebp
0x004387ea:	pushl %eax
0x004387eb:	leal %eax, -12(%ebp)
0x004387ee:	movl %fs:0, %eax
0x004387f4:	xorl %eax, %eax
0x004387f6:	movl -4(%ebp), %eax
0x004387f9:	leal %ebx, 0x18(%eax)
0x004387fc:	movl %esi, $0x447b90<UINT32>
0x00438801:	movb 0x448860, %al
0x00438806:	call 0x004034c0
0x0043880b:	movl %eax, 0x447b94
0x00438810:	movl %ecx, 0x447b90
0x00438816:	movl %edx, 0x43ff24
0x0043881c:	addl %eax, %ecx
0x0043881e:	movl (%eax), %edx
0x00438820:	movl %ecx, 0x43ff28
0x00438826:	movl 0x4(%eax), %ecx
0x00438829:	addl 0x447b94, $0x8<UINT8>
0x00438830:	pushl $0x43a8c0<UINT32>
0x00438835:	call 0x0041dcb8
0x0043883a:	addl %esp, $0x4<UINT8>
0x0043883d:	movl %ecx, -12(%ebp)
0x00438840:	movl %fs:0, %ecx
0x00438847:	popl %ecx
0x00438848:	popl %esi
0x00438849:	popl %ebx
0x0043884a:	movl %esp, %ebp
0x0043884c:	popl %ebp
0x0043884d:	ret

0x00438850:	pushl %ebp
0x00438851:	movl %ebp, %esp
0x00438853:	pushl $0xffffffff<UINT8>
0x00438855:	pushl $0x43490a<UINT32>
0x0043885a:	movl %eax, %fs:0
0x00438860:	pushl %eax
0x00438861:	pushl %ebx
0x00438862:	pushl %esi
0x00438863:	movl %eax, 0x44609c
0x00438868:	xorl %eax, %ebp
0x0043886a:	pushl %eax
0x0043886b:	leal %eax, -12(%ebp)
0x0043886e:	movl %fs:0, %eax
0x00438874:	xorl %eax, %eax
0x00438876:	movl -4(%ebp), %eax
0x00438879:	leal %ebx, 0x1b(%eax)
0x0043887c:	movl %esi, $0x447ba0<UINT32>
0x00438881:	movb 0x448860, %al
0x00438886:	call 0x004034c0
0x0043888b:	movl %eax, 0x447ba4
0x00438890:	movl %ecx, 0x447ba0
0x00438896:	movl %edx, 0x43ff30
0x0043889c:	addl %eax, %ecx
0x0043889e:	movl (%eax), %edx
0x004388a0:	movl %ecx, 0x43ff34
0x004388a6:	movl 0x4(%eax), %ecx
0x004388a9:	movw %dx, 0x43ff38
0x004388b0:	movw 0x8(%eax), %dx
0x004388b4:	movb %cl, 0x43ff3a
0x004388ba:	movb 0xa(%eax), %cl
0x004388bd:	addl 0x447ba4, $0xb<UINT8>
0x004388c4:	pushl $0x43a900<UINT32>
0x004388c9:	call 0x0041dcb8
0x004388ce:	addl %esp, $0x4<UINT8>
0x004388d1:	movl %ecx, -12(%ebp)
0x004388d4:	movl %fs:0, %ecx
0x004388db:	popl %ecx
0x004388dc:	popl %esi
0x004388dd:	popl %ebx
0x004388de:	movl %esp, %ebp
0x004388e0:	popl %ebp
0x004388e1:	ret

0x004388f0:	pushl %ebp
0x004388f1:	movl %ebp, %esp
0x004388f3:	pushl $0xffffffff<UINT8>
0x004388f5:	pushl $0x43493a<UINT32>
0x004388fa:	movl %eax, %fs:0
0x00438900:	pushl %eax
0x00438901:	pushl %ebx
0x00438902:	pushl %esi
0x00438903:	movl %eax, 0x44609c
0x00438908:	xorl %eax, %ebp
0x0043890a:	pushl %eax
0x0043890b:	leal %eax, -12(%ebp)
0x0043890e:	movl %fs:0, %eax
0x00438914:	xorl %eax, %eax
0x00438916:	movl -4(%ebp), %eax
0x00438919:	leal %ebx, 0x15(%eax)
0x0043891c:	movl %esi, $0x447bb0<UINT32>
0x00438921:	movb 0x448860, %al
0x00438926:	call 0x004034c0
0x0043892b:	movl %eax, 0x447bb4
0x00438930:	movl %ecx, 0x447bb0
0x00438936:	movl %edx, 0x440608
0x0043893c:	addl %eax, %ecx
0x0043893e:	movl (%eax), %edx
0x00438940:	movb %cl, 0x44060c
0x00438946:	movb 0x4(%eax), %cl
0x00438949:	addl 0x447bb4, $0x5<UINT8>
0x00438950:	pushl $0x43a940<UINT32>
0x00438955:	call 0x0041dcb8
0x0043895a:	addl %esp, $0x4<UINT8>
0x0043895d:	movl %ecx, -12(%ebp)
0x00438960:	movl %fs:0, %ecx
0x00438967:	popl %ecx
0x00438968:	popl %esi
0x00438969:	popl %ebx
0x0043896a:	movl %esp, %ebp
0x0043896c:	popl %ebp
0x0043896d:	ret

0x00438970:	pushl %ebp
0x00438971:	movl %ebp, %esp
0x00438973:	pushl $0xffffffff<UINT8>
0x00438975:	pushl $0x43496a<UINT32>
0x0043897a:	movl %eax, %fs:0
0x00438980:	pushl %eax
0x00438981:	pushl %ebx
0x00438982:	pushl %esi
0x00438983:	movl %eax, 0x44609c
0x00438988:	xorl %eax, %ebp
0x0043898a:	pushl %eax
0x0043898b:	leal %eax, -12(%ebp)
0x0043898e:	movl %fs:0, %eax
0x00438994:	xorl %eax, %eax
0x00438996:	movl -4(%ebp), %eax
0x00438999:	leal %ebx, 0x17(%eax)
0x0043899c:	movl %esi, $0x447bc0<UINT32>
0x004389a1:	movb 0x448860, %al
0x004389a6:	call 0x004034c0
0x004389ab:	movl %eax, 0x447bc4
0x004389b0:	movl %ecx, 0x447bc0
0x004389b6:	movl %edx, 0x440b00
0x004389bc:	addl %eax, %ecx
0x004389be:	movl (%eax), %edx
0x004389c0:	movw %cx, 0x440b04
0x004389c7:	movw 0x4(%eax), %cx
0x004389cb:	movb %dl, 0x440b06
0x004389d1:	movb 0x6(%eax), %dl
0x004389d4:	addl 0x447bc4, $0x7<UINT8>
0x004389db:	pushl $0x43a980<UINT32>
0x004389e0:	call 0x0041dcb8
0x004389e5:	addl %esp, $0x4<UINT8>
0x004389e8:	movl %ecx, -12(%ebp)
0x004389eb:	movl %fs:0, %ecx
0x004389f2:	popl %ecx
0x004389f3:	popl %esi
0x004389f4:	popl %ebx
0x004389f5:	movl %esp, %ebp
0x004389f7:	popl %ebp
0x004389f8:	ret

0x00438a00:	pushl %ebp
0x00438a01:	movl %ebp, %esp
0x00438a03:	pushl $0xffffffff<UINT8>
0x00438a05:	pushl $0x43499a<UINT32>
0x00438a0a:	movl %eax, %fs:0
0x00438a10:	pushl %eax
0x00438a11:	pushl %ebx
0x00438a12:	pushl %esi
0x00438a13:	movl %eax, 0x44609c
0x00438a18:	xorl %eax, %ebp
0x00438a1a:	pushl %eax
0x00438a1b:	leal %eax, -12(%ebp)
0x00438a1e:	movl %fs:0, %eax
0x00438a24:	xorl %eax, %eax
0x00438a26:	movl -4(%ebp), %eax
0x00438a29:	leal %ebx, 0x1c(%eax)
0x00438a2c:	movl %esi, $0x447bd0<UINT32>
0x00438a31:	movb 0x448860, %al
0x00438a36:	call 0x004034c0
0x00438a3b:	movl %eax, 0x447bd4
0x00438a40:	movl %ecx, 0x447bd0
0x00438a46:	movl %edx, 0x440610
0x00438a4c:	addl %eax, %ecx
0x00438a4e:	movl (%eax), %edx
0x00438a50:	movl %ecx, 0x440614
0x00438a56:	movl 0x4(%eax), %ecx
0x00438a59:	movl %edx, 0x440618
0x00438a5f:	movl 0x8(%eax), %edx
0x00438a62:	addl 0x447bd4, $0xc<UINT8>
0x00438a69:	pushl $0x43a9c0<UINT32>
0x00438a6e:	call 0x0041dcb8
0x00438a73:	addl %esp, $0x4<UINT8>
0x00438a76:	movl %ecx, -12(%ebp)
0x00438a79:	movl %fs:0, %ecx
0x00438a80:	popl %ecx
0x00438a81:	popl %esi
0x00438a82:	popl %ebx
0x00438a83:	movl %esp, %ebp
0x00438a85:	popl %ebp
0x00438a86:	ret

0x00438a90:	pushl %ebp
0x00438a91:	movl %ebp, %esp
0x00438a93:	pushl $0xffffffff<UINT8>
0x00438a95:	pushl $0x4349ca<UINT32>
0x00438a9a:	movl %eax, %fs:0
0x00438aa0:	pushl %eax
0x00438aa1:	pushl %ebx
0x00438aa2:	pushl %esi
0x00438aa3:	movl %eax, 0x44609c
0x00438aa8:	xorl %eax, %ebp
0x00438aaa:	pushl %eax
0x00438aab:	leal %eax, -12(%ebp)
0x00438aae:	movl %fs:0, %eax
0x00438ab4:	xorl %eax, %eax
0x00438ab6:	movl -4(%ebp), %eax
0x00438ab9:	leal %ebx, 0x1e(%eax)
0x00438abc:	movl %esi, $0x447be0<UINT32>
0x00438ac1:	movb 0x448860, %al
0x00438ac6:	call 0x004034c0
0x00438acb:	movl %eax, 0x447be4
0x00438ad0:	movl %ecx, 0x447be0
0x00438ad6:	movl %edx, 0x440620
0x00438adc:	addl %eax, %ecx
0x00438ade:	movl (%eax), %edx
0x00438ae0:	movl %ecx, 0x440624
0x00438ae6:	movl 0x4(%eax), %ecx
0x00438ae9:	movl %edx, 0x440628
0x00438aef:	movl 0x8(%eax), %edx
0x00438af2:	movw %cx, 0x44062c
0x00438af9:	movw 0xc(%eax), %cx
0x00438afd:	addl 0x447be4, $0xe<UINT8>
0x00438b04:	pushl $0x43aa00<UINT32>
0x00438b09:	call 0x0041dcb8
0x00438b0e:	addl %esp, $0x4<UINT8>
0x00438b11:	movl %ecx, -12(%ebp)
0x00438b14:	movl %fs:0, %ecx
0x00438b1b:	popl %ecx
0x00438b1c:	popl %esi
0x00438b1d:	popl %ebx
0x00438b1e:	movl %esp, %ebp
0x00438b20:	popl %ebp
0x00438b21:	ret

0x00438d40:	pushl %ebp
0x00438d41:	movl %ebp, %esp
0x00438d43:	pushl $0xffffffff<UINT8>
0x00438d45:	pushl $0x4349fa<UINT32>
0x00438d4a:	movl %eax, %fs:0
0x00438d50:	pushl %eax
0x00438d51:	pushl %ebx
0x00438d52:	pushl %esi
0x00438d53:	movl %eax, 0x44609c
0x00438d58:	xorl %eax, %ebp
0x00438d5a:	pushl %eax
0x00438d5b:	leal %eax, -12(%ebp)
0x00438d5e:	movl %fs:0, %eax
0x00438d64:	xorl %eax, %eax
0x00438d66:	movl -4(%ebp), %eax
0x00438d69:	leal %ebx, 0x17(%eax)
0x00438d6c:	movl %esi, $0x447bf0<UINT32>
0x00438d71:	movb 0x448860, %al
0x00438d76:	call 0x004034c0
0x00438d7b:	movl %eax, 0x447bf4
0x00438d80:	movl %ecx, 0x447bf0
0x00438d86:	movl %edx, 0x43febc
0x00438d8c:	addl %eax, %ecx
0x00438d8e:	movl (%eax), %edx
0x00438d90:	movw %cx, 0x43fec0
0x00438d97:	movw 0x4(%eax), %cx
0x00438d9b:	movb %dl, 0x43fec2
0x00438da1:	movb 0x6(%eax), %dl
0x00438da4:	addl 0x447bf4, $0x7<UINT8>
0x00438dab:	pushl $0x43ab40<UINT32>
0x00438db0:	call 0x0041dcb8
0x00438db5:	addl %esp, $0x4<UINT8>
0x00438db8:	movl %ecx, -12(%ebp)
0x00438dbb:	movl %fs:0, %ecx
0x00438dc2:	popl %ecx
0x00438dc3:	popl %esi
0x00438dc4:	popl %ebx
0x00438dc5:	movl %esp, %ebp
0x00438dc7:	popl %ebp
0x00438dc8:	ret

0x00438c30:	pushl %ebp
0x00438c31:	movl %ebp, %esp
0x00438c33:	pushl $0xffffffff<UINT8>
0x00438c35:	pushl $0x434a2a<UINT32>
0x00438c3a:	movl %eax, %fs:0
0x00438c40:	pushl %eax
0x00438c41:	pushl %ebx
0x00438c42:	pushl %esi
0x00438c43:	movl %eax, 0x44609c
0x00438c48:	xorl %eax, %ebp
0x00438c4a:	pushl %eax
0x00438c4b:	leal %eax, -12(%ebp)
0x00438c4e:	movl %fs:0, %eax
0x00438c54:	xorl %eax, %eax
0x00438c56:	movl -4(%ebp), %eax
0x00438c59:	leal %ebx, 0x1a(%eax)
0x00438c5c:	movl %esi, $0x447c00<UINT32>
0x00438c61:	movb 0x448860, %al
0x00438c66:	call 0x004034c0
0x00438c6b:	movl %eax, 0x447c04
0x00438c70:	movl %ecx, 0x447c00
0x00438c76:	movl %edx, 0x43fed4
0x00438c7c:	addl %eax, %ecx
0x00438c7e:	movl (%eax), %edx
0x00438c80:	movl %ecx, 0x43fed8
0x00438c86:	movl 0x4(%eax), %ecx
0x00438c89:	movw %dx, 0x43fedc
0x00438c90:	movw 0x8(%eax), %dx
0x00438c94:	addl 0x447c04, $0xa<UINT8>
0x00438c9b:	pushl $0x43aac0<UINT32>
0x00438ca0:	call 0x0041dcb8
0x00438ca5:	addl %esp, $0x4<UINT8>
0x00438ca8:	movl %ecx, -12(%ebp)
0x00438cab:	movl %fs:0, %ecx
0x00438cb2:	popl %ecx
0x00438cb3:	popl %esi
0x00438cb4:	popl %ebx
0x00438cb5:	movl %esp, %ebp
0x00438cb7:	popl %ebp
0x00438cb8:	ret

0x00438cc0:	pushl %ebp
0x00438cc1:	movl %ebp, %esp
0x00438cc3:	pushl $0xffffffff<UINT8>
0x00438cc5:	pushl $0x434a5a<UINT32>
0x00438cca:	movl %eax, %fs:0
0x00438cd0:	pushl %eax
0x00438cd1:	pushl %ebx
0x00438cd2:	pushl %esi
0x00438cd3:	movl %eax, 0x44609c
0x00438cd8:	xorl %eax, %ebp
0x00438cda:	pushl %eax
0x00438cdb:	leal %eax, -12(%ebp)
0x00438cde:	movl %fs:0, %eax
0x00438ce4:	xorl %eax, %eax
0x00438ce6:	movl -4(%ebp), %eax
0x00438ce9:	leal %ebx, 0x16(%eax)
0x00438cec:	movl %esi, $0x447c10<UINT32>
0x00438cf1:	movb 0x448860, %al
0x00438cf6:	call 0x004034c0
0x00438cfb:	movl %eax, 0x447c14
0x00438d00:	movl %ecx, 0x447c10
0x00438d06:	movl %edx, 0x43feec
0x00438d0c:	addl %eax, %ecx
0x00438d0e:	movl (%eax), %edx
0x00438d10:	movw %cx, 0x43fef0
0x00438d17:	movw 0x4(%eax), %cx
0x00438d1b:	addl 0x447c14, $0x6<UINT8>
0x00438d22:	pushl $0x43ab00<UINT32>
0x00438d27:	call 0x0041dcb8
0x00438d2c:	addl %esp, $0x4<UINT8>
0x00438d2f:	movl %ecx, -12(%ebp)
0x00438d32:	movl %fs:0, %ecx
0x00438d39:	popl %ecx
0x00438d3a:	popl %esi
0x00438d3b:	popl %ebx
0x00438d3c:	movl %esp, %ebp
0x00438d3e:	popl %ebp
0x00438d3f:	ret

0x00438b90:	pushl %ebp
0x00438b91:	movl %ebp, %esp
0x00438b93:	pushl $0xffffffff<UINT8>
0x00438b95:	pushl $0x434a8a<UINT32>
0x00438b9a:	movl %eax, %fs:0
0x00438ba0:	pushl %eax
0x00438ba1:	pushl %ebx
0x00438ba2:	pushl %esi
0x00438ba3:	movl %eax, 0x44609c
0x00438ba8:	xorl %eax, %ebp
0x00438baa:	pushl %eax
0x00438bab:	leal %eax, -12(%ebp)
0x00438bae:	movl %fs:0, %eax
0x00438bb4:	xorl %eax, %eax
0x00438bb6:	movl -4(%ebp), %eax
0x00438bb9:	leal %ebx, 0x24(%eax)
0x00438bbc:	movl %esi, $0x447c20<UINT32>
0x00438bc1:	movb 0x448860, %al
0x00438bc6:	call 0x004034c0
0x00438bcb:	movl %ecx, 0x447c24
0x00438bd1:	movl %eax, 0x447c20
0x00438bd6:	movl %edx, 0x4405c4
0x00438bdc:	addl %eax, %ecx
0x00438bde:	movl (%eax), %edx
0x00438be0:	movl %ecx, 0x4405c8
0x00438be6:	movl 0x4(%eax), %ecx
0x00438be9:	movl %edx, 0x4405cc
0x00438bef:	movl 0x8(%eax), %edx
0x00438bf2:	movl %ecx, 0x4405d0
0x00438bf8:	movl 0xc(%eax), %ecx
0x00438bfb:	movl %edx, 0x4405d4
0x00438c01:	movl 0x10(%eax), %edx
0x00438c04:	addl 0x447c24, $0x14<UINT8>
0x00438c0b:	pushl $0x43aa80<UINT32>
0x00438c10:	call 0x0041dcb8
0x00438c15:	addl %esp, $0x4<UINT8>
0x00438c18:	movl %ecx, -12(%ebp)
0x00438c1b:	movl %fs:0, %ecx
0x00438c22:	popl %ecx
0x00438c23:	popl %esi
0x00438c24:	popl %ebx
0x00438c25:	movl %esp, %ebp
0x00438c27:	popl %ebp
0x00438c28:	ret

0x00438b30:	pushl %ebp
0x00438b31:	movl %ebp, %esp
0x00438b33:	pushl $0xffffffff<UINT8>
0x00438b35:	pushl $0x434aba<UINT32>
0x00438b3a:	movl %eax, %fs:0
0x00438b40:	pushl %eax
0x00438b41:	pushl %ebx
0x00438b42:	pushl %esi
0x00438b43:	movl %eax, 0x44609c
0x00438b48:	xorl %eax, %ebp
0x00438b4a:	pushl %eax
0x00438b4b:	leal %eax, -12(%ebp)
0x00438b4e:	movl %fs:0, %eax
0x00438b54:	xorl %eax, %eax
0x00438b56:	movl -4(%ebp), %eax
0x00438b59:	leal %ebx, 0x10(%eax)
0x00438b5c:	movl %esi, $0x447d40<UINT32>
0x00438b61:	movb 0x448860, %al
0x00438b66:	call 0x004034c0
0x00438b6b:	pushl $0x43aa40<UINT32>
0x00438b70:	call 0x0041dcb8
0x00438b75:	addl %esp, $0x4<UINT8>
0x00438b78:	movl %ecx, -12(%ebp)
0x00438b7b:	movl %fs:0, %ecx
0x00438b82:	popl %ecx
0x00438b83:	popl %esi
0x00438b84:	popl %ebx
0x00438b85:	movl %esp, %ebp
0x00438b87:	popl %ebp
0x00438b88:	ret

0x00439200:	pushl %ebp
0x00439201:	movl %ebp, %esp
0x00439203:	pushl $0xffffffff<UINT8>
0x00439205:	pushl $0x434b62<UINT32>
0x0043920a:	movl %eax, %fs:0
0x00439210:	pushl %eax
0x00439211:	pushl %ebx
0x00439212:	pushl %esi
0x00439213:	pushl %edi
0x00439214:	movl %eax, 0x44609c
0x00439219:	xorl %eax, %ebp
0x0043921b:	pushl %eax
0x0043921c:	leal %eax, -12(%ebp)
0x0043921f:	movl %fs:0, %eax
0x00439225:	xorl %eax, %eax
0x00439227:	movl -4(%ebp), %eax
0x0043922a:	leal %ebx, 0x1c(%eax)
0x0043922d:	movl %esi, $0x447c30<UINT32>
0x00439232:	movb 0x448860, %al
0x00439237:	call 0x004034c0
0x0043923c:	movl %eax, 0x447c34
0x00439241:	movl %ecx, 0x447c30
0x00439247:	movl %edx, 0x440a64
0x0043924d:	addl %eax, %ecx
0x0043924f:	movl (%eax), %edx
0x00439251:	movl %ecx, 0x440a68
0x00439257:	movl 0x4(%eax), %ecx
0x0043925a:	movl %edx, 0x440a6c
0x00439260:	movl 0x8(%eax), %edx
0x00439263:	addl 0x447c34, $0xc<UINT8>
0x0043926a:	xorl %eax, %eax
0x0043926c:	movl %edi, $0x1<UINT32>
0x00439271:	movl -4(%ebp), %edi
0x00439274:	movl 0x447c40, %eax
0x00439279:	movl 0x447c44, %eax
0x0043927e:	movl 0x447c48, %eax
0x00439283:	movb -4(%ebp), $0x2<UINT8>
0x00439287:	movl %esi, $0x447c40<UINT32>
0x0043928c:	movl 0x447c4c, %edi
0x00439292:	movb 0x448860, %al
0x00439297:	call 0x004034c0
0x0043929c:	movl %ecx, 0x447c44
0x004392a2:	movl %eax, 0x447c40
0x004392a7:	movl %edx, 0x440a54
0x004392ad:	addl %eax, %ecx
0x004392af:	movl (%eax), %edx
0x004392b1:	movl %ecx, 0x440a58
0x004392b7:	movl 0x4(%eax), %ecx
0x004392ba:	movl %edx, 0x440a5c
0x004392c0:	movl 0x8(%eax), %edx
0x004392c3:	addl 0x447c44, $0xc<UINT8>
0x004392ca:	xorl %eax, %eax
0x004392cc:	movl 0x447c50, %eax
0x004392d1:	movl 0x447c54, %eax
0x004392d6:	movl 0x447c58, %eax
0x004392db:	movb -4(%ebp), $0x4<UINT8>
0x004392df:	leal %ebx, 0x27(%edi)
0x004392e2:	movl %esi, $0x447c50<UINT32>
0x004392e7:	movl 0x447c5c, %edi
0x004392ed:	movb 0x448860, %al
0x004392f2:	call 0x004034c0
0x004392f7:	movl %eax, 0x447c54
0x004392fc:	movl %ecx, 0x447c50
0x00439302:	movl %edx, 0x440a38
0x00439308:	addl %eax, %ecx
0x0043930a:	movl (%eax), %edx
0x0043930c:	movl %ecx, 0x440a3c
0x00439312:	movl 0x4(%eax), %ecx
0x00439315:	movl %edx, 0x440a40
0x0043931b:	movl 0x8(%eax), %edx
0x0043931e:	movl %ecx, 0x440a44
0x00439324:	movl 0xc(%eax), %ecx
0x00439327:	movl %edx, 0x440a48
0x0043932d:	movl 0x10(%eax), %edx
0x00439330:	movl %ecx, 0x440a4c
0x00439336:	movl 0x14(%eax), %ecx
0x00439339:	addl 0x447c54, $0x18<UINT8>
0x00439340:	xorl %eax, %eax
0x00439342:	movl 0x447c60, %eax
0x00439347:	movl 0x447c64, %eax
0x0043934c:	movl 0x447c68, %eax
0x00439351:	movb -4(%ebp), $0x6<UINT8>
0x00439355:	leal %ebx, 0x1b(%edi)
0x00439358:	movl %esi, $0x447c60<UINT32>
0x0043935d:	movl 0x447c6c, %edi
0x00439363:	movb 0x448860, %al
0x00439368:	call 0x004034c0
0x0043936d:	movl %edx, 0x447c64
0x00439373:	movl %eax, 0x447c60
0x00439378:	movl %ecx, 0x440a28
0x0043937e:	addl %eax, %edx
0x00439380:	movl (%eax), %ecx
0x00439382:	movl %edx, 0x440a2c
0x00439388:	movl 0x4(%eax), %edx
0x0043938b:	movl %ecx, 0x440a30
0x00439391:	movl 0x8(%eax), %ecx
0x00439394:	addl 0x447c64, $0xc<UINT8>
0x0043939b:	xorl %eax, %eax
0x0043939d:	movl 0x447c70, %eax
0x004393a2:	movl 0x447c74, %eax
0x004393a7:	movl 0x447c78, %eax
0x004393ac:	movb -4(%ebp), $0x8<UINT8>
0x004393b0:	leal %ebx, 0x2f(%edi)
0x004393b3:	movl %esi, $0x447c70<UINT32>
0x004393b8:	movl 0x447c7c, %edi
0x004393be:	movb 0x448860, %al
0x004393c3:	call 0x004034c0
0x004393c8:	movl %edx, 0x447c70
0x004393ce:	movl %eax, 0x447c74
0x004393d3:	pushl $0x20<UINT8>
0x004393d5:	addl %eax, %edx
0x004393d7:	pushl $0x440a04<UINT32>
0x004393dc:	pushl %eax
0x004393dd:	call 0x00420c20
0x004393e2:	addl 0x447c74, $0x20<UINT8>
0x004393e9:	addl %esp, $0xc<UINT8>
0x004393ec:	xorl %eax, %eax
0x004393ee:	movl 0x447c80, %eax
0x004393f3:	movl 0x447c84, %eax
0x004393f8:	movl 0x447c88, %eax
0x004393fd:	movb -4(%ebp), $0xa<UINT8>
0x00439401:	leal %ebx, 0x27(%edi)
0x00439404:	movl %esi, $0x447c80<UINT32>
0x00439409:	movl 0x447c8c, %edi
0x0043940f:	movb 0x448860, %al
0x00439414:	call 0x004034c0
0x00439419:	movl %edx, 0x447c84
0x0043941f:	movl %ecx, 0x447c80
0x00439425:	leal %eax, (%edx,%ecx)
0x00439428:	movl %ecx, 0x4409e8
0x0043942e:	movl (%eax), %ecx
0x00439430:	movl %edx, 0x4409ec
0x00439436:	movl 0x4(%eax), %edx
0x00439439:	movl %ecx, 0x4409f0
0x0043943f:	movl 0x8(%eax), %ecx
0x00439442:	movl %edx, 0x4409f4
0x00439448:	movl 0xc(%eax), %edx
0x0043944b:	movl %ecx, 0x4409f8
0x00439451:	movl 0x10(%eax), %ecx
0x00439454:	movl %edx, 0x4409fc
0x0043945a:	movl 0x14(%eax), %edx
0x0043945d:	addl 0x447c84, $0x18<UINT8>
0x00439464:	xorl %eax, %eax
0x00439466:	movl 0x447c90, %eax
0x0043946b:	movl 0x447c94, %eax
0x00439470:	movl 0x447c98, %eax
0x00439475:	movb -4(%ebp), $0xc<UINT8>
0x00439479:	movl %esi, $0x447c90<UINT32>
0x0043947e:	movl 0x447c9c, %edi
0x00439484:	movb 0x448860, %al
0x00439489:	call 0x004034c0
0x0043948e:	movl %ecx, 0x447c94
0x00439494:	movl %eax, 0x447c90
0x00439499:	movl %edx, 0x4409cc
0x0043949f:	addl %eax, %ecx
0x004394a1:	movl (%eax), %edx
0x004394a3:	movl %ecx, 0x4409d0
0x004394a9:	movl 0x4(%eax), %ecx
0x004394ac:	movl %edx, 0x4409d4
0x004394b2:	movl 0x8(%eax), %edx
0x004394b5:	movl %ecx, 0x4409d8
0x004394bb:	movl 0xc(%eax), %ecx
0x004394be:	movl %edx, 0x4409dc
0x004394c4:	movl 0x10(%eax), %edx
0x004394c7:	movl %ecx, 0x4409e0
0x004394cd:	movl 0x14(%eax), %ecx
0x004394d0:	addl 0x447c94, $0x18<UINT8>
0x004394d7:	pushl $0x43abe0<UINT32>
0x004394dc:	call 0x0041dcb8
0x004394e1:	addl %esp, $0x4<UINT8>
0x004394e4:	movl %ecx, -12(%ebp)
0x004394e7:	movl %fs:0, %ecx
0x004394ee:	popl %ecx
0x004394ef:	popl %edi
0x004394f0:	popl %esi
0x004394f1:	popl %ebx
0x004394f2:	movl %esp, %ebp
0x004394f4:	popl %ebp
0x004394f5:	ret

0x00438dd0:	pushl %ebp
0x00438dd1:	movl %ebp, %esp
0x00438dd3:	pushl $0xffffffff<UINT8>
0x00438dd5:	pushl $0x434bb2<UINT32>
0x00438dda:	movl %eax, %fs:0
0x00438de0:	pushl %eax
0x00438de1:	pushl %ebx
0x00438de2:	pushl %esi
0x00438de3:	movl %eax, 0x44609c
0x00438de8:	xorl %eax, %ebp
0x00438dea:	pushl %eax
0x00438deb:	leal %eax, -12(%ebp)
0x00438dee:	movl %fs:0, %eax
0x00438df4:	xorl %eax, %eax
0x00438df6:	movl -4(%ebp), %eax
0x00438df9:	leal %ebx, 0x26(%eax)
0x00438dfc:	movl %esi, $0x447ca0<UINT32>
0x00438e01:	movb 0x448860, %al
0x00438e06:	call 0x004034c0
0x00438e0b:	movl %ecx, 0x447ca4
0x00438e11:	movl %eax, 0x447ca0
0x00438e16:	movl %edx, 0x440aa8
0x00438e1c:	addl %eax, %ecx
0x00438e1e:	movl (%eax), %edx
0x00438e20:	movl %ecx, 0x440aac
0x00438e26:	movl 0x4(%eax), %ecx
0x00438e29:	movl %edx, 0x440ab0
0x00438e2f:	movl 0x8(%eax), %edx
0x00438e32:	movl %ecx, 0x440ab4
0x00438e38:	movl 0xc(%eax), %ecx
0x00438e3b:	movl %edx, 0x440ab8
0x00438e41:	movl 0x10(%eax), %edx
0x00438e44:	movw %cx, 0x440abc
0x00438e4b:	movw 0x14(%eax), %cx
0x00438e4f:	addl 0x447ca4, $0x16<UINT8>
0x00438e56:	xorl %eax, %eax
0x00438e58:	movl -4(%ebp), $0x1<UINT32>
0x00438e5f:	movl 0x447cb0, %eax
0x00438e64:	movl 0x447cb4, %eax
0x00438e69:	movl 0x447cb8, %eax
0x00438e6e:	movb -4(%ebp), $0x2<UINT8>
0x00438e72:	leal %ebx, 0x27(%eax)
0x00438e75:	movl %esi, $0x447cb0<UINT32>
0x00438e7a:	movl 0x447cbc, $0x1<UINT32>
0x00438e84:	movb 0x448860, %al
0x00438e89:	call 0x004034c0
0x00438e8e:	movl %edx, 0x447cb0
0x00438e94:	movl %eax, 0x447cb4
0x00438e99:	pushl $0x17<UINT8>
0x00438e9b:	addl %eax, %edx
0x00438e9d:	pushl $0x440a90<UINT32>
0x00438ea2:	pushl %eax
0x00438ea3:	call 0x00420c20
0x00438ea8:	addl 0x447cb4, $0x17<UINT8>
0x00438eaf:	addl %esp, $0xc<UINT8>
0x00438eb2:	xorl %eax, %eax
0x00438eb4:	movl 0x447cc0, %eax
0x00438eb9:	movl 0x447cc4, %eax
0x00438ebe:	movl 0x447cc8, %eax
0x00438ec3:	movb -4(%ebp), $0x4<UINT8>
0x00438ec7:	leal %ebx, 0x28(%eax)
0x00438eca:	movl %esi, $0x447cc0<UINT32>
0x00438ecf:	movl 0x447ccc, $0x1<UINT32>
0x00438ed9:	movb 0x448860, %al
0x00438ede:	call 0x004034c0
0x00438ee3:	movl %edx, 0x447cc4
0x00438ee9:	movl %ecx, 0x447cc0
0x00438eef:	leal %eax, (%edx,%ecx)
0x00438ef2:	movl %ecx, 0x440a74
0x00438ef8:	movl (%eax), %ecx
0x00438efa:	movl %edx, 0x440a78
0x00438f00:	movl 0x4(%eax), %edx
0x00438f03:	movl %ecx, 0x440a7c
0x00438f09:	movl 0x8(%eax), %ecx
0x00438f0c:	movl %edx, 0x440a80
0x00438f12:	movl 0xc(%eax), %edx
0x00438f15:	movl %ecx, 0x440a84
0x00438f1b:	movl 0x10(%eax), %ecx
0x00438f1e:	movl %edx, 0x440a88
0x00438f24:	movl 0x14(%eax), %edx
0x00438f27:	addl 0x447cc4, $0x18<UINT8>
0x00438f2e:	pushl $0x43ab80<UINT32>
0x00438f33:	call 0x0041dcb8
0x00438f38:	addl %esp, $0x4<UINT8>
0x00438f3b:	movl %ecx, -12(%ebp)
0x00438f3e:	movl %fs:0, %ecx
0x00438f45:	popl %ecx
0x00438f46:	popl %esi
0x00438f47:	popl %ebx
0x00438f48:	movl %esp, %ebp
0x00438f4a:	popl %ebp
0x00438f4b:	ret

0x00438f50:	pushl %ebp
0x00438f51:	movl %ebp, %esp
0x00438f53:	pushl $0xffffffff<UINT8>
0x00438f55:	pushl $0x434c02<UINT32>
0x00438f5a:	movl %eax, %fs:0
0x00438f60:	pushl %eax
0x00438f61:	pushl %ebx
0x00438f62:	pushl %esi
0x00438f63:	movl %eax, 0x44609c
0x00438f68:	xorl %eax, %ebp
0x00438f6a:	pushl %eax
0x00438f6b:	leal %eax, -12(%ebp)
0x00438f6e:	movl %fs:0, %eax
0x00438f74:	xorl %eax, %eax
0x00438f76:	movl -4(%ebp), %eax
0x00438f79:	leal %ebx, 0x10(%eax)
0x00438f7c:	movl %esi, $0x447cd0<UINT32>
0x00438f81:	movb 0x448860, %al
0x00438f86:	call 0x004034c0
0x00438f8b:	movl %eax, 0x447cd4
0x00438f90:	movl %ecx, 0x447cd0
0x00438f96:	xorl %ebx, %ebx
0x00438f98:	pushl %ebx
0x00438f99:	addl %eax, %ecx
0x00438f9b:	pushl $0x43fad1<UINT32>
0x00438fa0:	pushl %eax
0x00438fa1:	call 0x00420c20
0x00438fa6:	addl %esp, $0xc<UINT8>
0x00438fa9:	movl -4(%ebp), $0x1<UINT32>
0x00438fb0:	movl 0x447ce0, %ebx
0x00438fb6:	movl 0x447ce4, %ebx
0x00438fbc:	movl 0x447ce8, %ebx
0x00438fc2:	movb -4(%ebp), $0x2<UINT8>
0x00438fc6:	movb 0x448860, %bl
0x00438fcc:	movl %ebx, $0x19<UINT32>
0x00438fd1:	movl %esi, $0x447ce0<UINT32>
0x00438fd6:	movl 0x447cec, $0x1<UINT32>
0x00438fe0:	call 0x004034c0
0x00438fe5:	movl %edx, 0x447ce4
0x00438feb:	movl %eax, 0x447ce0
0x00438ff0:	movl %ecx, 0x440ad4
0x00438ff6:	addl %eax, %edx
0x00438ff8:	movl (%eax), %ecx
0x00438ffa:	movl %edx, 0x440ad8
0x00439000:	movl 0x4(%eax), %edx
0x00439003:	movb %cl, 0x440adc
0x00439009:	movb 0x8(%eax), %cl
0x0043900c:	addl 0x447ce4, $0x9<UINT8>
0x00439013:	xorl %eax, %eax
0x00439015:	movl 0x447cf0, %eax
0x0043901a:	movl 0x447cf4, %eax
0x0043901f:	movl 0x447cf8, %eax
0x00439024:	movb -4(%ebp), $0x4<UINT8>
0x00439028:	leal %ebx, 0x23(%eax)
0x0043902b:	movl %esi, $0x447cf0<UINT32>
0x00439030:	movl 0x447cfc, $0x1<UINT32>
0x0043903a:	movb 0x448860, %al
0x0043903f:	call 0x004034c0
0x00439044:	movl %edx, 0x447cf4
0x0043904a:	movl %eax, 0x447cf0
0x0043904f:	movl %ecx, 0x440ac0
0x00439055:	addl %eax, %edx
0x00439057:	movl (%eax), %ecx
0x00439059:	movl %edx, 0x440ac4
0x0043905f:	movl 0x4(%eax), %edx
0x00439062:	movl %ecx, 0x440ac8
0x00439068:	movl 0x8(%eax), %ecx
0x0043906b:	movl %edx, 0x440acc
0x00439071:	movl 0xc(%eax), %edx
0x00439074:	movw %cx, 0x440ad0
0x0043907b:	movw 0x10(%eax), %cx
0x0043907f:	movb %dl, 0x440ad2
0x00439085:	movb 0x12(%eax), %dl
0x00439088:	addl 0x447cf4, $0x13<UINT8>
0x0043908f:	pushl $0x43aba0<UINT32>
0x00439094:	call 0x0041dcb8
0x00439099:	addl %esp, $0x4<UINT8>
0x0043909c:	movl %ecx, -12(%ebp)
0x0043909f:	movl %fs:0, %ecx
0x004390a6:	popl %ecx
0x004390a7:	popl %esi
0x004390a8:	popl %ebx
0x004390a9:	movl %esp, %ebp
0x004390ab:	popl %ebp
0x004390ac:	ret

0x004390b0:	pushl %ebp
0x004390b1:	movl %ebp, %esp
0x004390b3:	pushl $0xffffffff<UINT8>
0x004390b5:	pushl $0x434c52<UINT32>
0x004390ba:	movl %eax, %fs:0
0x004390c0:	pushl %eax
0x004390c1:	pushl %ebx
0x004390c2:	pushl %esi
0x004390c3:	movl %eax, 0x44609c
0x004390c8:	xorl %eax, %ebp
0x004390ca:	pushl %eax
0x004390cb:	leal %eax, -12(%ebp)
0x004390ce:	movl %fs:0, %eax
0x004390d4:	xorl %eax, %eax
0x004390d6:	movl -4(%ebp), %eax
0x004390d9:	leal %ebx, 0x15(%eax)
0x004390dc:	movl %esi, $0x447d00<UINT32>
0x004390e1:	movb 0x448860, %al
0x004390e6:	call 0x004034c0
0x004390eb:	movl %ecx, 0x447d04
0x004390f1:	movl %eax, 0x447d00
0x004390f6:	movl %edx, 0x440aec
0x004390fc:	addl %eax, %ecx
0x004390fe:	movl (%eax), %edx
0x00439100:	movzbl %ecx, 0x440af0
0x00439107:	movb 0x4(%eax), %cl
0x0043910a:	addl 0x447d04, $0x5<UINT8>
0x00439111:	xorl %eax, %eax
0x00439113:	movl -4(%ebp), $0x1<UINT32>
0x0043911a:	movl 0x447d10, %eax
0x0043911f:	movl 0x447d14, %eax
0x00439124:	movl 0x447d18, %eax
0x00439129:	movb -4(%ebp), $0x2<UINT8>
0x0043912d:	leal %ebx, 0x19(%eax)
0x00439130:	movl %esi, $0x447d10<UINT32>
0x00439135:	movl 0x447d1c, $0x1<UINT32>
0x0043913f:	movb 0x448860, %al
0x00439144:	call 0x004034c0
0x00439149:	movl %edx, 0x447d14
0x0043914f:	movl %eax, 0x447d10
0x00439154:	movl %ecx, 0x440ae0
0x0043915a:	addl %eax, %edx
0x0043915c:	movl (%eax), %ecx
0x0043915e:	movl %edx, 0x440ae4
0x00439164:	movl 0x4(%eax), %edx
0x00439167:	movzbl %ecx, 0x440ae8
0x0043916e:	movb 0x8(%eax), %cl
0x00439171:	addl 0x447d14, $0x9<UINT8>
0x00439178:	xorl %eax, %eax
0x0043917a:	movl 0x447d20, %eax
0x0043917f:	movl 0x447d24, %eax
0x00439184:	movl 0x447d28, %eax
0x00439189:	movb -4(%ebp), $0x4<UINT8>
0x0043918d:	movl %esi, $0x447d20<UINT32>
0x00439192:	movl 0x447d2c, $0x1<UINT32>
0x0043919c:	movb 0x448860, %al
0x004391a1:	call 0x004034c0
0x004391a6:	movl %edx, 0x447d20
0x004391ac:	movl %eax, 0x447d24
0x004391b1:	movl %ecx, 0x440ae0
0x004391b7:	addl %eax, %edx
0x004391b9:	movl (%eax), %ecx
0x004391bb:	movl %edx, 0x440ae4
0x004391c1:	movl 0x4(%eax), %edx
0x004391c4:	movzbl %ecx, 0x440ae8
0x004391cb:	movb 0x8(%eax), %cl
0x004391ce:	addl 0x447d24, $0x9<UINT8>
0x004391d5:	pushl $0x43abc0<UINT32>
0x004391da:	call 0x0041dcb8
0x004391df:	addl %esp, $0x4<UINT8>
0x004391e2:	movl %ecx, -12(%ebp)
0x004391e5:	movl %fs:0, %ecx
0x004391ec:	popl %ecx
0x004391ed:	popl %esi
0x004391ee:	popl %ebx
0x004391ef:	movl %esp, %ebp
0x004391f1:	popl %ebp
0x004391f2:	ret

0x0041e2cd:	popl %esi
0x0041e2ce:	popl %ebp
0x0041e2cf:	ret

0x0041e350:	cmpl 0x44abb8, $0x0<UINT8>
0x0041e357:	popl %ecx
0x0041e358:	je 0x0041e375
0x0041e375:	xorl %eax, %eax
0x0041e377:	popl %ebp
0x0041e378:	ret

0x0041ee41:	popl %ecx
0x0041ee42:	testl %eax, %eax
0x0041ee44:	je 0x0041ee4d
0x0041ee4d:	movl %eax, 0x447d90
0x0041ee52:	movl 0x447d94, %eax
0x0041ee57:	pushl %eax
0x0041ee58:	pushl 0x447d88
0x0041ee5e:	pushl 0x447d84
0x0041ee64:	call 0x0041b6f0
0x0041b6f0:	pushl %ebp
0x0041b6f1:	movl %ebp, %esp
0x0041b6f3:	andl %esp, $0xfffffff8<UINT8>
0x0041b6f6:	pushl $0xffffffff<UINT8>
0x0041b6f8:	pushl $0x433701<UINT32>
0x0041b6fd:	movl %eax, %fs:0
0x0041b703:	pushl %eax
0x0041b704:	subl %esp, $0x428<UINT32>
0x0041b70a:	movl %eax, 0x44609c
0x0041b70f:	xorl %eax, %esp
0x0041b711:	movl 0x420(%esp), %eax
0x0041b718:	pushl %ebx
0x0041b719:	pushl %esi
0x0041b71a:	pushl %edi
0x0041b71b:	movl %eax, 0x44609c
0x0041b720:	xorl %eax, %esp
0x0041b722:	pushl %eax
0x0041b723:	leal %eax, 0x438(%esp)
0x0041b72a:	movl %fs:0, %eax
0x0041b730:	xorl %ebx, %ebx
0x0041b732:	pushl %ebx
0x0041b733:	call 0x0041ddcf
0x0041ddcf:	movl %edi, %edi
0x0041ddd1:	pushl %ebp
0x0041ddd2:	movl %ebp, %esp
0x0041ddd4:	pushl %ecx
0x0041ddd5:	pushl %ecx
0x0041ddd6:	leal %eax, -8(%ebp)
0x0041ddd9:	pushl %eax
0x0041ddda:	call GetSystemTimeAsFileTime@kernel32.dll
0x0041dde0:	movl %eax, -8(%ebp)
0x0041dde3:	movl %ecx, -4(%ebp)
0x0041dde6:	pushl $0x0<UINT8>
0x0041dde8:	addl %eax, $0x2ac18000<UINT32>
0x0041dded:	pushl $0x989680<UINT32>
0x0041ddf2:	adcl %ecx, $0xfe624e21<UINT32>
0x0041ddf8:	pushl %ecx
0x0041ddf9:	pushl %eax
0x0041ddfa:	call 0x00424750
0x00424750:	pushl %ebx
0x00424751:	pushl %esi
0x00424752:	movl %eax, 0x18(%esp)
0x00424756:	orl %eax, %eax
0x00424758:	jne 24
0x0042475a:	movl %ecx, 0x14(%esp)
0x0042475e:	movl %eax, 0x10(%esp)
0x00424762:	xorl %edx, %edx
0x00424764:	divl %eax, %ecx
0x00424766:	movl %ebx, %eax
0x00424768:	movl %eax, 0xc(%esp)
0x0042476c:	divl %eax, %ecx
0x0042476e:	movl %edx, %ebx
0x00424770:	jmp 0x004247b3
0x004247b3:	popl %esi
0x004247b4:	popl %ebx
0x004247b5:	ret $0x10<UINT16>

0x0041ddff:	cmpl %edx, $0x7<UINT8>
0x0041de02:	jl 0x0041de12
0x0041de12:	movl %ecx, 0x8(%ebp)
0x0041de15:	testl %ecx, %ecx
0x0041de17:	je 0x0041de1e
0x0041de1e:	leave
0x0041de1f:	ret

0x0041b738:	addl %esp, $0x4<UINT8>
0x0041b73b:	pushl %eax
0x0041b73c:	call 0x0041dd9b
0x0041dd9b:	movl %edi, %edi
0x0041dd9d:	pushl %ebp
0x0041dd9e:	movl %ebp, %esp
0x0041dda0:	call 0x00422ebd
0x0041dda5:	movl %ecx, 0x8(%ebp)
0x0041dda8:	movl 0x14(%eax), %ecx
0x0041ddab:	popl %ebp
0x0041ddac:	ret

0x0041b741:	addl %esp, $0x4<UINT8>
0x0041b744:	call 0x00408810
0x00408810:	pushl %ebp
0x00408811:	movl %ebp, %esp
0x00408813:	pushl $0xffffffff<UINT8>
0x00408815:	pushl $0x432770<UINT32>
0x0040881a:	movl %eax, %fs:0
0x00408820:	pushl %eax
0x00408821:	subl %esp, $0x44<UINT8>
0x00408824:	pushl %ebx
0x00408825:	pushl %esi
0x00408826:	pushl %edi
0x00408827:	movl %eax, 0x44609c
0x0040882c:	xorl %eax, %ebp
0x0040882e:	pushl %eax
0x0040882f:	leal %eax, -12(%ebp)
0x00408832:	movl %fs:0, %eax
0x00408838:	xorl %eax, %eax
0x0040883a:	movl -48(%ebp), %eax
0x0040883d:	movl -44(%ebp), %eax
0x00408840:	movl -40(%ebp), %eax
0x00408843:	movl -4(%ebp), %eax
0x00408846:	leal %ebx, 0x28(%eax)
0x00408849:	leal %esi, -48(%ebp)
0x0040884c:	movl -36(%ebp), $0x1<UINT32>
0x00408853:	movb 0x448860, %al
0x00408858:	call 0x004034c0
0x0040885d:	movl %eax, -44(%ebp)
0x00408860:	movl %edx, 0x440064
0x00408866:	movl %ecx, -48(%ebp)
0x00408869:	movl (%eax,%ecx), %edx
0x0040886c:	movl %edx, 0x440068
0x00408872:	movl 0x4(%eax,%ecx), %edx
0x00408876:	movl %edx, 0x44006c
0x0040887c:	movl 0x8(%eax,%ecx), %edx
0x00408880:	movl %edx, 0x440070
0x00408886:	movl 0xc(%eax,%ecx), %edx
0x0040888a:	movl %edx, 0x440074
0x00408890:	movl 0x10(%eax,%ecx), %edx
0x00408894:	movl %edx, 0x440078
0x0040889a:	movl 0x14(%eax,%ecx), %edx
0x0040889e:	addl %eax, $0x18<UINT8>
0x004088a1:	movl -44(%ebp), %eax
0x004088a4:	xorl %eax, %eax
0x004088a6:	movl %ecx, $0x1<UINT32>
0x004088ab:	movl -4(%ebp), %ecx
0x004088ae:	movl -32(%ebp), %eax
0x004088b1:	movl -28(%ebp), %eax
0x004088b4:	movl -24(%ebp), %eax
0x004088b7:	movb -4(%ebp), $0x2<UINT8>
0x004088bb:	leal %esi, -32(%ebp)
0x004088be:	movl -20(%ebp), %ecx
0x004088c1:	movb 0x448860, %al
0x004088c6:	call 0x004034c0
0x004088cb:	movl %eax, -28(%ebp)
0x004088ce:	movl %edx, 0x440080
0x004088d4:	movl %ecx, -32(%ebp)
0x004088d7:	movl (%eax,%ecx), %edx
0x004088da:	movl %edx, 0x440084
0x004088e0:	movl 0x4(%eax,%ecx), %edx
0x004088e4:	movl %edx, 0x440088
0x004088ea:	movl 0x8(%eax,%ecx), %edx
0x004088ee:	movl %edx, 0x44008c
0x004088f4:	movl 0xc(%eax,%ecx), %edx
0x004088f8:	movl %edx, 0x440090
0x004088fe:	movl 0x10(%eax,%ecx), %edx
0x00408902:	movl %edx, 0x440094
0x00408908:	movl 0x14(%eax,%ecx), %edx
0x0040890c:	addl %eax, $0x18<UINT8>
0x0040890f:	movl -28(%ebp), %eax
0x00408912:	pushl $0x1<UINT8>
0x00408914:	xorl %ebx, %ebx
0x00408916:	pushl %ebx
0x00408917:	leal %eax, -48(%ebp)
0x0040891a:	pushl %eax
0x0040891b:	leal %ecx, -80(%ebp)
0x0040891e:	pushl %ecx
0x0040891f:	movb -4(%ebp), $0x3<UINT8>
0x00408923:	call 0x0041b070
0x0041b070:	pushl %ebp
0x0041b071:	movl %ebp, %esp
0x0041b073:	pushl $0xffffffff<UINT8>
0x0041b075:	pushl $0x43194d<UINT32>
0x0041b07a:	movl %eax, %fs:0
0x0041b080:	pushl %eax
0x0041b081:	subl %esp, $0x230<UINT32>
0x0041b087:	movl %eax, 0x44609c
0x0041b08c:	xorl %eax, %ebp
0x0041b08e:	movl -16(%ebp), %eax
0x0041b091:	pushl %ebx
0x0041b092:	pushl %esi
0x0041b093:	pushl %edi
0x0041b094:	pushl %eax
0x0041b095:	leal %eax, -12(%ebp)
0x0041b098:	movl %fs:0, %eax
0x0041b09e:	movl %eax, 0x8(%ebp)
0x0041b0a1:	movl %edi, 0xc(%ebp)
0x0041b0a4:	movl -548(%ebp), %eax
0x0041b0aa:	movl -556(%ebp), $0x0<UINT32>
0x0041b0b4:	call 0x00405790
0x00405790:	pushl %ebp
0x00405791:	movl %ebp, %esp
0x00405793:	pushl $0xffffffff<UINT8>
0x00405795:	pushl $0x4318fe<UINT32>
0x0040579a:	movl %eax, %fs:0
0x004057a0:	pushl %eax
0x004057a1:	movl %eax, 0x44609c
0x004057a6:	xorl %eax, %ebp
0x004057a8:	pushl %eax
0x004057a9:	leal %eax, -12(%ebp)
0x004057ac:	movl %fs:0, %eax
0x004057b2:	movl %eax, $0x1<UINT32>
0x004057b7:	testb 0x448864, %al
0x004057bd:	jne 0x004057de
0x004057bf:	orl 0x448864, %eax
0x004057c5:	movl -4(%ebp), $0x0<UINT32>
0x004057cc:	call 0x00406ed0
0x00406ed0:	pushl %ebp
0x00406ed1:	movl %ebp, %esp
0x00406ed3:	pushl $0xffffffff<UINT8>
0x00406ed5:	pushl $0x4318d3<UINT32>
0x00406eda:	movl %eax, %fs:0
0x00406ee0:	pushl %eax
0x00406ee1:	pushl %ecx
0x00406ee2:	pushl %ebx
0x00406ee3:	pushl %esi
0x00406ee4:	movl %eax, 0x44609c
0x00406ee9:	xorl %eax, %ebp
0x00406eeb:	pushl %eax
0x00406eec:	leal %eax, -12(%ebp)
0x00406eef:	movl %fs:0, %eax
0x00406ef5:	movl %esi, $0x448868<UINT32>
0x00406efa:	movl %ecx, %esi
0x00406efc:	movl -16(%ebp), %esi
0x00406eff:	call 0x00406a10
0x00406a10:	pushl %ebp
0x00406a11:	movl %ebp, %esp
0x00406a13:	pushl $0xffffffff<UINT8>
0x00406a15:	pushl $0x430738<UINT32>
0x00406a1a:	movl %eax, %fs:0
0x00406a20:	pushl %eax
0x00406a21:	pushl %ecx
0x00406a22:	pushl %ebx
0x00406a23:	pushl %esi
0x00406a24:	movl %eax, 0x44609c
0x00406a29:	xorl %eax, %ebp
0x00406a2b:	pushl %eax
0x00406a2c:	leal %eax, -12(%ebp)
0x00406a2f:	movl %fs:0, %eax
0x00406a35:	movl %esi, %ecx
0x00406a37:	movl -16(%ebp), %esi
0x00406a3a:	xorl %eax, %eax
0x00406a3c:	movl (%esi), %eax
0x00406a3e:	movl 0x4(%esi), %eax
0x00406a41:	movl 0x8(%esi), %eax
0x00406a44:	movl -4(%ebp), %eax
0x00406a47:	movl 0xc(%esi), $0x1<UINT32>
0x00406a4e:	movl %ebx, $0x10<UINT32>
0x00406a53:	movb 0x448860, %al
0x00406a58:	cmpl 0x8(%esi), %ebx
0x00406a5b:	jae 5
0x00406a5d:	call 0x004034c0
0x00406a62:	movl %eax, %esi
0x00406a64:	movl %ecx, -12(%ebp)
0x00406a67:	movl %fs:0, %ecx
0x00406a6e:	popl %ecx
0x00406a6f:	popl %esi
0x00406a70:	popl %ebx
0x00406a71:	movl %esp, %ebp
0x00406a73:	popl %ebp
0x00406a74:	ret

0x00406f04:	xorl %ebx, %ebx
0x00406f06:	movl %ecx, $0x448878<UINT32>
0x00406f0b:	movl -4(%ebp), %ebx
0x00406f0e:	call 0x00406a10
0x00406f13:	movl %ecx, $0x448888<UINT32>
0x00406f18:	movb -4(%ebp), $0x1<UINT8>
0x00406f1c:	call 0x00408720
0x00406f21:	movl %ecx, $0x4488d8<UINT32>
0x00406f26:	movb -4(%ebp), $0x2<UINT8>
0x00406f2a:	call 0x00406a10
0x00406f2f:	movl %ecx, $0x4488e8<UINT32>
0x00406f34:	movb -4(%ebp), $0x3<UINT8>
0x00406f38:	call 0x00406a10
0x00406f3d:	movl %ecx, $0x4488f8<UINT32>
0x00406f42:	movb -4(%ebp), $0x4<UINT8>
0x00406f46:	call 0x00406a10
0x00406f4b:	movl %ecx, $0x448908<UINT32>
0x00406f50:	movb -4(%ebp), $0x5<UINT8>
0x00406f54:	call 0x00406a10
0x00406f59:	movl %ecx, $0x448918<UINT32>
0x00406f5e:	movb -4(%ebp), $0x6<UINT8>
0x00406f62:	call 0x00406a10
0x00406f67:	movl %ecx, $0x448928<UINT32>
0x00406f6c:	movb -4(%ebp), $0x7<UINT8>
0x00406f70:	call 0x00406a10
0x00406f75:	movl %ecx, $0x448938<UINT32>
0x00406f7a:	movb -4(%ebp), $0x8<UINT8>
0x00406f7e:	call 0x00406a10
0x00406f83:	movl %ecx, $0x448948<UINT32>
0x00406f88:	movb -4(%ebp), $0x9<UINT8>
0x00406f8c:	call 0x00406a10
0x00406f91:	movl %ecx, $0x448958<UINT32>
0x00406f96:	movb -4(%ebp), $0xa<UINT8>
0x00406f9a:	call 0x00406a10
0x00406f9f:	movl %ecx, $0x448968<UINT32>
0x00406fa4:	movb -4(%ebp), $0xb<UINT8>
0x00406fa8:	call 0x00406a10
0x00406fad:	movl %ecx, $0x448978<UINT32>
0x00406fb2:	movb -4(%ebp), $0xc<UINT8>
0x00406fb6:	call 0x00406a10
0x00406fbb:	movl %ecx, $0x4489f0<UINT32>
0x00406fc0:	movb -4(%ebp), $0xd<UINT8>
0x00406fc4:	call 0x004086b0
0x00406fc9:	pushl %esi
0x00406fca:	movb -4(%ebp), $0xe<UINT8>
0x00406fce:	call 0x00407490
0x00407490:	pushl %ebp
0x00407491:	movl %ebp, %esp
0x00407493:	andl %esp, $0xfffffff8<UINT8>
0x00407496:	pushl $0xffffffff<UINT8>
0x00407498:	pushl $0x4317eb<UINT32>
0x0040749d:	movl %eax, %fs:0
0x004074a3:	pushl %eax
0x004074a4:	subl %esp, $0x48<UINT8>
0x004074a7:	pushl %ebx
0x004074a8:	pushl %esi
0x004074a9:	pushl %edi
0x004074aa:	movl %eax, 0x44609c
0x004074af:	xorl %eax, %esp
0x004074b1:	pushl %eax
0x004074b2:	leal %eax, 0x58(%esp)
0x004074b6:	movl %fs:0, %eax
0x004074bc:	movl %edi, 0x8(%ebp)
0x004074bf:	addl %edi, $0x10<UINT8>
0x004074c2:	xorl %ebx, %ebx
0x004074c4:	pushl $0x43ff4c<UINT32>
0x004074c9:	movl %eax, %edi
0x004074cb:	movl 0x18(%esp), %ebx
0x004074cf:	call 0x00405eb0
0x00405eb0:	pushl %ebp
0x00405eb1:	movl %ebp, %esp
0x00405eb3:	pushl %ebx
0x00405eb4:	movl %ebx, 0x8(%ebp)
0x00405eb7:	pushl %esi
0x00405eb8:	pushl %edi
0x00405eb9:	movl %esi, %eax
0x00405ebb:	xorl %edi, %edi
0x00405ebd:	cmpl (%esi), %ebx
0x00405ebf:	je 96
0x00405ec1:	testl %ebx, %ebx
0x00405ec3:	je 42
0x00405ec5:	movl %eax, %ebx
0x00405ec7:	leal %edx, 0x1(%eax)
0x00405eca:	leal %ebx, (%ebx)
0x00405ed0:	movb %cl, (%eax)
0x00405ed2:	incl %eax
0x00405ed3:	testb %cl, %cl
0x00405ed5:	jne 0x00405ed0
0x00405ed7:	subl %eax, %edx
0x00405ed9:	movl %edi, %eax
0x00405edb:	je 0x00405eef
0x00405edd:	leal %eax, 0x10(%edi)
0x00405ee0:	cmpl %eax, 0x8(%esi)
0x00405ee3:	jbe 0x00405eef
0x00405ee5:	movl %ebx, %eax
0x00405ee7:	call 0x004034c0
0x004034e2:	xorl %eax, %eax
0x004034e4:	cmpl 0x4(%esi), %eax
0x004034e7:	jbe 0x004034fe
0x004034fe:	movl %eax, (%esi)
0x00403500:	pushl %eax
0x00403501:	call 0x0041d3a9
0x0041d3a9:	movl %edi, %edi
0x0041d3ab:	pushl %ebp
0x0041d3ac:	movl %ebp, %esp
0x0041d3ae:	popl %ebp
0x0041d3af:	jmp 0x0041dccf
0x0041dccf:	movl %edi, %edi
0x0041dcd1:	pushl %ebp
0x0041dcd2:	movl %ebp, %esp
0x0041dcd4:	popl %ebp
0x0041dcd5:	jmp 0x0041ecb7
0x00403506:	addl %esp, $0x4<UINT8>
0x00405eec:	movl %ebx, 0x8(%ebp)
0x00405eef:	movl %eax, %esi
0x00405ef1:	call 0x00403420
0x00403420:	pushl %ebx
0x00403421:	xorl %ebx, %ebx
0x00403423:	pushl %esi
0x00403424:	movl %esi, %eax
0x00403426:	cmpl %edi, %ebx
0x00403428:	jne 0x00403444
0x00403444:	movl %edx, 0x4(%esi)
0x00403447:	cmpl %edi, %edx
0x00403449:	jae 0x00403456
0x00403456:	cmpl %edi, 0x8(%esi)
0x00403459:	jbe 0x00403463
0x00403463:	movl %eax, 0x4(%esi)
0x00403466:	cmpl %eax, %edi
0x00403468:	jae 0x00403480
0x0040346a:	leal %ebx, (%ebx)
0x00403470:	movl %ecx, (%esi)
0x00403472:	movb %dl, 0x448860
0x00403478:	movb (%eax,%ecx), %dl
0x0040347b:	incl %eax
0x0040347c:	cmpl %eax, %edi
0x0040347e:	jb 0x00403470
0x00403480:	movl 0x4(%esi), %edi
0x00403483:	popl %esi
0x00403484:	popl %ebx
0x00403485:	ret

0x00405ef6:	testl %edi, %edi
0x00405ef8:	jne 0x00405f12
0x00405f12:	movl %eax, (%esi)
0x00405f14:	pushl %edi
0x00405f15:	pushl %ebx
0x00405f16:	pushl %eax
0x00405f17:	call 0x00420c20
0x00405f1c:	addl %esp, $0xc<UINT8>
0x00405f1f:	movl %eax, %esi
0x00405f21:	popl %edi
0x00405f22:	popl %esi
0x00405f23:	popl %ebx
0x00405f24:	popl %ebp
0x00405f25:	ret $0x4<UINT16>

0x004074d4:	movl 0x18(%esp), %ebx
0x004074d8:	movl %eax, 0x447588(%ebx)
0x004074de:	testl %eax, %eax
0x004074e0:	jle 133
0x004074e6:	cltd
0x004074e7:	pushl %edx
0x004074e8:	pushl %eax
0x004074e9:	leal %esi, 0x4c(%esp)
0x004074ed:	call 0x0041ae90
0x0041ae90:	pushl %ebp
0x0041ae91:	movl %ebp, %esp
0x0041ae93:	andl %esp, $0xfffffff8<UINT8>
0x0041ae96:	subl %esp, $0x20<UINT8>
0x0041ae99:	movl %eax, 0x44609c
0x0041ae9e:	xorl %eax, %esp
0x0041aea0:	movl 0x1c(%esp), %eax
0x0041aea4:	movl %ecx, 0x8(%ebp)
0x0041aea7:	xorl %eax, %eax
0x0041aea9:	movl 0x5(%esp), %eax
0x0041aead:	movl 0x9(%esp), %eax
0x0041aeb1:	movl 0xd(%esp), %eax
0x0041aeb5:	movl 0x11(%esp), %eax
0x0041aeb9:	movl 0x15(%esp), %eax
0x0041aebd:	movl %eax, 0xc(%ebp)
0x0041aec0:	pushl %eax
0x0041aec1:	pushl %ecx
0x0041aec2:	pushl $0x440cfc<UINT32>
0x0041aec7:	leal %edx, 0x10(%esp)
0x0041aecb:	pushl $0x15<UINT8>
0x0041aecd:	pushl %edx
0x0041aece:	movl 0x14(%esp), $0x0<UINT32>
0x0041aed6:	movb 0x18(%esp), $0x0<UINT8>
0x0041aedb:	call 0x0041d6a5
0x0041d6a5:	movl %edi, %edi
0x0041d6a7:	pushl %ebp
0x0041d6a8:	movl %ebp, %esp
0x0041d6aa:	leal %eax, 0x14(%ebp)
0x0041d6ad:	pushl %eax
0x0041d6ae:	pushl $0x0<UINT8>
0x0041d6b0:	pushl 0x10(%ebp)
0x0041d6b3:	pushl 0xc(%ebp)
0x0041d6b6:	pushl 0x8(%ebp)
0x0041d6b9:	call 0x004218b8
0x004218b8:	movl %edi, %edi
0x004218ba:	pushl %ebp
0x004218bb:	movl %ebp, %esp
0x004218bd:	pushl %ebx
0x004218be:	xorl %ebx, %ebx
0x004218c0:	cmpl 0x10(%ebp), %ebx
0x004218c3:	jne 0x004218e2
0x004218e2:	pushl %esi
0x004218e3:	movl %esi, 0x8(%ebp)
0x004218e6:	cmpl %esi, %ebx
0x004218e8:	je 5
0x004218ea:	cmpl 0xc(%ebp), %ebx
0x004218ed:	ja 0x004218fc
0x004218fc:	pushl 0x18(%ebp)
0x004218ff:	pushl 0x14(%ebp)
0x00421902:	pushl 0x10(%ebp)
0x00421905:	pushl 0xc(%ebp)
0x00421908:	pushl %esi
0x00421909:	pushl $0x429deb<UINT32>
0x0042190e:	call 0x004217ec
0x004217ec:	movl %edi, %edi
0x004217ee:	pushl %ebp
0x004217ef:	movl %ebp, %esp
0x004217f1:	subl %esp, $0x20<UINT8>
0x004217f4:	pushl %ebx
0x004217f5:	xorl %ebx, %ebx
0x004217f7:	cmpl 0x14(%ebp), %ebx
0x004217fa:	jne 0x0042181c
0x0042181c:	pushl %esi
0x0042181d:	movl %esi, 0xc(%ebp)
0x00421820:	pushl %edi
0x00421821:	movl %edi, 0x10(%ebp)
0x00421824:	cmpl %edi, %ebx
0x00421826:	je 33
0x00421828:	cmpl %esi, %ebx
0x0042182a:	jne 0x00421849
0x00421849:	movl %eax, $0x7fffffff<UINT32>
0x0042184e:	movl -28(%ebp), %eax
0x00421851:	cmpl %edi, %eax
0x00421853:	ja 3
0x00421855:	movl -28(%ebp), %edi
0x00421858:	pushl 0x1c(%ebp)
0x0042185b:	leal %eax, -32(%ebp)
0x0042185e:	pushl 0x18(%ebp)
0x00421861:	movl -20(%ebp), $0x42<UINT32>
0x00421868:	pushl 0x14(%ebp)
0x0042186b:	movl -24(%ebp), %esi
0x0042186e:	pushl %eax
0x0042186f:	movl -32(%ebp), %esi
0x00421872:	call 0x00429deb
0x00429deb:	movl %edi, %edi
0x00429ded:	pushl %ebp
0x00429dee:	movl %ebp, %esp
0x00429df0:	subl %esp, $0x278<UINT32>
0x00429df6:	movl %eax, 0x44609c
0x00429dfb:	xorl %eax, %ebp
0x00429dfd:	movl -4(%ebp), %eax
0x00429e00:	pushl %ebx
0x00429e01:	movl %ebx, 0xc(%ebp)
0x00429e04:	pushl %esi
0x00429e05:	movl %esi, 0x8(%ebp)
0x00429e08:	xorl %eax, %eax
0x00429e0a:	pushl %edi
0x00429e0b:	movl %edi, 0x14(%ebp)
0x00429e0e:	pushl 0x10(%ebp)
0x00429e11:	leal %ecx, -592(%ebp)
0x00429e17:	movl -608(%ebp), %esi
0x00429e1d:	movl -548(%ebp), %edi
0x00429e23:	movl -604(%ebp), %eax
0x00429e29:	movl -528(%ebp), %eax
0x00429e2f:	movl -564(%ebp), %eax
0x00429e35:	movl -536(%ebp), %eax
0x00429e3b:	movl -560(%ebp), %eax
0x00429e41:	movl -600(%ebp), %eax
0x00429e47:	movl -568(%ebp), %eax
0x00429e4d:	call 0x0041d900
0x00429e52:	testl %esi, %esi
0x00429e54:	jne 0x00429e8b
0x00429e8b:	testb 0xc(%esi), $0x40<UINT8>
0x00429e8f:	jne 0x00429eef
0x00429eef:	xorl %eax, %eax
0x00429ef1:	cmpl %ebx, %eax
0x00429ef3:	je -163
0x00429ef9:	movb %dl, (%ebx)
0x00429efb:	movl -552(%ebp), %eax
0x00429f01:	movl -544(%ebp), %eax
0x00429f07:	movl -576(%ebp), %eax
0x00429f0d:	movl -596(%ebp), %eax
0x00429f13:	movb -529(%ebp), %dl
0x00429f19:	testb %dl, %dl
0x00429f1b:	je 2640
0x00429f21:	incl %ebx
0x00429f22:	xorl %eax, %eax
0x00429f24:	cmpl -552(%ebp), %eax
0x00429f2a:	movl -572(%ebp), %ebx
0x00429f30:	jl 2579
0x00429f36:	movb %cl, %dl
0x00429f38:	subb %cl, $0x20<UINT8>
0x00429f3b:	cmpb %cl, $0x58<UINT8>
0x00429f3e:	ja 13
0x00429f40:	movsbl %eax, %dl
0x00429f43:	movzbl %eax, 0x43bef0(%eax)
0x00429f4a:	andl %eax, $0xf<UINT8>
0x00429f4d:	movl %ecx, -576(%ebp)
0x00429f53:	imull %eax, %eax, $0x9<UINT8>
0x00429f56:	movzbl %eax, 0x43bf10(%eax,%ecx)
0x00429f5e:	pushl $0x8<UINT8>
0x00429f60:	shrl %eax, $0x4<UINT8>
0x00429f63:	popl %esi
0x00429f64:	movl -576(%ebp), %eax
0x00429f6a:	cmpl %eax, %esi
0x00429f6c:	je -284
0x00429f72:	pushl $0x7<UINT8>
0x00429f74:	popl %ecx
0x00429f75:	cmpl %eax, %ecx
0x00429f77:	ja 2477
0x00429f7d:	jmp 0x0042a1e0
0x00429f84:	xorl %eax, %eax
0x00429f86:	orl -536(%ebp), $0xffffffff<UINT8>
0x00429f8d:	movl -620(%ebp), %eax
0x00429f93:	movl -600(%ebp), %eax
0x00429f99:	movl -564(%ebp), %eax
0x00429f9f:	movl -560(%ebp), %eax
0x00429fa5:	movl -528(%ebp), %eax
0x00429fab:	movl -568(%ebp), %eax
0x00429fb1:	jmp 0x0042a92a
0x0042a92a:	movl %ebx, -572(%ebp)
0x0042a930:	movb %al, (%ebx)
0x0042a932:	movb -529(%ebp), %al
0x0042a938:	testb %al, %al
0x0042a93a:	je 0x0042a949
0x0042a93c:	movl %edi, -548(%ebp)
0x0042a942:	movb %dl, %al
0x0042a944:	jmp 0x00429f21
0x0042a0b0:	cmpb %dl, $0x49<UINT8>
0x0042a0b3:	je 85
0x0042a0b5:	cmpb %dl, $0x68<UINT8>
0x0042a0b8:	je 68
0x0042a0ba:	cmpb %dl, $0x6c<UINT8>
0x0042a0bd:	je 0x0042a0d7
0x0042a0d7:	cmpb (%ebx), $0x6c<UINT8>
0x0042a0da:	jne 22
0x0042a0dc:	incl %ebx
0x0042a0dd:	orl -528(%ebp), $0x1000<UINT32>
0x0042a0e7:	movl -572(%ebp), %ebx
0x0042a0ed:	jmp 0x0042a92a
0x0042a1e0:	movsbl %eax, %dl
0x0042a1e3:	cmpl %eax, $0x64<UINT8>
0x0042a1e6:	jg 0x0042a3d6
0x0042a1ec:	je 0x0042a469
0x0042a469:	orl -528(%ebp), $0x40<UINT8>
0x0042a470:	movl -544(%ebp), $0xa<UINT32>
0x0042a47a:	movl %ecx, -528(%ebp)
0x0042a480:	testl %ecx, $0x8000<UINT32>
0x0042a486:	je 0x0042a633
0x0042a633:	testl %ecx, $0x1000<UINT32>
0x0042a639:	jne 0x0042a48c
0x0042a48c:	addl %edi, %esi
0x0042a48e:	movl %eax, -8(%edi)
0x0042a491:	movl %edx, -4(%edi)
0x0042a494:	jmp 0x0042a66c
0x0042a66c:	movl -548(%ebp), %edi
0x0042a672:	testb %cl, $0x40<UINT8>
0x0042a675:	je 0x0042a692
0x0042a677:	testl %edx, %edx
0x0042a679:	jg 23
0x0042a67b:	jl 0x0042a681
0x0042a67d:	testl %eax, %eax
0x0042a67f:	jae 0x0042a692
0x0042a692:	testl -528(%ebp), $0x9000<UINT32>
0x0042a69c:	movl %ebx, %edx
0x0042a69e:	movl %edi, %eax
0x0042a6a0:	jne 0x0042a6a4
0x0042a6a4:	cmpl -536(%ebp), $0x0<UINT8>
0x0042a6ab:	jnl 12
0x0042a6ad:	movl -536(%ebp), $0x1<UINT32>
0x0042a6b7:	jmp 0x0042a6d3
0x0042a6d3:	movl %eax, %edi
0x0042a6d5:	orl %eax, %ebx
0x0042a6d7:	jne 0x0042a6df
0x0042a6df:	leal %esi, -13(%ebp)
0x0042a6e2:	movl %eax, -536(%ebp)
0x0042a6e8:	decl -536(%ebp)
0x0042a6ee:	testl %eax, %eax
0x0042a6f0:	jg 0x0042a6f8
0x0042a6f8:	movl %eax, -544(%ebp)
0x0042a6fe:	cltd
0x0042a6ff:	pushl %edx
0x0042a700:	pushl %eax
0x0042a701:	pushl %ebx
0x0042a702:	pushl %edi
0x0042a703:	call 0x00429cb0
0x00429cb0:	pushl %esi
0x00429cb1:	movl %eax, 0x14(%esp)
0x00429cb5:	orl %eax, %eax
0x00429cb7:	jne 40
0x00429cb9:	movl %ecx, 0x10(%esp)
0x00429cbd:	movl %eax, 0xc(%esp)
0x00429cc1:	xorl %edx, %edx
0x00429cc3:	divl %eax, %ecx
0x00429cc5:	movl %ebx, %eax
0x00429cc7:	movl %eax, 0x8(%esp)
0x00429ccb:	divl %eax, %ecx
0x00429ccd:	movl %esi, %eax
0x00429ccf:	movl %eax, %ebx
0x00429cd1:	mull %eax, 0x10(%esp)
0x00429cd5:	movl %ecx, %eax
0x00429cd7:	movl %eax, %esi
0x00429cd9:	mull %eax, 0x10(%esp)
0x00429cdd:	addl %edx, %ecx
0x00429cdf:	jmp 0x00429d28
0x00429d28:	subl %eax, 0x8(%esp)
0x00429d2c:	sbbl %edx, 0xc(%esp)
0x00429d30:	negl %edx
0x00429d32:	negl %eax
0x00429d34:	sbbl %edx, $0x0<UINT8>
0x00429d37:	movl %ecx, %edx
0x00429d39:	movl %edx, %ebx
0x00429d3b:	movl %ebx, %ecx
0x00429d3d:	movl %ecx, %eax
0x00429d3f:	movl %eax, %esi
0x00429d41:	popl %esi
0x00429d42:	ret $0x10<UINT16>

0x0042a708:	addl %ecx, $0x30<UINT8>
0x0042a70b:	cmpl %ecx, $0x39<UINT8>
0x0042a70e:	movl -612(%ebp), %ebx
0x0042a714:	movl %edi, %eax
0x0042a716:	movl %ebx, %edx
0x0042a718:	jle 0x0042a720
0x0042a720:	movb (%esi), %cl
0x0042a722:	decl %esi
0x0042a723:	jmp 0x0042a6e2
0x0042a6f2:	movl %eax, %edi
0x0042a6f4:	orl %eax, %ebx
0x0042a6f6:	je 0x0042a725
0x0042a725:	leal %eax, -13(%ebp)
0x0042a728:	subl %eax, %esi
0x0042a72a:	incl %esi
0x0042a72b:	testl -528(%ebp), $0x200<UINT32>
0x0042a735:	movl -544(%ebp), %eax
0x0042a73b:	movl -540(%ebp), %esi
0x0042a741:	je 0x0042a7a5
0x0042a7a5:	cmpl -600(%ebp), $0x0<UINT8>
0x0042a7ac:	jne 348
0x0042a7b2:	movl %eax, -528(%ebp)
0x0042a7b8:	testb %al, $0x40<UINT8>
0x0042a7ba:	je 0x0042a7ee
0x0042a7bc:	testl %eax, $0x100<UINT32>
0x0042a7c1:	je 0x0042a7cc
0x0042a7cc:	testb %al, $0x1<UINT8>
0x0042a7ce:	je 0x0042a7d9
0x0042a7d9:	testb %al, $0x2<UINT8>
0x0042a7db:	je 0x0042a7ee
0x0042a7ee:	movl %ebx, -564(%ebp)
0x0042a7f4:	subl %ebx, -544(%ebp)
0x0042a7fa:	subl %ebx, -560(%ebp)
0x0042a800:	testb -528(%ebp), $0xc<UINT8>
0x0042a807:	jne 0x0042a820
0x0042a809:	pushl -608(%ebp)
0x0042a80f:	leal %eax, -552(%ebp)
0x0042a815:	pushl %ebx
0x0042a816:	pushl $0x20<UINT8>
0x0042a818:	call 0x00429d78
0x00429d78:	movl %edi, %edi
0x00429d7a:	pushl %ebp
0x00429d7b:	movl %ebp, %esp
0x00429d7d:	pushl %esi
0x00429d7e:	movl %esi, %eax
0x00429d80:	jmp 0x00429d95
0x00429d95:	cmpl 0xc(%ebp), $0x0<UINT8>
0x00429d99:	jg 0x00429d82
0x00429d9b:	popl %esi
0x00429d9c:	popl %ebp
0x00429d9d:	ret

0x0042a81d:	addl %esp, $0xc<UINT8>
0x0042a820:	pushl -560(%ebp)
0x0042a826:	movl %edi, -608(%ebp)
0x0042a82c:	leal %eax, -552(%ebp)
0x0042a832:	leal %ecx, -556(%ebp)
0x0042a838:	call 0x00429d9e
0x00429d9e:	movl %edi, %edi
0x00429da0:	pushl %ebp
0x00429da1:	movl %ebp, %esp
0x00429da3:	testb 0xc(%edi), $0x40<UINT8>
0x00429da7:	pushl %ebx
0x00429da8:	pushl %esi
0x00429da9:	movl %esi, %eax
0x00429dab:	movl %ebx, %ecx
0x00429dad:	je 50
0x00429daf:	cmpl 0x8(%edi), $0x0<UINT8>
0x00429db3:	jne 0x00429de1
0x00429de1:	cmpl 0x8(%ebp), $0x0<UINT8>
0x00429de5:	jg 0x00429dbc
0x00429de7:	popl %esi
0x00429de8:	popl %ebx
0x00429de9:	popl %ebp
0x00429dea:	ret

0x0042a83d:	testb -528(%ebp), $0x8<UINT8>
0x0042a844:	popl %ecx
0x0042a845:	je 0x0042a862
0x0042a862:	cmpl -568(%ebp), $0x0<UINT8>
0x0042a869:	movl %eax, -544(%ebp)
0x0042a86f:	je 0x0042a8d7
0x0042a8d7:	movl %ecx, -540(%ebp)
0x0042a8dd:	pushl %eax
0x0042a8de:	leal %eax, -552(%ebp)
0x0042a8e4:	call 0x00429d9e
0x00429dbc:	movb %al, (%ebx)
0x00429dbe:	decl 0x8(%ebp)
0x00429dc1:	movl %ecx, %edi
0x00429dc3:	call 0x00429d45
0x00429d45:	testb 0xc(%ecx), $0x40<UINT8>
0x00429d49:	je 6
0x00429d4b:	cmpl 0x8(%ecx), $0x0<UINT8>
0x00429d4f:	je 36
0x00429d51:	decl 0x4(%ecx)
0x00429d54:	js 11
0x00429d56:	movl %edx, (%ecx)
0x00429d58:	movb (%edx), %al
0x00429d5a:	incl (%ecx)
0x00429d5c:	movzbl %eax, %al
0x00429d5f:	jmp 0x00429d6d
0x00429d6d:	cmpl %eax, $0xffffffff<UINT8>
0x00429d70:	jne 0x00429d75
0x00429d75:	incl (%esi)
0x00429d77:	ret

0x00429dc8:	incl %ebx
0x00429dc9:	cmpl (%esi), $0xffffffff<UINT8>
0x00429dcc:	jne 0x00429de1
0x0042a8e9:	popl %ecx
0x0042a8ea:	cmpl -552(%ebp), $0x0<UINT8>
0x0042a8f1:	jl 27
0x0042a8f3:	testb -528(%ebp), $0x4<UINT8>
0x0042a8fa:	je 0x0042a90e
0x0042a90e:	cmpl -596(%ebp), $0x0<UINT8>
0x0042a915:	je 0x0042a92a
0x0042a949:	xorl %esi, %esi
0x0042a94b:	cmpl -576(%ebp), %esi
0x0042a951:	je 30
0x0042a953:	cmpl -576(%ebp), $0x7<UINT8>
0x0042a95a:	je 0x0042a971
0x0042a971:	cmpb -580(%ebp), $0x0<UINT8>
0x0042a978:	je 10
0x0042a97a:	movl %eax, -584(%ebp)
0x0042a980:	andl 0x70(%eax), $0xfffffffd<UINT8>
0x0042a984:	movl %eax, -552(%ebp)
0x0042a98a:	movl %ecx, -4(%ebp)
0x0042a98d:	popl %edi
0x0042a98e:	popl %esi
0x0042a98f:	xorl %ecx, %ebp
0x0042a991:	popl %ebx
0x0042a992:	call 0x0041d190
0x0042a997:	leave
0x0042a998:	ret

0x00421875:	addl %esp, $0x10<UINT8>
0x00421878:	movl 0x14(%ebp), %eax
0x0042187b:	cmpl %esi, %ebx
0x0042187d:	je 52
0x0042187f:	cmpl %eax, %ebx
0x00421881:	jl 34
0x00421883:	decl -28(%ebp)
0x00421886:	js 7
0x00421888:	movl %eax, -32(%ebp)
0x0042188b:	movb (%eax), %bl
0x0042188d:	jmp 0x004218a0
0x004218a0:	movl %eax, 0x14(%ebp)
0x004218a3:	jmp 0x004218b3
0x004218b3:	popl %edi
0x004218b4:	popl %esi
0x004218b5:	popl %ebx
0x004218b6:	leave
0x004218b7:	ret

0x00421913:	addl %esp, $0x18<UINT8>
0x00421916:	cmpl %eax, %ebx
0x00421918:	jnl 0x0042191c
0x0042191c:	cmpl %eax, $0xfffffffe<UINT8>
0x0042191f:	jne 0x0042193c
0x0042193c:	popl %esi
0x0042193d:	popl %ebx
0x0042193e:	popl %ebp
0x0042193f:	ret

0x0041d6be:	addl %esp, $0x14<UINT8>
0x0041d6c1:	popl %ebp
0x0041d6c2:	ret

0x0041aee0:	addl %esp, $0x14<UINT8>
0x0041aee3:	pushl $0x0<UINT8>
0x0041aee5:	leal %eax, 0x8(%esp)
0x0041aee9:	pushl %eax
0x0041aeea:	pushl %esi
0x0041aeeb:	call 0x004035d0
0x004035d0:	pushl %ebp
0x004035d1:	movl %ebp, %esp
0x004035d3:	pushl $0xffffffff<UINT8>
0x004035d5:	pushl $0x4307e8<UINT32>
0x004035da:	movl %eax, %fs:0
0x004035e0:	pushl %eax
0x004035e1:	pushl %esi
0x004035e2:	movl %eax, 0x44609c
0x004035e7:	xorl %eax, %ebp
0x004035e9:	pushl %eax
0x004035ea:	leal %eax, -12(%ebp)
0x004035ed:	movl %fs:0, %eax
0x004035f3:	movl %esi, 0x8(%ebp)
0x004035f6:	xorl %eax, %eax
0x004035f8:	movl (%esi), %eax
0x004035fa:	movl 0x4(%esi), %eax
0x004035fd:	movl 0x8(%esi), %eax
0x00403600:	movl -4(%ebp), %eax
0x00403603:	movl 0xc(%esi), $0x1<UINT32>
0x0040360a:	movb 0x448860, %al
0x0040360f:	movl %eax, 0xc(%ebp)
0x00403612:	pushl %eax
0x00403613:	movl %eax, 0x10(%ebp)
0x00403616:	movl %ecx, %esi
0x00403618:	call 0x00403540
0x00403540:	pushl %ebp
0x00403541:	movl %ebp, %esp
0x00403543:	pushl %ecx
0x00403544:	movl %edx, 0x8(%ebp)
0x00403547:	pushl %esi
0x00403548:	pushl %edi
0x00403549:	movl %edi, %eax
0x0040354b:	movl %esi, %ecx
0x0040354d:	testl %edx, %edx
0x0040354f:	je 111
0x00403551:	pushl %ebx
0x00403552:	movl %ebx, (%esi)
0x00403554:	cmpl %edx, %ebx
0x00403556:	sete -1(%ebp)
0x0040355a:	testl %edi, %edi
0x0040355c:	jne 0x00403580
0x0040355e:	movl %eax, %edx
0x00403560:	leal %edi, 0x1(%eax)
0x00403563:	movb %cl, (%eax)
0x00403565:	incl %eax
0x00403566:	testb %cl, %cl
0x00403568:	jne 0x00403563
0x0040356a:	subl %eax, %edi
0x0040356c:	movl %edi, %eax
0x0040356e:	jne 0x00403580
0x00403580:	movl %eax, 0x8(%esi)
0x00403583:	cmpl %edi, %eax
0x00403585:	ja 0x00403590
0x00403590:	leal %ebx, 0x10(%eax,%edi)
0x00403594:	cmpl %ebx, %eax
0x00403596:	jbe 8
0x00403598:	call 0x004034c0
0x0040359d:	movl %edx, 0x8(%ebp)
0x004035a0:	cmpb -1(%ebp), $0x0<UINT8>
0x004035a4:	popl %ebx
0x004035a5:	je 0x004035ab
0x004035ab:	movl %eax, %edx
0x004035ad:	movl %edx, 0x4(%esi)
0x004035b0:	addl %edx, (%esi)
0x004035b2:	pushl %edi
0x004035b3:	pushl %eax
0x004035b4:	pushl %edx
0x004035b5:	call 0x00420c20
0x004035ba:	addl %esp, $0xc<UINT8>
0x004035bd:	addl 0x4(%esi), %edi
0x004035c0:	popl %edi
0x004035c1:	movl %eax, %esi
0x004035c3:	popl %esi
0x004035c4:	movl %esp, %ebp
0x004035c6:	popl %ebp
0x004035c7:	ret $0x4<UINT16>

0x0040361d:	movl %eax, %esi
0x0040361f:	movl %ecx, -12(%ebp)
0x00403622:	movl %fs:0, %ecx
0x00403629:	popl %ecx
0x0040362a:	popl %esi
0x0040362b:	movl %esp, %ebp
0x0040362d:	popl %ebp
0x0040362e:	ret $0xc<UINT16>

0x0041aef0:	movl %ecx, 0x1c(%esp)
0x0041aef4:	xorl %ecx, %esp
0x0041aef6:	movl %eax, %esi
0x0041aef8:	call 0x0041d190
0x0041aefd:	movl %esp, %ebp
0x0041aeff:	popl %ebp
0x0041af00:	ret

0x004074f2:	addl %esp, $0x8<UINT8>
0x004074f5:	movl 0x1c(%esp), %eax
0x004074f9:	xorl %esi, %esi
0x004074fb:	movl 0x60(%esp), %esi
0x004074ff:	orl 0x14(%esp), $0x1<UINT8>
0x00407504:	cmpl (%edi), $0x43ff64<UINT32>
0x0040750a:	movl %ecx, 0x8(%edi)
0x0040750d:	sete 0x13(%esp)
0x00407512:	cmpl %ecx, $0x1<UINT8>
0x00407515:	jb 10
0x00407517:	movl %edx, %ecx
0x00407519:	subl %edx, 0x4(%edi)
0x0040751c:	cmpl %edx, $0x1<UINT8>
0x0040751f:	jae 0x0040753b
0x0040753b:	cmpb 0x13(%esp), $0x0<UINT8>
0x00407540:	je 0x00407546
0x00407546:	movl %ecx, $0x43ff64<UINT32>
0x0040754b:	movl %edx, 0x4(%edi)
0x0040754e:	movb %cl, (%ecx)
0x00407550:	addl %edx, (%edi)
0x00407552:	movb (%edx), %cl
0x00407554:	incl 0x4(%edi)
0x00407557:	movl %ecx, 0x4(%eax)
0x0040755a:	movl %eax, (%eax)
0x0040755c:	pushl %eax
0x0040755d:	movl %eax, %ecx
0x0040755f:	movl %ecx, %edi
0x00407561:	call 0x00403540
0x00403587:	movl %ecx, %eax
0x00403589:	subl %ecx, 0x4(%esi)
0x0040358c:	cmpl %edi, %ecx
0x0040358e:	jbe 0x004035a0
0x00420c84:	movl %eax, %edi
0x00420c86:	movl %edx, $0x3<UINT32>
0x00420c8b:	subl %ecx, $0x4<UINT8>
0x00420c8e:	jb 0x00420c9c
0x00420c90:	andl %eax, $0x3<UINT8>
0x00420c93:	addl %ecx, %eax
0x00420c95:	jmp 0x00420d08
0x00420cb8:	andl %edx, %ecx
0x00420cba:	movb %al, (%esi)
0x00420cbc:	movb (%edi), %al
0x00420cbe:	movb %al, 0x1(%esi)
0x00420cc1:	movb 0x1(%edi), %al
0x00420cc4:	movb %al, 0x2(%esi)
0x00420cc7:	shrl %ecx, $0x2<UINT8>
0x00420cca:	movb 0x2(%edi), %al
0x00420ccd:	addl %esi, $0x3<UINT8>
0x00420cd0:	addl %edi, $0x3<UINT8>
0x00420cd3:	cmpl %ecx, $0x8<UINT8>
0x00420cd6:	jb 0x00420ca4
0x00420dac:	movb %al, (%esi)
0x00420dae:	movb (%edi), %al
0x00420db0:	movl %eax, 0x8(%ebp)
0x00420db3:	popl %esi
0x00420db4:	popl %edi
0x00420db5:	leave
0x00420db6:	ret

0x00407566:	jmp 0x00407676
0x00407676:	testb 0x14(%esp), $0x4<UINT8>
0x0040767b:	je 0x0040769f
0x0040769f:	testb 0x14(%esp), $0x2<UINT8>
0x004076a4:	je 0x004076c8
0x004076c8:	movl 0x60(%esp), $0xffffffff<UINT32>
0x004076d0:	testb 0x14(%esp), $0x1<UINT8>
0x004076d5:	je 34
0x004076d7:	movl %eax, 0x44(%esp)
0x004076db:	andl 0x14(%esp), $0xfffffffe<UINT8>
0x004076e0:	cmpl %eax, %esi
0x004076e2:	je 9
0x004076e4:	pushl %eax
0x004076e5:	call 0x0041d3a9
0x004076ea:	addl %esp, $0x4<UINT8>
0x004076ed:	movl 0x44(%esp), %esi
0x004076f1:	movl 0x48(%esp), %esi
0x004076f5:	movl 0x4c(%esp), %esi
0x004076f9:	addl %ebx, $0x1c<UINT8>
0x004076fc:	movl 0x18(%esp), %ebx
0x00407700:	cmpl %ebx, $0x54<UINT8>
0x00407703:	jb 0x004074d8
0x00420ce4:	andl %edx, %ecx
0x00420ce6:	movb %al, (%esi)
0x00420ce8:	movb (%edi), %al
0x00420cea:	movb %al, 0x1(%esi)
0x00420ced:	shrl %ecx, $0x2<UINT8>
0x00420cf0:	movb 0x1(%edi), %al
0x00420cf3:	addl %esi, $0x2<UINT8>
0x00420cf6:	addl %edi, $0x2<UINT8>
0x00420cf9:	cmpl %ecx, $0x8<UINT8>
0x00420cfc:	jb 0x00420ca4
0x00420d08:	andl %edx, %ecx
0x00420d0a:	movb %al, (%esi)
0x00420d0c:	movb (%edi), %al
0x00420d0e:	addl %esi, $0x1<UINT8>
0x00420d11:	shrl %ecx, $0x2<UINT8>
0x00420d14:	addl %edi, $0x1<UINT8>
0x00420d17:	cmpl %ecx, $0x8<UINT8>
0x00420d1a:	jb 0x00420ca4
0x00407709:	movl %eax, 0x8(%ebp)
0x0040770c:	pushl %eax
0x0040770d:	movl %ecx, %edi
0x0040770f:	call 0x00408200
0x00408200:	pushl %ebp
0x00408201:	movl %ebp, %esp
0x00408203:	pushl $0xffffffff<UINT8>
0x00408205:	pushl $0x4315e1<UINT32>
0x0040820a:	movl %eax, %fs:0
0x00408210:	pushl %eax
0x00408211:	subl %esp, $0xa0<UINT32>
0x00408217:	movl %eax, 0x44609c
0x0040821c:	xorl %eax, %ebp
0x0040821e:	movl -20(%ebp), %eax
0x00408221:	pushl %ebx
0x00408222:	pushl %esi
0x00408223:	pushl %edi
0x00408224:	pushl %eax
0x00408225:	leal %eax, -12(%ebp)
0x00408228:	movl %fs:0, %eax
0x0040822e:	movl %eax, 0x8(%ebp)
0x00408231:	movl -136(%ebp), %eax
0x00408237:	xorl %eax, %eax
0x00408239:	movl -64(%ebp), %eax
0x0040823c:	movl -60(%ebp), %eax
0x0040823f:	movl %ebx, %ecx
0x00408241:	movl %edi, 0x4(%ebx)
0x00408244:	movl %eax, $0xaaaaaaab<UINT32>
0x00408249:	mull %eax, %edi
0x0040824b:	shrl %edx
0x0040824d:	leal %ecx, (%edx,%edx,2)
0x00408250:	movl %eax, %edi
0x00408252:	subl %eax, %ecx
0x00408254:	negl %eax
0x00408256:	sbbl %eax, %eax
0x00408258:	negl %eax
0x0040825a:	addl %eax, %edx
0x0040825c:	addl %eax, %eax
0x0040825e:	addl %eax, %eax
0x00408260:	incl %eax
0x00408261:	pushl %eax
0x00408262:	movb -132(%ebp), $0x0<UINT8>
0x00408269:	movl -56(%ebp), $0x67452301<UINT32>
0x00408270:	movl -52(%ebp), $0xefcdab89<UINT32>
0x00408277:	movl -48(%ebp), $0x98badcfe<UINT32>
0x0040827e:	movl -44(%ebp), $0x10325476<UINT32>
0x00408285:	movl -140(%ebp), %eax
0x0040828b:	call 0x0041cfd3
0x00408290:	movl %edx, -140(%ebp)
0x00408296:	pushl %edx
0x00408297:	movl %esi, %eax
0x00408299:	pushl $0x0<UINT8>
0x0040829b:	pushl %esi
0x0040829c:	call 0x0041f180
0x004082a1:	movl %ebx, (%ebx)
0x004082a3:	addl %esp, $0x10<UINT8>
0x004082a6:	movl %eax, %edi
0x004082a8:	movl %ecx, %esi
0x004082aa:	movl %edx, %ebx
0x004082ac:	call 0x004025a0
0x004025a0:	pushl %esi
0x004025a1:	pushl %edi
0x004025a2:	movl %edi, %eax
0x004025a4:	movl %esi, %edx
0x004025a6:	cmpl %edi, $0x3<UINT8>
0x004025a9:	jb 125
0x004025ab:	movl %eax, $0xaaaaaaab<UINT32>
0x004025b0:	mull %eax, %edi
0x004025b2:	shrl %edx
0x004025b4:	pushl %ebx
0x004025b5:	jmp 0x004025c0
0x004025c0:	movzbl %eax, (%esi)
0x004025c3:	shrl %eax, $0x2<UINT8>
0x004025c6:	movzbl %eax, 0x43d7c8(%eax)
0x004025cd:	movb (%ecx), %al
0x004025cf:	movzbl %eax, (%esi)
0x004025d2:	movzbl %ebx, 0x1(%esi)
0x004025d6:	andl %eax, $0x3<UINT8>
0x004025d9:	shll %eax, $0x4<UINT8>
0x004025dc:	shrl %ebx, $0x4<UINT8>
0x004025df:	orl %eax, %ebx
0x004025e1:	movzbl %eax, 0x43d7c8(%eax)
0x004025e8:	movb 0x1(%ecx), %al
0x004025eb:	movzbl %eax, 0x1(%esi)
0x004025ef:	movzbl %ebx, 0x2(%esi)
0x004025f3:	incl %ecx
0x004025f4:	andl %eax, $0xf<UINT8>
0x004025f7:	addl %eax, %eax
0x004025f9:	addl %eax, %eax
0x004025fb:	shrl %ebx, $0x6<UINT8>
0x004025fe:	orl %eax, %ebx
0x00402600:	movzbl %eax, 0x43d7c8(%eax)
0x00402607:	incl %ecx
0x00402608:	movb (%ecx), %al
0x0040260a:	movzbl %eax, 0x2(%esi)
0x0040260e:	andl %eax, $0x3f<UINT8>
0x00402611:	movzbl %eax, 0x43d7c8(%eax)
0x00402618:	incl %ecx
0x00402619:	movb (%ecx), %al
0x0040261b:	incl %ecx
0x0040261c:	subl %edi, $0x3<UINT8>
0x0040261f:	addl %esi, $0x3<UINT8>
0x00402622:	subl %edx, $0x1<UINT8>
0x00402625:	jne 0x004025c0
0x00402627:	popl %ebx
0x00402628:	testl %edi, %edi
0x0040262a:	jbe 0x00402684
0x0040262c:	movzbl %edx, (%esi)
0x0040262f:	shrl %edx, $0x2<UINT8>
0x00402632:	movzbl %eax, 0x43d7c8(%edx)
0x00402639:	movb (%ecx), %al
0x0040263b:	movb %al, (%esi)
0x0040263d:	andb %al, $0x3<UINT8>
0x0040263f:	incl %ecx
0x00402640:	shlb %al, $0x4<UINT8>
0x00402643:	cmpl %edi, $0x1<UINT8>
0x00402646:	jbe 8
0x00402648:	movb %dl, 0x1(%esi)
0x0040264b:	shrb %dl, $0x4<UINT8>
0x0040264e:	orb %al, %dl
0x00402650:	movzbl %eax, %al
0x00402653:	movb %dl, 0x43d7c8(%eax)
0x00402659:	movb (%ecx), %dl
0x0040265b:	incl %ecx
0x0040265c:	cmpl %edi, $0x2<UINT8>
0x0040265f:	jae 0x0040266f
0x0040266f:	movzbl %eax, 0x1(%esi)
0x00402673:	andl %eax, $0xf<UINT8>
0x00402676:	movb %al, 0x43d7c8(,%eax,4)
0x0040267d:	movb (%ecx), %al
0x0040267f:	incl %ecx
0x00402680:	movb (%ecx), $0x3d<UINT8>
0x00402683:	incl %ecx
0x00402684:	popl %edi
0x00402685:	movb (%ecx), $0x0<UINT8>
0x00402688:	popl %esi
0x00402689:	ret

0x004082b1:	pushl %esi
0x004082b2:	xorl %eax, %eax
0x004082b4:	leal %edi, -156(%ebp)
0x004082ba:	leal %ecx, -132(%ebp)
0x004082c0:	call 0x00403210
0x00403210:	pushl %ebp
0x00403211:	movl %ebp, %esp
0x00403213:	pushl %ecx
0x00403214:	xorl %edx, %edx
0x00403216:	pushl %esi
0x00403217:	movl %esi, %ecx
0x00403219:	movl -4(%ebp), %edx
0x0040321c:	cmpl %eax, %edx
0x0040321e:	jne 0x00403233
0x00403220:	movl %eax, 0x8(%ebp)
0x00403223:	pushl %ebx
0x00403224:	leal %ebx, 0x1(%eax)
0x00403227:	movb %cl, (%eax)
0x00403229:	incl %eax
0x0040322a:	cmpb %cl, %dl
0x0040322c:	jne 0x00403227
0x0040322e:	subl %eax, %ebx
0x00403230:	popl %ebx
0x00403231:	cmpl %eax, %edx
0x00403233:	jg 0x0040324a
0x0040324a:	movl %ecx, 0x8(%ebp)
0x0040324d:	pushl %ecx
0x0040324e:	movb (%esi), %dl
0x00403250:	movl 0x44(%esi), %edx
0x00403253:	movl 0x48(%esi), %edx
0x00403256:	movl 0x4c(%esi), $0x67452301<UINT32>
0x0040325d:	movl 0x50(%esi), $0xefcdab89<UINT32>
0x00403264:	movl 0x54(%esi), $0x98badcfe<UINT32>
0x0040326b:	movl 0x58(%esi), $0x10325476<UINT32>
0x00403272:	call 0x00402f50
0x00402f50:	pushl %ebp
0x00402f51:	movl %ebp, %esp
0x00402f53:	pushl %ecx
0x00402f54:	movl %ecx, 0x44(%esi)
0x00402f57:	pushl %ebx
0x00402f58:	pushl %edi
0x00402f59:	movl %edi, %eax
0x00402f5b:	movl %eax, %ecx
0x00402f5d:	shrl %eax, $0x3<UINT8>
0x00402f60:	leal %ecx, (%ecx,%edi,8)
0x00402f63:	leal %edx, (,%edi,8)
0x00402f6a:	andl %eax, $0x3f<UINT8>
0x00402f6d:	movl 0x44(%esi), %ecx
0x00402f70:	cmpl %ecx, %edx
0x00402f72:	jae 0x00402f77
0x00402f77:	movl %ecx, %edi
0x00402f79:	shrl %ecx, $0x1d<UINT8>
0x00402f7c:	addl 0x48(%esi), %ecx
0x00402f7f:	movl %ebx, $0x40<UINT32>
0x00402f84:	subl %ebx, %eax
0x00402f86:	cmpl %edi, %ebx
0x00402f88:	jb 0x00402fcd
0x00402fcd:	movl -4(%ebp), $0x0<UINT32>
0x00402fd4:	movl %ecx, -4(%ebp)
0x00402fd7:	movl %edx, 0x8(%ebp)
0x00402fda:	subl %edi, %ecx
0x00402fdc:	pushl %edi
0x00402fdd:	addl %ecx, %edx
0x00402fdf:	pushl %ecx
0x00402fe0:	leal %eax, 0x1(%eax,%esi)
0x00402fe4:	pushl %eax
0x00402fe5:	call 0x00420c20
0x00420cd8:	rep movsl %es:(%edi), %ds:(%esi)
0x00420cda:	jmp 0x00420dac
0x00402fea:	addl %esp, $0xc<UINT8>
0x00402fed:	popl %edi
0x00402fee:	popl %ebx
0x00402fef:	movl %esp, %ebp
0x00402ff1:	popl %ebp
0x00402ff2:	ret $0x4<UINT16>

0x00403277:	movl %ecx, %esi
0x00403279:	call 0x00403000
0x00403000:	pushl %ebp
0x00403001:	movl %ebp, %esp
0x00403003:	subl %esp, $0xc<UINT8>
0x00403006:	movl %eax, 0x44609c
0x0040300b:	xorl %eax, %ebp
0x0040300d:	movl -4(%ebp), %eax
0x00403010:	pushl %esi
0x00403011:	movl %esi, %ecx
0x00403013:	cmpb (%esi), $0x0<UINT8>
0x00403016:	jne 180
0x0040301c:	xorl %ecx, %ecx
0x0040301e:	leal %eax, 0x46(%esi)
0x00403021:	movzbl %edx, -2(%eax)
0x00403025:	movb -12(%ebp,%ecx), %dl
0x00403029:	movzbl %edx, -1(%eax)
0x0040302d:	movb -11(%ebp,%ecx), %dl
0x00403031:	movzbl %edx, (%eax)
0x00403034:	movb -10(%ebp,%ecx), %dl
0x00403038:	movzbl %edx, 0x1(%eax)
0x0040303c:	movb -9(%ebp,%ecx), %dl
0x00403040:	addl %ecx, $0x4<UINT8>
0x00403043:	addl %eax, $0x4<UINT8>
0x00403046:	cmpl %ecx, $0x8<UINT8>
0x00403049:	jb 0x00403021
0x0040304b:	movl %ecx, 0x44(%esi)
0x0040304e:	shrl %ecx, $0x3<UINT8>
0x00403051:	andl %ecx, $0x3f<UINT8>
0x00403054:	movl %eax, $0x38<UINT32>
0x00403059:	cmpl %ecx, $0x38<UINT8>
0x0040305c:	jb 0x00403063
0x00403063:	pushl %edi
0x00403064:	subl %eax, %ecx
0x00403066:	pushl $0x4473a8<UINT32>
0x0040306b:	call 0x00402f50
0x00403070:	leal %eax, -12(%ebp)
0x00403073:	pushl %eax
0x00403074:	movl %eax, $0x8<UINT32>
0x00403079:	call 0x00402f50
0x00402f8a:	movl %edx, 0x8(%ebp)
0x00402f8d:	pushl %ebx
0x00402f8e:	pushl %edx
0x00402f8f:	leal %eax, 0x1(%eax,%esi)
0x00402f93:	pushl %eax
0x00402f94:	call 0x00420c20
0x00402f99:	addl %esp, $0xc<UINT8>
0x00402f9c:	leal %ecx, 0x1(%esi)
0x00402f9f:	pushl %ecx
0x00402fa0:	pushl %esi
0x00402fa1:	call 0x004028b0
0x004028b0:	pushl %ebp
0x004028b1:	movl %ebp, %esp
0x004028b3:	subl %esp, $0x44<UINT8>
0x004028b6:	pushl %ebx
0x004028b7:	pushl %esi
0x004028b8:	pushl %edi
0x004028b9:	movl %edi, 0x8(%ebp)
0x004028bc:	movl %eax, 0x58(%edi)
0x004028bf:	movl %esi, 0x50(%edi)
0x004028c2:	movl %ebx, 0x54(%edi)
0x004028c5:	movl -4(%ebp), %eax
0x004028c8:	movl %eax, 0xc(%ebp)
0x004028cb:	leal %ecx, -68(%ebp)
0x004028ce:	call 0x00402870
0x00402870:	pushl %esi
0x00402871:	addl %eax, $0x2<UINT8>
0x00402874:	movl %edx, $0x10<UINT32>
0x00402879:	pushl %edi
0x0040287a:	leal %ebx, (%ebx)
0x00402880:	movzbl %esi, 0x1(%eax)
0x00402884:	movzbl %edi, (%eax)
0x00402887:	shll %esi, $0x8<UINT8>
0x0040288a:	orl %esi, %edi
0x0040288c:	movzbl %edi, -1(%eax)
0x00402890:	shll %esi, $0x8<UINT8>
0x00402893:	orl %esi, %edi
0x00402895:	movzbl %edi, -2(%eax)
0x00402899:	shll %esi, $0x8<UINT8>
0x0040289c:	orl %esi, %edi
0x0040289e:	movl (%ecx), %esi
0x004028a0:	addl %ecx, $0x4<UINT8>
0x004028a3:	addl %eax, $0x4<UINT8>
0x004028a6:	subl %edx, $0x1<UINT8>
0x004028a9:	jne 0x00402880
0x004028ab:	popl %edi
0x004028ac:	popl %esi
0x004028ad:	ret

0x004028d3:	movl %edi, 0x4c(%edi)
0x004028d6:	movl %ecx, -4(%ebp)
0x004028d9:	movl %edx, %esi
0x004028db:	notl %edx
0x004028dd:	andl %edx, %ecx
0x004028df:	movl %eax, %ebx
0x004028e1:	andl %eax, %esi
0x004028e3:	orl %edx, %eax
0x004028e5:	addl %edx, %edi
0x004028e7:	movl %eax, -68(%ebp)
0x004028ea:	leal %eax, -680876936(%edx,%eax)
0x004028f1:	roll %eax, $0x7<UINT8>
0x004028f4:	addl %eax, %esi
0x004028f6:	movl %edi, %esi
0x004028f8:	andl %edi, %eax
0x004028fa:	movl %edx, %eax
0x004028fc:	notl %edx
0x004028fe:	andl %edx, %ebx
0x00402900:	orl %edx, %edi
0x00402902:	addl %edx, -64(%ebp)
0x00402905:	leal %ecx, -389564586(%edx,%ecx)
0x0040290c:	roll %ecx, $0xc<UINT8>
0x0040290f:	addl %ecx, %eax
0x00402911:	movl %edx, %ecx
0x00402913:	notl %edx
0x00402915:	andl %edx, %esi
0x00402917:	movl %edi, %ecx
0x00402919:	andl %edi, %eax
0x0040291b:	orl %edx, %edi
0x0040291d:	addl %edx, -60(%ebp)
0x00402920:	leal %edx, 0x242070db(%edx,%ebx)
0x00402927:	rorl %edx, $0xf<UINT8>
0x0040292a:	addl %edx, %ecx
0x0040292c:	movl %ebx, %ecx
0x0040292e:	andl %ebx, %edx
0x00402930:	movl %edi, %edx
0x00402932:	notl %edi
0x00402934:	andl %edi, %eax
0x00402936:	orl %edi, %ebx
0x00402938:	addl %edi, -56(%ebp)
0x0040293b:	leal %esi, -1044525330(%edi,%esi)
0x00402942:	rorl %esi, $0xa<UINT8>
0x00402945:	addl %esi, %edx
0x00402947:	movl 0xc(%ebp), %esi
0x0040294a:	notl %esi
0x0040294c:	andl %esi, %ecx
0x0040294e:	movl %edi, %edx
0x00402950:	andl %edi, 0xc(%ebp)
0x00402953:	orl %esi, %edi
0x00402955:	addl %esi, -52(%ebp)
0x00402958:	leal %eax, -176418897(%esi,%eax)
0x0040295f:	movl %esi, 0xc(%ebp)
0x00402962:	roll %eax, $0x7<UINT8>
0x00402965:	addl %eax, %esi
0x00402967:	movl %edi, %eax
0x00402969:	notl %edi
0x0040296b:	andl %edi, %edx
0x0040296d:	movl %ebx, %esi
0x0040296f:	andl %ebx, %eax
0x00402971:	orl %edi, %ebx
0x00402973:	addl %edi, -48(%ebp)
0x00402976:	leal %ecx, 0x4787c62a(%edi,%ecx)
0x0040297d:	roll %ecx, $0xc<UINT8>
0x00402980:	addl %ecx, %eax
0x00402982:	movl %edi, %ecx
0x00402984:	notl %edi
0x00402986:	movl %ebx, %ecx
0x00402988:	andl %edi, %esi
0x0040298a:	andl %ebx, %eax
0x0040298c:	orl %edi, %ebx
0x0040298e:	addl %edi, -44(%ebp)
0x00402991:	movl %ebx, %ecx
0x00402993:	leal %edx, -1473231341(%edi,%edx)
0x0040299a:	rorl %edx, $0xf<UINT8>
0x0040299d:	addl %edx, %ecx
0x0040299f:	andl %ebx, %edx
0x004029a1:	movl %edi, %edx
0x004029a3:	notl %edi
0x004029a5:	andl %edi, %eax
0x004029a7:	orl %edi, %ebx
0x004029a9:	addl %edi, -40(%ebp)
0x004029ac:	leal %esi, -45705983(%edi,%esi)
0x004029b3:	rorl %esi, $0xa<UINT8>
0x004029b6:	addl %esi, %edx
0x004029b8:	movl 0xc(%ebp), %esi
0x004029bb:	notl %esi
0x004029bd:	andl %esi, %ecx
0x004029bf:	movl %edi, %edx
0x004029c1:	andl %edi, 0xc(%ebp)
0x004029c4:	orl %esi, %edi
0x004029c6:	addl %esi, -36(%ebp)
0x004029c9:	leal %eax, 0x698098d8(%esi,%eax)
0x004029d0:	movl %esi, 0xc(%ebp)
0x004029d3:	roll %eax, $0x7<UINT8>
0x004029d6:	addl %eax, %esi
0x004029d8:	movl %ebx, %esi
0x004029da:	andl %ebx, %eax
0x004029dc:	movl %edi, %eax
0x004029de:	notl %edi
0x004029e0:	andl %edi, %edx
0x004029e2:	orl %edi, %ebx
0x004029e4:	addl %edi, -32(%ebp)
0x004029e7:	leal %ecx, -1958414417(%edi,%ecx)
0x004029ee:	roll %ecx, $0xc<UINT8>
0x004029f1:	addl %ecx, %eax
0x004029f3:	movl %edi, %ecx
0x004029f5:	notl %edi
0x004029f7:	andl %edi, %esi
0x004029f9:	movl %ebx, %ecx
0x004029fb:	andl %ebx, %eax
0x004029fd:	orl %edi, %ebx
0x004029ff:	addl %edi, -28(%ebp)
0x00402a02:	movl %ebx, %ecx
0x00402a04:	leal %edx, -42063(%edi,%edx)
0x00402a0b:	rorl %edx, $0xf<UINT8>
0x00402a0e:	addl %edx, %ecx
0x00402a10:	andl %ebx, %edx
0x00402a12:	movl %edi, %edx
0x00402a14:	notl %edi
0x00402a16:	andl %edi, %eax
0x00402a18:	orl %edi, %ebx
0x00402a1a:	addl %edi, -24(%ebp)
0x00402a1d:	leal %esi, -1990404162(%edi,%esi)
0x00402a24:	rorl %esi, $0xa<UINT8>
0x00402a27:	addl %esi, %edx
0x00402a29:	movl 0xc(%ebp), %esi
0x00402a2c:	notl %esi
0x00402a2e:	andl %esi, %ecx
0x00402a30:	movl %edi, %edx
0x00402a32:	andl %edi, 0xc(%ebp)
0x00402a35:	orl %esi, %edi
0x00402a37:	addl %esi, -20(%ebp)
0x00402a3a:	leal %eax, 0x6b901122(%esi,%eax)
0x00402a41:	movl %esi, 0xc(%ebp)
0x00402a44:	roll %eax, $0x7<UINT8>
0x00402a47:	addl %eax, %esi
0x00402a49:	movl %edi, %eax
0x00402a4b:	notl %edi
0x00402a4d:	andl %edi, %edx
0x00402a4f:	movl %ebx, %esi
0x00402a51:	andl %ebx, %eax
0x00402a53:	orl %edi, %ebx
0x00402a55:	addl %edi, -16(%ebp)
0x00402a58:	leal %ecx, -40341101(%edi,%ecx)
0x00402a5f:	roll %ecx, $0xc<UINT8>
0x00402a62:	addl %ecx, %eax
0x00402a64:	movl %edi, %ecx
0x00402a66:	notl %edi
0x00402a68:	movl %ebx, %edi
0x00402a6a:	andl %ebx, %esi
0x00402a6c:	movl %esi, %ecx
0x00402a6e:	andl %esi, %eax
0x00402a70:	orl %ebx, %esi
0x00402a72:	addl %ebx, -12(%ebp)
0x00402a75:	leal %edx, -1502002290(%ebx,%edx)
0x00402a7c:	rorl %edx, $0xf<UINT8>
0x00402a7f:	addl %edx, %ecx
0x00402a81:	andl %edi, %edx
0x00402a83:	movl %esi, %edx
0x00402a85:	notl %esi
0x00402a87:	movl -4(%ebp), %esi
0x00402a8a:	andl %esi, %eax
0x00402a8c:	movl %ebx, %ecx
0x00402a8e:	andl %ebx, %edx
0x00402a90:	orl %esi, %ebx
0x00402a92:	addl %esi, -8(%ebp)
0x00402a95:	movl %ebx, 0xc(%ebp)
0x00402a98:	leal %esi, 0x49b40821(%esi,%ebx)
0x00402a9f:	rorl %esi, $0xa<UINT8>
0x00402aa2:	addl %esi, %edx
0x00402aa4:	movl %ebx, %ecx
0x00402aa6:	andl %ebx, %esi
0x00402aa8:	orl %edi, %ebx
0x00402aaa:	addl %edi, -64(%ebp)
0x00402aad:	movl %ebx, %edx
0x00402aaf:	leal %eax, -165796510(%edi,%eax)
0x00402ab6:	movl %edi, -4(%ebp)
0x00402ab9:	andl %edi, %esi
0x00402abb:	roll %eax, $0x5<UINT8>
0x00402abe:	addl %eax, %esi
0x00402ac0:	andl %ebx, %eax
0x00402ac2:	orl %edi, %ebx
0x00402ac4:	addl %edi, -44(%ebp)
0x00402ac7:	leal %ecx, -1069501632(%edi,%ecx)
0x00402ace:	roll %ecx, $0x9<UINT8>
0x00402ad1:	addl %ecx, %eax
0x00402ad3:	movl %edi, %esi
0x00402ad5:	notl %edi
0x00402ad7:	andl %edi, %eax
0x00402ad9:	movl %ebx, %ecx
0x00402adb:	andl %ebx, %esi
0x00402add:	orl %edi, %ebx
0x00402adf:	addl %edi, -24(%ebp)
0x00402ae2:	leal %edx, 0x265e5a51(%edi,%edx)
0x00402ae9:	movl %edi, %eax
0x00402aeb:	notl %edi
0x00402aed:	andl %edi, %ecx
0x00402aef:	roll %edx, $0xe<UINT8>
0x00402af2:	addl %edx, %ecx
0x00402af4:	movl %ebx, %edx
0x00402af6:	andl %ebx, %eax
0x00402af8:	orl %edi, %ebx
0x00402afa:	addl %edi, -68(%ebp)
0x00402afd:	movl %ebx, %ecx
0x00402aff:	leal %esi, -373897302(%edi,%esi)
0x00402b06:	rorl %esi, $0xc<UINT8>
0x00402b09:	addl %esi, %edx
0x00402b0b:	movl %edi, %ecx
0x00402b0d:	notl %edi
0x00402b0f:	andl %edi, %edx
0x00402b11:	andl %ebx, %esi
0x00402b13:	orl %edi, %ebx
0x00402b15:	addl %edi, -48(%ebp)
0x00402b18:	leal %eax, -701558691(%edi,%eax)
0x00402b1f:	roll %eax, $0x5<UINT8>
0x00402b22:	addl %eax, %esi
0x00402b24:	movl 0xc(%ebp), %eax
0x00402b27:	movl %eax, %edx
0x00402b29:	notl %eax
0x00402b2b:	andl %eax, %esi
0x00402b2d:	movl %edi, %edx
0x00402b2f:	andl %edi, 0xc(%ebp)
0x00402b32:	orl %eax, %edi
0x00402b34:	addl %eax, -28(%ebp)
0x00402b37:	movl %edi, %esi
0x00402b39:	leal %ecx, 0x2441453(%eax,%ecx)
0x00402b40:	movl %eax, 0xc(%ebp)
0x00402b43:	roll %ecx, $0x9<UINT8>
0x00402b46:	addl %ecx, %eax
0x00402b48:	notl %edi
0x00402b4a:	andl %edi, %eax
0x00402b4c:	movl %ebx, %ecx
0x00402b4e:	notl %eax
0x00402b50:	andl %eax, %ecx
0x00402b52:	andl %ebx, %esi
0x00402b54:	orl %edi, %ebx
0x00402b56:	addl %edi, -8(%ebp)
0x00402b59:	leal %edx, -660478335(%edi,%edx)
0x00402b60:	roll %edx, $0xe<UINT8>
0x00402b63:	addl %edx, %ecx
0x00402b65:	movl %edi, %edx
0x00402b67:	andl %edi, 0xc(%ebp)
0x00402b6a:	orl %eax, %edi
0x00402b6c:	addl %eax, -52(%ebp)
0x00402b6f:	movl %edi, %ecx
0x00402b71:	leal %esi, -405537848(%eax,%esi)
0x00402b78:	rorl %esi, $0xc<UINT8>
0x00402b7b:	addl %esi, %edx
0x00402b7d:	andl %edi, %esi
0x00402b7f:	movl %eax, %ecx
0x00402b81:	notl %eax
0x00402b83:	andl %eax, %edx
0x00402b85:	orl %eax, %edi
0x00402b87:	addl %eax, -32(%ebp)
0x00402b8a:	movl %edi, 0xc(%ebp)
0x00402b8d:	leal %eax, 0x21e1cde6(%eax,%edi)
0x00402b94:	roll %eax, $0x5<UINT8>
0x00402b97:	addl %eax, %esi
0x00402b99:	movl 0xc(%ebp), %eax
0x00402b9c:	movl %eax, %edx
0x00402b9e:	notl %eax
0x00402ba0:	andl %eax, %esi
0x00402ba2:	movl %edi, %edx
0x00402ba4:	andl %edi, 0xc(%ebp)
0x00402ba7:	orl %eax, %edi
0x00402ba9:	addl %eax, -12(%ebp)
0x00402bac:	movl %edi, %esi
0x00402bae:	leal %ecx, -1019803690(%eax,%ecx)
0x00402bb5:	movl %eax, 0xc(%ebp)
0x00402bb8:	roll %ecx, $0x9<UINT8>
0x00402bbb:	addl %ecx, %eax
0x00402bbd:	notl %edi
0x00402bbf:	andl %edi, %eax
0x00402bc1:	movl %ebx, %ecx
0x00402bc3:	andl %ebx, %esi
0x00402bc5:	orl %edi, %ebx
0x00402bc7:	addl %edi, -56(%ebp)
0x00402bca:	notl %eax
0x00402bcc:	andl %eax, %ecx
0x00402bce:	leal %edx, -187363961(%edi,%edx)
0x00402bd5:	roll %edx, $0xe<UINT8>
0x00402bd8:	addl %edx, %ecx
0x00402bda:	movl %edi, %edx
0x00402bdc:	andl %edi, 0xc(%ebp)
0x00402bdf:	orl %eax, %edi
0x00402be1:	addl %eax, -36(%ebp)
0x00402be4:	movl %edi, %ecx
0x00402be6:	leal %esi, 0x455a14ed(%eax,%esi)
0x00402bed:	rorl %esi, $0xc<UINT8>
0x00402bf0:	addl %esi, %edx
0x00402bf2:	movl %eax, %ecx
0x00402bf4:	notl %eax
0x00402bf6:	andl %eax, %edx
0x00402bf8:	andl %edi, %esi
0x00402bfa:	orl %eax, %edi
0x00402bfc:	addl %eax, -16(%ebp)
0x00402bff:	movl %edi, 0xc(%ebp)
0x00402c02:	leal %eax, -1444681467(%eax,%edi)
0x00402c09:	roll %eax, $0x5<UINT8>
0x00402c0c:	addl %eax, %esi
0x00402c0e:	movl 0xc(%ebp), %eax
0x00402c11:	movl %eax, %edx
0x00402c13:	notl %eax
0x00402c15:	andl %eax, %esi
0x00402c17:	movl %edi, %edx
0x00402c19:	andl %edi, 0xc(%ebp)
0x00402c1c:	orl %eax, %edi
0x00402c1e:	addl %eax, -60(%ebp)
0x00402c21:	movl %edi, %esi
0x00402c23:	leal %ecx, -51403784(%eax,%ecx)
0x00402c2a:	movl %eax, 0xc(%ebp)
0x00402c2d:	roll %ecx, $0x9<UINT8>
0x00402c30:	addl %ecx, %eax
0x00402c32:	notl %edi
0x00402c34:	andl %edi, %eax
0x00402c36:	movl %ebx, %ecx
0x00402c38:	andl %ebx, %esi
0x00402c3a:	orl %edi, %ebx
0x00402c3c:	addl %edi, -40(%ebp)
0x00402c3f:	notl %eax
0x00402c41:	leal %edx, 0x676f02d9(%edi,%edx)
0x00402c48:	andl %eax, %ecx
0x00402c4a:	roll %edx, $0xe<UINT8>
0x00402c4d:	addl %edx, %ecx
0x00402c4f:	movl %edi, %edx
0x00402c51:	andl %edi, 0xc(%ebp)
0x00402c54:	orl %eax, %edi
0x00402c56:	addl %eax, -20(%ebp)
0x00402c59:	movl %edi, 0xc(%ebp)
0x00402c5c:	leal %esi, -1926607734(%eax,%esi)
0x00402c63:	movl %eax, %ecx
0x00402c65:	xorl %eax, %edx
0x00402c67:	rorl %esi, $0xc<UINT8>
0x00402c6a:	addl %esi, %edx
0x00402c6c:	xorl %eax, %esi
0x00402c6e:	addl %eax, -48(%ebp)
0x00402c71:	leal %eax, -378558(%eax,%edi)
0x00402c78:	roll %eax, $0x4<UINT8>
0x00402c7b:	addl %eax, %esi
0x00402c7d:	movl %edi, %edx
0x00402c7f:	xorl %edi, %esi
0x00402c81:	xorl %edi, %eax
0x00402c83:	addl %edi, -36(%ebp)
0x00402c86:	leal %ecx, -2022574463(%edi,%ecx)
0x00402c8d:	roll %ecx, $0xb<UINT8>
0x00402c90:	addl %ecx, %eax
0x00402c92:	movl %edi, %ecx
0x00402c94:	xorl %edi, %esi
0x00402c96:	xorl %edi, %eax
0x00402c98:	addl %edi, -24(%ebp)
0x00402c9b:	leal %edx, 0x6d9d6122(%edi,%edx)
0x00402ca2:	roll %edx, $0x10<UINT8>
0x00402ca5:	addl %edx, %ecx
0x00402ca7:	movl %edi, %ecx
0x00402ca9:	xorl %edi, %edx
0x00402cab:	movl 0xc(%ebp), %edi
0x00402cae:	xorl %edi, %eax
0x00402cb0:	addl %edi, -12(%ebp)
0x00402cb3:	leal %esi, -35309556(%edi,%esi)
0x00402cba:	movl %edi, 0xc(%ebp)
0x00402cbd:	rorl %esi, $0x9<UINT8>
0x00402cc0:	addl %esi, %edx
0x00402cc2:	xorl %edi, %esi
0x00402cc4:	addl %edi, -64(%ebp)
0x00402cc7:	leal %eax, -1530992060(%edi,%eax)
0x00402cce:	roll %eax, $0x4<UINT8>
0x00402cd1:	addl %eax, %esi
0x00402cd3:	movl %edi, %edx
0x00402cd5:	xorl %edi, %esi
0x00402cd7:	xorl %edi, %eax
0x00402cd9:	addl %edi, -52(%ebp)
0x00402cdc:	leal %edi, 0x4bdecfa9(%edi,%ecx)
0x00402ce3:	roll %edi, $0xb<UINT8>
0x00402ce6:	addl %edi, %eax
0x00402ce8:	movl %ecx, %edi
0x00402cea:	xorl %ecx, %esi
0x00402cec:	xorl %ecx, %eax
0x00402cee:	addl %ecx, -40(%ebp)
0x00402cf1:	movl %ebx, %edi
0x00402cf3:	leal %edx, -155497632(%ecx,%edx)
0x00402cfa:	roll %edx, $0x10<UINT8>
0x00402cfd:	addl %edx, %edi
0x00402cff:	xorl %ebx, %edx
0x00402d01:	movl %ecx, %ebx
0x00402d03:	xorl %ecx, %eax
0x00402d05:	addl %ecx, -28(%ebp)
0x00402d08:	leal %ecx, -1094730640(%ecx,%esi)
0x00402d0f:	rorl %ecx, $0x9<UINT8>
0x00402d12:	addl %ecx, %edx
0x00402d14:	xorl %ebx, %ecx
0x00402d16:	addl %ebx, -16(%ebp)
0x00402d19:	leal %eax, 0x289b7ec6(%ebx,%eax)
0x00402d20:	roll %eax, $0x4<UINT8>
0x00402d23:	addl %eax, %ecx
0x00402d25:	movl %esi, %edx
0x00402d27:	xorl %esi, %ecx
0x00402d29:	xorl %esi, %eax
0x00402d2b:	addl %esi, -68(%ebp)
0x00402d2e:	leal %esi, -358537222(%esi,%edi)
0x00402d35:	roll %esi, $0xb<UINT8>
0x00402d38:	addl %esi, %eax
0x00402d3a:	movl %edi, %esi
0x00402d3c:	xorl %edi, %ecx
0x00402d3e:	xorl %edi, %eax
0x00402d40:	addl %edi, -56(%ebp)
0x00402d43:	leal %edi, -722521979(%edi,%edx)
0x00402d4a:	roll %edi, $0x10<UINT8>
0x00402d4d:	addl %edi, %esi
0x00402d4f:	movl %edx, %esi
0x00402d51:	xorl %edx, %edi
0x00402d53:	movl %ebx, %edx
0x00402d55:	xorl %ebx, %eax
0x00402d57:	addl %ebx, -44(%ebp)
0x00402d5a:	leal %ecx, 0x4881d05(%ebx,%ecx)
0x00402d61:	rorl %ecx, $0x9<UINT8>
0x00402d64:	addl %ecx, %edi
0x00402d66:	xorl %edx, %ecx
0x00402d68:	addl %edx, -32(%ebp)
0x00402d6b:	leal %eax, -640364487(%edx,%eax)
0x00402d72:	movl %edx, %edi
0x00402d74:	xorl %edx, %ecx
0x00402d76:	roll %eax, $0x4<UINT8>
0x00402d79:	addl %eax, %ecx
0x00402d7b:	xorl %edx, %eax
0x00402d7d:	addl %edx, -20(%ebp)
0x00402d80:	leal %edx, -421815835(%edx,%esi)
0x00402d87:	roll %edx, $0xb<UINT8>
0x00402d8a:	addl %edx, %eax
0x00402d8c:	movl %esi, %edx
0x00402d8e:	xorl %esi, %ecx
0x00402d90:	xorl %esi, %eax
0x00402d92:	addl %esi, -8(%ebp)
0x00402d95:	leal %esi, 0x1fa27cf8(%esi,%edi)
0x00402d9c:	roll %esi, $0x10<UINT8>
0x00402d9f:	addl %esi, %edx
0x00402da1:	movl %edi, %edx
0x00402da3:	xorl %edi, %esi
0x00402da5:	xorl %edi, %eax
0x00402da7:	addl %edi, -60(%ebp)
0x00402daa:	leal %ecx, -995338651(%edi,%ecx)
0x00402db1:	rorl %ecx, $0x9<UINT8>
0x00402db4:	addl %ecx, %esi
0x00402db6:	movl %edi, %edx
0x00402db8:	notl %edi
0x00402dba:	orl %edi, %ecx
0x00402dbc:	xorl %edi, %esi
0x00402dbe:	addl %edi, -68(%ebp)
0x00402dc1:	leal %eax, -198630844(%edi,%eax)
0x00402dc8:	roll %eax, $0x6<UINT8>
0x00402dcb:	addl %eax, %ecx
0x00402dcd:	movl %edi, %esi
0x00402dcf:	notl %edi
0x00402dd1:	orl %edi, %eax
0x00402dd3:	xorl %edi, %ecx
0x00402dd5:	addl %edi, -40(%ebp)
0x00402dd8:	leal %edx, 0x432aff97(%edi,%edx)
0x00402ddf:	roll %edx, $0xa<UINT8>
0x00402de2:	addl %edx, %eax
0x00402de4:	movl %edi, %ecx
0x00402de6:	notl %edi
0x00402de8:	orl %edi, %edx
0x00402dea:	xorl %edi, %eax
0x00402dec:	addl %edi, -12(%ebp)
0x00402def:	leal %esi, -1416354905(%edi,%esi)
0x00402df6:	roll %esi, $0xf<UINT8>
0x00402df9:	addl %esi, %edx
0x00402dfb:	movl %edi, %eax
0x00402dfd:	notl %edi
0x00402dff:	orl %edi, %esi
0x00402e01:	xorl %edi, %edx
0x00402e03:	addl %edi, -48(%ebp)
0x00402e06:	leal %ecx, -57434055(%edi,%ecx)
0x00402e0d:	rorl %ecx, $0xb<UINT8>
0x00402e10:	addl %ecx, %esi
0x00402e12:	movl %edi, %edx
0x00402e14:	notl %edi
0x00402e16:	orl %edi, %ecx
0x00402e18:	xorl %edi, %esi
0x00402e1a:	addl %edi, -20(%ebp)
0x00402e1d:	leal %eax, 0x655b59c3(%edi,%eax)
0x00402e24:	roll %eax, $0x6<UINT8>
0x00402e27:	addl %eax, %ecx
0x00402e29:	movl %edi, %esi
0x00402e2b:	notl %edi
0x00402e2d:	orl %edi, %eax
0x00402e2f:	xorl %edi, %ecx
0x00402e31:	addl %edi, -56(%ebp)
0x00402e34:	leal %edx, -1894986606(%edi,%edx)
0x00402e3b:	movl %edi, %ecx
0x00402e3d:	notl %edi
0x00402e3f:	roll %edx, $0xa<UINT8>
0x00402e42:	addl %edx, %eax
0x00402e44:	orl %edi, %edx
0x00402e46:	xorl %edi, %eax
0x00402e48:	addl %edi, -28(%ebp)
0x00402e4b:	leal %esi, -1051523(%edi,%esi)
0x00402e52:	roll %esi, $0xf<UINT8>
0x00402e55:	addl %esi, %edx
0x00402e57:	movl %edi, %eax
0x00402e59:	notl %edi
0x00402e5b:	orl %edi, %esi
0x00402e5d:	xorl %edi, %edx
0x00402e5f:	addl %edi, -64(%ebp)
0x00402e62:	leal %ecx, -2054922799(%edi,%ecx)
0x00402e69:	rorl %ecx, $0xb<UINT8>
0x00402e6c:	addl %ecx, %esi
0x00402e6e:	movl %edi, %edx
0x00402e70:	notl %edi
0x00402e72:	orl %edi, %ecx
0x00402e74:	xorl %edi, %esi
0x00402e76:	addl %edi, -36(%ebp)
0x00402e79:	leal %eax, 0x6fa87e4f(%edi,%eax)
0x00402e80:	roll %eax, $0x6<UINT8>
0x00402e83:	addl %eax, %ecx
0x00402e85:	movl %edi, %esi
0x00402e87:	notl %edi
0x00402e89:	orl %edi, %eax
0x00402e8b:	xorl %edi, %ecx
0x00402e8d:	addl %edi, -8(%ebp)
0x00402e90:	leal %edx, -30611744(%edi,%edx)
0x00402e97:	movl %edi, %ecx
0x00402e99:	notl %edi
0x00402e9b:	roll %edx, $0xa<UINT8>
0x00402e9e:	addl %edx, %eax
0x00402ea0:	orl %edi, %edx
0x00402ea2:	xorl %edi, %eax
0x00402ea4:	addl %edi, -44(%ebp)
0x00402ea7:	leal %esi, -1560198380(%edi,%esi)
0x00402eae:	roll %esi, $0xf<UINT8>
0x00402eb1:	addl %esi, %edx
0x00402eb3:	movl %edi, %eax
0x00402eb5:	notl %edi
0x00402eb7:	orl %edi, %esi
0x00402eb9:	xorl %edi, %edx
0x00402ebb:	addl %edi, -16(%ebp)
0x00402ebe:	leal %edi, 0x4e0811a1(%edi,%ecx)
0x00402ec5:	rorl %edi, $0xb<UINT8>
0x00402ec8:	addl %edi, %esi
0x00402eca:	movl %ecx, %edx
0x00402ecc:	notl %ecx
0x00402ece:	orl %ecx, %edi
0x00402ed0:	xorl %ecx, %esi
0x00402ed2:	addl %ecx, -52(%ebp)
0x00402ed5:	leal %eax, -145523070(%ecx,%eax)
0x00402edc:	roll %eax, $0x6<UINT8>
0x00402edf:	addl %eax, %edi
0x00402ee1:	movl %ecx, %esi
0x00402ee3:	notl %ecx
0x00402ee5:	orl %ecx, %eax
0x00402ee7:	xorl %ecx, %edi
0x00402ee9:	addl %ecx, -24(%ebp)
0x00402eec:	leal %edx, -1120210379(%ecx,%edx)
0x00402ef3:	roll %edx, $0xa<UINT8>
0x00402ef6:	movl %ecx, %edi
0x00402ef8:	addl %edx, %eax
0x00402efa:	notl %ecx
0x00402efc:	orl %ecx, %edx
0x00402efe:	xorl %ecx, %eax
0x00402f00:	addl %ecx, -60(%ebp)
0x00402f03:	leal %esi, 0x2ad7d2bb(%ecx,%esi)
0x00402f0a:	movl %ecx, 0x8(%ebp)
0x00402f0d:	movl %ebx, 0x4c(%ecx)
0x00402f10:	addl %ebx, %eax
0x00402f12:	notl %eax
0x00402f14:	roll %esi, $0xf<UINT8>
0x00402f17:	addl %esi, %edx
0x00402f19:	orl %eax, %esi
0x00402f1b:	xorl %eax, %edx
0x00402f1d:	addl %eax, -32(%ebp)
0x00402f20:	movl 0x4c(%ecx), %ebx
0x00402f23:	leal %eax, -343485551(%eax,%edi)
0x00402f2a:	rorl %eax, $0xb<UINT8>
0x00402f2d:	addl %eax, 0x50(%ecx)
0x00402f30:	popl %edi
0x00402f31:	addl %eax, %esi
0x00402f33:	movl 0x50(%ecx), %eax
0x00402f36:	movl %eax, 0x54(%ecx)
0x00402f39:	addl %eax, %esi
0x00402f3b:	movl 0x54(%ecx), %eax
0x00402f3e:	movl %eax, 0x58(%ecx)
0x00402f41:	addl %eax, %edx
0x00402f43:	popl %esi
0x00402f44:	movl 0x58(%ecx), %eax
0x00402f47:	popl %ebx
0x00402f48:	movl %esp, %ebp
0x00402f4a:	popl %ebp
0x00402f4b:	ret $0x8<UINT16>

0x00402fa6:	movl -4(%ebp), %ebx
0x00402fa9:	addl %ebx, $0x40<UINT8>
0x00402fac:	cmpl %ebx, %edi
0x00402fae:	ja 0x00402fc9
0x00402fc9:	xorl %eax, %eax
0x00402fcb:	jmp 0x00402fd4
0x00420c9c:	jmp 0x00420dac
0x0040307e:	leal %ecx, 0x5d(%esi)
0x00403081:	leal %eax, 0x4e(%esi)
0x00403084:	movl %edi, $0x4<UINT32>
0x00403089:	leal %esp, (%esp)
0x00403090:	movzbl %edx, -2(%eax)
0x00403094:	movb -1(%ecx), %dl
0x00403097:	movzbl %edx, -1(%eax)
0x0040309b:	movb (%ecx), %dl
0x0040309d:	movzbl %edx, (%eax)
0x004030a0:	movb 0x1(%ecx), %dl
0x004030a3:	movzbl %edx, 0x1(%eax)
0x004030a7:	movb 0x2(%ecx), %dl
0x004030aa:	addl %eax, $0x4<UINT8>
0x004030ad:	addl %ecx, $0x4<UINT8>
0x004030b0:	subl %edi, $0x1<UINT8>
0x004030b3:	jne 0x00403090
0x004030b5:	pushl $0x40<UINT8>
0x004030b7:	leal %eax, 0x1(%esi)
0x004030ba:	pushl %edi
0x004030bb:	pushl %eax
0x004030bc:	call 0x0041f180
0x0041f1bb:	subl %edx, %ecx
0x0041f1bd:	movb (%edi), %al
0x0041f1bf:	addl %edi, $0x1<UINT8>
0x0041f1c2:	subl %ecx, $0x1<UINT8>
0x0041f1c5:	jne 0x0041f1bd
0x004030c1:	addl %esp, $0xc<UINT8>
0x004030c4:	xorl %eax, %eax
0x004030c6:	movl 0x44(%esi), %eax
0x004030c9:	movl 0x48(%esi), %eax
0x004030cc:	movb (%esi), $0x1<UINT8>
0x004030cf:	popl %edi
0x004030d0:	movl %ecx, -4(%ebp)
0x004030d3:	movl %eax, %esi
0x004030d5:	xorl %ecx, %ebp
0x004030d7:	popl %esi
0x004030d8:	call 0x0041d190
0x004030dd:	movl %esp, %ebp
0x004030df:	popl %ebp
0x004030e0:	ret

0x0040327e:	pushl %esi
0x0040327f:	movl %ecx, %edi
0x00403281:	call 0x004030f0
0x004030f0:	pushl %ebp
0x004030f1:	movl %ebp, %esp
0x004030f3:	pushl $0xffffffff<UINT8>
0x004030f5:	pushl $0x430818<UINT32>
0x004030fa:	movl %eax, %fs:0
0x00403100:	pushl %eax
0x00403101:	subl %esp, $0x38<UINT8>
0x00403104:	movl %eax, 0x44609c
0x00403109:	xorl %eax, %ebp
0x0040310b:	movl -16(%ebp), %eax
0x0040310e:	pushl %ebx
0x0040310f:	pushl %esi
0x00403110:	pushl %edi
0x00403111:	pushl %eax
0x00403112:	leal %eax, -12(%ebp)
0x00403115:	movl %fs:0, %eax
0x0040311b:	movl %eax, 0x8(%ebp)
0x0040311e:	xorl %edi, %edi
0x00403120:	cmpb (%eax), $0x0<UINT8>
0x00403123:	movl %esi, %ecx
0x00403125:	movl -60(%ebp), %eax
0x00403128:	movl -64(%ebp), %esi
0x0040312b:	movl -68(%ebp), %edi
0x0040312e:	jne 0x00403141
0x00403141:	leal %ebx, -52(%ebp)
0x00403144:	jmp 0x00403149
0x00403149:	movzbl %eax, 0x5c(%eax,%edi)
0x0040314e:	pushl %eax
0x0040314f:	pushl $0x43facc<UINT32>
0x00403154:	pushl $0x3<UINT8>
0x00403156:	pushl %ebx
0x00403157:	call 0x0041d6a5
0x00429fb6:	movsbl %eax, %dl
0x00429fb9:	subl %eax, $0x20<UINT8>
0x00429fbc:	je 72
0x00429fbe:	subl %eax, $0x3<UINT8>
0x00429fc1:	je 52
0x00429fc3:	subl %eax, %esi
0x00429fc5:	je 36
0x00429fc7:	decl %eax
0x00429fc8:	decl %eax
0x00429fc9:	je 20
0x00429fcb:	subl %eax, $0x3<UINT8>
0x00429fce:	jne 2390
0x00429fd4:	orl -528(%ebp), %esi
0x00429fda:	jmp 0x0042a92a
0x0042a012:	cmpb %dl, $0x2a<UINT8>
0x0042a015:	jne 0x0042a043
0x0042a043:	movl %eax, -564(%ebp)
0x0042a049:	imull %eax, %eax, $0xa<UINT8>
0x0042a04c:	movsbl %ecx, %dl
0x0042a04f:	leal %eax, -48(%eax,%ecx)
0x0042a053:	movl -564(%ebp), %eax
0x0042a059:	jmp 0x0042a92a
0x0042a3d6:	cmpl %eax, $0x70<UINT8>
0x0042a3d9:	jg 0x0042a5d4
0x0042a5d4:	subl %eax, $0x73<UINT8>
0x0042a5d7:	je -837
0x0042a5dd:	decl %eax
0x0042a5de:	decl %eax
0x0042a5df:	je -373
0x0042a5e5:	subl %eax, $0x3<UINT8>
0x0042a5e8:	jne 439
0x0042a5ee:	movl -604(%ebp), $0x27<UINT32>
0x0042a5f8:	testb -528(%ebp), $0xffffff80<UINT8>
0x0042a5ff:	movl -544(%ebp), $0x10<UINT32>
0x0042a609:	je 0x0042a47a
0x0042a63f:	addl %edi, $0x4<UINT8>
0x0042a642:	testb %cl, $0x20<UINT8>
0x0042a645:	je 0x0042a65f
0x0042a65f:	movl %eax, -4(%edi)
0x0042a662:	testb %cl, $0x40<UINT8>
0x0042a665:	je 0x0042a66a
0x0042a66a:	xorl %edx, %edx
0x0042a6a2:	xorl %ebx, %ebx
0x0042a847:	testb -528(%ebp), $0x4<UINT8>
0x0042a84e:	jne 18
0x0042a850:	pushl %edi
0x0042a851:	pushl %ebx
0x0042a852:	pushl $0x30<UINT8>
0x0042a854:	leal %eax, -552(%ebp)
0x0042a85a:	call 0x00429d78
0x0042a85f:	addl %esp, $0xc<UINT8>
0x0040315c:	incl %edi
0x0040315d:	addl %esp, $0x10<UINT8>
0x00403160:	addl %ebx, $0x2<UINT8>
0x00403163:	cmpl %edi, $0x10<UINT8>
0x00403166:	jl 0x00403146
0x00403146:	movl %eax, -60(%ebp)
0x0042a71a:	addl %ecx, -604(%ebp)
0x00429d82:	movl %ecx, 0x10(%ebp)
0x00429d85:	movb %al, 0x8(%ebp)
0x00429d88:	decl 0xc(%ebp)
0x00429d8b:	call 0x00429d45
0x00429d90:	cmpl (%esi), $0xffffffff<UINT8>
0x00429d93:	je 6
0x00403168:	xorl %ebx, %ebx
0x0040316a:	movb -20(%ebp), $0x0<UINT8>
0x0040316e:	movl (%esi), %ebx
0x00403170:	movl 0x4(%esi), %ebx
0x00403173:	movl 0x8(%esi), %ebx
0x00403176:	movl -4(%ebp), %ebx
0x00403179:	movl 0xc(%esi), $0x1<UINT32>
0x00403180:	movb 0x448860, %bl
0x00403186:	movl %edx, (%esi)
0x00403188:	leal %ecx, -52(%ebp)
0x0040318b:	cmpl %ecx, %edx
0x0040318d:	movl %eax, %ecx
0x0040318f:	sete -53(%ebp)
0x00403193:	leal %edi, 0x1(%eax)
0x00403196:	movb %cl, (%eax)
0x00403198:	incl %eax
0x00403199:	testb %cl, %cl
0x0040319b:	jne 0x00403196
0x0040319d:	subl %eax, %edi
0x0040319f:	movl %edi, %eax
0x004031a1:	jne 0x004031b3
0x004031b3:	movl %eax, 0x8(%esi)
0x004031b6:	cmpl %edi, %eax
0x004031b8:	ja 0x004031c3
0x004031c3:	leal %ebx, 0x10(%eax,%edi)
0x004031c7:	cmpl %ebx, %eax
0x004031c9:	jbe 5
0x004031cb:	call 0x004034c0
0x004031d0:	cmpb -53(%ebp), $0x0<UINT8>
0x004031d4:	je 0x004031da
0x004031da:	leal %eax, -52(%ebp)
0x004031dd:	pushl %edi
0x004031de:	pushl %eax
0x004031df:	movl %eax, 0x4(%esi)
0x004031e2:	addl %eax, (%esi)
0x004031e4:	pushl %eax
0x004031e5:	call 0x00420c20
0x004031ea:	addl %esp, $0xc<UINT8>
0x004031ed:	addl 0x4(%esi), %edi
0x004031f0:	movl %eax, %esi
0x004031f2:	movl %ecx, -12(%ebp)
0x004031f5:	movl %fs:0, %ecx
0x004031fc:	popl %ecx
0x004031fd:	popl %edi
0x004031fe:	popl %esi
0x004031ff:	popl %ebx
0x00403200:	movl %ecx, -16(%ebp)
0x00403203:	xorl %ecx, %ebp
0x00403205:	call 0x0041d190
0x0040320a:	movl %esp, %ebp
0x0040320c:	popl %ebp
0x0040320d:	ret $0x4<UINT16>

0x00403286:	movl %eax, %edi
0x00403288:	popl %esi
0x00403289:	movl %esp, %ebp
0x0040328b:	popl %ebp
0x0040328c:	ret $0x4<UINT16>

0x004082c5:	pushl %esi
0x004082c6:	movl -4(%ebp), $0x0<UINT32>
0x004082cd:	call 0x0041d3a9
0x004082d2:	addl %esp, $0x4<UINT8>
0x004082d5:	pushl $0x43d428<UINT32>
0x004082da:	xorl %eax, %eax
0x004082dc:	leal %edi, -172(%ebp)
0x004082e2:	leal %ecx, -132(%ebp)
0x004082e8:	call 0x00403210
0x00402fb0:	movl %edx, 0x8(%ebp)
0x00402fb3:	leal %eax, -64(%edx,%ebx)
0x00402fb7:	pushl %eax
0x00402fb8:	pushl %esi
0x00402fb9:	call 0x004028b0
0x00402fbe:	addl -4(%ebp), $0x40<UINT8>
0x00402fc2:	addl %ebx, $0x40<UINT8>
0x00402fc5:	cmpl %ebx, %edi
0x00402fc7:	jbe 0x00402fb0
0x00420d48:	movl %eax, -28(%esi,%ecx,4)
0x00420d4c:	movl -28(%edi,%ecx,4), %eax
0x00420d50:	movl %eax, -24(%esi,%ecx,4)
0x00420d54:	movl -24(%edi,%ecx,4), %eax
0x004082ed:	movb -4(%ebp), $0x1<UINT8>
0x004082f1:	movl %ecx, 0x4(%eax)
0x004082f4:	movl %eax, (%eax)
0x004082f6:	pushl %eax
0x004082f7:	movl %eax, %ecx
0x004082f9:	leal %ecx, -156(%ebp)
0x004082ff:	call 0x00403540
0x004034e9:	leal %esp, (%esp)
0x004034f0:	movl %ecx, (%esi)
0x004034f2:	movb %dl, (%eax,%ecx)
0x004034f5:	movb (%eax,%edi), %dl
0x004034f8:	incl %eax
0x004034f9:	cmpl %eax, 0x4(%esi)
0x004034fc:	jb 0x004034f0
0x00408304:	movb -4(%ebp), $0x0<UINT8>
0x00408308:	movl %eax, -172(%ebp)
0x0040830e:	testl %eax, %eax
0x00408310:	je 9
0x00408312:	pushl %eax
0x00408313:	call 0x0041d3a9
0x00408318:	addl %esp, $0x4<UINT8>
0x0040831b:	movl %eax, -156(%ebp)
0x00408321:	pushl %eax
0x00408322:	movl %eax, -152(%ebp)
0x00408328:	leal %edi, -172(%ebp)
0x0040832e:	leal %ecx, -132(%ebp)
0x00408334:	call 0x00403210
0x00408339:	movb -4(%ebp), $0x2<UINT8>
0x0040833d:	movl %ecx, (%eax)
0x0040833f:	movl %eax, 0x4(%eax)
0x00408342:	pushl %ecx
0x00408343:	leal %ecx, -156(%ebp)
0x00408349:	call 0x00406e50
0x00406e50:	pushl %ebp
0x00406e51:	movl %ebp, %esp
0x00406e53:	pushl %ebx
0x00406e54:	movl %ebx, 0x8(%ebp)
0x00406e57:	pushl %esi
0x00406e58:	movl %esi, %ecx
0x00406e5a:	pushl %edi
0x00406e5b:	movl %edi, %eax
0x00406e5d:	cmpl (%esi), %ebx
0x00406e5f:	je 0x00406ebf
0x00406e61:	testl %ebx, %ebx
0x00406e63:	je 22
0x00406e65:	testl %edi, %edi
0x00406e67:	jne 0x00406e7f
0x00406e7f:	leal %eax, 0x10(%edi)
0x00406e82:	cmpl %eax, 0x8(%esi)
0x00406e85:	jbe 0x00406e91
0x00406e91:	movl %eax, %esi
0x00406e93:	call 0x00403420
0x0040344b:	popl %esi
0x0040344c:	subl %edx, %edi
0x0040344e:	movl %ecx, %edi
0x00403450:	popl %ebx
0x00403451:	jmp 0x004033c0
0x004033c0:	pushl %esi
0x004033c1:	movl %esi, %edx
0x004033c3:	movl %edx, 0x4(%eax)
0x004033c6:	testl %edx, %edx
0x004033c8:	je 73
0x004033ca:	cmpl %ecx, %edx
0x004033cc:	jae 69
0x004033ce:	pushl %edi
0x004033cf:	cmpl %esi, %edx
0x004033d1:	ja 7
0x004033d3:	leal %edi, (%ecx,%esi)
0x004033d6:	cmpl %edi, %edx
0x004033d8:	jb 0x004033de
0x004033da:	subl %edx, %ecx
0x004033dc:	movl %esi, %edx
0x004033de:	movl %edx, 0x8(%eax)
0x004033e1:	addl %edx, 0xc(%eax)
0x004033e4:	cmpl %ecx, %edx
0x004033e6:	jae 39
0x004033e8:	leal %edx, (%ecx,%esi)
0x004033eb:	cmpl %edx, 0x4(%eax)
0x004033ee:	jae 0x004033f9
0x004033f9:	movb %dl, 0x448860
0x004033ff:	movl %edi, (%eax)
0x00403401:	movb (%ecx,%edi), %dl
0x00403404:	movl %edx, 0x8(%eax)
0x00403407:	addl %edx, 0xc(%eax)
0x0040340a:	incl %ecx
0x0040340b:	cmpl %ecx, %edx
0x0040340d:	jb 0x004033e8
0x0040340f:	subl 0x4(%eax), %esi
0x00403412:	popl %edi
0x00403413:	popl %esi
0x00403414:	ret

0x00406e98:	testl %edi, %edi
0x00406e9a:	jne 0x00406eb2
0x00406eb2:	movl %eax, (%esi)
0x00406eb4:	pushl %edi
0x00406eb5:	pushl %ebx
0x00406eb6:	pushl %eax
0x00406eb7:	call 0x00420c20
0x00406ebc:	addl %esp, $0xc<UINT8>
0x00406ebf:	popl %edi
0x00406ec0:	popl %esi
0x00406ec1:	popl %ebx
0x00406ec2:	popl %ebp
0x00406ec3:	ret $0x4<UINT16>

0x0040834e:	movl %eax, -172(%ebp)
0x00408354:	testl %eax, %eax
0x00408356:	je 9
0x00408358:	pushl %eax
0x00408359:	call 0x0041d3a9
0x0040835e:	addl %esp, $0x4<UINT8>
0x00408361:	movl %edi, -136(%ebp)
0x00408367:	xorl %eax, %eax
0x00408369:	addl %edi, $0x152<UINT32>
0x0040836f:	movl (%edi), %eax
0x00408371:	movl 0x4(%edi), %eax
0x00408374:	movl 0x8(%edi), %eax
0x00408377:	movl 0xc(%edi), %eax
0x0040837a:	movl 0x10(%edi), %eax
0x0040837d:	movl 0x14(%edi), %eax
0x00408380:	movl 0x18(%edi), %eax
0x00408383:	movl 0x1c(%edi), %eax
0x00408386:	movb 0x20(%edi), %al
0x00408389:	movl %eax, -156(%ebp)
0x0040838f:	movl %esi, %eax
0x00408391:	movl %ecx, $0x8<UINT32>
0x00408396:	pushl %eax
0x00408397:	rep movsl %es:(%edi), %ds:(%esi)
0x00408399:	call 0x0041d3a9
0x0040839e:	addl %esp, $0x4<UINT8>
0x004083a1:	movl %ecx, -12(%ebp)
0x004083a4:	movl %fs:0, %ecx
0x004083ab:	popl %ecx
0x004083ac:	popl %edi
0x004083ad:	popl %esi
0x004083ae:	popl %ebx
0x004083af:	movl %ecx, -20(%ebp)
0x004083b2:	xorl %ecx, %ebp
0x004083b4:	call 0x0041d190
0x004083b9:	movl %esp, %ebp
0x004083bb:	popl %ebp
0x004083bc:	ret $0x4<UINT16>

0x00407714:	movl %ecx, 0x58(%esp)
0x00407718:	movl %fs:0, %ecx
0x0040771f:	popl %ecx
0x00407720:	popl %edi
0x00407721:	popl %esi
0x00407722:	popl %ebx
0x00407723:	movl %esp, %ebp
0x00407725:	popl %ebp
0x00407726:	ret $0x4<UINT16>

0x00406fd3:	pushl $0x43ff3c<UINT32>
0x00406fd8:	movl %eax, $0x448908<UINT32>
0x00406fdd:	movl 0x4489e4, %ebx
0x00406fe3:	movl 0x448a00, %ebx
0x00406fe9:	movl 0x448a04, $0xa<UINT32>
0x00406ff3:	movl 0x4489dc, %ebx
0x00406ff9:	movl 0x448a08, %ebx
0x00406fff:	movb 0x4489e0, %bl
0x00407005:	movb 0x4489e1, %bl
0x0040700b:	call 0x00405eb0
0x00407010:	pushl $0x43ff44<UINT32>
0x00407015:	movl %eax, $0x448978<UINT32>
0x0040701a:	call 0x00405eb0
0x0040701f:	movb 0x4489ec, %bl
0x00407025:	movl 0x4489e8, $0xffffffff<UINT32>
0x0040702f:	movl %ecx, %esi
0x00407031:	call 0x00407730
0x00407730:	pushl %ebp
0x00407731:	movl %ebp, %esp
0x00407733:	andl %esp, $0xfffffff8<UINT8>
0x00407736:	pushl $0xffffffff<UINT8>
0x00407738:	pushl $0x43176a<UINT32>
0x0040773d:	movl %eax, %fs:0
0x00407743:	pushl %eax
0x00407744:	subl %esp, $0x310<UINT32>
0x0040774a:	movl %eax, 0x44609c
0x0040774f:	xorl %eax, %esp
0x00407751:	movl 0x308(%esp), %eax
0x00407758:	pushl %ebx
0x00407759:	pushl %esi
0x0040775a:	pushl %edi
0x0040775b:	movl %eax, 0x44609c
0x00407760:	xorl %eax, %esp
0x00407762:	pushl %eax
0x00407763:	leal %eax, 0x320(%esp)
0x0040776a:	movl %fs:0, %eax
0x00407770:	xorl %eax, %eax
0x00407772:	movl %edi, %ecx
0x00407774:	movl 0x18(%esp), %edi
0x00407778:	movl 0x30(%esp), %eax
0x0040777c:	movl 0x34(%esp), %eax
0x00407780:	movl 0x38(%esp), %eax
0x00407784:	movl 0x328(%esp), %eax
0x0040778b:	leal %ebx, 0x28(%eax)
0x0040778e:	leal %esi, 0x30(%esp)
0x00407792:	movl 0x3c(%esp), $0x1<UINT32>
0x0040779a:	movb 0x448860, %al
0x0040779f:	call 0x004034c0
0x004077a4:	movl %ecx, 0x34(%esp)
0x004077a8:	movl %eax, 0x30(%esp)
0x004077ac:	movl %edx, 0x43ff68
0x004077b2:	addl %eax, %ecx
0x004077b4:	movl (%eax), %edx
0x004077b6:	movl %ecx, 0x43ff6c
0x004077bc:	movl 0x4(%eax), %ecx
0x004077bf:	movl %edx, 0x43ff70
0x004077c5:	movl 0x8(%eax), %edx
0x004077c8:	movl %ecx, 0x43ff74
0x004077ce:	movl 0xc(%eax), %ecx
0x004077d1:	movl %edx, 0x43ff78
0x004077d7:	movl 0x10(%eax), %edx
0x004077da:	movl %ecx, 0x43ff7c
0x004077e0:	movl 0x14(%eax), %ecx
0x004077e3:	addl 0x34(%esp), $0x18<UINT8>
0x004077e8:	xorl %eax, %eax
0x004077ea:	movl %ecx, $0x1<UINT32>
0x004077ef:	movl 0x328(%esp), %ecx
0x004077f6:	movl 0x1c(%esp), %eax
0x004077fa:	movl 0x20(%esp), %eax
0x004077fe:	movl 0x24(%esp), %eax
0x00407802:	movb 0x328(%esp), $0x2<UINT8>
0x0040780a:	leal %esi, 0x1c(%esp)
0x0040780e:	movl 0x28(%esp), %ecx
0x00407812:	movb 0x448860, %al
0x00407817:	call 0x004034c0
0x0040781c:	movl %edx, 0x1c(%esp)
0x00407820:	movl %eax, 0x20(%esp)
0x00407824:	movl %ecx, 0x43ff84
0x0040782a:	addl %eax, %edx
0x0040782c:	movl (%eax), %ecx
0x0040782e:	movl %edx, 0x43ff88
0x00407834:	movl 0x4(%eax), %edx
0x00407837:	movl %ecx, 0x43ff8c
0x0040783d:	movl 0x8(%eax), %ecx
0x00407840:	movl %edx, 0x43ff90
0x00407846:	movl 0xc(%eax), %edx
0x00407849:	movl %ecx, 0x43ff94
0x0040784f:	movl 0x10(%eax), %ecx
0x00407852:	movl %edx, 0x43ff98
0x00407858:	movl 0x14(%eax), %edx
0x0040785b:	addl 0x20(%esp), $0x18<UINT8>
0x00407860:	pushl $0x1<UINT8>
0x00407862:	pushl $0x0<UINT8>
0x00407864:	leal %eax, 0x38(%esp)
0x00407868:	pushl %eax
0x00407869:	leal %ecx, 0x50(%esp)
0x0040786d:	pushl %ecx
0x0040786e:	movb 0x338(%esp), $0x3<UINT8>
0x00407876:	call 0x0041b070
0x004057de:	movl %eax, $0x448868<UINT32>
0x004057e3:	movl %ecx, -12(%ebp)
0x004057e6:	movl %fs:0, %ecx
0x004057ed:	popl %ecx
0x004057ee:	movl %esp, %ebp
0x004057f0:	popl %ebp
0x004057f1:	ret

0x0041b0b9:	movl -552(%ebp), %eax
0x0041b0bf:	movl %eax, 0x4(%edi)
0x0041b0c2:	shrl %eax, $0x2<UINT8>
0x0041b0c5:	leal %esi, (%eax,%eax,2)
0x0041b0c8:	leal %eax, 0x1(%esi)
0x0041b0cb:	pushl %eax
0x0041b0cc:	call 0x0041cfd3
0x0041b0d1:	movl %ebx, %eax
0x0041b0d3:	leal %eax, 0x1(%esi)
0x0041b0d6:	pushl %eax
0x0041b0d7:	pushl $0x0<UINT8>
0x0041b0d9:	pushl %ebx
0x0041b0da:	call 0x0041f180
0x0041b0df:	movl %edi, (%edi)
0x0041b0e1:	pushl %esi
0x0041b0e2:	pushl %ebx
0x0041b0e3:	pushl %edi
0x0041b0e4:	call 0x00402690
0x00402690:	pushl %ebp
0x00402691:	movl %ebp, %esp
0x00402693:	pushl %ecx
0x00402694:	pushl %ebx
0x00402695:	movl %ebx, 0x8(%ebp)
0x00402698:	xorb %al, %al
0x0040269a:	cmpb (%ebx), $0x2b<UINT8>
0x0040269d:	pushl %edi
0x0040269e:	movl %edi, 0xc(%ebp)
0x004026a1:	movl -4(%ebp), $0x0<UINT32>
0x004026a8:	jne 0x004026b6
0x004026b6:	movb %cl, (%ebx)
0x004026b8:	cmpb %cl, $0xd<UINT8>
0x004026bb:	jne 0x004026c5
0x004026c5:	pushl %esi
0x004026c6:	testb %cl, %cl
0x004026c8:	je 351
0x004026ce:	movl %edi, %edi
0x004026d0:	cmpb %cl, $0xd<UINT8>
0x004026d3:	je 340
0x004026d9:	cmpb %al, $0x3d<UINT8>
0x004026db:	je 332
0x004026e1:	cmpb %cl, $0xffffff80<UINT8>
0x004026e4:	jae 313
0x004026ea:	movzbl %eax, %cl
0x004026ed:	movsbl %edx, 0x43d810(%eax)
0x004026f4:	cmpb %dl, $0xffffffff<UINT8>
0x004026f7:	je 294
0x004026fd:	movb %cl, 0x1(%ebx)
0x00402700:	cmpb %cl, $0xffffff80<UINT8>
0x00402703:	jae 282
0x00402709:	movzbl %esi, %cl
0x0040270c:	movsbl %ecx, 0x43d810(%esi)
0x00402713:	cmpb %cl, $0xffffffff<UINT8>
0x00402716:	je 263
0x0040271c:	movb %bl, 0x2(%ebx)
0x0040271f:	cmpb %bl, $0x3d<UINT8>
0x00402722:	je 0x0040273d
0x00402724:	cmpb %bl, $0xffffff80<UINT8>
0x00402727:	jae 246
0x0040272d:	movzbl %eax, %bl
0x00402730:	cmpb 0x43d810(%eax), $0xffffffff<UINT8>
0x00402737:	je 230
0x0040273d:	movl %eax, 0x8(%ebp)
0x00402740:	movb %al, 0x3(%eax)
0x00402743:	cmpb %al, $0x3d<UINT8>
0x00402745:	je 0x0040275f
0x00402747:	cmpb %al, $0xffffff80<UINT8>
0x00402749:	jae 212
0x0040274f:	movzbl %edi, %al
0x00402752:	cmpb 0x43d810(%edi), $0xffffffff<UINT8>
0x00402759:	je 196
0x0040275f:	movl %edi, 0x10(%ebp)
0x00402762:	addl 0x8(%ebp), $0x4<UINT8>
0x00402766:	incl -4(%ebp)
0x00402769:	testl %edi, %edi
0x0040276b:	je 9
0x0040276d:	cmpl -4(%ebp), %edi
0x00402770:	ja 173
0x00402776:	movl %edi, 0xc(%ebp)
0x00402779:	addb %dl, %dl
0x0040277b:	sarb %cl, $0x4<UINT8>
0x0040277e:	addb %dl, %dl
0x00402780:	orb %cl, %dl
0x00402782:	movb (%edi), %cl
0x00402784:	incl %edi
0x00402785:	movl 0xc(%ebp), %edi
0x00402788:	cmpb %bl, $0x3d<UINT8>
0x0040278b:	je 0x00402812
0x00402791:	movl %ecx, 0x10(%ebp)
0x00402794:	incl -4(%ebp)
0x00402797:	testl %ecx, %ecx
0x00402799:	je 9
0x0040279b:	cmpl -4(%ebp), %ecx
0x0040279e:	ja 127
0x004027a4:	movsbl %edx, 0x43d810(%esi)
0x004027ab:	cmpb %bl, $0xffffff80<UINT8>
0x004027ae:	jae 12
0x004027b0:	movzbl %ecx, %bl
0x004027b3:	movsbl %ecx, 0x43d810(%ecx)
0x004027ba:	jmp 0x004027bf
0x004027bf:	sarb %cl, $0x2<UINT8>
0x004027c2:	shlb %dl, $0x4<UINT8>
0x004027c5:	orb %cl, %dl
0x004027c7:	movb (%edi), %cl
0x004027c9:	incl %edi
0x004027ca:	movl 0xc(%ebp), %edi
0x004027cd:	cmpb %al, $0x3d<UINT8>
0x004027cf:	je 0x00402812
0x004027d1:	movl %ecx, 0x10(%ebp)
0x004027d4:	incl -4(%ebp)
0x004027d7:	testl %ecx, %ecx
0x004027d9:	je 5
0x004027db:	cmpl -4(%ebp), %ecx
0x004027de:	ja 67
0x004027e0:	cmpb %bl, $0xffffff80<UINT8>
0x004027e3:	jae 12
0x004027e5:	movzbl %edx, %bl
0x004027e8:	movsbl %ecx, 0x43d810(%edx)
0x004027ef:	jmp 0x004027f4
0x004027f4:	cmpb %al, $0xffffff80<UINT8>
0x004027f6:	jae 12
0x004027f8:	movzbl %edx, %al
0x004027fb:	movsbl %edx, 0x43d810(%edx)
0x00402802:	jmp 0x00402807
0x00402807:	shlb %cl, $0x6<UINT8>
0x0040280a:	orb %cl, %dl
0x0040280c:	movb (%edi), %cl
0x0040280e:	incl %edi
0x0040280f:	movl 0xc(%ebp), %edi
0x00402812:	movl %ecx, 0x8(%ebp)
0x00402815:	movb %cl, (%ecx)
0x00402817:	testb %cl, %cl
0x00402819:	je 0x0040282d
0x0040281b:	movl %ebx, 0x8(%ebp)
0x0040281e:	jmp 0x004026d0
0x0040282d:	movl %eax, -4(%ebp)
0x00402830:	popl %esi
0x00402831:	movb (%edi), $0x0<UINT8>
0x00402834:	popl %edi
0x00402835:	popl %ebx
0x00402836:	movl %esp, %ebp
0x00402838:	popl %ebp
0x00402839:	ret

0x0041b0e9:	movl %edi, %eax
0x0041b0eb:	addl %esp, $0x1c<UINT8>
0x0041b0ee:	cmpl %edi, $0xffffffff<UINT8>
0x0041b0f1:	jne 0x0041b114
0x0041b114:	leal %eax, 0x1(%edi)
0x0041b117:	pushl %eax
0x0041b118:	call 0x0041cfd3
0x0041b11d:	movl %esi, %eax
0x0041b11f:	leal %eax, 0x1(%edi)
0x0041b122:	pushl %eax
0x0041b123:	pushl $0x0<UINT8>
0x0041b125:	pushl %esi
0x0041b126:	call 0x0041f180
0x0041b12b:	movl %eax, 0x10(%ebp)
0x0041b12e:	addl %esp, $0x10<UINT8>
0x0041b131:	subl %eax, $0x0<UINT8>
0x0041b134:	pushl %edi
0x0041b135:	je 0x0041b181
0x0041b181:	movl %ecx, -552(%ebp)
0x0041b187:	pushl %esi
0x0041b188:	addl %ecx, $0x152<UINT32>
0x0041b18e:	pushl $0x1<UINT8>
0x0041b190:	movl %edx, %ebx
0x0041b192:	call 0x00403900
0x00403900:	pushl %ebp
0x00403901:	movl %ebp, %esp
0x00403903:	subl %esp, $0x30<UINT8>
0x00403906:	movl %eax, 0x44609c
0x0040390b:	xorl %eax, %ebp
0x0040390d:	movl -8(%ebp), %eax
0x00403910:	movl %eax, 0xc(%ebp)
0x00403913:	pushl %ebx
0x00403914:	movl %ebx, 0x10(%ebp)
0x00403917:	pushl %esi
0x00403918:	xorl %esi, %esi
0x0040391a:	cmpb 0x1(%eax), $0x3a<UINT8>
0x0040391e:	pushl %edi
0x0040391f:	movl -28(%ebp), %edx
0x00403922:	movl -16(%ebp), %eax
0x00403925:	movl -20(%ebp), %esi
0x00403928:	movl -24(%ebp), %esi
0x0040392b:	jne 0x00403946
0x00403946:	cmpl %ebx, %esi
0x00403948:	jne 0x0040395b
0x0040395b:	leal %eax, -44(%ebp)
0x0040395e:	pushl %eax
0x0040395f:	call 0x00403800
0x00403800:	pushl %ebp
0x00403801:	movl %ebp, %esp
0x00403803:	subl %esp, $0x18<UINT8>
0x00403806:	movl %eax, 0x44609c
0x0040380b:	xorl %eax, %ebp
0x0040380d:	movl -4(%ebp), %eax
0x00403810:	movl %eax, 0x8(%ebp)
0x00403813:	pushl %ebx
0x00403814:	pushl %esi
0x00403815:	pushl %edi
0x00403816:	pushl $0x21<UINT8>
0x00403818:	movl %esi, %ecx
0x0040381a:	movl -24(%ebp), %eax
0x0040381d:	call 0x0041cfd3
0x00403822:	movl %ebx, %eax
0x00403824:	movl %eax, %esi
0x00403826:	addl %esp, $0x4<UINT8>
0x00403829:	movb 0x20(%ebx), $0x0<UINT8>
0x0040382d:	leal %edx, 0x1(%eax)
0x00403830:	movb %cl, (%eax)
0x00403832:	incl %eax
0x00403833:	testb %cl, %cl
0x00403835:	jne 0x00403830
0x00403837:	subl %eax, %edx
0x00403839:	movl %ecx, %eax
0x0040383b:	cmpl %ecx, $0x20<UINT8>
0x0040383e:	jl 11
0x00403840:	movl %ecx, $0x8<UINT32>
0x00403845:	movl %edi, %ebx
0x00403847:	rep movsl %es:(%edi), %ds:(%esi)
0x00403849:	jmp 0x00403893
0x00403893:	movl %edi, -24(%ebp)
0x00403896:	xorl %eax, %eax
0x00403898:	movb -16(%ebp), $0x0<UINT8>
0x0040389c:	movl -15(%ebp), %eax
0x0040389f:	movl -11(%ebp), %eax
0x004038a2:	movl -20(%ebp), %eax
0x004038a5:	xorl %esi, %esi
0x004038a7:	movl %eax, (%ebx)
0x004038a9:	leal %edx, -20(%ebp)
0x004038ac:	pushl %edx
0x004038ad:	movl -16(%ebp), %eax
0x004038b0:	movl %ecx, 0x4(%ebx)
0x004038b3:	leal %eax, -16(%ebp)
0x004038b6:	pushl $0x43fad4<UINT32>
0x004038bb:	pushl %eax
0x004038bc:	movl -12(%ebp), %ecx
0x004038bf:	call 0x0041d72d
0x0041d72d:	movl %edi, %edi
0x0041d72f:	pushl %ebp
0x0041d730:	movl %ebp, %esp
0x0041d732:	pushl %esi
0x0041d733:	movl %esi, 0x8(%ebp)
0x0041d736:	leal %eax, 0x10(%ebp)
0x0041d739:	pushl %eax
0x0041d73a:	pushl $0x0<UINT8>
0x0041d73c:	pushl 0xc(%ebp)
0x0041d73f:	pushl $0x421a95<UINT32>
0x0041d744:	call 0x0041d6c3
0x0041d6c3:	movl %edi, %edi
0x0041d6c5:	pushl %ebp
0x0041d6c6:	movl %ebp, %esp
0x0041d6c8:	subl %esp, $0x20<UINT8>
0x0041d6cb:	pushl %edi
0x0041d6cc:	pushl %esi
0x0041d6cd:	call 0x00421940
0x004219c1:	leal %eax, -4(%ecx)
0x004219c4:	movl %ecx, 0x4(%esp)
0x004219c8:	subl %eax, %ecx
0x004219ca:	ret

0x0041d6d2:	xorl %edi, %edi
0x0041d6d4:	popl %ecx
0x0041d6d5:	cmpl %esi, %edi
0x0041d6d7:	jne 0x0041d6f6
0x0041d6f6:	cmpl 0xc(%ebp), %edi
0x0041d6f9:	je -34
0x0041d6fb:	movl %ecx, $0x7fffffff<UINT32>
0x0041d700:	movl -20(%ebp), $0x49<UINT32>
0x0041d707:	movl -24(%ebp), %esi
0x0041d70a:	movl -32(%ebp), %esi
0x0041d70d:	movl -28(%ebp), %ecx
0x0041d710:	cmpl %eax, %ecx
0x0041d712:	ja 3
0x0041d714:	movl -28(%ebp), %eax
0x0041d717:	pushl 0x14(%ebp)
0x0041d71a:	leal %eax, -32(%ebp)
0x0041d71d:	pushl 0x10(%ebp)
0x0041d720:	pushl 0xc(%ebp)
0x0041d723:	pushl %eax
0x0041d724:	call 0x00421a95
0x00421a95:	movl %edi, %edi
0x00421a97:	pushl %ebp
0x00421a98:	movl %ebp, %esp
0x00421a9a:	subl %esp, $0x200<UINT32>
0x00421aa0:	movl %eax, 0x44609c
0x00421aa5:	xorl %eax, %ebp
0x00421aa7:	movl -4(%ebp), %eax
0x00421aaa:	movl %ecx, 0x14(%ebp)
0x00421aad:	movl %eax, 0x8(%ebp)
0x00421ab0:	pushl %esi
0x00421ab1:	xorl %esi, %esi
0x00421ab3:	pushl %edi
0x00421ab4:	movl %edi, 0xc(%ebp)
0x00421ab7:	movl -480(%ebp), %ecx
0x00421abd:	leal %ecx, -388(%ebp)
0x00421ac3:	movl -412(%ebp), %eax
0x00421ac9:	movl -428(%ebp), %edi
0x00421acf:	movl -420(%ebp), %ecx
0x00421ad5:	movl -472(%ebp), $0x15e<UINT32>
0x00421adf:	movl -464(%ebp), %esi
0x00421ae5:	movl -492(%ebp), %esi
0x00421aeb:	movl -392(%ebp), %esi
0x00421af1:	movl -496(%ebp), %esi
0x00421af7:	cmpl %edi, %esi
0x00421af9:	jne 0x00421b1b
0x00421b1b:	cmpl %eax, %esi
0x00421b1d:	je -36
0x00421b1f:	testb 0xc(%eax), $0x40<UINT8>
0x00421b23:	pushl %ebx
0x00421b24:	jne 0x00421ba0
0x00421ba0:	pushl 0x10(%ebp)
0x00421ba3:	leal %ecx, -512(%ebp)
0x00421ba9:	call 0x0041d900
0x00421bae:	movb %al, (%edi)
0x00421bb0:	movb -413(%ebp), $0x0<UINT8>
0x00421bb7:	movl -396(%ebp), %esi
0x00421bbd:	movl -452(%ebp), %esi
0x00421bc3:	testb %al, %al
0x00421bc5:	je 4038
0x00421bcb:	movzbl %eax, %al
0x00421bce:	pushl %eax
0x00421bcf:	call 0x0042ab11
0x0042ab11:	movl %edi, %edi
0x0042ab13:	pushl %ebp
0x0042ab14:	movl %ebp, %esp
0x0042ab16:	cmpl 0x448134, $0x0<UINT8>
0x0042ab1d:	jne 18
0x0042ab1f:	movl %eax, 0x8(%ebp)
0x0042ab22:	movl %ecx, 0x446c00
0x0042ab28:	movzwl %eax, (%ecx,%eax,2)
0x0042ab2c:	andl %eax, $0x8<UINT8>
0x0042ab2f:	popl %ebp
0x0042ab30:	ret

0x00421bd4:	popl %ecx
0x00421bd5:	testl %eax, %eax
0x00421bd7:	je 0x00421c1f
0x00421c1f:	movl %esi, -428(%ebp)
0x00421c25:	movb %al, (%esi)
0x00421c27:	cmpb %al, $0x25<UINT8>
0x00421c29:	jne 3532
0x00421c2f:	cmpb 0x1(%esi), %al
0x00421c32:	je 3509
0x00421c38:	xorl %edi, %edi
0x00421c3a:	movl -456(%ebp), %edi
0x00421c40:	movb -465(%ebp), $0x0<UINT8>
0x00421c47:	movl -424(%ebp), %edi
0x00421c4d:	movl -448(%ebp), %edi
0x00421c53:	movl -404(%ebp), %edi
0x00421c59:	movl -460(%ebp), %edi
0x00421c5f:	movb -414(%ebp), $0x0<UINT8>
0x00421c66:	movb -415(%ebp), $0x0<UINT8>
0x00421c6d:	movb -399(%ebp), $0x0<UINT8>
0x00421c74:	movb -397(%ebp), $0x0<UINT8>
0x00421c7b:	movb -406(%ebp), $0x0<UINT8>
0x00421c82:	movb -398(%ebp), $0x0<UINT8>
0x00421c89:	movb -405(%ebp), $0x1<UINT8>
0x00421c90:	movl -432(%ebp), %edi
0x00421c96:	incl %esi
0x00421c97:	movzbl %ebx, (%esi)
0x00421c9a:	movzbl %eax, %bl
0x00421c9d:	pushl %eax
0x00421c9e:	call 0x0042aa0c
0x0042aa0c:	movl %edi, %edi
0x0042aa0e:	pushl %ebp
0x0042aa0f:	movl %ebp, %esp
0x0042aa11:	cmpl 0x448134, $0x0<UINT8>
0x0042aa18:	jne 18
0x0042aa1a:	movl %eax, 0x8(%ebp)
0x0042aa1d:	movl %ecx, 0x446c00
0x0042aa23:	movzwl %eax, (%ecx,%eax,2)
0x0042aa27:	andl %eax, $0x4<UINT8>
0x0042aa2a:	popl %ebp
0x0042aa2b:	ret

0x00421ca3:	popl %ecx
0x00421ca4:	testl %eax, %eax
0x00421ca6:	je 0x00421cc6
0x00421cc6:	cmpl %ebx, $0x4e<UINT8>
0x00421cc9:	jg 0x00421d52
0x00421d52:	cmpl %ebx, $0x68<UINT8>
0x00421d55:	je 40
0x00421d57:	cmpl %ebx, $0x6c<UINT8>
0x00421d5a:	je 13
0x00421d5c:	cmpl %ebx, $0x77<UINT8>
0x00421d5f:	je 22
0x00421d61:	incb -397(%ebp)
0x00421d67:	jmp 0x00421d8b
0x00421d8b:	cmpb -397(%ebp), $0x0<UINT8>
0x00421d92:	je -258
0x00421d98:	cmpb -399(%ebp), $0x0<UINT8>
0x00421d9f:	movl -428(%ebp), %esi
0x00421da5:	jne 25
0x00421da7:	movl %eax, -480(%ebp)
0x00421dad:	movl %ebx, (%eax)
0x00421daf:	movl -488(%ebp), %eax
0x00421db5:	addl %eax, $0x4<UINT8>
0x00421db8:	movl -480(%ebp), %eax
0x00421dbe:	jmp 0x00421dc2
0x00421dc2:	cmpb -398(%ebp), $0x0<UINT8>
0x00421dc9:	movl -444(%ebp), %ebx
0x00421dcf:	movb -397(%ebp), $0x0<UINT8>
0x00421dd6:	jne 24
0x00421dd8:	movb %al, (%esi)
0x00421dda:	cmpb %al, $0x53<UINT8>
0x00421ddc:	je 11
0x00421dde:	movb -398(%ebp), $0xffffffff<UINT8>
0x00421de5:	cmpb %al, $0x43<UINT8>
0x00421de7:	jne 0x00421df0
0x00421df0:	movzbl %edi, (%esi)
0x00421df3:	orl %edi, $0x20<UINT8>
0x00421df6:	movl -484(%ebp), %edi
0x00421dfc:	cmpl %edi, $0x6e<UINT8>
0x00421dff:	je 74
0x00421e01:	cmpl %edi, $0x63<UINT8>
0x00421e04:	je 25
0x00421e06:	cmpl %edi, $0x7b<UINT8>
0x00421e09:	je 20
0x00421e0b:	pushl -412(%ebp)
0x00421e11:	leal %esi, -396(%ebp)
0x00421e17:	call 0x00421a6b
0x00421a6b:	movl %edi, %edi
0x00421a6d:	pushl %ebp
0x00421a6e:	movl %ebp, %esp
0x00421a70:	pushl %ebx
0x00421a71:	movl %edx, 0x8(%ebp)
0x00421a74:	incl (%esi)
0x00421a76:	call 0x00421a42
0x00421a42:	decl 0x4(%edx)
0x00421a45:	js 0x00421a50
0x00421a47:	movl %ecx, (%edx)
0x00421a49:	movzbl %eax, (%ecx)
0x00421a4c:	incl %ecx
0x00421a4d:	movl (%edx), %ecx
0x00421a4f:	ret

0x00421a7b:	movl %ebx, %eax
0x00421a7d:	cmpl %ebx, $0xffffffff<UINT8>
0x00421a80:	je 14
0x00421a82:	movzbl %eax, %bl
0x00421a85:	pushl %eax
0x00421a86:	call 0x0042ab11
0x00421a8b:	popl %ecx
0x00421a8c:	testl %eax, %eax
0x00421a8e:	jne -31
0x00421a90:	movl %eax, %ebx
0x00421a92:	popl %ebx
0x00421a93:	popl %ebp
0x00421a94:	ret

0x00421e1c:	popl %ecx
0x00421e1d:	jmp 0x00421e30
0x00421e30:	movl -392(%ebp), %eax
0x00421e36:	cmpl %eax, $0xffffffff<UINT8>
0x00421e39:	je 3303
0x00421e3f:	movl %ebx, -444(%ebp)
0x00421e45:	movl %esi, -428(%ebp)
0x00421e4b:	movl %ecx, -448(%ebp)
0x00421e51:	testl %ecx, %ecx
0x00421e53:	je 0x00421e62
0x00421e62:	cmpb -399(%ebp), $0x0<UINT8>
0x00421e69:	jne 65
0x00421e6b:	cmpl %edi, $0x63<UINT8>
0x00421e6e:	je 10
0x00421e70:	cmpl %edi, $0x73<UINT8>
0x00421e73:	je 5
0x00421e75:	cmpl %edi, $0x7b<UINT8>
0x00421e78:	jne 0x00421eac
0x00421eac:	cmpl %edi, $0x6f<UINT8>
0x00421eaf:	jg 0x00422434
0x00422434:	movl %eax, %edi
0x00422436:	subl %eax, $0x70<UINT8>
0x00422439:	je 647
0x0042243f:	subl %eax, $0x3<UINT8>
0x00422442:	je -363
0x00422448:	decl %eax
0x00422449:	decl %eax
0x0042244a:	je 637
0x00422450:	subl %eax, $0x3<UINT8>
0x00422453:	je 0x00421f01
0x00421f01:	cmpl -392(%ebp), $0x2d<UINT8>
0x00421f08:	jne 0x0042255e
0x0042255e:	cmpl -392(%ebp), $0x2b<UINT8>
0x00422565:	jne 0x00422593
0x00422593:	cmpl -392(%ebp), $0x30<UINT8>
0x0042259a:	jne 0x00422714
0x00422714:	cmpl -432(%ebp), $0x0<UINT8>
0x0042271b:	je 0x004228cc
0x004228cc:	cmpb -397(%ebp), $0x0<UINT8>
0x004228d3:	jne 213
0x004228d9:	cmpl %edi, $0x78<UINT8>
0x004228dc:	je 0x00422921
0x00422921:	movzbl %eax, -392(%ebp)
0x00422928:	pushl %eax
0x00422929:	call 0x0042aa90
0x0042aa90:	movl %edi, %edi
0x0042aa92:	pushl %ebp
0x0042aa93:	movl %ebp, %esp
0x0042aa95:	cmpl 0x448134, $0x0<UINT8>
0x0042aa9c:	jne 20
0x0042aa9e:	movl %eax, 0x8(%ebp)
0x0042aaa1:	movl %ecx, 0x446c00
0x0042aaa7:	movzwl %eax, (%ecx,%eax,2)
0x0042aaab:	andl %eax, $0x80<UINT32>
0x0042aab0:	popl %ebp
0x0042aab1:	ret

0x0042292e:	popl %ecx
0x0042292f:	testl %eax, %eax
0x00422931:	je 0x00422995
0x00422933:	pushl -392(%ebp)
0x00422939:	shll -456(%ebp), $0x4<UINT8>
0x00422940:	call 0x00421a22
0x00421a22:	movl %edi, %edi
0x00421a24:	pushl %ebp
0x00421a25:	movl %ebp, %esp
0x00421a27:	movzbl %eax, 0x8(%ebp)
0x00421a2b:	pushl %eax
0x00421a2c:	call 0x0042aa0c
0x00421a31:	testl %eax, %eax
0x00421a33:	movsbl %eax, 0x8(%ebp)
0x00421a37:	popl %ecx
0x00421a38:	jne 0x00421a40
0x00421a3a:	andl %eax, $0xffffffdf<UINT8>
0x00421a3d:	subl %eax, $0x7<UINT8>
0x00421a40:	popl %ebp
0x00421a41:	ret

0x00422945:	movl -392(%ebp), %eax
0x0042294b:	movl %eax, -456(%ebp)
0x00422951:	popl %ecx
0x00422952:	movl %ecx, -392(%ebp)
0x00422958:	incl -424(%ebp)
0x0042295e:	cmpl -448(%ebp), $0x0<UINT8>
0x00422965:	leal %eax, -48(%eax,%ecx)
0x00422969:	movl -456(%ebp), %eax
0x0042296f:	je 0x00422979
0x00422979:	movl %edx, -412(%ebp)
0x0042297f:	incl -396(%ebp)
0x00422985:	call 0x00421a42
0x0042298a:	movl -392(%ebp), %eax
0x00422990:	jmp 0x004228d9
0x00421a50:	pushl %edx
0x00421a51:	call 0x00420f85
0x00420f85:	movl %edi, %edi
0x00420f87:	pushl %ebp
0x00420f88:	movl %ebp, %esp
0x00420f8a:	pushl %esi
0x00420f8b:	movl %esi, 0x8(%ebp)
0x00420f8e:	pushl %edi
0x00420f8f:	xorl %edi, %edi
0x00420f91:	cmpl %esi, %edi
0x00420f93:	jne 0x00420fb2
0x00420fb2:	movl %eax, 0xc(%esi)
0x00420fb5:	testb %al, $0xffffff83<UINT8>
0x00420fb7:	je 236
0x00420fbd:	testb %al, $0x40<UINT8>
0x00420fbf:	jne 0x004210a9
0x004210a9:	orl %eax, $0xffffffff<UINT8>
0x004210ac:	popl %edi
0x004210ad:	popl %esi
0x004210ae:	popl %ebp
0x004210af:	ret

0x00421a56:	popl %ecx
0x00421a57:	ret

0x00422995:	pushl -412(%ebp)
0x0042299b:	decl -396(%ebp)
0x004229a1:	pushl -392(%ebp)
0x004229a7:	call 0x00421a58
0x00421a58:	movl %edi, %edi
0x00421a5a:	pushl %ebp
0x00421a5b:	movl %ebp, %esp
0x00421a5d:	cmpl 0x8(%ebp), $0xffffffff<UINT8>
0x00421a61:	je 0x00421a69
0x00421a69:	popl %ebp
0x00421a6a:	ret

0x004229ac:	popl %ecx
0x004229ad:	popl %ecx
0x004229ae:	cmpb -415(%ebp), $0x0<UINT8>
0x004229b5:	je 0x00422871
0x00422871:	movl %eax, -456(%ebp)
0x00422877:	cmpl %edi, $0x46<UINT8>
0x0042287a:	jne 0x00422883
0x00422883:	cmpl -424(%ebp), $0x0<UINT8>
0x0042288a:	je 662
0x00422890:	cmpb -399(%ebp), $0x0<UINT8>
0x00422897:	jne 315
0x0042289d:	incl -452(%ebp)
0x004228a3:	movl %ebx, -444(%ebp)
0x004228a9:	cmpl -432(%ebp), $0x0<UINT8>
0x004228b0:	je 0x004229c8
0x004229c8:	cmpb -405(%ebp), $0x0<UINT8>
0x004229cf:	je 4
0x004229d1:	movl (%ebx), %eax
0x004229d3:	jmp 0x004229d8
0x004229d8:	movl %esi, -428(%ebp)
0x004229de:	incb -413(%ebp)
0x004229e4:	incl %esi
0x004229e5:	movl -428(%ebp), %esi
0x004229eb:	jmp 0x00422a5b
0x00422a5b:	cmpl -392(%ebp), $0xffffffff<UINT8>
0x00422a62:	jne 27
0x00422a64:	cmpb (%esi), $0x25<UINT8>
0x00422a67:	jne 0x00422b26
0x00422b26:	cmpl -464(%ebp), $0x1<UINT8>
0x00422b2d:	jne 0x00422b3b
0x00422b3b:	cmpl -392(%ebp), $0xffffffff<UINT8>
0x00422b42:	jne 42
0x00422b44:	movl %eax, -452(%ebp)
0x00422b4a:	testl %eax, %eax
0x00422b4c:	jne 0x00422b59
0x00422b59:	cmpb -500(%ebp), $0x0<UINT8>
0x00422b60:	je 72
0x00422b62:	movl %ecx, -504(%ebp)
0x00422b68:	andl 0x70(%ecx), $0xfffffffd<UINT8>
0x00422b6c:	jmp 0x00422baa
0x00422baa:	popl %ebx
0x00422bab:	movl %ecx, -4(%ebp)
0x00422bae:	popl %edi
0x00422baf:	xorl %ecx, %ebp
0x00422bb1:	popl %esi
0x00422bb2:	call 0x0041d190
0x00422bb7:	leave
0x00422bb8:	ret

0x0041d727:	addl %esp, $0x10<UINT8>
0x0041d72a:	popl %edi
0x0041d72b:	leave
0x0041d72c:	ret

0x0041d749:	addl %esp, $0x10<UINT8>
0x0041d74c:	popl %esi
0x0041d74d:	popl %ebp
0x0041d74e:	ret

0x004038c4:	movl %ecx, -20(%ebp)
0x004038c7:	movl (%edi,%esi,4), %ecx
0x004038ca:	incl %esi
0x004038cb:	addl %esp, $0xc<UINT8>
0x004038ce:	addl %ebx, $0x8<UINT8>
0x004038d1:	cmpl %esi, $0x4<UINT8>
0x004038d4:	jl 0x004038a7
0x004038d6:	addl %ebx, $0xffffffe0<UINT8>
0x004038d9:	pushl %ebx
0x004038da:	call 0x0041d3a9
0x004038df:	movl %ecx, -4(%ebp)
0x004038e2:	addl %esp, $0x4<UINT8>
0x004038e5:	popl %edi
0x004038e6:	popl %esi
0x004038e7:	xorl %ecx, %ebp
0x004038e9:	popl %ebx
0x004038ea:	call 0x0041d190
0x004038ef:	movl %esp, %ebp
0x004038f1:	popl %ebp
0x004038f2:	ret

0x00403964:	movl %ecx, 0x8(%ebp)
0x00403967:	addl %esp, $0x4<UINT8>
0x0040396a:	cmpl %ecx, %esi
0x0040396c:	je 4
0x0040396e:	movl %edi, %ebx
0x00403970:	jmp 0x00403989
0x00403989:	movl %eax, %edi
0x0040398b:	shrl %eax, $0x3<UINT8>
0x0040398e:	addl %eax, %eax
0x00403990:	movl -12(%ebp), %eax
0x00403993:	cmpl %ecx, %esi
0x00403995:	je 9
0x00403997:	cmpl %ecx, $0x1<UINT8>
0x0040399a:	jne 128
0x004039a0:	leal %ecx, 0x1(%edi)
0x004039a3:	pushl %ecx
0x004039a4:	call 0x0041cfd3
0x004039a9:	addl %esp, $0x4<UINT8>
0x004039ac:	movl %edx, %edi
0x004039ae:	pushl %edi
0x004039af:	subl %edx, %ebx
0x004039b1:	movl %esi, %eax
0x004039b3:	pushl %edx
0x004039b4:	pushl %esi
0x004039b5:	call 0x0041f180
0x004039ba:	movl %eax, -28(%ebp)
0x004039bd:	addl %esp, $0xc<UINT8>
0x004039c0:	pushl %ebx
0x004039c1:	pushl %eax
0x004039c2:	pushl %esi
0x004039c3:	call 0x00420c20
0x004039c8:	addl %esp, $0xc<UINT8>
0x004039cb:	cmpl 0x8(%ebp), $0x0<UINT8>
0x004039cf:	jne 0x004039e5
0x004039e5:	leal %eax, -44(%ebp)
0x004039e8:	pushl %eax
0x004039e9:	movl %eax, -12(%ebp)
0x004039ec:	negl %eax
0x004039ee:	pushl %eax
0x004039ef:	movl %ecx, %esi
0x004039f1:	call 0x00403640
0x00403640:	pushl %ebp
0x00403641:	movl %ebp, %esp
0x00403643:	subl %esp, $0x10<UINT8>
0x00403646:	pushl %ebx
0x00403647:	pushl %esi
0x00403648:	pushl %edi
0x00403649:	movl %edi, 0x8(%ebp)
0x0040364c:	cmpl %edi, $0x1<UINT8>
0x0040364f:	jle 0x00403731
0x00403731:	cmpl %edi, $0xffffffff<UINT8>
0x00403734:	jge 190
0x0040373a:	movl %eax, $0x34<UINT32>
0x0040373f:	cltd
0x00403740:	negl %edi
0x00403742:	idivl %eax, %edi
0x00403744:	movl %esi, (%ecx)
0x00403746:	movl 0x8(%ebp), %edi
0x00403749:	addl %eax, $0x6<UINT8>
0x0040374c:	imull %eax, %eax, $0x9e3779b9<UINT32>
0x00403752:	movl -4(%ebp), %eax
0x00403755:	shrl %eax, $0x2<UINT8>
0x00403758:	andl %eax, $0x3<UINT8>
0x0040375b:	leal %edx, -1(%edi)
0x0040375e:	movl -8(%ebp), %eax
0x00403761:	testl %edx, %edx
0x00403763:	jle 71
0x00403765:	movl %eax, -4(%ecx,%edx,4)
0x00403769:	movl %ebx, %eax
0x0040376b:	shrl %ebx, $0x5<UINT8>
0x0040376e:	leal %edi, (,%esi,4)
0x00403775:	xorl %edi, %ebx
0x00403777:	shll %eax, $0x4<UINT8>
0x0040377a:	movl %ebx, %esi
0x0040377c:	xorl %esi, -4(%ebp)
0x0040377f:	shrl %ebx, $0x3<UINT8>
0x00403782:	xorl %ebx, %eax
0x00403784:	movl %eax, %edx
0x00403786:	andl %eax, $0x3<UINT8>
0x00403789:	xorl %eax, -8(%ebp)
0x0040378c:	addl %edi, %ebx
0x0040378e:	movl %ebx, 0xc(%ebp)
0x00403791:	movl %eax, (%ebx,%eax,4)
0x00403794:	xorl %eax, -4(%ecx,%edx,4)
0x00403798:	decl %edx
0x00403799:	addl %eax, %esi
0x0040379b:	xorl %edi, %eax
0x0040379d:	subl 0x4(%ecx,%edx,4), %edi
0x004037a1:	movl %esi, 0x4(%ecx,%edx,4)
0x004037a5:	testl %edx, %edx
0x004037a7:	jg 0x00403765
0x004037a9:	movl %edi, 0x8(%ebp)
0x004037ac:	movl %edi, -4(%ecx,%edi,4)
0x004037b0:	movl %eax, %edi
0x004037b2:	shrl %eax, $0x5<UINT8>
0x004037b5:	leal %ebx, (,%esi,4)
0x004037bc:	xorl %ebx, %eax
0x004037be:	movl %eax, %esi
0x004037c0:	shrl %eax, $0x3<UINT8>
0x004037c3:	shll %edi, $0x4<UINT8>
0x004037c6:	xorl %eax, %edi
0x004037c8:	movl %edi, 0x8(%ebp)
0x004037cb:	andl %edx, $0x3<UINT8>
0x004037ce:	xorl %edx, -8(%ebp)
0x004037d1:	addl %ebx, %eax
0x004037d3:	movl %eax, 0xc(%ebp)
0x004037d6:	movl %edx, (%eax,%edx,4)
0x004037d9:	movl %eax, -4(%ebp)
0x004037dc:	xorl %edx, -4(%ecx,%edi,4)
0x004037e0:	xorl %esi, %eax
0x004037e2:	addl %edx, %esi
0x004037e4:	xorl %ebx, %edx
0x004037e6:	subl (%ecx), %ebx
0x004037e8:	movl %esi, (%ecx)
0x004037ea:	addl %eax, $0x61c88647<UINT32>
0x004037ef:	movl -4(%ebp), %eax
0x004037f2:	jne 0x00403755
0x004037f8:	popl %edi
0x004037f9:	popl %esi
0x004037fa:	popl %ebx
0x004037fb:	movl %esp, %ebp
0x004037fd:	popl %ebp
0x004037fe:	ret

0x004039f6:	movsbl %ecx, -1(%esi,%ebx)
0x004039fb:	addl %esp, $0x8<UINT8>
0x004039fe:	subl %ebx, %ecx
0x00403a00:	movl %edi, %ebx
0x00403a02:	movl %eax, -16(%ebp)
0x00403a05:	leal %edx, 0x1(%edi)
0x00403a08:	pushl %edx
0x00403a09:	pushl %esi
0x00403a0a:	pushl %eax
0x00403a0b:	movb (%esi,%edi), $0x0<UINT8>
0x00403a0f:	call 0x00420c20
0x00403a14:	addl %esp, $0xc<UINT8>
0x00403a17:	pushl %esi
0x00403a18:	call 0x0041d3a9
0x00403a1d:	addl %esp, $0x4<UINT8>
0x00403a20:	movl %eax, -20(%ebp)
0x00403a23:	testl %eax, %eax
0x00403a25:	je 0x00403a30
0x00403a30:	movl %eax, -24(%ebp)
0x00403a33:	testl %eax, %eax
0x00403a35:	je 0x00403a40
0x00403a40:	movl %ecx, -8(%ebp)
0x00403a43:	movl %eax, %edi
0x00403a45:	popl %edi
0x00403a46:	popl %esi
0x00403a47:	xorl %ecx, %ebp
0x00403a49:	popl %ebx
0x00403a4a:	call 0x0041d190
0x00403a4f:	movl %esp, %ebp
0x00403a51:	popl %ebp
0x00403a52:	ret

0x0041b197:	addl %esp, $0xc<UINT8>
0x0041b19a:	xorl %ecx, %ecx
0x0041b19c:	testl %eax, %eax
0x0041b19e:	jbe 27
0x0041b1a0:	movsbl %edx, (%ecx,%esi)
0x0041b1a4:	testb %cl, $0x1<UINT8>
0x0041b1a7:	jne 0x0041b1af
0x0041b1a9:	subl %edx, %ecx
0x0041b1ab:	addl %edx, %eax
0x0041b1ad:	jmp 0x0041b1b3
0x0041b1b3:	movb (%ecx,%esi), %dl
0x0041b1b6:	incl %ecx
0x0041b1b7:	cmpl %ecx, %eax
0x0041b1b9:	jb 0x0041b1a0
0x0041b1af:	subl %edx, %eax
0x0041b1b1:	addl %edx, %ecx
0x0041b1bb:	pushl $0x0<UINT8>
0x0041b1bd:	pushl %esi
0x0041b1be:	leal %ecx, -572(%ebp)
0x0041b1c4:	pushl %ecx
0x0041b1c5:	call 0x004035d0
0x0041b1ca:	pushl %ebx
0x0041b1cb:	movl -4(%ebp), $0x1<UINT32>
0x0041b1d2:	call 0x0041d3a9
0x0041b1d7:	pushl %esi
0x0041b1d8:	call 0x0041d3a9
0x0041b1dd:	movl %esi, -548(%ebp)
0x0041b1e3:	addl %esp, $0x8<UINT8>
0x0041b1e6:	leal %edx, -572(%ebp)
0x0041b1ec:	pushl %edx
0x0041b1ed:	pushl %esi
0x0041b1ee:	call 0x00406bd0
0x00406bd0:	pushl %ebp
0x00406bd1:	movl %ebp, %esp
0x00406bd3:	pushl $0xffffffff<UINT8>
0x00406bd5:	pushl $0x4307e8<UINT32>
0x00406bda:	movl %eax, %fs:0
0x00406be0:	pushl %eax
0x00406be1:	pushl %esi
0x00406be2:	movl %eax, 0x44609c
0x00406be7:	xorl %eax, %ebp
0x00406be9:	pushl %eax
0x00406bea:	leal %eax, -12(%ebp)
0x00406bed:	movl %fs:0, %eax
0x00406bf3:	movl %eax, 0xc(%ebp)
0x00406bf6:	movl %esi, 0x8(%ebp)
0x00406bf9:	xorl %ecx, %ecx
0x00406bfb:	movl (%esi), %ecx
0x00406bfd:	movl 0x4(%esi), %ecx
0x00406c00:	movl 0x8(%esi), %ecx
0x00406c03:	movl -4(%ebp), %ecx
0x00406c06:	movl 0xc(%esi), $0x1<UINT32>
0x00406c0d:	movb 0x448860, %cl
0x00406c13:	movl %ecx, (%eax)
0x00406c15:	movl %eax, 0x4(%eax)
0x00406c18:	pushl %ecx
0x00406c19:	movl %ecx, %esi
0x00406c1b:	call 0x00406e50
0x00406e87:	movl %ebx, %eax
0x00406e89:	call 0x004034c0
0x00406e8e:	movl %ebx, 0x8(%ebp)
0x00406c20:	movl %eax, %esi
0x00406c22:	movl %ecx, -12(%ebp)
0x00406c25:	movl %fs:0, %ecx
0x00406c2c:	popl %ecx
0x00406c2d:	popl %esi
0x00406c2e:	movl %esp, %ebp
0x00406c30:	popl %ebp
0x00406c31:	ret $0x8<UINT16>

0x0041b1f3:	movl %eax, -572(%ebp)
0x0041b1f9:	testl %eax, %eax
0x0041b1fb:	je 9
0x0041b1fd:	pushl %eax
0x0041b1fe:	call 0x0041d3a9
0x0041b203:	addl %esp, $0x4<UINT8>
0x0041b206:	movl %eax, %esi
0x0041b208:	movl %ecx, -12(%ebp)
0x0041b20b:	movl %fs:0, %ecx
0x0041b212:	popl %ecx
0x0041b213:	popl %edi
0x0041b214:	popl %esi
0x0041b215:	popl %ebx
0x0041b216:	movl %ecx, -16(%ebp)
0x0041b219:	xorl %ecx, %ebp
0x0041b21b:	call 0x0041d190
0x0041b220:	movl %esp, %ebp
0x0041b222:	popl %ebp
0x0041b223:	ret

0x0040787b:	movl %ebx, %eax
0x0040787d:	pushl $0x1<UINT8>
0x0040787f:	pushl $0x0<UINT8>
0x00407881:	movl %edx, %esi
0x00407883:	pushl %edx
0x00407884:	leal %eax, 0x70(%esp)
0x00407888:	pushl %eax
0x00407889:	movb 0x348(%esp), $0x4<UINT8>
0x00407891:	call 0x0041b070
0x00407896:	movl %esi, %eax
0x00407898:	movl %eax, (%esi)
0x0040789a:	addl %esp, $0x20<UINT8>
0x0040789d:	pushl %eax
0x0040789e:	call GetModuleHandleA@kernel32.dll
0x004078a4:	testl %eax, %eax
0x004078a6:	jne 0x004078b1
0x004078b1:	movl %ebx, (%ebx)
0x004078b3:	pushl %ebx
0x004078b4:	pushl %eax
0x004078b5:	call GetProcAddress@kernel32.dll
0x004078bb:	movl %esi, %eax
0x004078bd:	xorl %ebx, %ebx
0x004078bf:	cmpl %esi, %ebx
0x004078c1:	jne 0x004078c9
0x004078c9:	movl %eax, 0x54(%esp)
0x004078cd:	cmpl %eax, %ebx
0x004078cf:	je 9
0x004078d1:	pushl %eax
0x004078d2:	call 0x0041d3a9
0x004078d7:	addl %esp, $0x4<UINT8>
0x004078da:	movl %eax, 0x44(%esp)
0x004078de:	movl 0x54(%esp), %ebx
0x004078e2:	movl 0x58(%esp), %ebx
0x004078e6:	movl 0x5c(%esp), %ebx
0x004078ea:	cmpl %eax, %ebx
0x004078ec:	je 9
0x004078ee:	pushl %eax
0x004078ef:	call 0x0041d3a9
0x004078f4:	addl %esp, $0x4<UINT8>
0x004078f7:	movl %eax, 0x1c(%esp)
0x004078fb:	movl 0x44(%esp), %ebx
0x004078ff:	movl 0x48(%esp), %ebx
0x00407903:	movl 0x4c(%esp), %ebx
0x00407907:	cmpl %eax, %ebx
0x00407909:	je 9
0x0040790b:	pushl %eax
0x0040790c:	call 0x0041d3a9
0x00407911:	addl %esp, $0x4<UINT8>
0x00407914:	movl 0x328(%esp), $0xffffffff<UINT32>
0x0040791f:	movl %eax, 0x30(%esp)
0x00407923:	movl 0x1c(%esp), %ebx
0x00407927:	movl 0x20(%esp), %ebx
0x0040792b:	movl 0x24(%esp), %ebx
0x0040792f:	cmpl %eax, %ebx
0x00407931:	je 9
0x00407933:	pushl %eax
0x00407934:	call 0x0041d3a9
0x00407939:	addl %esp, $0x4<UINT8>
0x0040793c:	pushl $0xff<UINT32>
0x00407941:	leal %ecx, 0x219(%esp)
0x00407948:	pushl %ebx
0x00407949:	pushl %ecx
0x0040794a:	movl 0x3c(%esp), %ebx
0x0040794e:	movl 0x40(%esp), %ebx
0x00407952:	movl 0x44(%esp), %ebx
0x00407956:	movb 0x220(%esp), %bl
0x0040795d:	call 0x0041f180
0x00407962:	addl %esp, $0xc<UINT8>
0x00407965:	leal %edx, 0x84(%esp)
0x0040796c:	pushl %edx
0x0040796d:	pushl $0x101<UINT32>
0x00407972:	call WSAStartup@Ws2_32.dll
WSAStartup@Ws2_32.dll: API Node	
0x00407974:	testl %eax, %eax
0x00407976:	jne 39
0x00407978:	pushl $0x100<UINT32>
0x0040797d:	leal %eax, 0x218(%esp)
0x00407984:	pushl %eax
0x00407985:	call gethostname@ws2_32.dll
gethostname@ws2_32.dll: API Node	
0x0040798b:	cmpl %eax, $0xffffffff<UINT8>
0x0040798e:	je 15
0x00407990:	leal %ecx, 0x214(%esp)
0x00407997:	pushl %ecx
0x00407998:	movl %eax, %edi
0x0040799a:	call 0x00405eb0
0x0040799f:	cmpl 0x4(%edi), %ebx
0x004079a2:	jne 0x004079b0
0x004079b0:	movl %esi, $0x4475e0<UINT32>
0x004079b5:	movl 0x14(%esp), $0x6<UINT32>
0x004079bd:	leal %ecx, (%ecx)
0x004079c0:	pushl $0x1<UINT8>
0x004079c2:	pushl %ebx
0x004079c3:	leal %edx, 0x38(%esp)
0x004079c7:	pushl %esi
0x004079c8:	pushl %edx
0x004079c9:	call 0x0041b070
0x004079ce:	leal %eax, 0x224(%esp)
0x004079d5:	movl 0x338(%esp), $0x5<UINT32>
0x004079e0:	movl %ecx, 0x40(%esp)
0x004079e4:	pushl %eax
0x004079e5:	pushl %ecx
0x004079e6:	call 0x00424d49
0x00424d49:	movl %edi, %edi
0x00424d4b:	pushl %ebp
0x00424d4c:	movl %ebp, %esp
0x00424d4e:	pushl %esi
0x00424d4f:	xorl %esi, %esi
0x00424d51:	cmpl 0x448134, %esi
0x00424d57:	jne 48
0x00424d59:	cmpl 0x8(%ebp), %esi
0x00424d5c:	jne 0x00424d7d
0x00424d7d:	cmpl 0xc(%ebp), %esi
0x00424d80:	je -36
0x00424d82:	popl %esi
0x00424d83:	popl %ebp
0x00424d84:	jmp 0x00424c3b
0x00424c3b:	movl %edi, %edi
0x00424c3d:	pushl %ebp
0x00424c3e:	movl %ebp, %esp
0x00424c40:	movl %edx, 0xc(%ebp)
0x00424c43:	pushl %esi
0x00424c44:	movl %esi, 0x8(%ebp)
0x00424c47:	pushl %edi
0x00424c48:	movzbl %eax, (%esi)
0x00424c4b:	leal %ecx, -65(%eax)
0x00424c4e:	incl %esi
0x00424c4f:	cmpl %ecx, $0x19<UINT8>
0x00424c52:	ja 0x00424c57
0x00424c57:	movzbl %ecx, (%edx)
0x00424c5a:	leal %edi, -65(%ecx)
0x00424c5d:	incl %edx
0x00424c5e:	cmpl %edi, $0x19<UINT8>
0x00424c61:	ja 3
0x00424c63:	addl %ecx, $0x20<UINT8>
0x00424c66:	testl %eax, %eax
0x00424c68:	je 4
0x00424c6a:	cmpl %eax, %ecx
0x00424c6c:	je -38
0x00424c6e:	popl %edi
0x00424c6f:	subl %eax, %ecx
0x00424c71:	popl %esi
0x00424c72:	popl %ebp
0x00424c73:	ret

0x004079eb:	addl %esp, $0x18<UINT8>
0x004079ee:	testl %eax, %eax
0x004079f0:	jne 0x00407a20
0x00407a20:	movl 0x328(%esp), $0xffffffff<UINT32>
0x00407a2b:	movl %eax, 0x30(%esp)
0x00407a2f:	cmpl %eax, %ebx
0x00407a31:	je 9
0x00407a33:	pushl %eax
0x00407a34:	call 0x0041d3a9
0x00407a39:	addl %esp, $0x4<UINT8>
0x00407a3c:	addl %esi, $0x10<UINT8>
0x00407a3f:	subl 0x14(%esp), $0x1<UINT8>
0x00407a44:	movl 0x30(%esp), %ebx
0x00407a48:	movl 0x34(%esp), %ebx
0x00407a4c:	movl 0x38(%esp), %ebx
0x00407a50:	jne 0x004079c0
0x00424c54:	addl %eax, $0x20<UINT8>
0x00407a56:	call GetCurrentProcessId@kernel32.dll
0x00407a5c:	pushl %ebx
0x00407a5d:	pushl %eax
0x00407a5e:	leal %esi, 0x4c(%esp)
0x00407a62:	call 0x0041ae90
0x00407a67:	addl %esp, $0x8<UINT8>
0x00407a6a:	movl 0x14(%esp), %eax
0x00407a6e:	movl 0x328(%esp), $0x6<UINT32>
0x00407a79:	cmpl (%edi), $0x43ffa8<UINT32>
0x00407a7f:	movl %ecx, 0x8(%edi)
0x00407a82:	sete 0x2f(%esp)
0x00407a87:	cmpl %ecx, $0x1<UINT8>
0x00407a8a:	jb 10
0x00407a8c:	movl %edx, %ecx
0x00407a8e:	subl %edx, 0x4(%edi)
0x00407a91:	cmpl %edx, $0x1<UINT8>
0x00407a94:	jae 0x00407aac
0x00407aac:	cmpb 0x2f(%esp), $0x0<UINT8>
0x00407ab1:	je 0x00407ab7
0x00407ab7:	movl %ecx, $0x43ffa8<UINT32>
0x00407abc:	movl %edx, (%edi)
0x00407abe:	movb %cl, (%ecx)
0x00407ac0:	addl %edx, 0x4(%edi)
0x00407ac3:	movb (%edx), %cl
0x00407ac5:	incl 0x4(%edi)
0x00407ac8:	movl %ecx, 0x4(%eax)
0x00407acb:	movl %eax, (%eax)
0x00407acd:	pushl %eax
0x00407ace:	movl %eax, %ecx
0x00407ad0:	movl %ecx, %edi
0x00407ad2:	call 0x00403540
0x00407ad7:	movl 0x328(%esp), $0xffffffff<UINT32>
0x00407ae2:	movl %eax, 0x44(%esp)
0x00407ae6:	cmpl %eax, %ebx
0x00407ae8:	je 9
0x00407aea:	pushl %eax
0x00407aeb:	call 0x0041d3a9
0x00407af0:	addl %esp, $0x4<UINT8>
0x00407af3:	leal %edx, 0x30(%esp)
0x00407af7:	pushl %edi
0x00407af8:	pushl %edx
0x00407af9:	xorl %edx, %edx
0x00407afb:	call 0x0041ad30
0x0041ad30:	pushl %ebp
0x0041ad31:	movl %ebp, %esp
0x0041ad33:	pushl $0xffffffff<UINT8>
0x0041ad35:	pushl $0x430921<UINT32>
0x0041ad3a:	movl %eax, %fs:0
0x0041ad40:	pushl %eax
0x0041ad41:	subl %esp, $0x10<UINT8>
0x0041ad44:	pushl %esi
0x0041ad45:	pushl %edi
0x0041ad46:	movl %eax, 0x44609c
0x0041ad4b:	xorl %eax, %ebp
0x0041ad4d:	pushl %eax
0x0041ad4e:	leal %eax, -12(%ebp)
0x0041ad51:	movl %fs:0, %eax
0x0041ad57:	movl %eax, 0xc(%ebp)
0x0041ad5a:	movl %edi, 0x8(%ebp)
0x0041ad5d:	movl 0xc(%ebp), $0x0<UINT32>
0x0041ad64:	movl %ecx, 0x4(%eax)
0x0041ad67:	movl %eax, (%eax)
0x0041ad69:	pushl %edx
0x0041ad6a:	pushl %ecx
0x0041ad6b:	pushl %eax
0x0041ad6c:	call 0x0041adf0
0x0041adf0:	pushl %ebp
0x0041adf1:	movl %ebp, %esp
0x0041adf3:	movl %eax, 0xc(%ebp)
0x0041adf6:	movl %ecx, 0x8(%ebp)
0x0041adf9:	movl %edx, 0x10(%ebp)
0x0041adfc:	pushl %edi
0x0041adfd:	pushl $0x0<UINT8>
0x0041adff:	pushl $0x0<UINT8>
0x0041ae01:	pushl %eax
0x0041ae02:	pushl %ecx
0x0041ae03:	pushl $0x0<UINT8>
0x0041ae05:	pushl %edx
0x0041ae06:	call MultiByteToWideChar@kernel32.dll
0x0041ae0c:	movl %edi, %eax
0x0041ae0e:	testl %edi, %edi
0x0041ae10:	jg 0x0041ae17
0x0041ae17:	xorl %ecx, %ecx
0x0041ae19:	leal %eax, 0x1(%edi)
0x0041ae1c:	movl %edx, $0x2<UINT32>
0x0041ae21:	mull %eax, %edx
0x0041ae23:	seto %cl
0x0041ae26:	pushl %ebx
0x0041ae27:	pushl %esi
0x0041ae28:	negl %ecx
0x0041ae2a:	orl %ecx, %eax
0x0041ae2c:	movl %esi, %ecx
0x0041ae2e:	pushl %esi
0x0041ae2f:	call 0x0041cfd3
0x0041ae34:	movl %ebx, %eax
0x0041ae36:	addl %esp, $0x4<UINT8>
0x0041ae39:	testl %ebx, %ebx
0x0041ae3b:	je 65
0x0041ae3d:	pushl %esi
0x0041ae3e:	pushl $0x0<UINT8>
0x0041ae40:	pushl %ebx
0x0041ae41:	call 0x0041f180
0x0041ae46:	movl %eax, 0xc(%ebp)
0x0041ae49:	movl %ecx, 0x8(%ebp)
0x0041ae4c:	movl %edx, 0x10(%ebp)
0x0041ae4f:	addl %esp, $0xc<UINT8>
0x0041ae52:	pushl %edi
0x0041ae53:	pushl %ebx
0x0041ae54:	pushl %eax
0x0041ae55:	pushl %ecx
0x0041ae56:	pushl $0x0<UINT8>
0x0041ae58:	pushl %edx
0x0041ae59:	call MultiByteToWideChar@kernel32.dll
0x0041ae5f:	movl %eax, $0xfeff<UINT32>
0x0041ae64:	cmpw (%ebx), %ax
0x0041ae67:	jne 0x0041ae77
0x0041ae77:	popl %esi
0x0041ae78:	movl %eax, %ebx
0x0041ae7a:	popl %ebx
0x0041ae7b:	popl %edi
0x0041ae7c:	popl %ebp
0x0041ae7d:	ret

0x0041ad71:	addl %esp, $0xc<UINT8>
0x0041ad74:	movl %esi, %eax
0x0041ad76:	pushl $0x0<UINT8>
0x0041ad78:	testl %esi, %esi
0x0041ad7a:	jne 0x0041ad9a
0x0041ad9a:	leal %eax, -28(%ebp)
0x0041ad9d:	pushl %eax
0x0041ad9e:	movl %ecx, %esi
0x0041ada0:	call 0x00408650
0x00408650:	pushl %ebp
0x00408651:	movl %ebp, %esp
0x00408653:	pushl $0xffffffff<UINT8>
0x00408655:	pushl $0x4307e8<UINT32>
0x0040865a:	movl %eax, %fs:0
0x00408660:	pushl %eax
0x00408661:	pushl %esi
0x00408662:	movl %eax, 0x44609c
0x00408667:	xorl %eax, %ebp
0x00408669:	pushl %eax
0x0040866a:	leal %eax, -12(%ebp)
0x0040866d:	movl %fs:0, %eax
0x00408673:	movl %esi, 0x8(%ebp)
0x00408676:	xorl %eax, %eax
0x00408678:	movl (%esi), %eax
0x0040867a:	movl 0x4(%esi), %eax
0x0040867d:	movl 0x8(%esi), %eax
0x00408680:	movl -4(%ebp), %eax
0x00408683:	movl 0xc(%esi), $0x1<UINT32>
0x0040868a:	movw 0x448a0c, %ax
0x00408690:	movl %eax, 0xc(%ebp)
0x00408693:	pushl %ecx
0x00408694:	movl %ecx, %esi
0x00408696:	call 0x004085b0
0x004085b0:	pushl %ebp
0x004085b1:	movl %ebp, %esp
0x004085b3:	pushl %ecx
0x004085b4:	movl %edx, 0x8(%ebp)
0x004085b7:	pushl %esi
0x004085b8:	pushl %edi
0x004085b9:	movl %edi, %eax
0x004085bb:	movl %esi, %ecx
0x004085bd:	testl %edx, %edx
0x004085bf:	je 123
0x004085c1:	pushl %ebx
0x004085c2:	movl %ebx, (%esi)
0x004085c4:	cmpl %edx, %ebx
0x004085c6:	sete -1(%ebp)
0x004085ca:	testl %edi, %edi
0x004085cc:	jne 40
0x004085ce:	movl %eax, %edx
0x004085d0:	leal %edi, 0x2(%eax)
0x004085d3:	movw %cx, (%eax)
0x004085d6:	addl %eax, $0x2<UINT8>
0x004085d9:	testw %cx, %cx
0x004085dc:	jne 0x004085d3
0x004085de:	subl %eax, %edi
0x004085e0:	sarl %eax
0x004085e2:	movl %edi, %eax
0x004085e4:	jne 0x004085f6
0x004085f6:	movl %eax, 0x8(%esi)
0x004085f9:	cmpl %edi, %eax
0x004085fb:	ja 0x00408606
0x00408606:	leal %ebx, 0x10(%eax,%edi)
0x0040860a:	cmpl %ebx, %eax
0x0040860c:	jbe 8
0x0040860e:	call 0x00406000
0x00408613:	movl %edx, 0x8(%ebp)
0x00408616:	cmpb -1(%ebp), $0x0<UINT8>
0x0040861a:	popl %ebx
0x0040861b:	je 0x00408621
0x00408621:	movl %eax, %edx
0x00408623:	movl %ecx, (%esi)
0x00408625:	leal %edx, (%edi,%edi)
0x00408628:	pushl %edx
0x00408629:	pushl %eax
0x0040862a:	movl %eax, 0x4(%esi)
0x0040862d:	leal %edx, (%ecx,%eax,2)
0x00408630:	pushl %edx
0x00408631:	call 0x00420c20
0x00408636:	addl %esp, $0xc<UINT8>
0x00408639:	addl 0x4(%esi), %edi
0x0040863c:	popl %edi
0x0040863d:	movl %eax, %esi
0x0040863f:	popl %esi
0x00408640:	movl %esp, %ebp
0x00408642:	popl %ebp
0x00408643:	ret $0x4<UINT16>

0x0040869b:	movl %eax, %esi
0x0040869d:	movl %ecx, -12(%ebp)
0x004086a0:	movl %fs:0, %ecx
0x004086a7:	popl %ecx
0x004086a8:	popl %esi
0x004086a9:	movl %esp, %ebp
0x004086ab:	popl %ebp
0x004086ac:	ret $0x8<UINT16>

0x0041ada5:	pushl %esi
0x0041ada6:	movl -4(%ebp), $0x1<UINT32>
0x0041adad:	call 0x0041d3a9
0x0041adb2:	addl %esp, $0x4<UINT8>
0x0041adb5:	leal %ecx, -28(%ebp)
0x0041adb8:	pushl %ecx
0x0041adb9:	pushl %edi
0x0041adba:	call 0x00413f20
0x00413f20:	pushl %ebp
0x00413f21:	movl %ebp, %esp
0x00413f23:	pushl $0xffffffff<UINT8>
0x00413f25:	pushl $0x4307e8<UINT32>
0x00413f2a:	movl %eax, %fs:0
0x00413f30:	pushl %eax
0x00413f31:	pushl %esi
0x00413f32:	movl %eax, 0x44609c
0x00413f37:	xorl %eax, %ebp
0x00413f39:	pushl %eax
0x00413f3a:	leal %eax, -12(%ebp)
0x00413f3d:	movl %fs:0, %eax
0x00413f43:	movl %eax, 0xc(%ebp)
0x00413f46:	movl %esi, 0x8(%ebp)
0x00413f49:	xorl %ecx, %ecx
0x00413f4b:	movl (%esi), %ecx
0x00413f4d:	movl 0x4(%esi), %ecx
0x00413f50:	movl 0x8(%esi), %ecx
0x00413f53:	movl -4(%ebp), %ecx
0x00413f56:	movl 0xc(%esi), $0x1<UINT32>
0x00413f5d:	movw 0x448a0c, %cx
0x00413f64:	movl %edx, (%eax)
0x00413f66:	movl %eax, 0x4(%eax)
0x00413f69:	pushl %edx
0x00413f6a:	movl %ecx, %esi
0x00413f6c:	call 0x0040a0d0
0x0040a0d0:	pushl %ebp
0x0040a0d1:	movl %ebp, %esp
0x0040a0d3:	pushl %ebx
0x0040a0d4:	movl %ebx, 0x8(%ebp)
0x0040a0d7:	pushl %esi
0x0040a0d8:	movl %esi, %ecx
0x0040a0da:	pushl %edi
0x0040a0db:	movl %edi, %eax
0x0040a0dd:	cmpl (%esi), %ebx
0x0040a0df:	je 101
0x0040a0e1:	testl %ebx, %ebx
0x0040a0e3:	je 28
0x0040a0e5:	testl %edi, %edi
0x0040a0e7:	jne 0x0040a105
0x0040a105:	leal %ebx, 0x10(%edi)
0x0040a108:	cmpl %ebx, 0x8(%esi)
0x0040a10b:	jbe 5
0x0040a10d:	call 0x00406000
0x0040a112:	movl %ebx, 0x8(%ebp)
0x0040a115:	movl %eax, %esi
0x0040a117:	call 0x00406090
0x00406090:	pushl %ebx
0x00406091:	xorl %ebx, %ebx
0x00406093:	pushl %esi
0x00406094:	movl %esi, %eax
0x00406096:	cmpl %edi, %ebx
0x00406098:	jne 0x004060b4
0x004060b4:	movl %edx, 0x4(%esi)
0x004060b7:	cmpl %edi, %edx
0x004060b9:	jae 0x004060c6
0x004060c6:	cmpl %edi, 0x8(%esi)
0x004060c9:	jbe 0x004060d3
0x004060d3:	movl %eax, 0x4(%esi)
0x004060d6:	cmpl %eax, %edi
0x004060d8:	jae 24
0x004060da:	leal %ebx, (%ebx)
0x004060e0:	movl %ecx, (%esi)
0x004060e2:	movw %dx, 0x448a0c
0x004060e9:	movw (%ecx,%eax,2), %dx
0x004060ed:	incl %eax
0x004060ee:	cmpl %eax, %edi
0x004060f0:	jb 0x004060e0
0x004060f2:	movl 0x4(%esi), %edi
0x004060f5:	popl %esi
0x004060f6:	popl %ebx
0x004060f7:	ret

0x0040a11c:	testl %edi, %edi
0x0040a11e:	jne 0x0040a136
0x0040a136:	movl %ecx, (%esi)
0x0040a138:	leal %eax, (%edi,%edi)
0x0040a13b:	pushl %eax
0x0040a13c:	pushl %ebx
0x0040a13d:	pushl %ecx
0x0040a13e:	call 0x00420c20
0x0040a143:	addl %esp, $0xc<UINT8>
0x0040a146:	popl %edi
0x0040a147:	popl %esi
0x0040a148:	popl %ebx
0x0040a149:	popl %ebp
0x0040a14a:	ret $0x4<UINT16>

0x00413f71:	movl %eax, %esi
0x00413f73:	movl %ecx, -12(%ebp)
0x00413f76:	movl %fs:0, %ecx
0x00413f7d:	popl %ecx
0x00413f7e:	popl %esi
0x00413f7f:	movl %esp, %ebp
0x00413f81:	popl %ebp
0x00413f82:	ret $0x8<UINT16>

0x0041adbf:	movl %eax, -28(%ebp)
0x0041adc2:	testl %eax, %eax
0x0041adc4:	je 9
0x0041adc6:	pushl %eax
0x0041adc7:	call 0x0041d3a9
0x0041adcc:	addl %esp, $0x4<UINT8>
0x0041adcf:	movl %eax, %edi
0x0041add1:	movl %ecx, -12(%ebp)
0x0041add4:	movl %fs:0, %ecx
0x0041addb:	popl %ecx
0x0041addc:	popl %edi
0x0041addd:	popl %esi
0x0041adde:	movl %esp, %ebp
0x0041ade0:	popl %ebp
0x0041ade1:	ret

0x00407b00:	pushl %eax
0x00407b01:	leal %eax, 0x50(%esp)
0x00407b05:	pushl %eax
0x00407b06:	movl %edx, $0xfde9<UINT32>
0x00407b0b:	movl 0x338(%esp), $0x7<UINT32>
0x00407b16:	call 0x0041abf0
0x0041abf0:	pushl %ebp
0x0041abf1:	movl %ebp, %esp
0x0041abf3:	pushl $0xffffffff<UINT8>
0x0041abf5:	pushl $0x430921<UINT32>
0x0041abfa:	movl %eax, %fs:0
0x0041ac00:	pushl %eax
0x0041ac01:	subl %esp, $0x10<UINT8>
0x0041ac04:	pushl %esi
0x0041ac05:	pushl %edi
0x0041ac06:	movl %eax, 0x44609c
0x0041ac0b:	xorl %eax, %ebp
0x0041ac0d:	pushl %eax
0x0041ac0e:	leal %eax, -12(%ebp)
0x0041ac11:	movl %fs:0, %eax
0x0041ac17:	movl %eax, 0xc(%ebp)
0x0041ac1a:	movl %edi, 0x8(%ebp)
0x0041ac1d:	movl 0xc(%ebp), $0x0<UINT32>
0x0041ac24:	movl %ecx, 0x4(%eax)
0x0041ac27:	movl %eax, (%eax)
0x0041ac29:	pushl %edx
0x0041ac2a:	pushl %ecx
0x0041ac2b:	pushl %eax
0x0041ac2c:	call 0x0041acb0
0x0041acb0:	pushl %ebp
0x0041acb1:	movl %ebp, %esp
0x0041acb3:	movl %eax, 0xc(%ebp)
0x0041acb6:	movl %ecx, 0x8(%ebp)
0x0041acb9:	movl %edx, 0x10(%ebp)
0x0041acbc:	pushl %edi
0x0041acbd:	pushl $0x0<UINT8>
0x0041acbf:	pushl $0x0<UINT8>
0x0041acc1:	pushl $0x0<UINT8>
0x0041acc3:	pushl $0x0<UINT8>
0x0041acc5:	pushl %eax
0x0041acc6:	pushl %ecx
0x0041acc7:	pushl $0x0<UINT8>
0x0041acc9:	pushl %edx
0x0041acca:	call WideCharToMultiByte@kernel32.dll
0x0041acd0:	movl %edi, %eax
0x0041acd2:	testl %edi, %edi
0x0041acd4:	jg 0x0041acdb
0x0041acdb:	pushl %ebx
0x0041acdc:	pushl %esi
0x0041acdd:	leal %ebx, 0x1(%edi)
0x0041ace0:	pushl %ebx
0x0041ace1:	call 0x0041cfd3
0x0041ace6:	movl %esi, %eax
0x0041ace8:	addl %esp, $0x4<UINT8>
0x0041aceb:	testl %esi, %esi
0x0041aced:	je 45
0x0041acef:	pushl %ebx
0x0041acf0:	pushl $0x0<UINT8>
0x0041acf2:	pushl %esi
0x0041acf3:	call 0x0041f180
0x0041acf8:	movl %eax, 0xc(%ebp)
0x0041acfb:	movl %ecx, 0x8(%ebp)
0x0041acfe:	movl %edx, 0x10(%ebp)
0x0041ad01:	addl %esp, $0xc<UINT8>
0x0041ad04:	pushl $0x0<UINT8>
0x0041ad06:	pushl $0x0<UINT8>
0x0041ad08:	pushl %edi
0x0041ad09:	pushl %esi
0x0041ad0a:	pushl %eax
0x0041ad0b:	pushl %ecx
0x0041ad0c:	pushl $0x0<UINT8>
0x0041ad0e:	pushl %edx
0x0041ad0f:	call WideCharToMultiByte@kernel32.dll
0x0041ad15:	movl %eax, %esi
0x0041ad17:	popl %esi
0x0041ad18:	popl %ebx
0x0041ad19:	popl %edi
0x0041ad1a:	popl %ebp
0x0041ad1b:	ret

0x0041ac31:	addl %esp, $0xc<UINT8>
0x0041ac34:	movl %esi, %eax
0x0041ac36:	pushl $0x0<UINT8>
0x0041ac38:	testl %esi, %esi
0x0041ac3a:	jne 0x0041ac5a
0x0041ac5a:	pushl %esi
0x0041ac5b:	leal %eax, -28(%ebp)
0x0041ac5e:	pushl %eax
0x0041ac5f:	call 0x004035d0
0x0041ac64:	pushl %esi
0x0041ac65:	movl -4(%ebp), $0x1<UINT32>
0x0041ac6c:	call 0x0041d3a9
0x0041ac71:	addl %esp, $0x4<UINT8>
0x0041ac74:	leal %ecx, -28(%ebp)
0x0041ac77:	pushl %ecx
0x0041ac78:	pushl %edi
0x0041ac79:	call 0x00406bd0
0x0041ac7e:	movl %eax, -28(%ebp)
0x0041ac81:	testl %eax, %eax
0x0041ac83:	je 9
0x0041ac85:	pushl %eax
0x0041ac86:	call 0x0041d3a9
0x0041ac8b:	addl %esp, $0x4<UINT8>
0x0041ac8e:	movl %eax, %edi
0x0041ac90:	movl %ecx, -12(%ebp)
0x0041ac93:	movl %fs:0, %ecx
0x0041ac9a:	popl %ecx
0x0041ac9b:	popl %edi
0x0041ac9c:	popl %esi
0x0041ac9d:	movl %esp, %ebp
0x0041ac9f:	popl %ebp
0x0041aca0:	ret

0x00407b1b:	addl %esp, $0x10<UINT8>
0x00407b1e:	movb 0x328(%esp), $0x8<UINT8>
0x00407b26:	movl %ebx, (%eax)
0x00407b28:	movl %esi, 0x4(%eax)
0x00407b2b:	movl 0x14(%esp), %esi
0x00407b2f:	movl 0x40(%esp), %ebx
0x00407b33:	cmpl (%edi), %ebx
0x00407b35:	je 122
0x00407b37:	testl %ebx, %ebx
0x00407b39:	je 24
0x00407b3b:	testl %esi, %esi
0x00407b3d:	jne 0x00407b57
0x00407b57:	leal %eax, 0x10(%esi)
0x00407b5a:	cmpl %eax, 0x8(%edi)
0x00407b5d:	jbe 17
0x00407b5f:	movl %ebx, %eax
0x00407b61:	movl %esi, %edi
0x00407b63:	call 0x004034c0
0x00407b68:	movl %ebx, 0x40(%esp)
0x00407b6c:	movl %esi, 0x14(%esp)
0x00407b70:	movl %eax, 0x18(%esp)
0x00407b74:	movl %edi, %esi
0x00407b76:	call 0x00403420
0x00407b7b:	testl %esi, %esi
0x00407b7d:	jne 0x00407b98
0x00407b98:	movl %edx, 0x18(%esp)
0x00407b9c:	movl %eax, (%edx)
0x00407b9e:	pushl %esi
0x00407b9f:	pushl %ebx
0x00407ba0:	pushl %eax
0x00407ba1:	call 0x00420c20
0x00407ba6:	movl %edi, 0x24(%esp)
0x00407baa:	addl %esp, $0xc<UINT8>
0x00407bad:	jmp 0x00407bb1
0x00407bb1:	movl %eax, 0x44(%esp)
0x00407bb5:	testl %eax, %eax
0x00407bb7:	je 9
0x00407bb9:	pushl %eax
0x00407bba:	call 0x0041d3a9
0x00407bbf:	addl %esp, $0x4<UINT8>
0x00407bc2:	movl %eax, 0x30(%esp)
0x00407bc6:	xorl %ecx, %ecx
0x00407bc8:	movl 0x44(%esp), %ecx
0x00407bcc:	movl 0x48(%esp), %ecx
0x00407bd0:	movl 0x4c(%esp), %ecx
0x00407bd4:	cmpl %eax, %ecx
0x00407bd6:	je 9
0x00407bd8:	pushl %eax
0x00407bd9:	call 0x0041d3a9
0x00407bde:	addl %esp, $0x4<UINT8>
0x00407be1:	xorl %eax, %eax
0x00407be3:	movl 0x1c(%esp), %eax
0x00407be7:	movl 0x20(%esp), %eax
0x00407beb:	movl 0x24(%esp), %eax
0x00407bef:	movl 0x328(%esp), $0x9<UINT32>
0x00407bfa:	movb 0x448860, %al
0x00407bff:	leal %eax, 0x214(%esp)
0x00407c06:	movl 0x28(%esp), $0x1<UINT32>
0x00407c0e:	leal %edx, 0x1(%eax)
0x00407c11:	movb %cl, (%eax)
0x00407c13:	incl %eax
0x00407c14:	testb %cl, %cl
0x00407c16:	jne 0x00407c11
0x00407c18:	subl %eax, %edx
0x00407c1a:	movl %esi, %eax
0x00407c1c:	movl 0x14(%esp), %esi
0x00407c20:	jne 0x00407c29
0x00407c29:	testl %esi, %esi
0x00407c2b:	jbe 20
0x00407c2d:	leal %ebx, 0x10(%esi)
0x00407c30:	testl %ebx, %ebx
0x00407c32:	jbe 13
0x00407c34:	leal %esi, 0x1c(%esp)
0x00407c38:	call 0x004034c0
0x00407c3d:	movl %esi, 0x14(%esp)
0x00407c41:	movl %eax, 0x1c(%esp)
0x00407c45:	movl %edx, 0x20(%esp)
0x00407c49:	pushl %esi
0x00407c4a:	leal %ecx, 0x218(%esp)
0x00407c51:	pushl %ecx
0x00407c52:	addl %edx, %eax
0x00407c54:	pushl %edx
0x00407c55:	call 0x00420c20
0x00407c5a:	addl 0x2c(%esp), %esi
0x00407c5e:	addl %esp, $0xc<UINT8>
0x00407c61:	leal %ecx, 0x1c(%esp)
0x00407c65:	pushl %ecx
0x00407c66:	leal %edx, 0x78(%esp)
0x00407c6a:	pushl %edx
0x00407c6b:	xorl %edx, %edx
0x00407c6d:	movl 0x330(%esp), $0xa<UINT32>
0x00407c78:	call 0x0041ad30
0x00407c7d:	pushl %eax
0x00407c7e:	leal %eax, 0x70(%esp)
0x00407c82:	pushl %eax
0x00407c83:	movl %edx, $0xfde9<UINT32>
0x00407c88:	movb 0x338(%esp), $0xb<UINT8>
0x00407c90:	call 0x0041abf0
0x00407c95:	addl %esp, $0x10<UINT8>
0x00407c98:	leal %esi, 0x54(%esp)
0x00407c9c:	movb 0x328(%esp), $0xc<UINT8>
0x00407ca4:	call 0x00405f60
0x00405f60:	pushl %ebp
0x00405f61:	movl %ebp, %esp
0x00405f63:	pushl %ecx
0x00405f64:	movl %ecx, 0x4(%eax)
0x00405f67:	movl %edx, (%eax)
0x00405f69:	pushl %ecx
0x00405f6a:	pushl %edx
0x00405f6b:	pushl %esi
0x00405f6c:	movl -4(%ebp), $0x0<UINT32>
0x00405f73:	call 0x0041b390
0x0041b390:	pushl %ebp
0x0041b391:	movl %ebp, %esp
0x0041b393:	pushl $0xffffffff<UINT8>
0x0041b395:	pushl $0x4308d9<UINT32>
0x0041b39a:	movl %eax, %fs:0
0x0041b3a0:	pushl %eax
0x0041b3a1:	subl %esp, $0xc<UINT8>
0x0041b3a4:	pushl %ebx
0x0041b3a5:	pushl %esi
0x0041b3a6:	pushl %edi
0x0041b3a7:	movl %eax, 0x44609c
0x0041b3ac:	xorl %eax, %ebp
0x0041b3ae:	pushl %eax
0x0041b3af:	leal %eax, -12(%ebp)
0x0041b3b2:	movl %fs:0, %eax
0x0041b3b8:	movl %esi, 0x8(%ebp)
0x0041b3bb:	xorl %edi, %edi
0x0041b3bd:	movl -4(%ebp), %edi
0x0041b3c0:	movl %ecx, %esi
0x0041b3c2:	movl -24(%ebp), %edi
0x0041b3c5:	call 0x00406a10
0x0041b3ca:	movl %ebx, 0x10(%ebp)
0x0041b3cd:	movl -4(%ebp), %edi
0x0041b3d0:	addl %ebx, %ebx
0x0041b3d2:	movl -24(%ebp), $0x1<UINT32>
0x0041b3d9:	cmpl %ebx, 0x8(%esi)
0x0041b3dc:	jbe 0x0041b3e3
0x0041b3e3:	xorl %edx, %edx
0x0041b3e5:	movl -20(%ebp), %edx
0x0041b3e8:	cmpl 0x10(%ebp), %edi
0x0041b3eb:	jbe 268
0x0041b3f1:	movl %edi, 0xc(%ebp)
0x0041b3f4:	movb %al, (%edx,%edi)
0x0041b3f7:	cmpb %al, $0x30<UINT8>
0x0041b3f9:	jl 0x0041b403
0x0041b3fb:	cmpb %al, $0x39<UINT8>
0x0041b3fd:	jle 200
0x0041b403:	cmpb %al, $0x61<UINT8>
0x0041b405:	jl 0x0041b40f
0x0041b40f:	cmpb %al, $0x41<UINT8>
0x0041b411:	jl 0x0041b41b
0x0041b413:	cmpb %al, $0x5a<UINT8>
0x0041b415:	jle 0x0041b4cb
0x0041b4cb:	movl %eax, 0x8(%esi)
0x0041b4ce:	cmpl 0x4(%esi), %eax
0x0041b4d1:	jb 0x0041b4e2
0x0041b4e2:	movb %bl, (%edx,%edi)
0x0041b4e5:	movl %eax, 0x4(%esi)
0x0041b4e8:	movl %ecx, (%esi)
0x0041b4ea:	incl %edx
0x0041b4eb:	movb (%eax,%ecx), %bl
0x0041b4ee:	incl 0x4(%esi)
0x0041b4f1:	movl -20(%ebp), %edx
0x0041b4f4:	cmpl %edx, 0x10(%ebp)
0x0041b4f7:	jb 0x0041b3f1
0x0041b407:	cmpb %al, $0x7a<UINT8>
0x0041b409:	jle 0x0041b4cb
0x0041b41b:	cmpb %al, $0x2d<UINT8>
0x0041b41d:	je 0x0041b4cb
0x0041b4fd:	movl %eax, %esi
0x0041b4ff:	movl %ecx, -12(%ebp)
0x0041b502:	movl %fs:0, %ecx
0x0041b509:	popl %ecx
0x0041b50a:	popl %edi
0x0041b50b:	popl %esi
0x0041b50c:	popl %ebx
0x0041b50d:	movl %esp, %ebp
0x0041b50f:	popl %ebp
0x0041b510:	ret

0x00405f78:	addl %esp, $0xc<UINT8>
0x00405f7b:	movl %eax, %esi
0x00405f7d:	movl %esp, %ebp
0x00405f7f:	popl %ebp
0x00405f80:	ret

0x00407ca9:	movb 0x328(%esp), $0xd<UINT8>
0x00407cb1:	movl %ebx, (%eax)
0x00407cb3:	movl %esi, 0x4(%eax)
0x00407cb6:	movl 0x14(%esp), %esi
0x00407cba:	movl 0x40(%esp), %ebx
0x00407cbe:	cmpl 0x1c(%esp), %ebx
0x00407cc2:	je 116
0x00407cc4:	testl %ebx, %ebx
0x00407cc6:	je 24
0x00407cc8:	testl %esi, %esi
0x00407cca:	jne 0x00407ce4
0x00407ce4:	leal %eax, 0x10(%esi)
0x00407ce7:	cmpl %eax, 0x24(%esp)
0x00407ceb:	jbe 0x00407d00
0x00407d00:	movl %edi, %esi
0x00407d02:	leal %eax, 0x1c(%esp)
0x00407d06:	call 0x00403420
0x00407d0b:	testl %esi, %esi
0x00407d0d:	jne 0x00407d25
0x00407d25:	movl %ecx, 0x1c(%esp)
0x00407d29:	pushl %esi
0x00407d2a:	pushl %ebx
0x00407d2b:	pushl %ecx
0x00407d2c:	call 0x00420c20
0x00407d31:	addl %esp, $0xc<UINT8>
0x00407d34:	movl %edi, 0x18(%esp)
0x00407d38:	movl %eax, 0x54(%esp)
0x00407d3c:	testl %eax, %eax
0x00407d3e:	je 9
0x00407d40:	pushl %eax
0x00407d41:	call 0x0041d3a9
0x00407d46:	addl %esp, $0x4<UINT8>
0x00407d49:	movl %eax, 0x64(%esp)
0x00407d4d:	xorl %ecx, %ecx
0x00407d4f:	movl 0x54(%esp), %ecx
0x00407d53:	movl 0x58(%esp), %ecx
0x00407d57:	movl 0x5c(%esp), %ecx
0x00407d5b:	cmpl %eax, %ecx
0x00407d5d:	je 9
0x00407d5f:	pushl %eax
0x00407d60:	call 0x0041d3a9
0x00407d65:	addl %esp, $0x4<UINT8>
0x00407d68:	xorl %ecx, %ecx
0x00407d6a:	movb 0x328(%esp), $0xa<UINT8>
0x00407d72:	movl %eax, 0x74(%esp)
0x00407d76:	movl 0x64(%esp), %ecx
0x00407d7a:	movl 0x68(%esp), %ecx
0x00407d7e:	movl 0x6c(%esp), %ecx
0x00407d82:	cmpl %eax, %ecx
0x00407d84:	je 9
0x00407d86:	pushl %eax
0x00407d87:	call 0x0041d3a9
0x00407d8c:	addl %esp, $0x4<UINT8>
0x00407d8f:	leal %edx, 0x1c(%esp)
0x00407d93:	pushl %edx
0x00407d94:	movl %ecx, %edi
0x00407d96:	call 0x00407ff0
0x00407ff0:	pushl %ebp
0x00407ff1:	movl %ebp, %esp
0x00407ff3:	pushl $0xffffffff<UINT8>
0x00407ff5:	pushl $0x431639<UINT32>
0x00407ffa:	movl %eax, %fs:0
0x00408000:	pushl %eax
0x00408001:	subl %esp, $0xf8<UINT32>
0x00408007:	movl %eax, 0x44609c
0x0040800c:	xorl %eax, %ebp
0x0040800e:	movl -20(%ebp), %eax
0x00408011:	pushl %ebx
0x00408012:	pushl %esi
0x00408013:	pushl %edi
0x00408014:	pushl %eax
0x00408015:	leal %eax, -12(%ebp)
0x00408018:	movl %fs:0, %eax
0x0040801e:	movl %eax, 0x8(%ebp)
0x00408021:	pushl %eax
0x00408022:	leal %eax, -228(%ebp)
0x00408028:	xorl %esi, %esi
0x0040802a:	pushl %eax
0x0040802b:	movl %ebx, %ecx
0x0040802d:	movb -212(%ebp), $0x0<UINT8>
0x00408034:	movl -144(%ebp), %esi
0x0040803a:	movl -140(%ebp), %esi
0x00408040:	movl -136(%ebp), $0x67452301<UINT32>
0x0040804a:	movl -132(%ebp), $0xefcdab89<UINT32>
0x00408054:	movl -128(%ebp), $0x98badcfe<UINT32>
0x0040805b:	movl -124(%ebp), $0x10325476<UINT32>
0x00408062:	call 0x00406bd0
0x00408067:	movl -4(%ebp), %esi
0x0040806a:	movl %eax, -224(%ebp)
0x00408070:	cmpl %eax, $0x20<UINT8>
0x00408073:	movl %esi, %eax
0x00408075:	jl 0x0040807c
0x0040807c:	movl %ecx, -228(%ebp)
0x00408082:	pushl %ecx
0x00408083:	leal %edi, -260(%ebp)
0x00408089:	leal %ecx, -212(%ebp)
0x0040808f:	call 0x00403210
0x00408094:	movl %edx, %edi
0x00408096:	pushl %edx
0x00408097:	orl %edx, $0xffffffff<UINT8>
0x0040809a:	movl %ecx, %esi
0x0040809c:	leal %edi, -244(%ebp)
0x004080a2:	movb -4(%ebp), $0x1<UINT8>
0x004080a6:	call 0x00406a80
0x00406a80:	pushl %ebp
0x00406a81:	movl %ebp, %esp
0x00406a83:	andl %esp, $0xfffffff8<UINT8>
0x00406a86:	pushl $0xffffffff<UINT8>
0x00406a88:	pushl $0x430c18<UINT32>
0x00406a8d:	movl %eax, %fs:0
0x00406a93:	pushl %eax
0x00406a94:	subl %esp, $0x1c<UINT8>
0x00406a97:	pushl %ebx
0x00406a98:	pushl %esi
0x00406a99:	movl %eax, 0x44609c
0x00406a9e:	xorl %eax, %esp
0x00406aa0:	pushl %eax
0x00406aa1:	leal %eax, 0x28(%esp)
0x00406aa5:	movl %fs:0, %eax
0x00406aab:	movl %esi, 0x8(%ebp)
0x00406aae:	movl %eax, 0x4(%esi)
0x00406ab1:	xorl %ebx, %ebx
0x00406ab3:	movl 0x10(%esp), %ebx
0x00406ab7:	cmpl %ecx, %eax
0x00406ab9:	ja 95
0x00406abb:	cmpl %edx, %ebx
0x00406abd:	je 91
0x00406abf:	cmpl %ecx, %eax
0x00406ac1:	jne 0x00406b02
0x00406b02:	cmpl %edx, %eax
0x00406b04:	ja 0x00406b0e
0x00406b0e:	subl %eax, %ecx
0x00406b10:	movl %edx, %eax
0x00406b12:	pushl %edx
0x00406b13:	movl %edx, (%esi)
0x00406b15:	addl %edx, %ecx
0x00406b17:	pushl %edx
0x00406b18:	jmp 0x00406b1c
0x00406b1c:	pushl %edi
0x00406b1d:	call 0x004035d0
0x00406b22:	movl %eax, %edi
0x00406b24:	movl %ecx, 0x28(%esp)
0x00406b28:	movl %fs:0, %ecx
0x00406b2f:	popl %ecx
0x00406b30:	popl %esi
0x00406b31:	popl %ebx
0x00406b32:	movl %esp, %ebp
0x00406b34:	popl %ebp
0x00406b35:	ret $0x4<UINT16>

0x004080ab:	movb -4(%ebp), $0x2<UINT8>
0x004080af:	movl %eax, -244(%ebp)
0x004080b5:	pushl %eax
0x004080b6:	movl %eax, -240(%ebp)
0x004080bc:	leal %ecx, -228(%ebp)
0x004080c2:	call 0x00403540
0x004080c7:	movl %ecx, -228(%ebp)
0x004080cd:	movl %eax, -224(%ebp)
0x004080d3:	pushl %ecx
0x004080d4:	leal %edi, -36(%ebp)
0x004080d7:	leal %ecx, -212(%ebp)
0x004080dd:	call 0x00403210
0x004080e2:	movb -4(%ebp), $0x3<UINT8>
0x004080e6:	movl %ecx, 0x4(%eax)
0x004080e9:	movl %edx, (%eax)
0x004080eb:	movl %eax, %ecx
0x004080ed:	pushl %edx
0x004080ee:	leal %ecx, -228(%ebp)
0x004080f4:	call 0x00406e50
0x004080f9:	movb -4(%ebp), $0x2<UINT8>
0x004080fd:	movl %eax, -36(%ebp)
0x00408100:	testl %eax, %eax
0x00408102:	je 9
0x00408104:	pushl %eax
0x00408105:	call 0x0041d3a9
0x0040810a:	addl %esp, $0x4<UINT8>
0x0040810d:	movl %esi, -228(%ebp)
0x00408113:	movl %ecx, $0x8<UINT32>
0x00408118:	leal %edi, -52(%ebp)
0x0040811b:	rep movsl %es:(%edi), %ds:(%esi)
0x0040811d:	movl %eax, $0x20<UINT32>
0x00408122:	leal %ecx, -100(%ebp)
0x00408125:	leal %edx, -52(%ebp)
0x00408128:	call 0x004025a0
0x0040812d:	xorl %eax, %eax
0x0040812f:	addl %ebx, $0x120<UINT32>
0x00408135:	movl (%ebx), %eax
0x00408137:	movl 0x4(%ebx), %eax
0x0040813a:	movl 0x8(%ebx), %eax
0x0040813d:	movl 0xc(%ebx), %eax
0x00408140:	movl 0x10(%ebx), %eax
0x00408143:	movl 0x14(%ebx), %eax
0x00408146:	movl 0x18(%ebx), %eax
0x00408149:	movl 0x1c(%ebx), %eax
0x0040814c:	movb 0x20(%ebx), %al
0x0040814f:	leal %eax, -100(%ebp)
0x00408152:	pushl %eax
0x00408153:	xorl %eax, %eax
0x00408155:	leal %edi, -36(%ebp)
0x00408158:	leal %ecx, -212(%ebp)
0x0040815e:	call 0x00403210
0x00408163:	movl %eax, (%eax)
0x00408165:	movl %esi, %eax
0x00408167:	movl %eax, -36(%ebp)
0x0040816a:	movl %ecx, $0x8<UINT32>
0x0040816f:	movl %edi, %ebx
0x00408171:	rep movsl %es:(%edi), %ds:(%esi)
0x00408173:	xorl %esi, %esi
0x00408175:	cmpl %eax, %esi
0x00408177:	je 9
0x00408179:	pushl %eax
0x0040817a:	call 0x0041d3a9
0x0040817f:	addl %esp, $0x4<UINT8>
0x00408182:	movl %eax, -244(%ebp)
0x00408188:	cmpl %eax, %esi
0x0040818a:	je 9
0x0040818c:	pushl %eax
0x0040818d:	call 0x0041d3a9
0x00408192:	addl %esp, $0x4<UINT8>
0x00408195:	movl %eax, -260(%ebp)
0x0040819b:	movl -244(%ebp), %esi
0x004081a1:	movl -240(%ebp), %esi
0x004081a7:	movl -236(%ebp), %esi
0x004081ad:	cmpl %eax, %esi
0x004081af:	je 9
0x004081b1:	pushl %eax
0x004081b2:	call 0x0041d3a9
0x004081b7:	addl %esp, $0x4<UINT8>
0x004081ba:	movl %eax, -228(%ebp)
0x004081c0:	movl -260(%ebp), %esi
0x004081c6:	movl -256(%ebp), %esi
0x004081cc:	movl -252(%ebp), %esi
0x004081d2:	cmpl %eax, %esi
0x004081d4:	je 9
0x004081d6:	pushl %eax
0x004081d7:	call 0x0041d3a9
0x004081dc:	addl %esp, $0x4<UINT8>
0x004081df:	movl %ecx, -12(%ebp)
0x004081e2:	movl %fs:0, %ecx
0x004081e9:	popl %ecx
0x004081ea:	popl %edi
0x004081eb:	popl %esi
0x004081ec:	popl %ebx
0x004081ed:	movl %ecx, -20(%ebp)
0x004081f0:	xorl %ecx, %ebp
0x004081f2:	call 0x0041d190
0x004081f7:	movl %esp, %ebp
0x004081f9:	popl %ebp
0x004081fa:	ret $0x4<UINT16>

0x00407d9b:	leal %ecx, 0x1c(%esp)
0x00407d9f:	movl %esi, %edi
0x00407da1:	call 0x00407de0
0x00407de0:	pushl %ebp
0x00407de1:	movl %ebp, %esp
0x00407de3:	pushl $0xffffffff<UINT8>
0x00407de5:	pushl $0x4316a1<UINT32>
0x00407dea:	movl %eax, %fs:0
0x00407df0:	pushl %eax
0x00407df1:	subl %esp, $0xd0<UINT32>
0x00407df7:	movl %eax, 0x44609c
0x00407dfc:	xorl %eax, %ebp
0x00407dfe:	movl -16(%ebp), %eax
0x00407e01:	pushl %ebx
0x00407e02:	pushl %edi
0x00407e03:	pushl %eax
0x00407e04:	leal %eax, -12(%ebp)
0x00407e07:	movl %fs:0, %eax
0x00407e0d:	movl %eax, 0x4(%ecx)
0x00407e10:	movl %ecx, (%ecx)
0x00407e12:	xorl %ebx, %ebx
0x00407e14:	pushl %ecx
0x00407e15:	leal %edi, -216(%ebp)
0x00407e1b:	leal %ecx, -168(%ebp)
0x00407e21:	movb -168(%ebp), %bl
0x00407e27:	movl -100(%ebp), %ebx
0x00407e2a:	movl -96(%ebp), %ebx
0x00407e2d:	movl -92(%ebp), $0x67452301<UINT32>
0x00407e34:	movl -88(%ebp), $0xefcdab89<UINT32>
0x00407e3b:	movl -84(%ebp), $0x98badcfe<UINT32>
0x00407e42:	movl -80(%ebp), $0x10325476<UINT32>
0x00407e49:	call 0x00403210
0x00407e4e:	movl %eax, %edi
0x00407e50:	pushl %eax
0x00407e51:	leal %edx, 0xf(%ebx)
0x00407e54:	leal %ecx, 0x8(%ebx)
0x00407e57:	leal %edi, -184(%ebp)
0x00407e5d:	movl -4(%ebp), %ebx
0x00407e60:	call 0x00406a80
0x00406b06:	movl %ebx, %eax
0x00406b08:	subl %ebx, %ecx
0x00406b0a:	cmpl %edx, %ebx
0x00406b0c:	jbe 0x00406b12
0x00407e65:	movb -4(%ebp), $0x1<UINT8>
0x00407e69:	movl %eax, -184(%ebp)
0x00407e6f:	movl %edx, 0x4(%eax)
0x00407e72:	movl %ecx, (%eax)
0x00407e74:	movl -32(%ebp), %ecx
0x00407e77:	movl %ecx, 0x8(%eax)
0x00407e7a:	movl -28(%ebp), %edx
0x00407e7d:	movw %dx, 0xc(%eax)
0x00407e81:	movb %al, 0xe(%eax)
0x00407e84:	movl -24(%ebp), %ecx
0x00407e87:	movw -20(%ebp), %dx
0x00407e8b:	movb -18(%ebp), %al
0x00407e8e:	leal %eax, 0xf(%ebx)
0x00407e91:	leal %ecx, -56(%ebp)
0x00407e94:	leal %edx, -32(%ebp)
0x00407e97:	call 0x004025a0
0x00407e9c:	leal %ecx, -56(%ebp)
0x00407e9f:	pushl %ecx
0x00407ea0:	leal %edx, -32(%ebp)
0x00407ea3:	pushl %edx
0x00407ea4:	leal %ecx, -216(%ebp)
0x00407eaa:	call 0x004084c0
0x004084c0:	pushl %ebp
0x004084c1:	movl %ebp, %esp
0x004084c3:	pushl $0xffffffff<UINT8>
0x004084c5:	pushl $0x430ba9<UINT32>
0x004084ca:	movl %eax, %fs:0
0x004084d0:	pushl %eax
0x004084d1:	pushl %ecx
0x004084d2:	pushl %esi
0x004084d3:	pushl %edi
0x004084d4:	movl %eax, 0x44609c
0x004084d9:	xorl %eax, %ebp
0x004084db:	pushl %eax
0x004084dc:	leal %eax, -12(%ebp)
0x004084df:	movl %fs:0, %eax
0x004084e5:	movl %edi, %ecx
0x004084e7:	movl %esi, 0x8(%ebp)
0x004084ea:	movl %eax, 0xc(%ebp)
0x004084ed:	pushl $0x0<UINT8>
0x004084ef:	pushl %eax
0x004084f0:	movl -4(%ebp), $0x0<UINT32>
0x004084f7:	pushl %esi
0x004084f8:	movl -16(%ebp), $0x0<UINT32>
0x004084ff:	call 0x004035d0
0x00408504:	movl -4(%ebp), $0x0<UINT32>
0x0040850b:	movl %eax, 0x4(%edi)
0x0040850e:	movl %edi, (%edi)
0x00408510:	pushl %edi
0x00408511:	movl %ecx, %esi
0x00408513:	movl -16(%ebp), $0x1<UINT32>
0x0040851a:	call 0x00403540
0x0040851f:	movl %eax, %esi
0x00408521:	movl %ecx, -12(%ebp)
0x00408524:	movl %fs:0, %ecx
0x0040852b:	popl %ecx
0x0040852c:	popl %edi
0x0040852d:	popl %esi
0x0040852e:	movl %esp, %ebp
0x00408530:	popl %ebp
0x00408531:	ret

0x00407eaf:	addl %esp, $0x8<UINT8>
0x00407eb2:	movb -4(%ebp), $0x2<UINT8>
0x00407eb6:	movl %ecx, 0x4(%eax)
0x00407eb9:	movl %eax, (%eax)
0x00407ebb:	pushl %eax
0x00407ebc:	movl %eax, %ecx
0x00407ebe:	movl %ecx, %edi
0x00407ec0:	call 0x00406e50
0x00407ec5:	movb -4(%ebp), $0x1<UINT8>
0x00407ec9:	movl %eax, -32(%ebp)
0x00407ecc:	cmpl %eax, %ebx
0x00407ece:	je 9
0x00407ed0:	pushl %eax
0x00407ed1:	call 0x0041d3a9
0x00407ed6:	addl %esp, $0x4<UINT8>
0x00407ed9:	movl %ecx, -184(%ebp)
0x00407edf:	movl %eax, -180(%ebp)
0x00407ee5:	pushl %ecx
0x00407ee6:	leal %edi, -200(%ebp)
0x00407eec:	leal %ecx, -168(%ebp)
0x00407ef2:	call 0x00403210
0x00407ef7:	movl %edx, %edi
0x00407ef9:	pushl %edx
0x00407efa:	orl %edx, $0xffffffff<UINT8>
0x00407efd:	movl %ecx, $0x10<UINT32>
0x00407f02:	leal %edi, -32(%ebp)
0x00407f05:	movb -4(%ebp), $0x3<UINT8>
0x00407f09:	call 0x00406a80
0x00407f0e:	movb -4(%ebp), $0x4<UINT8>
0x00407f12:	movl %ecx, (%eax)
0x00407f14:	movl %eax, 0x4(%eax)
0x00407f17:	pushl %ecx
0x00407f18:	leal %ecx, -200(%ebp)
0x00407f1e:	call 0x00406e50
0x00407f23:	movl %eax, -32(%ebp)
0x00407f26:	cmpl %eax, %ebx
0x00407f28:	je 9
0x00407f2a:	pushl %eax
0x00407f2b:	call 0x0041d3a9
0x00407f30:	addl %esp, $0x4<UINT8>
0x00407f33:	xorl %eax, %eax
0x00407f35:	movl 0x141(%esi), %eax
0x00407f3b:	movl 0x145(%esi), %eax
0x00407f41:	movl 0x149(%esi), %eax
0x00407f47:	movl 0x14d(%esi), %eax
0x00407f4d:	movb 0x151(%esi), %al
0x00407f53:	movl %eax, -200(%ebp)
0x00407f59:	movl %edx, (%eax)
0x00407f5b:	movl 0x141(%esi), %edx
0x00407f61:	movl %ecx, 0x4(%eax)
0x00407f64:	movl 0x145(%esi), %ecx
0x00407f6a:	movl %edx, 0x8(%eax)
0x00407f6d:	movl 0x149(%esi), %edx
0x00407f73:	movl %ecx, 0xc(%eax)
0x00407f76:	pushl %eax
0x00407f77:	movl 0x14d(%esi), %ecx
0x00407f7d:	call 0x0041d3a9
0x00407f82:	movl %eax, -184(%ebp)
0x00407f88:	addl %esp, $0x4<UINT8>
0x00407f8b:	movl -200(%ebp), %ebx
0x00407f91:	movl -196(%ebp), %ebx
0x00407f97:	movl -192(%ebp), %ebx
0x00407f9d:	cmpl %eax, %ebx
0x00407f9f:	je 9
0x00407fa1:	pushl %eax
0x00407fa2:	call 0x0041d3a9
0x00407fa7:	addl %esp, $0x4<UINT8>
0x00407faa:	movl %eax, -216(%ebp)
0x00407fb0:	movl -184(%ebp), %ebx
0x00407fb6:	movl -180(%ebp), %ebx
0x00407fbc:	movl -176(%ebp), %ebx
0x00407fc2:	cmpl %eax, %ebx
0x00407fc4:	je 9
0x00407fc6:	pushl %eax
0x00407fc7:	call 0x0041d3a9
0x00407fcc:	addl %esp, $0x4<UINT8>
0x00407fcf:	movl %ecx, -12(%ebp)
0x00407fd2:	movl %fs:0, %ecx
0x00407fd9:	popl %ecx
0x00407fda:	popl %edi
0x00407fdb:	popl %ebx
0x00407fdc:	movl %ecx, -16(%ebp)
0x00407fdf:	xorl %ecx, %ebp
0x00407fe1:	call 0x0041d190
0x00407fe6:	movl %esp, %ebp
0x00407fe8:	popl %ebp
0x00407fe9:	ret

0x00407da6:	movl %eax, 0x1c(%esp)
0x00407daa:	testl %eax, %eax
0x00407dac:	je 9
0x00407dae:	pushl %eax
0x00407daf:	call 0x0041d3a9
0x00407db4:	addl %esp, $0x4<UINT8>
0x00407db7:	movl %ecx, 0x320(%esp)
0x00407dbe:	movl %fs:0, %ecx
0x00407dc5:	popl %ecx
0x00407dc6:	popl %edi
0x00407dc7:	popl %esi
0x00407dc8:	popl %ebx
0x00407dc9:	movl %ecx, 0x308(%esp)
0x00407dd0:	xorl %ecx, %esp
0x00407dd2:	call 0x0041d190
0x00407dd7:	movl %esp, %ebp
0x00407dd9:	popl %ebp
0x00407dda:	ret

0x00407036:	call 0x00408440
0x00408440:	pushl %ebp
0x00408441:	movl %ebp, %esp
0x00408443:	subl %esp, $0x108<UINT32>
0x00408449:	movl %eax, 0x44609c
0x0040844e:	xorl %eax, %ebp
0x00408450:	movl -4(%ebp), %eax
0x00408453:	pushl $0x103<UINT32>
0x00408458:	leal %eax, -263(%ebp)
0x0040845e:	pushl $0x0<UINT8>
0x00408460:	pushl %eax
0x00408461:	movb -264(%ebp), $0x0<UINT8>
0x00408468:	call 0x0041f180
0x0040846d:	addl %esp, $0xc<UINT8>
0x00408470:	leal %ecx, -264(%ebp)
0x00408476:	pushl %ecx
0x00408477:	pushl $0x104<UINT32>
0x0040847c:	call GetTempPathA@kernel32.dll
GetTempPathA@kernel32.dll: API Node	
0x00408482:	leal %eax, -264(%ebp)
0x00408488:	leal %edx, 0x1(%eax)
0x0040848b:	jmp 0x00408490
0x00408490:	movb %cl, (%eax)
0x00408492:	incl %eax
0x00408493:	testb %cl, %cl
0x00408495:	jne 0x00408490
0x00408497:	subl %eax, %edx
0x00408499:	leal %edx, -264(%ebp)
0x0040849f:	movb -265(%ebp,%eax), %cl
0x004084a6:	pushl %edx
0x004084a7:	leal %eax, 0x70(%esi)
0x004084aa:	call 0x00405eb0
0x004084af:	movl %ecx, -4(%ebp)
0x004084b2:	xorl %ecx, %ebp
0x004084b4:	call 0x0041d190
0x004084b9:	movl %esp, %ebp
0x004084bb:	popl %ebp
0x004084bc:	ret

0x0040703b:	call 0x00408540
0x00408540:	pushl %ebp
0x00408541:	movl %ebp, %esp
0x00408543:	subl %esp, $0x108<UINT32>
0x00408549:	movl %eax, 0x44609c
0x0040854e:	xorl %eax, %ebp
0x00408550:	movl -4(%ebp), %eax
0x00408553:	pushl $0x103<UINT32>
0x00408558:	leal %eax, -263(%ebp)
0x0040855e:	pushl $0x0<UINT8>
0x00408560:	pushl %eax
0x00408561:	movb -264(%ebp), $0x0<UINT8>
0x00408568:	call 0x0041f180
0x0040856d:	addl %esp, $0xc<UINT8>
0x00408570:	pushl $0x104<UINT32>
0x00408575:	leal %ecx, -264(%ebp)
0x0040857b:	pushl %ecx
0x0040857c:	call GetWindowsDirectoryA@kernel32.dll
GetWindowsDirectoryA@kernel32.dll: API Node	
0x00408582:	leal %edx, -264(%ebp)
0x00408588:	pushl %edx
0x00408589:	leal %eax, 0x80(%esi)
0x0040858f:	call 0x00405eb0
0x00408594:	movl %ecx, -4(%ebp)
0x00408597:	xorl %ecx, %ebp
0x00408599:	call 0x0041d190
0x0040859e:	movl %esp, %ebp
0x004085a0:	popl %ebp
0x004085a1:	ret

0x00407040:	pushl %esi
0x00407041:	call 0x004083c0
0x004083c0:	pushl %ebp
0x004083c1:	movl %ebp, %esp
0x004083c3:	andl %esp, $0xfffffff8<UINT8>
0x004083c6:	pushl $0xffffffff<UINT8>
0x004083c8:	pushl $0x431248<UINT32>
0x004083cd:	movl %eax, %fs:0
0x004083d3:	pushl %eax
0x004083d4:	subl %esp, $0x14<UINT8>
0x004083d7:	movl %eax, 0x44609c
0x004083dc:	xorl %eax, %esp
0x004083de:	pushl %eax
0x004083df:	leal %eax, 0x18(%esp)
0x004083e3:	movl %fs:0, %eax
0x004083e9:	leal %eax, 0x4(%esp)
0x004083ed:	pushl %eax
0x004083ee:	call 0x00417bc0
0x00417bc0:	pushl %ebp
0x00417bc1:	movl %ebp, %esp
0x00417bc3:	pushl $0xffffffff<UINT8>
0x00417bc5:	pushl $0x430ef9<UINT32>
0x00417bca:	movl %eax, %fs:0
0x00417bd0:	pushl %eax
0x00417bd1:	subl %esp, $0x48<UINT8>
0x00417bd4:	pushl %ebx
0x00417bd5:	pushl %esi
0x00417bd6:	pushl %edi
0x00417bd7:	movl %eax, 0x44609c
0x00417bdc:	xorl %eax, %ebp
0x00417bde:	pushl %eax
0x00417bdf:	leal %eax, -12(%ebp)
0x00417be2:	movl %fs:0, %eax
0x00417be8:	xorl %ebx, %ebx
0x00417bea:	movl -4(%ebp), %ebx
0x00417bed:	movl -20(%ebp), %ebx
0x00417bf0:	movl %ecx, 0x8(%ebp)
0x00417bf3:	call 0x00406a10
0x00417bf8:	movl %edi, $0x1<UINT32>
0x00417bfd:	movl -20(%ebp), %edi
0x00417c00:	movl -36(%ebp), %ebx
0x00417c03:	movl -32(%ebp), %ebx
0x00417c06:	movl -28(%ebp), %ebx
0x00417c09:	movl -4(%ebp), %edi
0x00417c0c:	movb 0x448860, %bl
0x00417c12:	leal %ebx, 0x33(%edi)
0x00417c15:	leal %esi, -36(%ebp)
0x00417c18:	movl -24(%ebp), %edi
0x00417c1b:	call 0x004034c0
0x00417c20:	movl %eax, -36(%ebp)
0x00417c23:	movl %esi, -32(%ebp)
0x00417c26:	pushl $0x24<UINT8>
0x00417c28:	leal %ecx, (%esi,%eax)
0x00417c2b:	pushl $0x440b08<UINT32>
0x00417c30:	pushl %ecx
0x00417c31:	call 0x00420c20
0x00417c36:	addl %esi, $0x24<UINT8>
0x00417c39:	addl %esp, $0xc<UINT8>
0x00417c3c:	movl -32(%ebp), %esi
0x00417c3f:	xorl %eax, %eax
0x00417c41:	movl -4(%ebp), $0x2<UINT32>
0x00417c48:	movl -52(%ebp), %eax
0x00417c4b:	movl -48(%ebp), %eax
0x00417c4e:	movl -44(%ebp), %eax
0x00417c51:	movb -4(%ebp), $0x3<UINT8>
0x00417c55:	leal %ebx, 0x16(%edi)
0x00417c58:	leal %esi, -52(%ebp)
0x00417c5b:	movl -40(%ebp), %edi
0x00417c5e:	movb 0x448860, %al
0x00417c63:	call 0x004034c0
0x00417c68:	movl %eax, -48(%ebp)
0x00417c6b:	movl %edx, 0x440b30
0x00417c71:	movl %esi, -52(%ebp)
0x00417c74:	movl (%eax,%esi), %edx
0x00417c77:	movw %cx, 0x440b34
0x00417c7e:	movw 0x4(%eax,%esi), %cx
0x00417c83:	movb %dl, 0x440b36
0x00417c89:	movb 0x6(%eax,%esi), %dl
0x00417c8d:	addl %eax, $0x7<UINT8>
0x00417c90:	movl -48(%ebp), %eax
0x00417c93:	leal %eax, -52(%ebp)
0x00417c96:	pushl %eax
0x00417c97:	leal %ecx, -36(%ebp)
0x00417c9a:	pushl %ecx
0x00417c9b:	leal %edx, -68(%ebp)
0x00417c9e:	pushl %edx
0x00417c9f:	movb -4(%ebp), $0x4<UINT8>
0x00417ca3:	call 0x004173a0
0x004173a0:	pushl %ebp
0x004173a1:	movl %ebp, %esp
0x004173a3:	pushl $0xffffffff<UINT8>
0x004173a5:	pushl $0x430a5a<UINT32>
0x004173aa:	movl %eax, %fs:0
0x004173b0:	pushl %eax
0x004173b1:	subl %esp, $0xf0<UINT32>
0x004173b7:	pushl %ebx
0x004173b8:	pushl %esi
0x004173b9:	pushl %edi
0x004173ba:	movl %eax, 0x44609c
0x004173bf:	xorl %eax, %ebp
0x004173c1:	pushl %eax
0x004173c2:	leal %eax, -12(%ebp)
0x004173c5:	movl %fs:0, %eax
0x004173cb:	xorl %ebx, %ebx
0x004173cd:	leal %ecx, -72(%ebp)
0x004173d0:	movl -252(%ebp), %ebx
0x004173d6:	call 0x00406a10
0x004173db:	movl %eax, $0x1<UINT32>
0x004173e0:	movl -4(%ebp), %eax
0x004173e3:	movl -136(%ebp), %ebx
0x004173e9:	movl -132(%ebp), %ebx
0x004173ef:	movl -128(%ebp), %ebx
0x004173f2:	movb -4(%ebp), $0x2<UINT8>
0x004173f6:	movb 0x448860, %bl
0x004173fc:	leal %ebx, 0x27(%eax)
0x004173ff:	leal %esi, -136(%ebp)
0x00417405:	movl -124(%ebp), %eax
0x00417408:	call 0x004034c0
0x0041740d:	movl %ecx, -132(%ebp)
0x00417413:	movl %eax, -136(%ebp)
0x00417419:	movl %edx, 0x440960
0x0041741f:	addl %eax, %ecx
0x00417421:	movl (%eax), %edx
0x00417423:	movl %ecx, 0x440964
0x00417429:	movl 0x4(%eax), %ecx
0x0041742c:	movl %edx, 0x440968
0x00417432:	movl 0x8(%eax), %edx
0x00417435:	movl %ecx, 0x44096c
0x0041743b:	movl 0xc(%eax), %ecx
0x0041743e:	movl %edx, 0x440970
0x00417444:	movl 0x10(%eax), %edx
0x00417447:	movl %ecx, 0x440974
0x0041744d:	movl %edi, $0x18<UINT32>
0x00417452:	movl 0x14(%eax), %ecx
0x00417455:	addl -132(%ebp), %edi
0x0041745b:	xorl %eax, %eax
0x0041745d:	movl -88(%ebp), %eax
0x00417460:	movl -84(%ebp), %eax
0x00417463:	movl -80(%ebp), %eax
0x00417466:	movb -4(%ebp), $0x4<UINT8>
0x0041746a:	leal %esi, -88(%ebp)
0x0041746d:	movl -76(%ebp), $0x1<UINT32>
0x00417474:	movb 0x448860, %al
0x00417479:	call 0x004034c0
0x0041747e:	movl %edx, -88(%ebp)
0x00417481:	movl %eax, -84(%ebp)
0x00417484:	movl %ecx, 0x440120
0x0041748a:	addl %eax, %edx
0x0041748c:	movl (%eax), %ecx
0x0041748e:	movl %edx, 0x440124
0x00417494:	movl 0x4(%eax), %edx
0x00417497:	movl %ecx, 0x440128
0x0041749d:	movl 0x8(%eax), %ecx
0x004174a0:	movl %edx, 0x44012c
0x004174a6:	movl 0xc(%eax), %edx
0x004174a9:	movl %ecx, 0x440130
0x004174af:	movl 0x10(%eax), %ecx
0x004174b2:	movl %edx, 0x440134
0x004174b8:	movl 0x14(%eax), %edx
0x004174bb:	addl -84(%ebp), %edi
0x004174be:	pushl $0x1<UINT8>
0x004174c0:	xorl %ebx, %ebx
0x004174c2:	pushl %ebx
0x004174c3:	leal %eax, -136(%ebp)
0x004174c9:	pushl %eax
0x004174ca:	leal %ecx, -216(%ebp)
0x004174d0:	pushl %ecx
0x004174d1:	movb -4(%ebp), $0x5<UINT8>
0x004174d5:	call 0x0041b070
0x004174da:	movl -28(%ebp), %eax
0x004174dd:	pushl $0x1<UINT8>
0x004174df:	pushl %ebx
0x004174e0:	movl %edx, %esi
0x004174e2:	pushl %edx
0x004174e3:	leal %eax, -184(%ebp)
0x004174e9:	pushl %eax
0x004174ea:	movb -4(%ebp), $0x6<UINT8>
0x004174ee:	call 0x0041b070
0x004174f3:	movl %edi, 0x43b01c
0x004174f9:	movl %esi, %eax
0x004174fb:	movl %eax, (%esi)
0x004174fd:	addl %esp, $0x20<UINT8>
0x00417500:	pushl %eax
0x00417501:	call GetModuleHandleA@kernel32.dll
0x00417503:	cmpl %eax, %ebx
0x00417505:	jne 0x00417510
0x00417510:	movl %ecx, -28(%ebp)
0x00417513:	movl %ecx, (%ecx)
0x00417515:	pushl %ecx
0x00417516:	pushl %eax
0x00417517:	call GetProcAddress@kernel32.dll
0x0041751d:	movl -40(%ebp), %eax
0x00417520:	cmpl %eax, %ebx
0x00417522:	jne 0x0041752a
0x0041752a:	movl %eax, -184(%ebp)
0x00417530:	cmpl %eax, %ebx
0x00417532:	je 9
0x00417534:	pushl %eax
0x00417535:	call 0x0041d3a9
0x0041753a:	addl %esp, $0x4<UINT8>
0x0041753d:	movl %eax, -216(%ebp)
0x00417543:	movl -184(%ebp), %ebx
0x00417549:	movl -180(%ebp), %ebx
0x0041754f:	movl -176(%ebp), %ebx
0x00417555:	cmpl %eax, %ebx
0x00417557:	je 9
0x00417559:	pushl %eax
0x0041755a:	call 0x0041d3a9
0x0041755f:	addl %esp, $0x4<UINT8>
0x00417562:	movl %eax, -88(%ebp)
0x00417565:	movl -216(%ebp), %ebx
0x0041756b:	movl -212(%ebp), %ebx
0x00417571:	movl -208(%ebp), %ebx
0x00417577:	cmpl %eax, %ebx
0x00417579:	je 9
0x0041757b:	pushl %eax
0x0041757c:	call 0x0041d3a9
0x00417581:	addl %esp, $0x4<UINT8>
0x00417584:	movl %eax, -136(%ebp)
0x0041758a:	movl -88(%ebp), %ebx
0x0041758d:	movl -84(%ebp), %ebx
0x00417590:	movl -80(%ebp), %ebx
0x00417593:	cmpl %eax, %ebx
0x00417595:	je 9
0x00417597:	pushl %eax
0x00417598:	call 0x0041d3a9
0x0041759d:	addl %esp, $0x4<UINT8>
0x004175a0:	movl -136(%ebp), %ebx
0x004175a6:	movl -132(%ebp), %ebx
0x004175ac:	movl -128(%ebp), %ebx
0x004175af:	movl -120(%ebp), %ebx
0x004175b2:	movl -116(%ebp), %ebx
0x004175b5:	movl -112(%ebp), %ebx
0x004175b8:	movb -4(%ebp), $0x7<UINT8>
0x004175bc:	movb 0x448860, %bl
0x004175c2:	movl %ebx, $0x30<UINT32>
0x004175c7:	leal %esi, -120(%ebp)
0x004175ca:	movl -108(%ebp), $0x1<UINT32>
0x004175d1:	call 0x004034c0
0x004175d6:	movl %edx, -120(%ebp)
0x004175d9:	movl %eax, -116(%ebp)
0x004175dc:	pushl $0x20<UINT8>
0x004175de:	addl %eax, %edx
0x004175e0:	pushl $0x44097c<UINT32>
0x004175e5:	pushl %eax
0x004175e6:	call 0x00420c20
0x004175eb:	addl -116(%ebp), $0x20<UINT8>
0x004175ef:	addl %esp, $0xc<UINT8>
0x004175f2:	xorl %eax, %eax
0x004175f4:	movl -56(%ebp), %eax
0x004175f7:	movl -52(%ebp), %eax
0x004175fa:	movl -48(%ebp), %eax
0x004175fd:	movb -4(%ebp), $0x9<UINT8>
0x00417601:	leal %ebx, 0x28(%eax)
0x00417604:	leal %esi, -56(%ebp)
0x00417607:	movl -44(%ebp), $0x1<UINT32>
0x0041760e:	movb 0x448860, %al
0x00417613:	call 0x004034c0
0x00417618:	movl %edx, -52(%ebp)
0x0041761b:	movl %ecx, -56(%ebp)
0x0041761e:	leal %eax, (%edx,%ecx)
0x00417621:	movl %ecx, 0x440120
0x00417627:	movl (%eax), %ecx
0x00417629:	movl %edx, 0x440124
0x0041762f:	movl 0x4(%eax), %edx
0x00417632:	movl %ecx, 0x440128
0x00417638:	movl 0x8(%eax), %ecx
0x0041763b:	movl %edx, 0x44012c
0x00417641:	movl 0xc(%eax), %edx
0x00417644:	movl %ecx, 0x440130
0x0041764a:	movl 0x10(%eax), %ecx
0x0041764d:	movl %edx, 0x440134
0x00417653:	movl 0x14(%eax), %edx
0x00417656:	addl -52(%ebp), $0x18<UINT8>
0x0041765a:	pushl $0x1<UINT8>
0x0041765c:	xorl %ebx, %ebx
0x0041765e:	pushl %ebx
0x0041765f:	leal %eax, -120(%ebp)
0x00417662:	pushl %eax
0x00417663:	leal %ecx, -168(%ebp)
0x00417669:	pushl %ecx
0x0041766a:	movb -4(%ebp), $0xa<UINT8>
0x0041766e:	call 0x0041b070
0x00417673:	movl -28(%ebp), %eax
0x00417676:	pushl $0x1<UINT8>
0x00417678:	pushl %ebx
0x00417679:	movl %edx, %esi
0x0041767b:	pushl %edx
0x0041767c:	leal %eax, -200(%ebp)
0x00417682:	pushl %eax
0x00417683:	movb -4(%ebp), $0xb<UINT8>
0x00417687:	call 0x0041b070
0x0041768c:	movl %esi, %eax
0x0041768e:	movl %eax, (%esi)
0x00417690:	addl %esp, $0x20<UINT8>
0x00417693:	pushl %eax
0x00417694:	call GetModuleHandleA@kernel32.dll
0x00417696:	cmpl %eax, %ebx
0x00417698:	jne 0x004176a3
0x004176a3:	movl %ecx, -28(%ebp)
0x004176a6:	movl %ecx, (%ecx)
0x004176a8:	pushl %ecx
0x004176a9:	pushl %eax
0x004176aa:	call GetProcAddress@kernel32.dll
0x004176b0:	movl -32(%ebp), %eax
0x004176b3:	cmpl %eax, %ebx
0x004176b5:	jne 0x004176bd
0x004176bd:	movl %eax, -200(%ebp)
0x004176c3:	cmpl %eax, %ebx
0x004176c5:	je 9
0x004176c7:	pushl %eax
0x004176c8:	call 0x0041d3a9
0x004176cd:	addl %esp, $0x4<UINT8>
0x004176d0:	movl %eax, -168(%ebp)
0x004176d6:	movl -200(%ebp), %ebx
0x004176dc:	movl -196(%ebp), %ebx
0x004176e2:	movl -192(%ebp), %ebx
0x004176e8:	cmpl %eax, %ebx
0x004176ea:	je 9
0x004176ec:	pushl %eax
0x004176ed:	call 0x0041d3a9
0x004176f2:	addl %esp, $0x4<UINT8>
0x004176f5:	movl %eax, -56(%ebp)
0x004176f8:	movl -168(%ebp), %ebx
0x004176fe:	movl -164(%ebp), %ebx
0x00417704:	movl -160(%ebp), %ebx
0x0041770a:	cmpl %eax, %ebx
0x0041770c:	je 9
0x0041770e:	pushl %eax
0x0041770f:	call 0x0041d3a9
0x00417714:	addl %esp, $0x4<UINT8>
0x00417717:	movl %eax, -120(%ebp)
0x0041771a:	movl -56(%ebp), %ebx
0x0041771d:	movl -52(%ebp), %ebx
0x00417720:	movl -48(%ebp), %ebx
0x00417723:	cmpl %eax, %ebx
0x00417725:	je 9
0x00417727:	pushl %eax
0x00417728:	call 0x0041d3a9
0x0041772d:	addl %esp, $0x4<UINT8>
0x00417730:	movl -120(%ebp), %ebx
0x00417733:	movl -116(%ebp), %ebx
0x00417736:	movl -112(%ebp), %ebx
0x00417739:	movl -152(%ebp), %ebx
0x0041773f:	movl -148(%ebp), %ebx
0x00417745:	movl -144(%ebp), %ebx
0x0041774b:	movb -4(%ebp), $0xc<UINT8>
0x0041774f:	movb 0x448860, %bl
0x00417755:	movl %ebx, $0x28<UINT32>
0x0041775a:	leal %esi, -152(%ebp)
0x00417760:	movl -140(%ebp), $0x1<UINT32>
0x0041776a:	call 0x004034c0
0x0041776f:	movl %edx, -152(%ebp)
0x00417775:	movl %eax, -148(%ebp)
0x0041777b:	movl %ecx, 0x4409a0
0x00417781:	addl %eax, %edx
0x00417783:	movl (%eax), %ecx
0x00417785:	movl %edx, 0x4409a4
0x0041778b:	movl 0x4(%eax), %edx
0x0041778e:	movl %ecx, 0x4409a8
0x00417794:	movl 0x8(%eax), %ecx
0x00417797:	movl %edx, 0x4409ac
0x0041779d:	movl 0xc(%eax), %edx
0x004177a0:	movl %ecx, 0x4409b0
0x004177a6:	movl 0x10(%eax), %ecx
0x004177a9:	movl %edx, 0x4409b4
0x004177af:	movl 0x14(%eax), %edx
0x004177b2:	addl -148(%ebp), $0x18<UINT8>
0x004177b9:	xorl %eax, %eax
0x004177bb:	movl -104(%ebp), %eax
0x004177be:	movl -100(%ebp), %eax
0x004177c1:	movl -96(%ebp), %eax
0x004177c4:	movb -4(%ebp), $0xe<UINT8>
0x004177c8:	leal %esi, -104(%ebp)
0x004177cb:	movl -92(%ebp), $0x1<UINT32>
0x004177d2:	movb 0x448860, %al
0x004177d7:	call 0x004034c0
0x004177dc:	movl %ecx, -100(%ebp)
0x004177df:	movl %eax, -104(%ebp)
0x004177e2:	movl %edx, 0x440120
0x004177e8:	addl %eax, %ecx
0x004177ea:	movl (%eax), %edx
0x004177ec:	movl %ecx, 0x440124
0x004177f2:	movl 0x4(%eax), %ecx
0x004177f5:	movl %edx, 0x440128
0x004177fb:	movl 0x8(%eax), %edx
0x004177fe:	movl %ecx, 0x44012c
0x00417804:	movl 0xc(%eax), %ecx
0x00417807:	movl %edx, 0x440130
0x0041780d:	movl 0x10(%eax), %edx
0x00417810:	movl %ecx, 0x440134
0x00417816:	movl 0x14(%eax), %ecx
0x00417819:	addl -100(%ebp), $0x18<UINT8>
0x0041781d:	pushl $0x1<UINT8>
0x0041781f:	pushl $0x0<UINT8>
0x00417821:	leal %edx, -152(%ebp)
0x00417827:	pushl %edx
0x00417828:	leal %eax, -232(%ebp)
0x0041782e:	pushl %eax
0x0041782f:	movb -4(%ebp), $0xf<UINT8>
0x00417833:	call 0x0041b070
0x00417838:	movl %ebx, %eax
0x0041783a:	pushl $0x1<UINT8>
0x0041783c:	pushl $0x0<UINT8>
0x0041783e:	movl %ecx, %esi
0x00417840:	pushl %ecx
0x00417841:	leal %edx, -248(%ebp)
0x00417847:	pushl %edx
0x00417848:	movb -4(%ebp), $0x10<UINT8>
0x0041784c:	call 0x0041b070
0x00417851:	movl %esi, %eax
0x00417853:	movl %eax, (%esi)
0x00417855:	addl %esp, $0x20<UINT8>
0x00417858:	pushl %eax
0x00417859:	call GetModuleHandleA@kernel32.dll
0x0041785b:	xorl %edi, %edi
0x0041785d:	cmpl %eax, %edi
0x0041785f:	jne 0x0041786a
0x0041786a:	movl %ebx, (%ebx)
0x0041786c:	pushl %ebx
0x0041786d:	pushl %eax
0x0041786e:	call GetProcAddress@kernel32.dll
0x00417874:	movl -28(%ebp), %eax
0x00417877:	cmpl %eax, %edi
0x00417879:	jne 0x00417881
0x00417881:	movl %eax, -248(%ebp)
0x00417887:	cmpl %eax, %edi
0x00417889:	je 9
0x0041788b:	pushl %eax
0x0041788c:	call 0x0041d3a9
0x00417891:	addl %esp, $0x4<UINT8>
0x00417894:	movl %eax, -232(%ebp)
0x0041789a:	movl -248(%ebp), %edi
0x004178a0:	movl -244(%ebp), %edi
0x004178a6:	movl -240(%ebp), %edi
0x004178ac:	cmpl %eax, %edi
0x004178ae:	je 9
0x004178b0:	pushl %eax
0x004178b1:	call 0x0041d3a9
0x004178b6:	addl %esp, $0x4<UINT8>
0x004178b9:	movl %eax, -104(%ebp)
0x004178bc:	movl -232(%ebp), %edi
0x004178c2:	movl -228(%ebp), %edi
0x004178c8:	movl -224(%ebp), %edi
0x004178ce:	cmpl %eax, %edi
0x004178d0:	je 9
0x004178d2:	pushl %eax
0x004178d3:	call 0x0041d3a9
0x004178d8:	addl %esp, $0x4<UINT8>
0x004178db:	movb -4(%ebp), $0x1<UINT8>
0x004178df:	movl %eax, -152(%ebp)
0x004178e5:	movl -104(%ebp), %edi
0x004178e8:	movl -100(%ebp), %edi
0x004178eb:	movl -96(%ebp), %edi
0x004178ee:	cmpl %eax, %edi
0x004178f0:	je 9
0x004178f2:	pushl %eax
0x004178f3:	call 0x0041d3a9
0x004178f8:	addl %esp, $0x4<UINT8>
0x004178fb:	movl %eax, 0xc(%ebp)
0x004178fe:	movl %eax, (%eax)
0x00417900:	leal %ecx, -24(%ebp)
0x00417903:	pushl %ecx
0x00417904:	pushl $0x20119<UINT32>
0x00417909:	pushl %edi
0x0041790a:	pushl %eax
0x0041790b:	pushl $0x80000002<UINT32>
0x00417910:	movl -152(%ebp), %edi
0x00417916:	movl -148(%ebp), %edi
0x0041791c:	movl -144(%ebp), %edi
0x00417922:	call RegOpenKeyExA@AdvApi32.dll
RegOpenKeyExA@AdvApi32.dll: API Node	
0x00417925:	cmpl %eax, %edi
0x00417927:	je 0x0041793d
0x0041793d:	movl %ebx, 0x10(%ebp)
0x00417940:	movl %eax, (%ebx)
0x00417942:	movl %esi, -32(%ebp)
0x00417945:	leal %edx, -20(%ebp)
0x00417948:	pushl %edx
0x00417949:	movl %edx, -24(%ebp)
0x0041794c:	pushl %edi
0x0041794d:	leal %ecx, -36(%ebp)
0x00417950:	pushl %ecx
0x00417951:	pushl %edi
0x00417952:	pushl %eax
0x00417953:	pushl %edx
0x00417954:	movl -36(%ebp), %edi
0x00417957:	movl -20(%ebp), %edi
0x0041795a:	call RegQueryValueExA@AdvApi32.dll
RegQueryValueExA@AdvApi32.dll: API Node	
0x0041795c:	cmpl %eax, %edi
0x0041795e:	je 0x0041797b
0x0041797b:	movl %eax, -36(%ebp)
0x0041797e:	cmpl %eax, $0x4<UINT8>
0x00417981:	je 223
0x00417987:	cmpl %eax, $0x3<UINT8>
0x0041798a:	movl %eax, -20(%ebp)
0x0041798d:	movl %ebx, %eax
0x0041798f:	pushl %eax
0x00417990:	jne 0x00417a04
0x00417a04:	call 0x0041cfd3
0x00417a09:	movl %esi, %eax
0x00417a0b:	addl %esp, $0x4<UINT8>
0x00417a0e:	cmpl %esi, %edi
0x00417a10:	je 13
0x00417a12:	pushl %ebx
0x00417a13:	pushl %edi
0x00417a14:	pushl %esi
0x00417a15:	call 0x0041f180
0x00417a1a:	addl %esp, $0xc<UINT8>
0x00417a1d:	movl %edi, %esi
0x00417a1f:	movl %eax, 0x10(%ebp)
0x00417a22:	movl %eax, (%eax)
0x00417a24:	leal %ecx, -20(%ebp)
0x00417a27:	pushl %ecx
0x00417a28:	pushl %edi
0x00417a29:	leal %edx, -36(%ebp)
0x00417a2c:	pushl %edx
0x00417a2d:	pushl $0x0<UINT8>
0x00417a2f:	pushl %eax
0x00417a30:	movl %eax, -24(%ebp)
0x00417a33:	pushl %eax
0x00417a34:	call RegQueryValueExA@AdvApi32.dll
0x00417a37:	testl %eax, %eax
0x00417a39:	jne 30
0x00417a3b:	movl %ecx, -20(%ebp)
0x00417a3e:	pushl %ecx
0x00417a3f:	leal %eax, (%edi,%ecx)
0x00417a42:	movl %ecx, %esp
0x00417a44:	movl -40(%ebp), %esp
0x00417a47:	pushl %ecx
0x00417a48:	movl (%ecx), %eax
0x00417a4a:	movl %eax, %esp
0x00417a4c:	leal %esi, -72(%ebp)
0x00417a4f:	movl -40(%ebp), %esp
0x00417a52:	movl (%eax), %edi
0x00417a54:	call 0x00417b40
0x00417b40:	pushl %ebp
0x00417b41:	movl %ebp, %esp
0x00417b43:	movl %edx, 0x8(%ebp)
0x00417b46:	pushl %ebx
0x00417b47:	pushl %edi
0x00417b48:	movl %edi, 0xc(%ebp)
0x00417b4b:	cmpl %edx, %edi
0x00417b4d:	jae 102
0x00417b4f:	movl %eax, (%esi)
0x00417b51:	cmpl %edx, %eax
0x00417b53:	jb 15
0x00417b55:	movl %ecx, 0x4(%esi)
0x00417b58:	addl %ecx, %eax
0x00417b5a:	cmpl %edx, %ecx
0x00417b5c:	ja 0x00417b62
0x00417b62:	cmpl %edx, %eax
0x00417b64:	sbbl %ecx, %ecx
0x00417b66:	andl %ecx, $0xfffffffe<UINT8>
0x00417b69:	incl %ecx
0x00417b6a:	cmpl %edi, %eax
0x00417b6c:	jb 15
0x00417b6e:	movl %ebx, 0x4(%esi)
0x00417b71:	addl %ebx, %eax
0x00417b73:	cmpl %edi, %ebx
0x00417b75:	ja 0x00417b7b
0x00417b7b:	cmpl %edi, %eax
0x00417b7d:	sbbl %eax, %eax
0x00417b7f:	andl %eax, $0xfffffffe<UINT8>
0x00417b82:	incl %eax
0x00417b83:	cmpl %ecx, %eax
0x00417b85:	movl %eax, %esi
0x00417b87:	jne 46
0x00417b89:	subl %edi, %edx
0x00417b8b:	addl %edi, 0x4(%esi)
0x00417b8e:	call 0x00403420
0x0040345b:	leal %ebx, 0x10(%edi)
0x0040345e:	call 0x004034c0
0x00417b93:	movl %ecx, (%esi)
0x00417b95:	movl %eax, 0xc(%ebp)
0x00417b98:	addl %ecx, 0x4(%esi)
0x00417b9b:	cmpl 0x8(%ebp), %eax
0x00417b9e:	ja 21
0x00417ba0:	movl %edx, %eax
0x00417ba2:	decl %eax
0x00417ba3:	movl 0xc(%ebp), %eax
0x00417ba6:	movb %dl, (%edx)
0x00417ba8:	movl %eax, %ecx
0x00417baa:	movb (%eax), %dl
0x00417bac:	movl %eax, 0xc(%ebp)
0x00417baf:	decl %ecx
0x00417bb0:	cmpl 0x8(%ebp), %eax
0x00417bb3:	jbe 0x00417ba0
0x00417bb5:	movl %eax, %esi
0x00417bb7:	popl %edi
0x00417bb8:	popl %ebx
0x00417bb9:	popl %ebp
0x00417bba:	ret $0x8<UINT16>

0x00417a59:	pushl %edi
0x00417a5a:	call 0x0041d3a9
0x00417a5f:	addl %esp, $0x4<UINT8>
0x00417a62:	xorl %edi, %edi
0x00417a64:	jmp 0x00417ac1
0x00417ac1:	movl %eax, -68(%ebp)
0x00417ac4:	cmpl %eax, %edi
0x00417ac6:	je 59
0x00417ac8:	movl %ecx, -72(%ebp)
0x00417acb:	cmpb -1(%eax,%ecx), $0x0<UINT8>
0x00417ad0:	jne 19
0x00417ad2:	leal %ecx, -1(%eax)
0x00417ad5:	movl %edx, $0x1<UINT32>
0x00417ada:	leal %eax, -72(%ebp)
0x00417add:	call 0x004033c0
0x00417ae2:	movl %eax, -68(%ebp)
0x00417ae5:	cmpl %eax, %edi
0x00417ae7:	je 26
0x00417ae9:	movl %edx, -72(%ebp)
0x00417aec:	cmpb -1(%eax,%edx), $0x0<UINT8>
0x00417af1:	jne 16
0x00417af3:	leal %ecx, -1(%eax)
0x00417af6:	movl %edx, $0x1<UINT32>
0x00417afb:	leal %eax, -72(%ebp)
0x00417afe:	call 0x004033c0
0x00417b03:	movl %eax, -24(%ebp)
0x00417b06:	pushl %eax
0x00417b07:	call RegCloseKey@AdvApi32.dll
RegCloseKey@AdvApi32.dll: API Node	
0x00417b0a:	movl %esi, 0x8(%ebp)
0x00417b0d:	leal %ecx, -72(%ebp)
0x00417b10:	pushl %ecx
0x00417b11:	pushl %esi
0x00417b12:	call 0x00406bd0
0x00417b17:	movl %eax, -72(%ebp)
0x00417b1a:	cmpl %eax, %edi
0x00417b1c:	je 9
0x00417b1e:	pushl %eax
0x00417b1f:	call 0x0041d3a9
0x00417b24:	addl %esp, $0x4<UINT8>
0x00417b27:	movl %eax, %esi
0x00417b29:	movl %ecx, -12(%ebp)
0x00417b2c:	movl %fs:0, %ecx
0x00417b33:	popl %ecx
0x00417b34:	popl %edi
0x00417b35:	popl %esi
0x00417b36:	popl %ebx
0x00417b37:	movl %esp, %ebp
0x00417b39:	popl %ebp
0x00417b3a:	ret

0x00417ca8:	addl %esp, $0xc<UINT8>
0x00417cab:	xorl %ebx, %ebx
0x00417cad:	movb -4(%ebp), $0x5<UINT8>
0x00417cb1:	cmpl -64(%ebp), %ebx
0x00417cb4:	je 52
0x00417cb6:	leal %eax, -68(%ebp)
0x00417cb9:	movl %edx, %edi
0x00417cbb:	pushl %eax
0x00417cbc:	xorl %ecx, %ecx
0x00417cbe:	leal %edi, -84(%ebp)
0x00417cc1:	call 0x00406a80
0x00417cc6:	movb -4(%ebp), $0x6<UINT8>
0x00417cca:	movl %ecx, 0x4(%eax)
0x00417ccd:	movl %edx, (%eax)
0x00417ccf:	movl %eax, %ecx
0x00417cd1:	movl %ecx, 0x8(%ebp)
0x00417cd4:	pushl %edx
0x00417cd5:	call 0x00406e50
0x00417cda:	movl %eax, -84(%ebp)
0x00417cdd:	cmpl %eax, %ebx
0x00417cdf:	je 9
0x00417ce1:	pushl %eax
0x00417ce2:	call 0x0041d3a9
0x00417ce7:	addl %esp, $0x4<UINT8>
0x00417cea:	movl %eax, -68(%ebp)
0x00417ced:	cmpl %eax, %ebx
0x00417cef:	je 9
0x00417cf1:	pushl %eax
0x00417cf2:	call 0x0041d3a9
0x00417cf7:	addl %esp, $0x4<UINT8>
0x00417cfa:	movl -68(%ebp), %ebx
0x00417cfd:	movl -64(%ebp), %ebx
0x00417d00:	movl -60(%ebp), %ebx
0x00417d03:	cmpl %esi, %ebx
0x00417d05:	je 9
0x00417d07:	pushl %esi
0x00417d08:	call 0x0041d3a9
0x00417d0d:	addl %esp, $0x4<UINT8>
0x00417d10:	movl %eax, -36(%ebp)
0x00417d13:	cmpl %eax, %ebx
0x00417d15:	je 9
0x00417d17:	pushl %eax
0x00417d18:	call 0x0041d3a9
0x00417d1d:	addl %esp, $0x4<UINT8>
0x00417d20:	movl %eax, 0x8(%ebp)
0x00417d23:	movl %ecx, -12(%ebp)
0x00417d26:	movl %fs:0, %ecx
0x00417d2d:	popl %ecx
0x00417d2e:	popl %edi
0x00417d2f:	popl %esi
0x00417d30:	popl %ebx
0x00417d31:	movl %esp, %ebp
0x00417d33:	popl %ebp
0x00417d34:	ret

0x004083f3:	addl %esp, $0x4<UINT8>
0x004083f6:	movl 0x20(%esp), $0x0<UINT32>
0x004083fe:	movl %ecx, (%eax)
0x00408400:	movl %eax, 0x4(%eax)
0x00408403:	pushl %ecx
0x00408404:	movl %ecx, 0x8(%ebp)
0x00408407:	addl %ecx, $0x90<UINT32>
0x0040840d:	call 0x00406e50
0x00408412:	movl %eax, 0x4(%esp)
0x00408416:	testl %eax, %eax
0x00408418:	je 9
0x0040841a:	pushl %eax
0x0040841b:	call 0x0041d3a9
0x00408420:	addl %esp, $0x4<UINT8>
0x00408423:	movl %ecx, 0x18(%esp)
0x00408427:	movl %fs:0, %ecx
0x0040842e:	popl %ecx
0x0040842f:	movl %esp, %ebp
0x00408431:	popl %ebp
0x00408432:	ret $0x4<UINT16>

0x00407046:	movl %eax, %esi
0x00407048:	movl %ecx, -12(%ebp)
0x0040704b:	movl %fs:0, %ecx
0x00407052:	popl %ecx
0x00407053:	popl %esi
0x00407054:	popl %ebx
0x00407055:	movl %esp, %ebp
0x00407057:	popl %ebp
0x00407058:	ret

0x004057d1:	pushl $0x439500<UINT32>
0x004057d6:	call 0x0041dcb8
0x004057db:	addl %esp, $0x4<UINT8>
0x00408928:	movl -16(%ebp), %eax
0x0040892b:	pushl $0x1<UINT8>
0x0040892d:	pushl %ebx
0x0040892e:	movl %edx, %esi
0x00408930:	pushl %edx
0x00408931:	leal %eax, -64(%ebp)
0x00408934:	pushl %eax
0x00408935:	movb -4(%ebp), $0x4<UINT8>
0x00408939:	call 0x0041b070
0x0040893e:	movl %edi, 0x43b01c
0x00408944:	movl %esi, %eax
0x00408946:	movl %eax, (%esi)
0x00408948:	addl %esp, $0x20<UINT8>
0x0040894b:	pushl %eax
0x0040894c:	call GetModuleHandleA@kernel32.dll
0x0040894e:	cmpl %eax, %ebx
0x00408950:	jne 0x0040895b
0x0040895b:	movl %ecx, -16(%ebp)
0x0040895e:	movl %ecx, (%ecx)
0x00408960:	pushl %ecx
0x00408961:	pushl %eax
0x00408962:	call GetProcAddress@kernel32.dll
0x00408968:	movl %esi, %eax
0x0040896a:	cmpl %esi, %ebx
0x0040896c:	jne 0x00408974
0x00408974:	movl %eax, -64(%ebp)
0x00408977:	cmpl %eax, %ebx
0x00408979:	je 9
0x0040897b:	pushl %eax
0x0040897c:	call 0x0041d3a9
0x00408981:	addl %esp, $0x4<UINT8>
0x00408984:	movl %eax, -80(%ebp)
0x00408987:	movl -64(%ebp), %ebx
0x0040898a:	movl -60(%ebp), %ebx
0x0040898d:	movl -56(%ebp), %ebx
0x00408990:	cmpl %eax, %ebx
0x00408992:	je 9
0x00408994:	pushl %eax
0x00408995:	call 0x0041d3a9
0x0040899a:	addl %esp, $0x4<UINT8>
0x0040899d:	movl %eax, -32(%ebp)
0x004089a0:	movl -80(%ebp), %ebx
0x004089a3:	movl -76(%ebp), %ebx
0x004089a6:	movl -72(%ebp), %ebx
0x004089a9:	cmpl %eax, %ebx
0x004089ab:	je 9
0x004089ad:	pushl %eax
0x004089ae:	call 0x0041d3a9
0x004089b3:	addl %esp, $0x4<UINT8>
0x004089b6:	movl -4(%ebp), $0xffffffff<UINT32>
0x004089bd:	movl %eax, -48(%ebp)
0x004089c0:	cmpl %eax, %ebx
0x004089c2:	je 9
0x004089c4:	pushl %eax
0x004089c5:	call 0x0041d3a9
0x004089ca:	addl %esp, $0x4<UINT8>
0x004089cd:	pushl $0x2<UINT8>
0x004089cf:	call SetErrorMode@Kernel32.dll
SetErrorMode@Kernel32.dll: API Node	
0x004089d1:	call 0x00419af0
0x00419af0:	pushl %ebp
0x00419af1:	movl %ebp, %esp
0x00419af3:	pushl $0xffffffff<UINT8>
0x00419af5:	pushl $0x431a38<UINT32>
0x00419afa:	movl %eax, %fs:0
0x00419b00:	pushl %eax
0x00419b01:	subl %esp, $0x84<UINT32>
0x00419b07:	pushl %ebx
0x00419b08:	pushl %esi
0x00419b09:	pushl %edi
0x00419b0a:	movl %eax, 0x44609c
0x00419b0f:	xorl %eax, %ebp
0x00419b11:	pushl %eax
0x00419b12:	leal %eax, -12(%ebp)
0x00419b15:	movl %fs:0, %eax
0x00419b1b:	xorl %eax, %eax
0x00419b1d:	movl %edi, $0x1<UINT32>
0x00419b22:	movl -20(%ebp), %edi
0x00419b25:	movl -16(%ebp), $0xffffffff<UINT32>
0x00419b2c:	movl -60(%ebp), %eax
0x00419b2f:	movl -56(%ebp), %eax
0x00419b32:	movl -52(%ebp), %eax
0x00419b35:	movl -4(%ebp), %eax
0x00419b38:	leal %ebx, 0x2f(%edi)
0x00419b3b:	leal %esi, -60(%ebp)
0x00419b3e:	movl -48(%ebp), %edi
0x00419b41:	movb 0x448860, %al
0x00419b46:	call 0x004034c0
0x00419b4b:	movl %eax, -60(%ebp)
0x00419b4e:	movl %esi, -56(%ebp)
0x00419b51:	pushl $0x20<UINT8>
0x00419b53:	leal %ecx, (%esi,%eax)
0x00419b56:	pushl $0x440c3c<UINT32>
0x00419b5b:	pushl %ecx
0x00419b5c:	call 0x00420c20
0x00419b61:	addl %esi, $0x20<UINT8>
0x00419b64:	addl %esp, $0xc<UINT8>
0x00419b67:	movl -56(%ebp), %esi
0x00419b6a:	xorl %eax, %eax
0x00419b6c:	movl -4(%ebp), %edi
0x00419b6f:	movl -44(%ebp), %eax
0x00419b72:	movl -40(%ebp), %eax
0x00419b75:	movl -36(%ebp), %eax
0x00419b78:	movb -4(%ebp), $0x2<UINT8>
0x00419b7c:	leal %ebx, 0x27(%edi)
0x00419b7f:	leal %esi, -44(%ebp)
0x00419b82:	movl -32(%ebp), %edi
0x00419b85:	movb 0x448860, %al
0x00419b8a:	call 0x004034c0
0x00419b8f:	movl %eax, -40(%ebp)
0x00419b92:	movl %edx, 0x440120
0x00419b98:	movl %ecx, -44(%ebp)
0x00419b9b:	movl (%eax,%ecx), %edx
0x00419b9e:	movl %edx, 0x440124
0x00419ba4:	movl 0x4(%eax,%ecx), %edx
0x00419ba8:	movl %edx, 0x440128
0x00419bae:	movl 0x8(%eax,%ecx), %edx
0x00419bb2:	movl %edx, 0x44012c
0x00419bb8:	movl 0xc(%eax,%ecx), %edx
0x00419bbc:	movl %edx, 0x440130
0x00419bc2:	movl 0x10(%eax,%ecx), %edx
0x00419bc6:	movl %edx, 0x440134
0x00419bcc:	movl 0x14(%eax,%ecx), %edx
0x00419bd0:	addl %eax, $0x18<UINT8>
0x00419bd3:	movl -40(%ebp), %eax
0x00419bd6:	pushl %edi
0x00419bd7:	xorl %ebx, %ebx
0x00419bd9:	pushl %ebx
0x00419bda:	leal %eax, -60(%ebp)
0x00419bdd:	pushl %eax
0x00419bde:	leal %ecx, -92(%ebp)
0x00419be1:	pushl %ecx
0x00419be2:	movb -4(%ebp), $0x3<UINT8>
0x00419be6:	call 0x0041b070
0x00419beb:	movl -24(%ebp), %eax
0x00419bee:	pushl %edi
0x00419bef:	pushl %ebx
0x00419bf0:	movl %edx, %esi
0x00419bf2:	pushl %edx
0x00419bf3:	leal %eax, -76(%ebp)
0x00419bf6:	pushl %eax
0x00419bf7:	movb -4(%ebp), $0x4<UINT8>
0x00419bfb:	call 0x0041b070
0x00419c00:	movl %esi, %eax
0x00419c02:	movl %eax, (%esi)
0x00419c04:	addl %esp, $0x20<UINT8>
0x00419c07:	pushl %eax
0x00419c08:	call GetModuleHandleA@kernel32.dll
0x00419c0e:	cmpl %eax, %ebx
0x00419c10:	jne 0x00419c1b
0x00419c1b:	movl %ecx, -24(%ebp)
0x00419c1e:	movl %ecx, (%ecx)
0x00419c20:	pushl %ecx
0x00419c21:	pushl %eax
0x00419c22:	call GetProcAddress@kernel32.dll
0x00419c28:	movl -28(%ebp), %eax
0x00419c2b:	cmpl %eax, %ebx
0x00419c2d:	jne 0x00419c35
0x00419c35:	movl %eax, -76(%ebp)
0x00419c38:	cmpl %eax, %ebx
0x00419c3a:	je 9
0x00419c3c:	pushl %eax
0x00419c3d:	call 0x0041d3a9
0x00419c42:	addl %esp, $0x4<UINT8>
0x00419c45:	movl %eax, -92(%ebp)
0x00419c48:	movl -76(%ebp), %ebx
0x00419c4b:	movl -72(%ebp), %ebx
0x00419c4e:	movl -68(%ebp), %ebx
0x00419c51:	cmpl %eax, %ebx
0x00419c53:	je 9
0x00419c55:	pushl %eax
0x00419c56:	call 0x0041d3a9
0x00419c5b:	addl %esp, $0x4<UINT8>
0x00419c5e:	movl %eax, -44(%ebp)
0x00419c61:	movl -92(%ebp), %ebx
0x00419c64:	movl -88(%ebp), %ebx
0x00419c67:	movl -84(%ebp), %ebx
0x00419c6a:	cmpl %eax, %ebx
0x00419c6c:	je 9
0x00419c6e:	pushl %eax
0x00419c6f:	call 0x0041d3a9
0x00419c74:	addl %esp, $0x4<UINT8>
0x00419c77:	movl %eax, -60(%ebp)
0x00419c7a:	cmpl %eax, %ebx
0x00419c7c:	je 9
0x00419c7e:	pushl %eax
0x00419c7f:	call 0x0041d3a9
0x00419c84:	addl %esp, $0x4<UINT8>
0x00419c87:	movl -44(%ebp), %ebx
0x00419c8a:	movl -40(%ebp), %ebx
0x00419c8d:	movl -36(%ebp), %ebx
0x00419c90:	movl -4(%ebp), $0x5<UINT32>
0x00419c97:	movb 0x448860, %bl
0x00419c9d:	movl %ebx, $0x30<UINT32>
0x00419ca2:	leal %esi, -44(%ebp)
0x00419ca5:	movl -32(%ebp), %edi
0x00419ca8:	call 0x004034c0
0x00419cad:	movl %edx, -44(%ebp)
0x00419cb0:	movl %esi, -40(%ebp)
0x00419cb3:	pushl $0x20<UINT8>
0x00419cb5:	leal %eax, (%esi,%edx)
0x00419cb8:	pushl $0x440c60<UINT32>
0x00419cbd:	pushl %eax
0x00419cbe:	call 0x00420c20
0x00419cc3:	addl %esi, $0x20<UINT8>
0x00419cc6:	addl %esp, $0xc<UINT8>
0x00419cc9:	movl -40(%ebp), %esi
0x00419ccc:	xorl %eax, %eax
0x00419cce:	movl -4(%ebp), $0x6<UINT32>
0x00419cd5:	movl -60(%ebp), %eax
0x00419cd8:	movl -56(%ebp), %eax
0x00419cdb:	movl -52(%ebp), %eax
0x00419cde:	movb -4(%ebp), $0x7<UINT8>
0x00419ce2:	leal %ebx, 0x28(%eax)
0x00419ce5:	leal %esi, -60(%ebp)
0x00419ce8:	movl -48(%ebp), %edi
0x00419ceb:	movb 0x448860, %al
0x00419cf0:	call 0x004034c0
0x00419cf5:	movl %eax, -56(%ebp)
0x00419cf8:	movl %edx, 0x440120
0x00419cfe:	movl %ecx, -60(%ebp)
0x00419d01:	movl (%eax,%ecx), %edx
0x00419d04:	movl %edx, 0x440124
0x00419d0a:	movl 0x4(%eax,%ecx), %edx
0x00419d0e:	movl %edx, 0x440128
0x00419d14:	movl 0x8(%eax,%ecx), %edx
0x00419d18:	movl %edx, 0x44012c
0x00419d1e:	movl 0xc(%eax,%ecx), %edx
0x00419d22:	movl %edx, 0x440130
0x00419d28:	movl 0x10(%eax,%ecx), %edx
0x00419d2c:	movl %edx, 0x440134
0x00419d32:	movl 0x14(%eax,%ecx), %edx
0x00419d36:	addl %eax, $0x18<UINT8>
0x00419d39:	movl -56(%ebp), %eax
0x00419d3c:	pushl %edi
0x00419d3d:	xorl %ebx, %ebx
0x00419d3f:	pushl %ebx
0x00419d40:	leal %eax, -44(%ebp)
0x00419d43:	pushl %eax
0x00419d44:	leal %ecx, -124(%ebp)
0x00419d47:	pushl %ecx
0x00419d48:	movb -4(%ebp), $0x8<UINT8>
0x00419d4c:	call 0x0041b070
0x00419d51:	movl -24(%ebp), %eax
0x00419d54:	pushl %edi
0x00419d55:	pushl %ebx
0x00419d56:	movl %edx, %esi
0x00419d58:	pushl %edx
0x00419d59:	leal %eax, -108(%ebp)
0x00419d5c:	pushl %eax
0x00419d5d:	movb -4(%ebp), $0x9<UINT8>
0x00419d61:	call 0x0041b070
0x00419d66:	movl %esi, %eax
0x00419d68:	movl %eax, (%esi)
0x00419d6a:	addl %esp, $0x20<UINT8>
0x00419d6d:	pushl %eax
0x00419d6e:	call GetModuleHandleA@kernel32.dll
0x00419d74:	cmpl %eax, %ebx
0x00419d76:	jne 0x00419d81
0x00419d81:	movl %ecx, -24(%ebp)
0x00419d84:	movl %ecx, (%ecx)
0x00419d86:	pushl %ecx
0x00419d87:	pushl %eax
0x00419d88:	call GetProcAddress@kernel32.dll
0x00419d8e:	movl %esi, %eax
0x00419d90:	cmpl %esi, %ebx
0x00419d92:	jne 0x00419d9a
0x00419d9a:	movl %eax, -108(%ebp)
0x00419d9d:	cmpl %eax, %ebx
0x00419d9f:	je 9
0x00419da1:	pushl %eax
0x00419da2:	call 0x0041d3a9
0x00419da7:	addl %esp, $0x4<UINT8>
0x00419daa:	movl %eax, -124(%ebp)
0x00419dad:	movl -108(%ebp), %ebx
0x00419db0:	movl -104(%ebp), %ebx
0x00419db3:	movl -100(%ebp), %ebx
0x00419db6:	cmpl %eax, %ebx
0x00419db8:	je 9
0x00419dba:	pushl %eax
0x00419dbb:	call 0x0041d3a9
0x00419dc0:	addl %esp, $0x4<UINT8>
0x00419dc3:	movl %eax, -60(%ebp)
0x00419dc6:	movl -124(%ebp), %ebx
0x00419dc9:	movl -120(%ebp), %ebx
0x00419dcc:	movl -116(%ebp), %ebx
0x00419dcf:	cmpl %eax, %ebx
0x00419dd1:	je 9
0x00419dd3:	pushl %eax
0x00419dd4:	call 0x0041d3a9
0x00419dd9:	addl %esp, $0x4<UINT8>
0x00419ddc:	movl -4(%ebp), $0xffffffff<UINT32>
0x00419de3:	movl %eax, -44(%ebp)
0x00419de6:	cmpl %eax, %ebx
0x00419de8:	je 9
0x00419dea:	pushl %eax
0x00419deb:	call 0x0041d3a9
0x00419df0:	addl %esp, $0x4<UINT8>
0x00419df3:	leal %edx, -16(%ebp)
0x00419df6:	pushl %edx
0x00419df7:	pushl $0x28<UINT8>
0x00419df9:	call GetCurrentProcess@kernel32.dll
GetCurrentProcess@kernel32.dll: API Node	
0x00419dff:	pushl %eax
0x00419e00:	call OpenProcessToken@AdvApi32.dll
OpenProcessToken@AdvApi32.dll: API Node	
0x00419e03:	testl %eax, %eax
0x00419e05:	jne 0x00419e0a
0x00419e0a:	leal %eax, -136(%ebp)
0x00419e10:	pushl %eax
0x00419e11:	pushl $0x440c84<UINT32>
0x00419e16:	pushl %ebx
0x00419e17:	movl -140(%ebp), %edi
0x00419e1d:	movl -128(%ebp), $0x2<UINT32>
0x00419e24:	call LookupPrivilegeValueA@AdvApi32.dll
LookupPrivilegeValueA@AdvApi32.dll: API Node	
0x00419e26:	movl -44(%ebp), %ebx
0x00419e29:	movl -40(%ebp), %ebx
0x00419e2c:	movl -36(%ebp), %ebx
0x00419e2f:	movl -4(%ebp), $0xa<UINT32>
0x00419e36:	movb 0x448860, %bl
0x00419e3c:	movl %ebx, $0x30<UINT32>
0x00419e41:	leal %esi, -44(%ebp)
0x00419e44:	movl -32(%ebp), %edi
0x00419e47:	call 0x004034c0
0x00419e4c:	movl %ecx, -44(%ebp)
0x00419e4f:	movl %esi, -40(%ebp)
0x00419e52:	pushl $0x20<UINT8>
0x00419e54:	leal %edx, (%esi,%ecx)
0x00419e57:	pushl $0x440c98<UINT32>
0x00419e5c:	pushl %edx
0x00419e5d:	call 0x00420c20
0x00419e62:	addl %esi, $0x20<UINT8>
0x00419e65:	addl %esp, $0xc<UINT8>
0x00419e68:	movl -40(%ebp), %esi
0x00419e6b:	xorl %eax, %eax
0x00419e6d:	movl -4(%ebp), $0xb<UINT32>
0x00419e74:	movl -60(%ebp), %eax
0x00419e77:	movl -56(%ebp), %eax
0x00419e7a:	movl -52(%ebp), %eax
0x00419e7d:	movb -4(%ebp), $0xc<UINT8>
0x00419e81:	leal %ebx, 0x28(%eax)
0x00419e84:	leal %esi, -60(%ebp)
0x00419e87:	movl -48(%ebp), %edi
0x00419e8a:	movb 0x448860, %al
0x00419e8f:	call 0x004034c0
0x00419e94:	movl %eax, -56(%ebp)
0x00419e97:	movl %edx, 0x440120
0x00419e9d:	movl %ecx, -60(%ebp)
0x00419ea0:	movl (%eax,%ecx), %edx
0x00419ea3:	movl %edx, 0x440124
0x00419ea9:	movl 0x4(%eax,%ecx), %edx
0x00419ead:	movl %edx, 0x440128
0x00419eb3:	movl 0x8(%eax,%ecx), %edx
0x00419eb7:	movl %edx, 0x44012c
0x00419ebd:	movl 0xc(%eax,%ecx), %edx
0x00419ec1:	movl %edx, 0x440130
0x00419ec7:	movl 0x10(%eax,%ecx), %edx
0x00419ecb:	movl %edx, 0x440134
0x00419ed1:	movl 0x14(%eax,%ecx), %edx
0x00419ed5:	addl %eax, $0x18<UINT8>
0x00419ed8:	movl -56(%ebp), %eax
0x00419edb:	pushl %edi
0x00419edc:	pushl $0x0<UINT8>
0x00419ede:	leal %eax, -44(%ebp)
0x00419ee1:	pushl %eax
0x00419ee2:	leal %ecx, -108(%ebp)
0x00419ee5:	pushl %ecx
0x00419ee6:	movb -4(%ebp), $0xd<UINT8>
0x00419eea:	call 0x0041b070
0x00419eef:	movl %ebx, %eax
0x00419ef1:	pushl %edi
0x00419ef2:	pushl $0x0<UINT8>
0x00419ef4:	movl %edx, %esi
0x00419ef6:	pushl %edx
0x00419ef7:	leal %eax, -124(%ebp)
0x00419efa:	pushl %eax
0x00419efb:	movb -4(%ebp), $0xe<UINT8>
0x00419eff:	call 0x0041b070
0x00419f04:	movl %esi, %eax
0x00419f06:	movl %eax, (%esi)
0x00419f08:	addl %esp, $0x20<UINT8>
0x00419f0b:	pushl %eax
0x00419f0c:	call GetModuleHandleA@kernel32.dll
0x00419f12:	xorl %edi, %edi
0x00419f14:	cmpl %eax, %edi
0x00419f16:	jne 0x00419f21
0x00419f21:	movl %ebx, (%ebx)
0x00419f23:	pushl %ebx
0x00419f24:	pushl %eax
0x00419f25:	call GetProcAddress@kernel32.dll
0x00419f2b:	movl %esi, %eax
0x00419f2d:	cmpl %esi, %edi
0x00419f2f:	jne 0x00419f37
0x00419f37:	movl %eax, -124(%ebp)
0x00419f3a:	cmpl %eax, %edi
0x00419f3c:	je 9
0x00419f3e:	pushl %eax
0x00419f3f:	call 0x0041d3a9
0x00419f44:	addl %esp, $0x4<UINT8>
0x00419f47:	movl %eax, -108(%ebp)
0x00419f4a:	movl -124(%ebp), %edi
0x00419f4d:	movl -120(%ebp), %edi
0x00419f50:	movl -116(%ebp), %edi
0x00419f53:	cmpl %eax, %edi
0x00419f55:	je 9
0x00419f57:	pushl %eax
0x00419f58:	call 0x0041d3a9
0x00419f5d:	addl %esp, $0x4<UINT8>
0x00419f60:	movl %eax, -60(%ebp)
0x00419f63:	movl -108(%ebp), %edi
0x00419f66:	movl -104(%ebp), %edi
0x00419f69:	movl -100(%ebp), %edi
0x00419f6c:	cmpl %eax, %edi
0x00419f6e:	je 9
0x00419f70:	pushl %eax
0x00419f71:	call 0x0041d3a9
0x00419f76:	addl %esp, $0x4<UINT8>
0x00419f79:	movl -4(%ebp), $0xffffffff<UINT32>
0x00419f80:	movl %eax, -44(%ebp)
0x00419f83:	cmpl %eax, %edi
0x00419f85:	je 9
0x00419f87:	pushl %eax
0x00419f88:	call 0x0041d3a9
0x00419f8d:	addl %esp, $0x4<UINT8>
0x00419f90:	movl %edx, -16(%ebp)
0x00419f93:	pushl %edi
0x00419f94:	pushl %edi
0x00419f95:	pushl $0x10<UINT8>
0x00419f97:	leal %ecx, -140(%ebp)
0x00419f9d:	pushl %ecx
0x00419f9e:	pushl %edi
0x00419f9f:	pushl %edx
0x00419fa0:	call AdjustTokenPrivileges@AdvApi32.dll
AdjustTokenPrivileges@AdvApi32.dll: API Node	
0x00419fa2:	call GetLastError@kernel32.dll
0x00419fa8:	testl %eax, %eax
0x00419faa:	je 4
0x00419fac:	xorl %esi, %esi
0x00419fae:	jmp 0x00419fb3
0x00419fb3:	movl %eax, -16(%ebp)
0x00419fb6:	pushl %eax
0x00419fb7:	call CloseHandle@kernel32.dll
CloseHandle@kernel32.dll: API Node	
0x00419fbd:	movl %eax, %esi
0x00419fbf:	movl %ecx, -12(%ebp)
0x00419fc2:	movl %fs:0, %ecx
0x00419fc9:	popl %ecx
0x00419fca:	popl %edi
0x00419fcb:	popl %esi
0x00419fcc:	popl %ebx
0x00419fcd:	movl %esp, %ebp
0x00419fcf:	popl %ebp
0x00419fd0:	ret

0x004089d6:	movl -32(%ebp), %ebx
0x004089d9:	movl -28(%ebp), %ebx
0x004089dc:	movl -24(%ebp), %ebx
0x004089df:	movl -4(%ebp), $0x5<UINT32>
0x004089e6:	movb 0x448860, %bl
0x004089ec:	movl %ebx, $0x30<UINT32>
0x004089f1:	leal %esi, -32(%ebp)
0x004089f4:	movl -20(%ebp), $0x1<UINT32>
0x004089fb:	call 0x004034c0
0x00408a00:	movl %edx, -32(%ebp)
0x00408a03:	movl %esi, -28(%ebp)
0x00408a06:	pushl $0x20<UINT8>
0x00408a08:	leal %eax, (%esi,%edx)
0x00408a0b:	pushl $0x44009c<UINT32>
0x00408a10:	pushl %eax
0x00408a11:	call 0x00420c20
0x00408a16:	addl %esi, $0x20<UINT8>
0x00408a19:	addl %esp, $0xc<UINT8>
0x00408a1c:	movl -28(%ebp), %esi
0x00408a1f:	xorl %eax, %eax
0x00408a21:	movl -4(%ebp), $0x6<UINT32>
0x00408a28:	movl -48(%ebp), %eax
0x00408a2b:	movl -44(%ebp), %eax
0x00408a2e:	movl -40(%ebp), %eax
0x00408a31:	movb -4(%ebp), $0x7<UINT8>
0x00408a35:	leal %ebx, 0x28(%eax)
0x00408a38:	leal %esi, -48(%ebp)
0x00408a3b:	movl -36(%ebp), $0x1<UINT32>
0x00408a42:	movb 0x448860, %al
0x00408a47:	call 0x004034c0
0x00408a4c:	movl %eax, -44(%ebp)
0x00408a4f:	movl %edx, 0x440080
0x00408a55:	movl %ecx, -48(%ebp)
0x00408a58:	movl (%eax,%ecx), %edx
0x00408a5b:	movl %edx, 0x440084
0x00408a61:	movl 0x4(%eax,%ecx), %edx
0x00408a65:	movl %edx, 0x440088
0x00408a6b:	movl 0x8(%eax,%ecx), %edx
0x00408a6f:	movl %edx, 0x44008c
0x00408a75:	movl 0xc(%eax,%ecx), %edx
0x00408a79:	movl %edx, 0x440090
0x00408a7f:	movl 0x10(%eax,%ecx), %edx
0x00408a83:	movl %edx, 0x440094
0x00408a89:	movl 0x14(%eax,%ecx), %edx
0x00408a8d:	addl %eax, $0x18<UINT8>
0x00408a90:	movl -44(%ebp), %eax
0x00408a93:	pushl $0x1<UINT8>
0x00408a95:	pushl $0x0<UINT8>
0x00408a97:	leal %eax, -32(%ebp)
0x00408a9a:	pushl %eax
0x00408a9b:	leal %ecx, -64(%ebp)
0x00408a9e:	pushl %ecx
0x00408a9f:	movb -4(%ebp), $0x8<UINT8>
0x00408aa3:	call 0x0041b070
0x00408aa8:	movl %ebx, %eax
0x00408aaa:	pushl $0x1<UINT8>
0x00408aac:	pushl $0x0<UINT8>
0x00408aae:	movl %edx, %esi
0x00408ab0:	pushl %edx
0x00408ab1:	leal %eax, -80(%ebp)
0x00408ab4:	pushl %eax
0x00408ab5:	movb -4(%ebp), $0x9<UINT8>
0x00408ab9:	call 0x0041b070
0x00408abe:	movl %esi, %eax
0x00408ac0:	movl %eax, (%esi)
0x00408ac2:	addl %esp, $0x20<UINT8>
0x00408ac5:	pushl %eax
0x00408ac6:	call GetModuleHandleA@kernel32.dll
0x00408ac8:	xorl %edi, %edi
0x00408aca:	cmpl %eax, %edi
0x00408acc:	jne 0x00408ad7
0x00408ad7:	movl %ebx, (%ebx)
0x00408ad9:	pushl %ebx
0x00408ada:	pushl %eax
0x00408adb:	call GetProcAddress@kernel32.dll
0x00408ae1:	movl %esi, %eax
0x00408ae3:	cmpl %esi, %edi
0x00408ae5:	jne 0x00408aed
0x00408aed:	movl %eax, -80(%ebp)
0x00408af0:	cmpl %eax, %edi
0x00408af2:	je 9
0x00408af4:	pushl %eax
0x00408af5:	call 0x0041d3a9
0x00408afa:	addl %esp, $0x4<UINT8>
0x00408afd:	movl %eax, -64(%ebp)
0x00408b00:	movl -80(%ebp), %edi
0x00408b03:	movl -76(%ebp), %edi
0x00408b06:	movl -72(%ebp), %edi
0x00408b09:	cmpl %eax, %edi
0x00408b0b:	je 9
0x00408b0d:	pushl %eax
0x00408b0e:	call 0x0041d3a9
0x00408b13:	addl %esp, $0x4<UINT8>
0x00408b16:	movl %eax, -48(%ebp)
0x00408b19:	movl -64(%ebp), %edi
0x00408b1c:	movl -60(%ebp), %edi
0x00408b1f:	movl -56(%ebp), %edi
0x00408b22:	cmpl %eax, %edi
0x00408b24:	je 9
0x00408b26:	pushl %eax
0x00408b27:	call 0x0041d3a9
0x00408b2c:	addl %esp, $0x4<UINT8>
0x00408b2f:	movl -4(%ebp), $0xffffffff<UINT32>
0x00408b36:	movl %eax, -32(%ebp)
0x00408b39:	cmpl %eax, %edi
0x00408b3b:	je 9
0x00408b3d:	pushl %eax
0x00408b3e:	call 0x0041d3a9
0x00408b43:	addl %esp, $0x4<UINT8>
0x00408b46:	pushl $0x4000<UINT32>
0x00408b4b:	call GetCurrentProcess@kernel32.dll
0x00408b51:	pushl %eax
0x00408b52:	call SetPriorityClass@Kernel32.dll
SetPriorityClass@Kernel32.dll: API Node	
0x00408b54:	movb %al, $0x1<UINT8>
0x00408b56:	movl %ecx, -12(%ebp)
0x00408b59:	movl %fs:0, %ecx
0x00408b60:	popl %ecx
0x00408b61:	popl %edi
0x00408b62:	popl %esi
0x00408b63:	popl %ebx
0x00408b64:	movl %esp, %ebp
0x00408b66:	popl %ebp
0x00408b67:	ret

0x0041b749:	testb %al, %al
0x0041b74b:	je 2125
0x0041b751:	call 0x00405790
0x0041b756:	movl %esi, %eax
0x0041b758:	movl %ecx, 0x10(%esi)
0x0041b75b:	movl %eax, 0x14(%esi)
0x0041b75e:	pushl %ecx
0x0041b75f:	leal %edi, 0x98(%esp)
0x0041b766:	leal %ecx, 0xa8(%esp)
0x0041b76d:	movb 0xa8(%esp), %bl
0x0041b774:	movl 0xec(%esp), %ebx
0x0041b77b:	movl 0xf0(%esp), %ebx
0x0041b782:	movl 0xf4(%esp), $0x67452301<UINT32>
0x0041b78d:	movl 0xf8(%esp), $0xefcdab89<UINT32>
0x0041b798:	movl 0xfc(%esp), $0x98badcfe<UINT32>
0x0041b7a3:	movl 0x100(%esp), $0x10325476<UINT32>
0x0041b7ae:	call 0x00403210
0x0041b7b3:	pushl %ebx
0x0041b7b4:	pushl $0x440d04<UINT32>
0x0041b7b9:	leal %eax, 0x38(%esp)
0x0041b7bd:	pushl %eax
0x0041b7be:	movl 0x44c(%esp), %ebx
0x0041b7c5:	call 0x004035d0
0x0041b7ca:	pushl %ebx
0x0041b7cb:	pushl $0x440080<UINT32>
0x0041b7d0:	leal %ecx, 0x28(%esp)
0x0041b7d4:	pushl %ecx
0x0041b7d5:	movb 0x44c(%esp), $0x1<UINT8>
0x0041b7dd:	call 0x004035d0
0x0041b7e2:	pushl $0x1<UINT8>
0x0041b7e4:	pushl %ebx
0x0041b7e5:	leal %edx, 0x38(%esp)
0x0041b7e9:	pushl %edx
0x0041b7ea:	leal %eax, 0x5c(%esp)
0x0041b7ee:	pushl %eax
0x0041b7ef:	movb 0x450(%esp), $0x2<UINT8>
0x0041b7f7:	call 0x0041b070
0x0041b7fc:	movl 0x28(%esp), %eax
0x0041b800:	pushl $0x1<UINT8>
0x0041b802:	pushl %ebx
0x0041b803:	leal %ecx, 0x38(%esp)
0x0041b807:	pushl %ecx
0x0041b808:	leal %edx, 0x5c(%esp)
0x0041b80c:	pushl %edx
0x0041b80d:	movb 0x460(%esp), $0x3<UINT8>
0x0041b815:	call 0x0041b070
0x0041b81a:	movl %edi, %eax
0x0041b81c:	movl %eax, (%edi)
0x0041b81e:	addl %esp, $0x20<UINT8>
0x0041b821:	pushl %eax
0x0041b822:	call GetModuleHandleA@kernel32.dll
0x0041b828:	cmpl %eax, %ebx
0x0041b82a:	jne 0x0041b835
0x0041b835:	movl %ecx, 0x18(%esp)
0x0041b839:	movl %ecx, (%ecx)
0x0041b83b:	pushl %ecx
0x0041b83c:	pushl %eax
0x0041b83d:	call GetProcAddress@kernel32.dll
0x0041b843:	movl %edi, %eax
0x0041b845:	cmpl %edi, %ebx
0x0041b847:	jne 0x0041b84f
0x0041b84f:	movl %eax, 0x40(%esp)
0x0041b853:	cmpl %eax, %ebx
0x0041b855:	je 9
0x0041b857:	pushl %eax
0x0041b858:	call 0x0041d3a9
0x0041b85d:	addl %esp, $0x4<UINT8>
0x0041b860:	movl %eax, 0x50(%esp)
0x0041b864:	movl 0x40(%esp), %ebx
0x0041b868:	movl 0x44(%esp), %ebx
0x0041b86c:	movl 0x48(%esp), %ebx
0x0041b870:	cmpl %eax, %ebx
0x0041b872:	je 9
0x0041b874:	pushl %eax
0x0041b875:	call 0x0041d3a9
0x0041b87a:	addl %esp, $0x4<UINT8>
0x0041b87d:	movl %eax, 0x20(%esp)
0x0041b881:	movl 0x50(%esp), %ebx
0x0041b885:	movl 0x54(%esp), %ebx
0x0041b889:	movl 0x58(%esp), %ebx
0x0041b88d:	cmpl %eax, %ebx
0x0041b88f:	je 9
0x0041b891:	pushl %eax
0x0041b892:	call 0x0041d3a9
0x0041b897:	addl %esp, $0x4<UINT8>
0x0041b89a:	movb 0x440(%esp), $0x0<UINT8>
0x0041b8a2:	movl %eax, 0x30(%esp)
0x0041b8a6:	movl 0x20(%esp), %ebx
0x0041b8aa:	movl 0x24(%esp), %ebx
0x0041b8ae:	movl 0x28(%esp), %ebx
0x0041b8b2:	cmpl %eax, %ebx
0x0041b8b4:	je 9
0x0041b8b6:	pushl %eax
0x0041b8b7:	call 0x0041d3a9
0x0041b8bc:	addl %esp, $0x4<UINT8>
0x0041b8bf:	movl %edx, 0x94(%esp)
0x0041b8c6:	pushl %edx
0x0041b8c7:	pushl %ebx
0x0041b8c8:	pushl %ebx
0x0041b8c9:	movl 0x3c(%esp), %ebx
0x0041b8cd:	movl 0x40(%esp), %ebx
0x0041b8d1:	movl 0x44(%esp), %ebx
0x0041b8d5:	call CreateMutexA@Kernel32.dll
CreateMutexA@Kernel32.dll: API Node	
0x0041b8d7:	movl %edi, %eax
0x0041b8d9:	movl 0x90(%esp), %edi
0x0041b8e0:	call GetLastError@kernel32.dll
0x0041b8e6:	cmpl %eax, $0xb7<UINT32>
0x0041b8eb:	jne 0x0041b8f9
0x0041b8f9:	pushl $0x103<UINT32>
0x0041b8fe:	leal %eax, 0x329(%esp)
0x0041b905:	pushl %ebx
0x0041b906:	pushl %eax
0x0041b907:	movb 0x330(%esp), $0x0<UINT8>
0x0041b90f:	call 0x0041f180
0x0041b914:	addl %esp, $0xc<UINT8>
0x0041b917:	pushl $0x104<UINT32>
0x0041b91c:	leal %ecx, 0x328(%esp)
0x0041b923:	pushl %ecx
0x0041b924:	pushl %ebx
0x0041b925:	call GetModuleFileNameA@kernel32.dll
0x0041b92b:	pushl %ebx
0x0041b92c:	leal %edx, 0x328(%esp)
0x0041b933:	pushl %edx
0x0041b934:	leal %eax, 0x68(%esp)
0x0041b938:	pushl %eax
0x0041b939:	call 0x004035d0
0x0041b93e:	pushl $0xffffffff<UINT8>
0x0041b940:	leal %ecx, 0x18(%esp)
0x0041b944:	pushl %ecx
0x0041b945:	movb 0x448(%esp), $0x4<UINT8>
0x0041b94d:	movl %eax, $0x1<UINT32>
0x0041b952:	leal %ecx, 0x68(%esp)
0x0041b956:	movb 0x1c(%esp), $0x5c<UINT8>
0x0041b95b:	call 0x00414270
0x00414270:	pushl %ebp
0x00414271:	movl %ebp, %esp
0x00414273:	subl %esp, $0x8<UINT8>
0x00414276:	pushl %esi
0x00414277:	movl %esi, 0xc(%ebp)
0x0041427a:	pushl %edi
0x0041427b:	movl %edi, %eax
0x0041427d:	movl %eax, 0x4(%ecx)
0x00414280:	movl %ecx, (%ecx)
0x00414282:	movl -4(%ebp), %esi
0x00414285:	movl -8(%ebp), %ecx
0x00414288:	testl %ecx, %ecx
0x0041428a:	je 270
0x00414290:	movl %edx, 0x8(%ebp)
0x00414293:	testl %edx, %edx
0x00414295:	je 259
0x0041429b:	cmpl %ecx, %edx
0x0041429d:	jne 0x004142a9
0x004142a9:	testl %eax, %eax
0x004142ab:	jne 0x004142c4
0x004142c4:	testl %edi, %edi
0x004142c6:	jne 0x004142df
0x004142df:	cmpl %esi, $0xffffffff<UINT8>
0x004142e2:	jne 5
0x004142e4:	movl -4(%ebp), %eax
0x004142e7:	movl %esi, %eax
0x004142e9:	testl %esi, %esi
0x004142eb:	je 173
0x004142f1:	leal %eax, 0x1(%edi)
0x004142f4:	pushl %ebx
0x004142f5:	pushl %eax
0x004142f6:	call 0x0041cfd3
0x004142fb:	movl %ecx, 0x8(%ebp)
0x004142fe:	pushl %edi
0x004142ff:	movl %ebx, %eax
0x00414301:	pushl %ecx
0x00414302:	pushl %ebx
0x00414303:	movl 0xc(%ebp), %ebx
0x00414306:	call 0x00420c20
0x0041430b:	leal %edx, 0x1(%esi)
0x0041430e:	pushl %edx
0x0041430f:	movb (%ebx,%edi), $0x0<UINT8>
0x00414313:	call 0x0041cfd3
0x00414318:	movl %ebx, %eax
0x0041431a:	movl %eax, -8(%ebp)
0x0041431d:	pushl %esi
0x0041431e:	pushl %eax
0x0041431f:	pushl %ebx
0x00414320:	call 0x00420c20
0x00414325:	movl %ecx, %esi
0x00414327:	addl %esp, $0x20<UINT8>
0x0041432a:	movl %esi, %ebx
0x0041432c:	call 0x00406180
0x00406180:	pushl %edi
0x00406181:	movl %edi, %ecx
0x00406183:	xorl %eax, %eax
0x00406185:	shrl %edi
0x00406187:	je 0x004061a1
0x00406189:	leal %ecx, -1(%esi,%ecx)
0x0040618d:	pushl %ebx
0x0040618e:	movl %edi, %edi
0x00406190:	movb %bl, (%ecx)
0x00406192:	movb %dl, (%eax,%esi)
0x00406195:	movb (%eax,%esi), %bl
0x00406198:	movb (%ecx), %dl
0x0040619a:	incl %eax
0x0040619b:	decl %ecx
0x0040619c:	cmpl %eax, %edi
0x0040619e:	jb 0x00406190
0x004061a0:	popl %ebx
0x004061a1:	popl %edi
0x004061a2:	ret

0x00414331:	movl %esi, 0xc(%ebp)
0x00414334:	movl %ecx, %edi
0x00414336:	call 0x00406180
0x0041433b:	movl %esi, -4(%ebp)
0x0041433e:	movb (%ebx,%esi), $0x0<UINT8>
0x00414342:	movl 0x8(%ebp), $0xffffffff<UINT32>
0x00414349:	pushl %esi
0x0041434a:	cmpl %edi, $0x20<UINT8>
0x0041434d:	ja 20
0x0041434f:	movl %ecx, 0xc(%ebp)
0x00414352:	pushl %ecx
0x00414353:	pushl %ebx
0x00414354:	movl %esi, %edi
0x00414356:	call 0x004061b0
0x004061b0:	pushl %ebp
0x004061b1:	movl %ebp, %esp
0x004061b3:	subl %esp, $0x400<UINT32>
0x004061b9:	pushl %ebx
0x004061ba:	movl %ebx, 0x8(%ebp)
0x004061bd:	pushl %edi
0x004061be:	movl %edi, 0x10(%ebp)
0x004061c1:	cmpl %esi, %edi
0x004061c3:	ja 159
0x004061c9:	cmpl %esi, $0x20<UINT8>
0x004061cc:	ja 150
0x004061d2:	pushl $0x3fc<UINT32>
0x004061d7:	leal %eax, -1020(%ebp)
0x004061dd:	pushl $0x0<UINT8>
0x004061df:	pushl %eax
0x004061e0:	movl -1024(%ebp), $0x0<UINT32>
0x004061ea:	call 0x0041f180
0x004061ef:	addl %esp, $0xc<UINT8>
0x004061f2:	xorl %eax, %eax
0x004061f4:	testl %esi, %esi
0x004061f6:	jle 40
0x004061f8:	leal %ecx, -1(%esi)
0x004061fb:	jmp 0x00406200
0x00406200:	movl %edx, 0xc(%ebp)
0x00406203:	movzbl %edx, (%eax,%edx)
0x00406207:	movl %edi, $0x1<UINT32>
0x0040620c:	shll %edi, %cl
0x0040620e:	leal %edx, -1024(%ebp,%edx,4)
0x00406215:	incl %eax
0x00406216:	decl %ecx
0x00406217:	orl (%edx), %edi
0x00406219:	cmpl %eax, %esi
0x0040621b:	jl 0x00406200
0x0040621d:	movl %edi, 0x10(%ebp)
0x00406220:	xorl %edx, %edx
0x00406222:	subl %edi, %esi
0x00406224:	movl 0x10(%ebp), %edi
0x00406227:	js 63
0x00406229:	leal %esp, (%esp)
0x00406230:	leal %eax, -1(%ebx,%esi)
0x00406234:	movzbl %eax, (%eax,%edx)
0x00406238:	movl %eax, -1024(%ebp,%eax,4)
0x0040623f:	movl %ecx, %esi
0x00406241:	testl %eax, %eax
0x00406243:	je 0x00406261
0x00406261:	addl %edx, %ecx
0x00406263:	cmpl %edx, 0x10(%ebp)
0x00406266:	jle 0x00406230
0x00406245:	subl %ecx, $0x1<UINT8>
0x00406248:	je 0x00406270
0x00406270:	popl %edi
0x00406271:	leal %eax, (%edx,%ebx)
0x00406274:	popl %ebx
0x00406275:	movl %esp, %ebp
0x00406277:	popl %ebp
0x00406278:	ret

0x0041435b:	movl %esi, -4(%ebp)
0x0041435e:	addl %esp, $0xc<UINT8>
0x00414361:	jmp 0x00414371
0x00414371:	testl %eax, %eax
0x00414373:	je 11
0x00414375:	movl %ecx, %ebx
0x00414377:	subl %ecx, %eax
0x00414379:	subl %ecx, %edi
0x0041437b:	addl %ecx, %esi
0x0041437d:	movl 0x8(%ebp), %ecx
0x00414380:	pushl %ebx
0x00414381:	call 0x0041d3a9
0x00414386:	movl %edx, 0xc(%ebp)
0x00414389:	pushl %edx
0x0041438a:	call 0x0041d3a9
0x0041438f:	movl %eax, 0x8(%ebp)
0x00414392:	addl %esp, $0x8<UINT8>
0x00414395:	popl %ebx
0x00414396:	popl %edi
0x00414397:	popl %esi
0x00414398:	movl %esp, %ebp
0x0041439a:	popl %ebp
0x0041439b:	ret $0x8<UINT16>

0x0041b960:	leal %edx, 0x60(%esp)
0x0041b964:	pushl %edx
0x0041b965:	orl %edx, $0xffffffff<UINT8>
0x0041b968:	movl %ecx, %eax
0x0041b96a:	leal %edi, 0x84(%esp)
0x0041b971:	call 0x00406a80
0x0041b976:	movb 0x440(%esp), $0x5<UINT8>
0x0041b97e:	movl %ecx, 0x4(%eax)
0x0041b981:	movl %eax, (%eax)
0x0041b983:	pushl %eax
0x0041b984:	movl %eax, %ecx
0x0041b986:	leal %ecx, 0x64(%esp)
0x0041b98a:	call 0x00406e50
0x0041b98f:	movb 0x440(%esp), $0x4<UINT8>
0x0041b997:	movl %eax, 0x80(%esp)
0x0041b99e:	cmpl %eax, %ebx
0x0041b9a0:	je 9
0x0041b9a2:	pushl %eax
0x0041b9a3:	call 0x0041d3a9
0x0041b9a8:	addl %esp, $0x4<UINT8>
0x0041b9ab:	pushl $0xffffffff<UINT8>
0x0041b9ad:	leal %ecx, 0x18(%esp)
0x0041b9b1:	pushl %ecx
0x0041b9b2:	movl %eax, $0x1<UINT32>
0x0041b9b7:	leal %ecx, 0x68(%esp)
0x0041b9bb:	movb 0x1c(%esp), $0x2e<UINT8>
0x0041b9c0:	call 0x00414270
0x0041b9c5:	leal %edx, 0x60(%esp)
0x0041b9c9:	pushl %edx
0x0041b9ca:	movl %edx, %eax
0x0041b9cc:	xorl %ecx, %ecx
0x0041b9ce:	leal %edi, 0x84(%esp)
0x0041b9d5:	call 0x00406a80
0x0041b9da:	movb 0x440(%esp), $0x6<UINT8>
0x0041b9e2:	movl %ecx, 0x4(%eax)
0x0041b9e5:	movl %eax, (%eax)
0x0041b9e7:	pushl %eax
0x0041b9e8:	movl %eax, %ecx
0x0041b9ea:	leal %ecx, 0x64(%esp)
0x0041b9ee:	call 0x00406e50
0x0041b9f3:	movb 0x440(%esp), $0x4<UINT8>
0x0041b9fb:	movl %eax, 0x80(%esp)
0x0041ba02:	cmpl %eax, %ebx
0x0041ba04:	je 9
0x0041ba06:	pushl %eax
0x0041ba07:	call 0x0041d3a9
0x0041ba0c:	addl %esp, $0x4<UINT8>
0x0041ba0f:	pushl $0x103<UINT32>
0x0041ba14:	leal %ecx, 0x119(%esp)
0x0041ba1b:	pushl %ebx
0x0041ba1c:	pushl %ecx
0x0041ba1d:	movb 0x120(%esp), $0x0<UINT8>
0x0041ba25:	call 0x0041f180
0x0041ba2a:	movl %edi, 0x43b090
0x0041ba30:	addl %esp, $0xc<UINT8>
0x0041ba33:	pushl $0x440d20<UINT32>
0x0041ba38:	leal %edx, 0x118(%esp)
0x0041ba3f:	pushl %edx
0x0041ba40:	call lstrcatA@kernel32.dll
lstrcatA@kernel32.dll: API Node	
0x0041ba42:	movzbl %eax, 0x115(%esp)
0x0041ba4a:	movb %cl, 0x117(%esp)
0x0041ba51:	movb %dl, 0x119(%esp)
0x0041ba58:	movb 0x114(%esp), %al
0x0041ba5f:	movzbl %eax, 0x11b(%esp)
0x0041ba67:	pushl $0x103<UINT32>
0x0041ba6c:	movb 0x119(%esp), %cl
0x0041ba73:	leal %ecx, 0x221(%esp)
0x0041ba7a:	pushl %ebx
0x0041ba7b:	pushl %ecx
0x0041ba7c:	movb 0x122(%esp), %dl
0x0041ba83:	movb 0x123(%esp), %al
0x0041ba8a:	movb 0x124(%esp), $0x0<UINT8>
0x0041ba92:	movb 0x228(%esp), $0x0<UINT8>
0x0041ba9a:	call 0x0041f180
0x0041ba9f:	addl %esp, $0xc<UINT8>
0x0041baa2:	pushl %ebx
0x0041baa3:	pushl $0x18<UINT8>
0x0041baa5:	leal %edx, 0x224(%esp)
0x0041baac:	pushl %edx
0x0041baad:	pushl %ebx
0x0041baae:	call SHGetSpecialFolderPathA@shell32.dll
SHGetSpecialFolderPathA@shell32.dll: API Node	
0x0041bab4:	movl %eax, 0x60(%esp)
0x0041bab8:	pushl %eax
0x0041bab9:	leal %ecx, 0x220(%esp)
0x0041bac0:	pushl %ecx
0x0041bac1:	call lstrcatA@kernel32.dll
0x0041bac3:	leal %edx, 0x114(%esp)
0x0041baca:	pushl %edx
0x0041bacb:	leal %eax, 0x220(%esp)
0x0041bad2:	pushl %eax
0x0041bad3:	call lstrcatA@kernel32.dll
0x0041bad5:	pushl %ebx
0x0041bad6:	pushl $0x43fad1<UINT32>
0x0041badb:	leal %ecx, 0x88(%esp)
0x0041bae2:	pushl %ecx
0x0041bae3:	call 0x004035d0
0x00403570:	testl %ebx, %ebx
0x00403572:	jne 12
0x00403574:	movl %ebx, $0x10<UINT32>
0x00403579:	cmpl 0x8(%esi), %ebx
0x0040357c:	jae 34
0x0040357e:	jmp 0x00403598
0x0041bae8:	pushl %ebx
0x0041bae9:	pushl $0x43fad1<UINT32>
0x0041baee:	leal %edx, 0x48(%esp)
0x0041baf2:	pushl %edx
0x0041baf3:	movb 0x44c(%esp), $0x7<UINT8>
0x0041bafb:	call 0x004035d0
0x0041bb00:	pushl %ebx
0x0041bb01:	pushl $0x43fad1<UINT32>
0x0041bb06:	leal %eax, 0x58(%esp)
0x0041bb0a:	pushl %eax
0x0041bb0b:	movb 0x44c(%esp), $0x8<UINT8>
0x0041bb13:	call 0x004035d0
0x0041bb18:	pushl %ebx
0x0041bb19:	pushl $0x43fad1<UINT32>
0x0041bb1e:	leal %ecx, 0x78(%esp)
0x0041bb22:	pushl %ecx
0x0041bb23:	movb 0x44c(%esp), $0x9<UINT8>
0x0041bb2b:	call 0x004035d0
0x0041bb30:	pushl %ebx
0x0041bb31:	leal %edx, 0x220(%esp)
0x0041bb38:	pushl %edx
0x0041bb39:	leal %eax, 0x28(%esp)
0x0041bb3d:	pushl %eax
0x0041bb3e:	movb 0x44c(%esp), $0xa<UINT8>
0x0041bb46:	call 0x004035d0
0x0041bb4b:	movb 0x440(%esp), $0xb<UINT8>
0x0041bb53:	pushl %ebx
0x0041bb54:	leal %ecx, 0x328(%esp)
0x0041bb5b:	pushl %ecx
0x0041bb5c:	leal %edx, 0x38(%esp)
0x0041bb60:	pushl %edx
0x0041bb61:	call 0x004035d0
0x0041bb66:	leal %eax, 0x80(%esp)
0x0041bb6d:	pushl %eax
0x0041bb6e:	leal %ecx, 0x44(%esp)
0x0041bb72:	pushl %ecx
0x0041bb73:	leal %edx, 0x58(%esp)
0x0041bb77:	pushl %edx
0x0041bb78:	leal %eax, 0x2c(%esp)
0x0041bb7c:	pushl %eax
0x0041bb7d:	leal %ecx, 0x80(%esp)
0x0041bb84:	leal %edx, 0x40(%esp)
0x0041bb88:	movb 0x450(%esp), $0xc<UINT8>
0x0041bb90:	call 0x0041a2a0
0x0041a2a0:	pushl %ebp
0x0041a2a1:	movl %ebp, %esp
0x0041a2a3:	pushl $0xffffffff<UINT8>
0x0041a2a5:	pushl $0x433260<UINT32>
0x0041a2aa:	movl %eax, %fs:0
0x0041a2b0:	pushl %eax
0x0041a2b1:	subl %esp, $0x3c<UINT8>
0x0041a2b4:	pushl %ebx
0x0041a2b5:	pushl %esi
0x0041a2b6:	pushl %edi
0x0041a2b7:	movl %eax, 0x44609c
0x0041a2bc:	xorl %eax, %ebp
0x0041a2be:	pushl %eax
0x0041a2bf:	leal %eax, -12(%ebp)
0x0041a2c2:	movl %fs:0, %eax
0x0041a2c8:	movl %ebx, %ecx
0x0041a2ca:	movl %edi, %edx
0x0041a2cc:	cmpl 0x4(%edi), $0x0<UINT8>
0x0041a2d0:	movl %esi, $0x57<UINT32>
0x0041a2d5:	je 435
0x0041a2db:	movl %eax, 0x8(%ebp)
0x0041a2de:	cmpl 0x4(%eax), $0x0<UINT8>
0x0041a2e2:	je 422
0x0041a2e8:	pushl $0x0<UINT8>
0x0041a2ea:	call CoInitialize@ole32.dll
CoInitialize@ole32.dll: API Node	
0x0041a2f0:	leal %ecx, -16(%ebp)
0x0041a2f3:	pushl %ecx
0x0041a2f4:	pushl $0x43b5f8<UINT32>
0x0041a2f9:	pushl $0x1<UINT8>
0x0041a2fb:	pushl $0x0<UINT8>
0x0041a2fd:	pushl $0x43b608<UINT32>
0x0041a302:	call CoCreateInstance@ole32.dll
CoCreateInstance@ole32.dll: API Node	
0x0041a308:	testl %eax, %eax
0x0041a30a:	jl 0x0041a480
0x0041a310:	leal %edx, -36(%ebp)
0x0041a480:	call GetLastError@kernel32.dll
0x0041a486:	movl %esi, %eax
0x0041a488:	call CoUninitialize@ole32.dll
CoUninitialize@ole32.dll: API Node	
0x0041a48e:	pushl %esi
0x0041a48f:	call SetLastError@kernel32.dll
0x0041a495:	xorl %eax, %eax
0x0041a497:	testl %esi, %esi
0x0041a499:	sete %al
0x0041a49c:	movl %ecx, -12(%ebp)
0x0041a49f:	movl %fs:0, %ecx
0x0041a4a6:	popl %ecx
0x0041a4a7:	popl %edi
0x0041a4a8:	popl %esi
0x0041a4a9:	popl %ebx
0x0041a4aa:	movl %esp, %ebp
0x0041a4ac:	popl %ebp
0x0041a4ad:	ret

0x0041bb95:	movl 0x28(%esp), %eax
0x0041bb99:	movl %eax, 0x40(%esp)
0x0041bb9d:	addl %esp, $0x10<UINT8>
0x0041bba0:	cmpl %eax, %ebx
0x0041bba2:	je 9
0x0041bba4:	pushl %eax
0x0041bba5:	call 0x0041d3a9
0x0041bbaa:	addl %esp, $0x4<UINT8>
0x0041bbad:	movl %eax, 0x20(%esp)
0x0041bbb1:	movl 0x30(%esp), %ebx
0x0041bbb5:	movl 0x34(%esp), %ebx
0x0041bbb9:	movl 0x38(%esp), %ebx
0x0041bbbd:	cmpl %eax, %ebx
0x0041bbbf:	je 9
0x0041bbc1:	pushl %eax
0x0041bbc2:	call 0x0041d3a9
0x0041bbc7:	addl %esp, $0x4<UINT8>
0x0041bbca:	movl %eax, 0x70(%esp)
0x0041bbce:	movl 0x20(%esp), %ebx
0x0041bbd2:	movl 0x24(%esp), %ebx
0x0041bbd6:	movl 0x28(%esp), %ebx
0x0041bbda:	cmpl %eax, %ebx
0x0041bbdc:	je 9
0x0041bbde:	pushl %eax
0x0041bbdf:	call 0x0041d3a9
0x0041bbe4:	addl %esp, $0x4<UINT8>
0x0041bbe7:	movl %eax, 0x50(%esp)
0x0041bbeb:	movl 0x70(%esp), %ebx
0x0041bbef:	movl 0x74(%esp), %ebx
0x0041bbf3:	movl 0x78(%esp), %ebx
0x0041bbf7:	cmpl %eax, %ebx
0x0041bbf9:	je 9
0x0041bbfb:	pushl %eax
0x0041bbfc:	call 0x0041d3a9
0x0041bc01:	addl %esp, $0x4<UINT8>
0x0041bc04:	movl %eax, 0x40(%esp)
0x0041bc08:	movl 0x50(%esp), %ebx
0x0041bc0c:	movl 0x54(%esp), %ebx
0x0041bc10:	movl 0x58(%esp), %ebx
0x0041bc14:	cmpl %eax, %ebx
0x0041bc16:	je 9
0x0041bc18:	pushl %eax
0x0041bc19:	call 0x0041d3a9
0x0041bc1e:	addl %esp, $0x4<UINT8>
0x0041bc21:	movb 0x440(%esp), $0x4<UINT8>
0x0041bc29:	movl %eax, 0x80(%esp)
0x0041bc30:	movl 0x40(%esp), %ebx
0x0041bc34:	movl 0x44(%esp), %ebx
0x0041bc38:	movl 0x48(%esp), %ebx
0x0041bc3c:	cmpl %eax, %ebx
0x0041bc3e:	je 9
0x0041bc40:	pushl %eax
0x0041bc41:	call 0x0041d3a9
0x0041bc46:	addl %esp, $0x4<UINT8>
0x0041bc49:	cmpl 0x18(%esp), %ebx
0x0041bc4d:	jne 0x0041be14
0x0041be14:	pushl %ebx
0x0041be15:	pushl %ebx
0x0041be16:	pushl $0x1<UINT8>
0x0041be18:	pushl %ebx
0x0041be19:	pushl %ebx
0x0041be1a:	call 433
0x0041be1f:	addl %esp, $0x14<UINT8>
0x0041be22:	testb %al, %al
0x0041be24:	je -18
0x0041be26:	pushl $0x1<UINT8>
0x0041be28:	movb 0x20(%esp), $0x1<UINT8>
0x0041be2d:	call 0x0040a3b0
0x0040a3b0:	pushl %ebp
0x0040a3b1:	movl %ebp, %esp
0x0040a3b3:	pushl $0xffffffff<UINT8>
0x0040a3b5:	pushl $0x433610<UINT32>
0x0040a3ba:	movl %eax, %fs:0
0x0040a3c0:	pushl %eax
0x0040a3c1:	subl %esp, $0x30<UINT8>
0x0040a3c4:	pushl %ebx
0x0040a3c5:	pushl %esi
0x0040a3c6:	pushl %edi
0x0040a3c7:	movl %eax, 0x44609c
0x0040a3cc:	xorl %eax, %ebp
0x0040a3ce:	pushl %eax
0x0040a3cf:	leal %eax, -12(%ebp)
0x0040a3d2:	movl %fs:0, %eax
0x0040a3d8:	xorl %ebx, %ebx
0x0040a3da:	cmpb 0x8(%ebp), %bl
0x0040a3dd:	jne 0x0040a3ff
0x0040a3ff:	call 0x00405790
0x0040a404:	xorl %edi, %edi
0x0040a406:	movl -20(%ebp), %eax
0x0040a409:	movl -36(%ebp), %edi
0x0040a40c:	leal %esp, (%esp)
0x0040a410:	xorl %ecx, %ecx
0x0040a412:	cmpl %edi, %ebx
0x0040a414:	setne %cl
0x0040a417:	cmpl %ecx, $0x2<UINT8>
0x0040a41a:	movl -32(%ebp), %ecx
0x0040a41d:	jge 643
0x0040a423:	cmpl %ecx, %ebx
0x0040a425:	je 0x0040a42b
0x0040a42b:	movl %eax, -20(%ebp)
0x0040a42e:	movl %eax, 0x17c(%eax)
0x0040a434:	cmpl %eax, $0x3<UINT8>
0x0040a437:	movl 0x8(%ebp), %eax
0x0040a43a:	jge 601
0x0040a440:	leal %esi, (,%eax,8)
0x0040a447:	subl %esi, %eax
0x0040a449:	leal %esi, 0x447650(,%esi,4)
0x0040a450:	movl -28(%ebp), %esi
0x0040a453:	cmpl %edi, %ebx
0x0040a455:	jne 9
0x0040a457:	cmpl (%esi), %ebx
0x0040a459:	jg 0x0040a468
0x0040a468:	movl -24(%ebp), %ebx
0x0040a46b:	pushl %ebx
0x0040a46c:	pushl %ebx
0x0040a46d:	pushl $0x1<UINT8>
0x0040a46f:	pushl %ebx
0x0040a470:	pushl %ebx
0x0040a471:	call 0x0041bfd0
0x0041bfd0:	pushl %ebp
0x0041bfd1:	movl %ebp, %esp
0x0041bfd3:	pushl $0xffffffff<UINT8>
0x0041bfd5:	pushl $0x4335e0<UINT32>
0x0041bfda:	movl %eax, %fs:0
0x0041bfe0:	pushl %eax
0x0041bfe1:	subl %esp, $0x28<UINT8>
0x0041bfe4:	pushl %ebx
0x0041bfe5:	pushl %esi
0x0041bfe6:	pushl %edi
0x0041bfe7:	movl %eax, 0x44609c
0x0041bfec:	xorl %eax, %ebp
0x0041bfee:	pushl %eax
0x0041bfef:	leal %eax, -12(%ebp)
0x0041bff2:	movl %fs:0, %eax
0x0041bff8:	xorl %eax, %eax
0x0041bffa:	cmpl 0x10(%ebp), %eax
0x0041bffd:	je 293
0x0041c003:	movl -36(%ebp), %eax
0x0041c006:	movl -32(%ebp), %eax
0x0041c009:	movl -28(%ebp), %eax
0x0041c00c:	movl -24(%ebp), %eax
0x0041c00f:	movl -4(%ebp), %eax
0x0041c012:	movl 0x10(%ebp), $0x447c30<UINT32>
0x0041c019:	movl -20(%ebp), $0x7<UINT32>
0x0041c020:	movl %eax, 0x10(%ebp)
0x0041c023:	pushl $0x1<UINT8>
0x0041c025:	pushl $0x0<UINT8>
0x0041c027:	pushl %eax
0x0041c028:	leal %ecx, -52(%ebp)
0x0041c02b:	pushl %ecx
0x0041c02c:	call 0x0041b070
0x0041c031:	addl %esp, $0x10<UINT8>
0x0041c034:	movb -4(%ebp), $0x1<UINT8>
0x0041c038:	movl %ebx, -48(%ebp)
0x0041c03b:	movl %edi, -52(%ebp)
0x0041c03e:	xorl %esi, %esi
0x0041c040:	testl %ebx, %ebx
0x0041c042:	jbe 21
0x0041c044:	movsbl %edx, (%esi,%edi)
0x0041c048:	pushl %edx
0x0041c049:	call 0x0041db65
0x0041db65:	movl %edi, %edi
0x0041db67:	pushl %ebp
0x0041db68:	movl %ebp, %esp
0x0041db6a:	cmpl 0x448134, $0x0<UINT8>
0x0041db71:	jne 16
0x0041db73:	movl %eax, 0x8(%ebp)
0x0041db76:	leal %ecx, -97(%eax)
0x0041db79:	cmpl %ecx, $0x19<UINT8>
0x0041db7c:	ja 0x0041db8f
0x0041db7e:	addl %eax, $0xffffffe0<UINT8>
0x0041db81:	popl %ebp
0x0041db82:	ret

0x0041c04e:	movb (%esi,%edi), %al
0x0041c051:	incl %esi
0x0041c052:	addl %esp, $0x4<UINT8>
0x0041c055:	cmpl %esi, %ebx
0x0041c057:	jb 0x0041c044
0x0041c059:	movl %ecx, -28(%ebp)
0x0041c05c:	cmpl -32(%ebp), %ecx
0x0041c05f:	jb 0x0041c072
0x0041c061:	leal %eax, 0x10(%ecx)
0x0041c064:	cmpl %eax, %ecx
0x0041c066:	jbe 10
0x0041c068:	pushl %eax
0x0041c069:	leal %eax, -36(%ebp)
0x0041c06c:	pushl %eax
0x0041c06d:	call 0x0041a8f0
0x0041a8f0:	pushl %ebp
0x0041a8f1:	movl %ebp, %esp
0x0041a8f3:	pushl $0xffffffff<UINT8>
0x0041a8f5:	pushl $0x430b1b<UINT32>
0x0041a8fa:	movl %eax, %fs:0
0x0041a900:	pushl %eax
0x0041a901:	subl %esp, $0x8<UINT8>
0x0041a904:	pushl %ebx
0x0041a905:	pushl %esi
0x0041a906:	pushl %edi
0x0041a907:	movl %eax, 0x44609c
0x0041a90c:	xorl %eax, %ebp
0x0041a90e:	pushl %eax
0x0041a90f:	leal %eax, -12(%ebp)
0x0041a912:	movl %fs:0, %eax
0x0041a918:	movl %ebx, 0x8(%ebp)
0x0041a91b:	movl %esi, 0xc(%ebx)
0x0041a91e:	addl %esi, 0xc(%ebp)
0x0041a921:	xorl %ecx, %ecx
0x0041a923:	movl %eax, %esi
0x0041a925:	movl %edx, $0x10<UINT32>
0x0041a92a:	mull %eax, %edx
0x0041a92c:	seto %cl
0x0041a92f:	negl %ecx
0x0041a931:	orl %ecx, %eax
0x0041a933:	xorl %eax, %eax
0x0041a935:	addl %ecx, $0x4<UINT8>
0x0041a938:	setb %al
0x0041a93b:	negl %eax
0x0041a93d:	orl %eax, %ecx
0x0041a93f:	pushl %eax
0x0041a940:	call 0x0041cfd3
0x0041a945:	addl %esp, $0x4<UINT8>
0x0041a948:	movl -20(%ebp), %eax
0x0041a94b:	xorl %ecx, %ecx
0x0041a94d:	movl -4(%ebp), %ecx
0x0041a950:	cmpl %eax, %ecx
0x0041a952:	je 29
0x0041a954:	pushl $0x403390<UINT32>
0x0041a959:	pushl $0x406a10<UINT32>
0x0041a95e:	pushl %esi
0x0041a95f:	leal %edi, 0x4(%eax)
0x0041a962:	pushl $0x10<UINT8>
0x0041a964:	pushl %edi
0x0041a965:	movl (%eax), %esi
0x0041a967:	call 0x0041e5c6
0x0041e5c6:	pushl $0x10<UINT8>
0x0041e5c8:	pushl $0x4413c0<UINT32>
0x0041e5cd:	call 0x0041fad0
0x0041e5d2:	xorl %eax, %eax
0x0041e5d4:	movl -32(%ebp), %eax
0x0041e5d7:	movl -4(%ebp), %eax
0x0041e5da:	movl -28(%ebp), %eax
0x0041e5dd:	movl %eax, -28(%ebp)
0x0041e5e0:	cmpl %eax, 0x10(%ebp)
0x0041e5e3:	jnl 0x0041e5f8
0x0041e5e5:	movl %esi, 0x8(%ebp)
0x0041e5e8:	movl %ecx, %esi
0x0041e5ea:	call 0x00406a10
0x0041e5ed:	addl %esi, 0xc(%ebp)
0x0041e5f0:	movl 0x8(%ebp), %esi
0x0041e5f3:	incl -28(%ebp)
0x0041e5f6:	jmp 0x0041e5dd
0x0041e5f8:	movl -32(%ebp), $0x1<UINT32>
0x0041e5ff:	movl -4(%ebp), $0xfffffffe<UINT32>
0x0041e606:	call 0x0041e613
0x0041e613:	cmpl -32(%ebp), $0x0<UINT8>
0x0041e617:	jne 0x0041e62a
0x0041e62a:	ret

0x0041e60b:	call 0x0041fb15
0x0041e610:	ret $0x14<UINT16>

0x0041a96c:	movl -16(%ebp), %edi
0x0041a96f:	jmp 0x0041a974
0x0041a974:	cmpl -16(%ebp), $0x0<UINT8>
0x0041a978:	movl -4(%ebp), $0xffffffff<UINT32>
0x0041a97f:	je 376
0x0041a985:	movl %ecx, 0xc(%ebp)
0x0041a988:	cmpl %ecx, 0x4(%ebx)
0x0041a98b:	jae 0x0041a990
0x0041a990:	cmpl (%ebx), $0x0<UINT8>
0x0041a993:	je 0x0041a9e6
0x0041a9e6:	movl %edx, -16(%ebp)
0x0041a9e9:	movl %eax, 0x4(%ebx)
0x0041a9ec:	movl (%ebx), %edx
0x0041a9ee:	movl %edx, 0xc(%ebx)
0x0041a9f1:	addl %edx, %ecx
0x0041a9f3:	movl 0x8(%ebx), %ecx
0x0041a9f6:	movl 0xc(%ebp), %eax
0x0041a9f9:	cmpl %eax, %edx
0x0041a9fb:	jae 252
0x0041aa01:	movl %ecx, %eax
0x0041aa03:	movl %eax, 0x447d40
0x0041aa08:	shll %ecx, $0x4<UINT8>
0x0041aa0b:	movl -16(%ebp), %ecx
0x0041aa0e:	movl %edi, %edi
0x0041aa10:	movl %esi, (%ebx)
0x0041aa12:	movl %edi, 0x447d44
0x0041aa18:	addl %esi, %ecx
0x0041aa1a:	movl -20(%ebp), %eax
0x0041aa1d:	cmpl (%esi), %eax
0x0041aa1f:	je 186
0x0041aa25:	testl %eax, %eax
0x0041aa27:	je 18
0x0041aa29:	testl %edi, %edi
0x0041aa2b:	jne 18
0x0041aa2d:	movl %edi, %eax
0x0041aa2f:	leal %ecx, 0x1(%edi)
0x0041aa32:	movb %al, (%edi)
0x0041aa34:	incl %edi
0x0041aa35:	testb %al, %al
0x0041aa37:	jne -7
0x0041aa39:	subl %edi, %ecx
0x0041aa3b:	testl %edi, %edi
0x0041aa3d:	je 0x0041aa55
0x0041aa55:	movl %eax, (%esi)
0x0041aa57:	testl %eax, %eax
0x0041aa59:	je 9
0x0041aa5b:	pushl %eax
0x0041aa5c:	call 0x0041d3a9
0x0041aa61:	addl %esp, $0x4<UINT8>
0x0041aa64:	xorl %eax, %eax
0x0041aa66:	movl (%esi), %eax
0x0041aa68:	movl 0x4(%esi), %eax
0x0041aa6b:	movl 0x8(%esi), %eax
0x0041aa6e:	jmp 0x0041aab3
0x0041aab3:	testl %edi, %edi
0x0041aab5:	jne 19
0x0041aab7:	cmpl 0x8(%esi), $0x10<UINT8>
0x0041aabb:	jae 29
0x0041aabd:	leal %ebx, 0x10(%edi)
0x0041aac0:	call 0x004034c0
0x0041aac5:	movl %ebx, 0x8(%ebp)
0x0041aac8:	jmp 0x0041aada
0x0041aada:	movl %eax, 0x447d40
0x0041aadf:	movl %edx, 0xc(%ebp)
0x0041aae2:	movl %esi, 0xc(%ebx)
0x0041aae5:	movl %ecx, -16(%ebp)
0x0041aae8:	addl %esi, 0x8(%ebx)
0x0041aaeb:	incl %edx
0x0041aaec:	addl %ecx, $0x10<UINT8>
0x0041aaef:	movl 0xc(%ebp), %edx
0x0041aaf2:	movl -16(%ebp), %ecx
0x0041aaf5:	cmpl %edx, %esi
0x0041aaf7:	jb 0x0041aa10
0x0041aafd:	movl %ecx, -12(%ebp)
0x0041ab00:	movl %fs:0, %ecx
0x0041ab07:	popl %ecx
0x0041ab08:	popl %edi
0x0041ab09:	popl %esi
0x0041ab0a:	popl %ebx
0x0041ab0b:	movl %esp, %ebp
0x0041ab0d:	popl %ebp
0x0041ab0e:	ret $0x8<UINT16>

0x0041c072:	movl %esi, -32(%ebp)
0x0041c075:	movl %edx, -52(%ebp)
0x0041c078:	movl %eax, -48(%ebp)
0x0041c07b:	movl %ecx, %esi
0x0041c07d:	shll %ecx, $0x4<UINT8>
0x0041c080:	addl %ecx, -36(%ebp)
0x0041c083:	pushl %edx
0x0041c084:	call 0x00406e50
0x0041c089:	incl %esi
0x0041c08a:	movl -32(%ebp), %esi
0x0041c08d:	movb -4(%ebp), $0x0<UINT8>
0x0041c091:	movl %eax, -52(%ebp)
0x0041c094:	xorl %esi, %esi
0x0041c096:	cmpl %eax, %esi
0x0041c098:	je 9
0x0041c09a:	pushl %eax
0x0041c09b:	call 0x0041d3a9
0x0041c0a0:	addl %esp, $0x4<UINT8>
0x0041c0a3:	addl 0x10(%ebp), $0x10<UINT8>
0x0041c0a7:	subl -20(%ebp), $0x1<UINT8>
0x0041c0ab:	movl -52(%ebp), %esi
0x0041c0ae:	movl -48(%ebp), %esi
0x0041c0b1:	movl -44(%ebp), %esi
0x0041c0b4:	jne 0x0041c020
0x0041db8f:	popl %ebp
0x0041db90:	ret

0x0041c0ba:	leal %eax, -36(%ebp)
0x0041c0bd:	pushl %eax
0x0041c0be:	call 0x0041c1e0
0x0041c1e0:	pushl %ebp
0x0041c1e1:	movl %ebp, %esp
0x0041c1e3:	pushl $0xffffffff<UINT8>
0x0041c1e5:	pushl $0x432973<UINT32>
0x0041c1ea:	movl %eax, %fs:0
0x0041c1f0:	pushl %eax
0x0041c1f1:	subl %esp, $0xa8<UINT32>
0x0041c1f7:	pushl %ebx
0x0041c1f8:	pushl %esi
0x0041c1f9:	pushl %edi
0x0041c1fa:	movl %eax, 0x44609c
0x0041c1ff:	xorl %eax, %ebp
0x0041c201:	pushl %eax
0x0041c202:	leal %eax, -12(%ebp)
0x0041c205:	movl %fs:0, %eax
0x0041c20b:	xorl %eax, %eax
0x0041c20d:	movl -76(%ebp), %eax
0x0041c210:	movl -72(%ebp), %eax
0x0041c213:	movl -68(%ebp), %eax
0x0041c216:	movl -64(%ebp), %eax
0x0041c219:	movl -4(%ebp), %eax
0x0041c21c:	movl -52(%ebp), %eax
0x0041c21f:	movl -48(%ebp), %eax
0x0041c222:	movl -44(%ebp), %eax
0x0041c225:	movl %ecx, $0x1<UINT32>
0x0041c22a:	movb -4(%ebp), %cl
0x0041c22d:	leal %ebx, 0x30(%eax)
0x0041c230:	leal %esi, -52(%ebp)
0x0041c233:	movl -40(%ebp), %ecx
0x0041c236:	movb 0x448860, %al
0x0041c23b:	call 0x004034c0
0x0041c240:	movl %eax, -52(%ebp)
0x0041c243:	movl %esi, -48(%ebp)
0x0041c246:	pushl $0x20<UINT8>
0x0041c248:	leal %ecx, (%esi,%eax)
0x0041c24b:	pushl $0x440d2c<UINT32>
0x0041c250:	pushl %ecx
0x0041c251:	call 0x00420c20
0x0041c256:	addl %esi, $0x20<UINT8>
0x0041c259:	addl %esp, $0xc<UINT8>
0x0041c25c:	movl -48(%ebp), %esi
0x0041c25f:	xorl %eax, %eax
0x0041c261:	movl -32(%ebp), %eax
0x0041c264:	movl -28(%ebp), %eax
0x0041c267:	movl -24(%ebp), %eax
0x0041c26a:	movb -4(%ebp), $0x3<UINT8>
0x0041c26e:	leal %ebx, 0x28(%eax)
0x0041c271:	leal %esi, -32(%ebp)
0x0041c274:	movl -20(%ebp), $0x1<UINT32>
0x0041c27b:	movb 0x448860, %al
0x0041c280:	call 0x004034c0
0x0041c285:	movl %eax, -28(%ebp)
0x0041c288:	movl %edx, 0x440c20
0x0041c28e:	movl %ecx, -32(%ebp)
0x0041c291:	movl (%eax,%ecx), %edx
0x0041c294:	movl %edx, 0x440c24
0x0041c29a:	movl 0x4(%eax,%ecx), %edx
0x0041c29e:	movl %edx, 0x440c28
0x0041c2a4:	movl 0x8(%eax,%ecx), %edx
0x0041c2a8:	movl %edx, 0x440c2c
0x0041c2ae:	movl 0xc(%eax,%ecx), %edx
0x0041c2b2:	movl %edx, 0x440c30
0x0041c2b8:	movl 0x10(%eax,%ecx), %edx
0x0041c2bc:	movl %edx, 0x440c34
0x0041c2c2:	movl 0x14(%eax,%ecx), %edx
0x0041c2c6:	addl %eax, $0x18<UINT8>
0x0041c2c9:	movl -28(%ebp), %eax
0x0041c2cc:	pushl $0x1<UINT8>
0x0041c2ce:	xorl %ebx, %ebx
0x0041c2d0:	pushl %ebx
0x0041c2d1:	leal %eax, -52(%ebp)
0x0041c2d4:	pushl %eax
0x0041c2d5:	leal %ecx, -100(%ebp)
0x0041c2d8:	pushl %ecx
0x0041c2d9:	movb -4(%ebp), $0x4<UINT8>
0x0041c2dd:	call 0x0041b070
0x0041c2e2:	movl %edi, %eax
0x0041c2e4:	pushl $0x1<UINT8>
0x0041c2e6:	pushl %ebx
0x0041c2e7:	movl %edx, %esi
0x0041c2e9:	pushl %edx
0x0041c2ea:	leal %eax, -132(%ebp)
0x0041c2f0:	pushl %eax
0x0041c2f1:	movb -4(%ebp), $0x5<UINT8>
0x0041c2f5:	call 0x0041b070
0x0041c2fa:	movl %esi, %eax
0x0041c2fc:	movl %eax, (%esi)
0x0041c2fe:	addl %esp, $0x20<UINT8>
0x0041c301:	pushl %eax
0x0041c302:	call GetModuleHandleA@kernel32.dll
0x0041c308:	cmpl %eax, %ebx
0x0041c30a:	jne 0x0041c315
0x0041c315:	movl %edi, (%edi)
0x0041c317:	pushl %edi
0x0041c318:	pushl %eax
0x0041c319:	call GetProcAddress@kernel32.dll
0x0041c31f:	movl -84(%ebp), %eax
0x0041c322:	cmpl %eax, %ebx
0x0041c324:	jne 0x0041c32c
0x0041c32c:	movl %eax, -132(%ebp)
0x0041c332:	cmpl %eax, %ebx
0x0041c334:	je 9
0x0041c336:	pushl %eax
0x0041c337:	call 0x0041d3a9
0x0041c33c:	addl %esp, $0x4<UINT8>
0x0041c33f:	movl %eax, -100(%ebp)
0x0041c342:	movl -132(%ebp), %ebx
0x0041c348:	movl -128(%ebp), %ebx
0x0041c34b:	movl -124(%ebp), %ebx
0x0041c34e:	cmpl %eax, %ebx
0x0041c350:	je 9
0x0041c352:	pushl %eax
0x0041c353:	call 0x0041d3a9
0x0041c358:	addl %esp, $0x4<UINT8>
0x0041c35b:	movl %eax, -32(%ebp)
0x0041c35e:	movl -100(%ebp), %ebx
0x0041c361:	movl -96(%ebp), %ebx
0x0041c364:	movl -92(%ebp), %ebx
0x0041c367:	cmpl %eax, %ebx
0x0041c369:	je 9
0x0041c36b:	pushl %eax
0x0041c36c:	call 0x0041d3a9
0x0041c371:	addl %esp, $0x4<UINT8>
0x0041c374:	movl %eax, -52(%ebp)
0x0041c377:	cmpl %eax, %ebx
0x0041c379:	je 9
0x0041c37b:	pushl %eax
0x0041c37c:	call 0x0041d3a9
0x0041c381:	addl %esp, $0x4<UINT8>
0x0041c384:	movl -32(%ebp), %ebx
0x0041c387:	movl -28(%ebp), %ebx
0x0041c38a:	movl -24(%ebp), %ebx
0x0041c38d:	movb -4(%ebp), $0x6<UINT8>
0x0041c391:	movb 0x448860, %bl
0x0041c397:	movl %ebx, $0x28<UINT32>
0x0041c39c:	leal %esi, -32(%ebp)
0x0041c39f:	movl -20(%ebp), $0x1<UINT32>
0x0041c3a6:	call 0x004034c0
0x0041c3ab:	movl %eax, -28(%ebp)
0x0041c3ae:	movl %edx, 0x440d50
0x0041c3b4:	movl %ecx, -32(%ebp)
0x0041c3b7:	movl (%eax,%ecx), %edx
0x0041c3ba:	movl %edx, 0x440d54
0x0041c3c0:	movl 0x4(%eax,%ecx), %edx
0x0041c3c4:	movl %edx, 0x440d58
0x0041c3ca:	movl 0x8(%eax,%ecx), %edx
0x0041c3ce:	movl %edx, 0x440d5c
0x0041c3d4:	movl 0xc(%eax,%ecx), %edx
0x0041c3d8:	movl %edx, 0x440d60
0x0041c3de:	movl 0x10(%eax,%ecx), %edx
0x0041c3e2:	movl %edx, 0x440d64
0x0041c3e8:	movl 0x14(%eax,%ecx), %edx
0x0041c3ec:	addl %eax, $0x18<UINT8>
0x0041c3ef:	movl -28(%ebp), %eax
0x0041c3f2:	xorl %eax, %eax
0x0041c3f4:	movl -52(%ebp), %eax
0x0041c3f7:	movl -48(%ebp), %eax
0x0041c3fa:	movl -44(%ebp), %eax
0x0041c3fd:	movb -4(%ebp), $0x8<UINT8>
0x0041c401:	leal %esi, -52(%ebp)
0x0041c404:	movl -40(%ebp), $0x1<UINT32>
0x0041c40b:	movb 0x448860, %al
0x0041c410:	call 0x004034c0
0x0041c415:	movl %eax, -48(%ebp)
0x0041c418:	movl %edx, 0x440c20
0x0041c41e:	movl %ecx, -52(%ebp)
0x0041c421:	movl (%eax,%ecx), %edx
0x0041c424:	movl %edx, 0x440c24
0x0041c42a:	movl 0x4(%eax,%ecx), %edx
0x0041c42e:	movl %edx, 0x440c28
0x0041c434:	movl 0x8(%eax,%ecx), %edx
0x0041c438:	movl %edx, 0x440c2c
0x0041c43e:	movl 0xc(%eax,%ecx), %edx
0x0041c442:	movl %edx, 0x440c30
0x0041c448:	movl 0x10(%eax,%ecx), %edx
0x0041c44c:	movl %edx, 0x440c34
0x0041c452:	movl 0x14(%eax,%ecx), %edx
0x0041c456:	addl %eax, $0x18<UINT8>
0x0041c459:	movl -48(%ebp), %eax
0x0041c45c:	pushl $0x1<UINT8>
0x0041c45e:	xorl %ebx, %ebx
0x0041c460:	pushl %ebx
0x0041c461:	leal %eax, -32(%ebp)
0x0041c464:	pushl %eax
0x0041c465:	leal %ecx, -116(%ebp)
0x0041c468:	pushl %ecx
0x0041c469:	movb -4(%ebp), $0x9<UINT8>
0x0041c46d:	call 0x0041b070
0x0041c472:	movl -56(%ebp), %eax
0x0041c475:	pushl $0x1<UINT8>
0x0041c477:	pushl %ebx
0x0041c478:	movl %edx, %esi
0x0041c47a:	pushl %edx
0x0041c47b:	leal %eax, -164(%ebp)
0x0041c481:	pushl %eax
0x0041c482:	movb -4(%ebp), $0xa<UINT8>
0x0041c486:	call 0x0041b070
0x0041c48b:	movl %edi, 0x43b01c
0x0041c491:	movl %esi, %eax
0x0041c493:	movl %eax, (%esi)
0x0041c495:	addl %esp, $0x20<UINT8>
0x0041c498:	pushl %eax
0x0041c499:	call GetModuleHandleA@kernel32.dll
0x0041c49b:	cmpl %eax, %ebx
0x0041c49d:	jne 0x0041c4a8
0x0041c4a8:	movl %ecx, -56(%ebp)
0x0041c4ab:	movl %ecx, (%ecx)
0x0041c4ad:	pushl %ecx
0x0041c4ae:	pushl %eax
0x0041c4af:	call GetProcAddress@kernel32.dll
0x0041c4b5:	movl -56(%ebp), %eax
0x0041c4b8:	cmpl %eax, %ebx
0x0041c4ba:	jne 0x0041c4c2
0x0041c4c2:	movl %eax, -164(%ebp)
0x0041c4c8:	cmpl %eax, %ebx
0x0041c4ca:	je 9
0x0041c4cc:	pushl %eax
0x0041c4cd:	call 0x0041d3a9
0x0041c4d2:	addl %esp, $0x4<UINT8>
0x0041c4d5:	movl %eax, -116(%ebp)
0x0041c4d8:	movl -164(%ebp), %ebx
0x0041c4de:	movl -160(%ebp), %ebx
0x0041c4e4:	movl -156(%ebp), %ebx
0x0041c4ea:	cmpl %eax, %ebx
0x0041c4ec:	je 9
0x0041c4ee:	pushl %eax
0x0041c4ef:	call 0x0041d3a9
0x0041c4f4:	addl %esp, $0x4<UINT8>
0x0041c4f7:	movl %eax, -52(%ebp)
0x0041c4fa:	movl -116(%ebp), %ebx
0x0041c4fd:	movl -112(%ebp), %ebx
0x0041c500:	movl -108(%ebp), %ebx
0x0041c503:	cmpl %eax, %ebx
0x0041c505:	je 9
0x0041c507:	pushl %eax
0x0041c508:	call 0x0041d3a9
0x0041c50d:	addl %esp, $0x4<UINT8>
0x0041c510:	movl %eax, -32(%ebp)
0x0041c513:	cmpl %eax, %ebx
0x0041c515:	je 9
0x0041c517:	pushl %eax
0x0041c518:	call 0x0041d3a9
0x0041c51d:	addl %esp, $0x4<UINT8>
0x0041c520:	movl -32(%ebp), %ebx
0x0041c523:	movl -28(%ebp), %ebx
0x0041c526:	movl -24(%ebp), %ebx
0x0041c529:	movb -4(%ebp), $0xb<UINT8>
0x0041c52d:	movb 0x448860, %bl
0x0041c533:	movl %ebx, $0x28<UINT32>
0x0041c538:	leal %esi, -32(%ebp)
0x0041c53b:	movl -20(%ebp), $0x1<UINT32>
0x0041c542:	call 0x004034c0
0x0041c547:	movl %eax, -28(%ebp)
0x0041c54a:	movl %edx, 0x440d6c
0x0041c550:	movl %ecx, -32(%ebp)
0x0041c553:	movl (%eax,%ecx), %edx
0x0041c556:	movl %edx, 0x440d70
0x0041c55c:	movl 0x4(%eax,%ecx), %edx
0x0041c560:	movl %edx, 0x440d74
0x0041c566:	movl 0x8(%eax,%ecx), %edx
0x0041c56a:	movl %edx, 0x440d78
0x0041c570:	movl 0xc(%eax,%ecx), %edx
0x0041c574:	movl %edx, 0x440d7c
0x0041c57a:	movl 0x10(%eax,%ecx), %edx
0x0041c57e:	movl %edx, 0x440d80
0x0041c584:	movl 0x14(%eax,%ecx), %edx
0x0041c588:	addl %eax, $0x18<UINT8>
0x0041c58b:	movl -28(%ebp), %eax
0x0041c58e:	xorl %eax, %eax
0x0041c590:	movl -52(%ebp), %eax
0x0041c593:	movl -48(%ebp), %eax
0x0041c596:	movl -44(%ebp), %eax
0x0041c599:	movb -4(%ebp), $0xd<UINT8>
0x0041c59d:	leal %esi, -52(%ebp)
0x0041c5a0:	movl -40(%ebp), $0x1<UINT32>
0x0041c5a7:	movb 0x448860, %al
0x0041c5ac:	call 0x004034c0
0x0041c5b1:	movl %eax, -48(%ebp)
0x0041c5b4:	movl %edx, 0x440c20
0x0041c5ba:	movl %ecx, -52(%ebp)
0x0041c5bd:	movl (%eax,%ecx), %edx
0x0041c5c0:	movl %edx, 0x440c24
0x0041c5c6:	movl 0x4(%eax,%ecx), %edx
0x0041c5ca:	movl %edx, 0x440c28
0x0041c5d0:	movl 0x8(%eax,%ecx), %edx
0x0041c5d4:	movl %edx, 0x440c2c
0x0041c5da:	movl 0xc(%eax,%ecx), %edx
0x0041c5de:	movl %edx, 0x440c30
0x0041c5e4:	movl 0x10(%eax,%ecx), %edx
0x0041c5e8:	movl %edx, 0x440c34
0x0041c5ee:	movl 0x14(%eax,%ecx), %edx
0x0041c5f2:	addl %eax, $0x18<UINT8>
0x0041c5f5:	movl -48(%ebp), %eax
0x0041c5f8:	pushl $0x1<UINT8>
0x0041c5fa:	pushl $0x0<UINT8>
0x0041c5fc:	leal %eax, -32(%ebp)
0x0041c5ff:	pushl %eax
0x0041c600:	leal %ecx, -180(%ebp)
0x0041c606:	pushl %ecx
0x0041c607:	movb -4(%ebp), $0xe<UINT8>
0x0041c60b:	call 0x0041b070
0x0041c610:	movl %ebx, %eax
0x0041c612:	pushl $0x1<UINT8>
0x0041c614:	pushl $0x0<UINT8>
0x0041c616:	movl %edx, %esi
0x0041c618:	pushl %edx
0x0041c619:	leal %eax, -148(%ebp)
0x0041c61f:	pushl %eax
0x0041c620:	movb -4(%ebp), $0xf<UINT8>
0x0041c624:	call 0x0041b070
0x0041c629:	movl %esi, %eax
0x0041c62b:	movl %eax, (%esi)
0x0041c62d:	addl %esp, $0x20<UINT8>
0x0041c630:	pushl %eax
0x0041c631:	call GetModuleHandleA@kernel32.dll
0x0041c633:	xorl %edi, %edi
0x0041c635:	cmpl %eax, %edi
0x0041c637:	jne 0x0041c642
0x0041c642:	movl %ebx, (%ebx)
0x0041c644:	pushl %ebx
0x0041c645:	pushl %eax
0x0041c646:	call GetProcAddress@kernel32.dll
0x0041c64c:	movl -80(%ebp), %eax
0x0041c64f:	cmpl %eax, %edi
0x0041c651:	jne 0x0041c659
0x0041c659:	movl %eax, -148(%ebp)
0x0041c65f:	cmpl %eax, %edi
0x0041c661:	je 9
0x0041c663:	pushl %eax
0x0041c664:	call 0x0041d3a9
0x0041c669:	addl %esp, $0x4<UINT8>
0x0041c66c:	movl %eax, -180(%ebp)
0x0041c672:	movl -148(%ebp), %edi
0x0041c678:	movl -144(%ebp), %edi
0x0041c67e:	movl -140(%ebp), %edi
0x0041c684:	cmpl %eax, %edi
0x0041c686:	je 9
0x0041c688:	pushl %eax
0x0041c689:	call 0x0041d3a9
0x0041c68e:	addl %esp, $0x4<UINT8>
0x0041c691:	movl %eax, -52(%ebp)
0x0041c694:	movl -180(%ebp), %edi
0x0041c69a:	movl -176(%ebp), %edi
0x0041c6a0:	movl -172(%ebp), %edi
0x0041c6a6:	cmpl %eax, %edi
0x0041c6a8:	je 9
0x0041c6aa:	pushl %eax
0x0041c6ab:	call 0x0041d3a9
0x0041c6b0:	addl %esp, $0x4<UINT8>
0x0041c6b3:	movb -4(%ebp), $0x0<UINT8>
0x0041c6b7:	movl %eax, -32(%ebp)
0x0041c6ba:	cmpl %eax, %edi
0x0041c6bc:	je 9
0x0041c6be:	pushl %eax
0x0041c6bf:	call 0x0041d3a9
0x0041c6c4:	addl %esp, $0x4<UINT8>
0x0041c6c7:	pushl %edi
0x0041c6c8:	pushl $0x4198c0<UINT32>
0x0041c6cd:	call EnumWindows@User32.dll
EnumWindows@User32.dll: API Node	
0x0041c6d0:	cmpl 0x448a18, %edi
0x0041c6d6:	movl -60(%ebp), %edi
0x0041c6d9:	jl 490
0x0041c6df:	movl %esi, %edi
0x0041c6e1:	movl %ecx, 0x448a20(,%esi,4)
0x0041c6e8:	pushl %ecx
0x0041c6e9:	call GetWindowTextLengthA@User32.dll
GetWindowTextLengthA@User32.dll: API Node	
0x0041c6ec:	movl %edi, %eax
0x0041c6ee:	testl %edi, %edi
0x0041c6f0:	jle 0x0041c8b9
0x0041c8b9:	incl %esi
0x0041c8ba:	cmpl %esi, 0x448a18
0x0041c8c0:	movl -60(%ebp), %esi
0x0041c8c3:	jle -488
0x0041c8c9:	movl %eax, 0x8(%ebp)
0x0041c8cc:	movl %eax, 0x4(%eax)
0x0041c8cf:	movl -36(%ebp), $0x0<UINT32>
0x0041c8d6:	testl %eax, %eax
0x0041c8d8:	jbe 67
0x0041c8da:	xorl %ebx, %ebx
0x0041c8dc:	cmpl -72(%ebp), %ebx
0x0041c8df:	jbe 0x0041c90e
0x0041c90e:	movl %eax, -36(%ebp)
0x0041c911:	movl %edx, 0x8(%ebp)
0x0041c914:	incl %eax
0x0041c915:	movl -36(%ebp), %eax
0x0041c918:	cmpl %eax, 0x4(%edx)
0x0041c91b:	jb 0x0041c8da
0x0041c91d:	movl -4(%ebp), $0xffffffff<UINT32>
0x0041c924:	movl %eax, -76(%ebp)
0x0041c927:	testl %eax, %eax
0x0041c929:	je 0x0041c948
0x0041c948:	xorb %al, %al
0x0041c94a:	movl %ecx, -12(%ebp)
0x0041c94d:	movl %fs:0, %ecx
0x0041c954:	popl %ecx
0x0041c955:	popl %edi
0x0041c956:	popl %esi
0x0041c957:	popl %ebx
0x0041c958:	movl %esp, %ebp
0x0041c95a:	popl %ebp
0x0041c95b:	ret

0x0041c0c3:	addl %esp, $0x4<UINT8>
0x0041c0c6:	testb %al, %al
0x0041c0c8:	je 0x0041c0fd
0x0041c0fd:	movl -4(%ebp), $0xffffffff<UINT32>
0x0041c104:	movl %eax, -36(%ebp)
0x0041c107:	testl %eax, %eax
0x0041c109:	je 29
0x0041c10b:	movl %ecx, -4(%eax)
0x0041c10e:	leal %esi, -4(%eax)
0x0041c111:	pushl $0x403390<UINT32>
0x0041c116:	pushl %ecx
0x0041c117:	pushl $0x10<UINT8>
0x0041c119:	pushl %eax
0x0041c11a:	call 0x0041dd38
0x0041dd38:	pushl $0xc<UINT8>
0x0041dd3a:	pushl $0x441380<UINT32>
0x0041dd3f:	call 0x0041fad0
0x0041dd44:	andl -28(%ebp), $0x0<UINT8>
0x0041dd48:	movl %esi, 0xc(%ebp)
0x0041dd4b:	movl %eax, %esi
0x0041dd4d:	imull %eax, 0x10(%ebp)
0x0041dd51:	addl 0x8(%ebp), %eax
0x0041dd54:	andl -4(%ebp), $0x0<UINT8>
0x0041dd58:	decl 0x10(%ebp)
0x0041dd5b:	js 0x0041dd68
0x0041dd5d:	subl 0x8(%ebp), %esi
0x0041dd60:	movl %ecx, 0x8(%ebp)
0x0041dd63:	call 0x00403390
0x00403390:	pushl %esi
0x00403391:	movl %esi, %ecx
0x00403393:	movl %eax, (%esi)
0x00403395:	testl %eax, %eax
0x00403397:	je 9
0x00403399:	pushl %eax
0x0040339a:	call 0x0041d3a9
0x0040339f:	addl %esp, $0x4<UINT8>
0x004033a2:	movl (%esi), $0x0<UINT32>
0x004033a8:	movl 0x4(%esi), $0x0<UINT32>
0x004033af:	movl 0x8(%esi), $0x0<UINT32>
0x004033b6:	popl %esi
0x004033b7:	ret

0x0041dd66:	jmp 0x0041dd58
0x0041dd68:	movl -28(%ebp), $0x1<UINT32>
0x0041dd6f:	movl -4(%ebp), $0xfffffffe<UINT32>
0x0041dd76:	call 0x0041dd83
0x0041dd83:	cmpl -28(%ebp), $0x0<UINT8>
0x0041dd87:	jne 0x0041dd9a
0x0041dd9a:	ret

0x0041dd7b:	call 0x0041fb15
0x0041dd80:	ret $0x10<UINT16>

0x0041c11f:	pushl %esi
0x0041c120:	call 0x0041d3a9
0x0041c125:	addl %esp, $0x4<UINT8>
0x0041c128:	cmpl 0x18(%ebp), $0x0<UINT8>
0x0041c12c:	je 0x0041c1c4
0x0041c1c4:	movb %al, $0x1<UINT8>
0x0041c1c6:	movl %ecx, -12(%ebp)
0x0041c1c9:	movl %fs:0, %ecx
0x0041c1d0:	popl %ecx
0x0041c1d1:	popl %edi
0x0041c1d2:	popl %esi
0x0041c1d3:	popl %ebx
0x0041c1d4:	movl %esp, %ebp
0x0041c1d6:	popl %ebp
0x0041c1d7:	ret

0x0040a476:	addl %esp, $0x14<UINT8>
0x0040a479:	testb %al, %al
0x0040a47b:	je 581
0x0040a481:	pushl %ebx
0x0040a482:	movl %ecx, %esi
0x0040a484:	call 0x00411630
0x00411630:	pushl %ebp
0x00411631:	movl %ebp, %esp
0x00411633:	pushl $0xffffffff<UINT8>
0x00411635:	pushl $0x432e84<UINT32>
0x0041163a:	movl %eax, %fs:0
0x00411640:	pushl %eax
0x00411641:	subl %esp, $0x230<UINT32>
0x00411647:	movl %eax, 0x44609c
0x0041164c:	xorl %eax, %ebp
0x0041164e:	movl -20(%ebp), %eax
0x00411651:	pushl %ebx
0x00411652:	pushl %esi
0x00411653:	pushl %edi
0x00411654:	pushl %eax
0x00411655:	leal %eax, -12(%ebp)
0x00411658:	movl %fs:0, %eax
0x0041165e:	movl %eax, 0x8(%ebp)
0x00411661:	movl %esi, %ecx
0x00411663:	movl -492(%ebp), %eax
0x00411669:	call 0x00405790
0x0041166e:	movl %edi, %eax
0x00411670:	pushl %esi
0x00411671:	movl -440(%ebp), %edi
0x00411677:	call 0x00411070
0x00411070:	pushl %ebp
0x00411071:	movl %ebp, %esp
0x00411073:	pushl $0xffffffff<UINT8>
0x00411075:	pushl $0x431f3a<UINT32>
0x0041107a:	movl %eax, %fs:0
0x00411080:	pushl %eax
0x00411081:	subl %esp, $0xf8<UINT32>
0x00411087:	pushl %ebx
0x00411088:	pushl %esi
0x00411089:	pushl %edi
0x0041108a:	movl %eax, 0x44609c
0x0041108f:	xorl %eax, %ebp
0x00411091:	pushl %eax
0x00411092:	leal %eax, -12(%ebp)
0x00411095:	movl %fs:0, %eax
0x0041109b:	call 0x00405790
0x004110a0:	xorl %ebx, %ebx
0x004110a2:	movl -56(%ebp), %eax
0x004110a5:	cmpl 0xb4(%eax), %ebx
0x004110ab:	jne 1292
0x004110b1:	movl %esi, 0x8(%ebp)
0x004110b4:	pushl $0x1<UINT8>
0x004110b6:	pushl %ebx
0x004110b7:	leal %eax, 0x8(%esi)
0x004110ba:	pushl %eax
0x004110bb:	leal %ecx, -48(%ebp)
0x004110be:	pushl %ecx
0x004110bf:	call 0x0041b070
0x004110c4:	addl %esp, $0x10<UINT8>
0x004110c7:	pushl %ebx
0x004110c8:	pushl $0x440584<UINT32>
0x004110cd:	leal %edx, -120(%ebp)
0x004110d0:	pushl %edx
0x004110d1:	movl -4(%ebp), %ebx
0x004110d4:	call 0x004035d0
0x004110d9:	pushl $0xffffffff<UINT8>
0x004110db:	pushl $0x1<UINT8>
0x004110dd:	leal %eax, -48(%ebp)
0x004110e0:	pushl %eax
0x004110e1:	leal %ecx, -32(%ebp)
0x004110e4:	pushl %ecx
0x004110e5:	leal %ecx, -120(%ebp)
0x004110e8:	movb -4(%ebp), $0x1<UINT8>
0x004110ec:	call 0x00406c40
0x00406c40:	pushl %ebp
0x00406c41:	movl %ebp, %esp
0x00406c43:	pushl $0xffffffff<UINT8>
0x00406c45:	pushl $0x4313c5<UINT32>
0x00406c4a:	movl %eax, %fs:0
0x00406c50:	pushl %eax
0x00406c51:	subl %esp, $0x3c<UINT8>
0x00406c54:	pushl %ebx
0x00406c55:	pushl %esi
0x00406c56:	pushl %edi
0x00406c57:	movl %eax, 0x44609c
0x00406c5c:	xorl %eax, %ebp
0x00406c5e:	pushl %eax
0x00406c5f:	leal %eax, -12(%ebp)
0x00406c62:	movl %fs:0, %eax
0x00406c68:	movl %esi, %ecx
0x00406c6a:	leal %ecx, -36(%ebp)
0x00406c6d:	movl -16(%ebp), $0x0<UINT32>
0x00406c74:	call 0x00406a10
0x00406c79:	movl -4(%ebp), $0x1<UINT32>
0x00406c80:	cmpl 0x4(%esi), $0x0<UINT8>
0x00406c84:	jne 0x00406c98
0x00406c98:	movl %edi, 0xc(%ebp)
0x00406c9b:	pushl $0x0<UINT8>
0x00406c9d:	movl %eax, %esi
0x00406c9f:	movl %ecx, %edi
0x00406ca1:	call 0x004068e0
0x004068e0:	pushl %ebp
0x004068e1:	movl %ebp, %esp
0x004068e3:	subl %esp, $0xc<UINT8>
0x004068e6:	movl %edx, (%eax)
0x004068e8:	pushl %esi
0x004068e9:	movl %esi, 0x4(%eax)
0x004068ec:	movl %eax, (%ecx)
0x004068ee:	pushl %edi
0x004068ef:	movl %edi, 0x4(%ecx)
0x004068f2:	movl -8(%ebp), %edx
0x004068f5:	movl -12(%ebp), %eax
0x004068f8:	testl %eax, %eax
0x004068fa:	je 247
0x00406900:	testl %edx, %edx
0x00406902:	je 239
0x00406908:	cmpl %eax, %edx
0x0040690a:	jne 0x00406916
0x00406916:	testl %edi, %edi
0x00406918:	jne 0x00406938
0x00406938:	testl %esi, %esi
0x0040693a:	jne 0x0040694c
0x0040694c:	movl %eax, 0x8(%ebp)
0x0040694f:	cmpl %eax, $0xffffffff<UINT8>
0x00406952:	je 159
0x00406958:	cmpl %eax, %edi
0x0040695a:	je 151
0x00406960:	leal %eax, 0x1(%esi)
0x00406963:	pushl %ebx
0x00406964:	pushl %eax
0x00406965:	call 0x0041cfd3
0x0040696a:	movl %ecx, -8(%ebp)
0x0040696d:	pushl %esi
0x0040696e:	movl %ebx, %eax
0x00406970:	pushl %ecx
0x00406971:	pushl %ebx
0x00406972:	movl -4(%ebp), %ebx
0x00406975:	call 0x00420c20
0x0040697a:	subl %edi, 0x8(%ebp)
0x0040697d:	movb (%ebx,%esi), $0x0<UINT8>
0x00406981:	leal %edx, 0x1(%edi)
0x00406984:	pushl %edx
0x00406985:	call 0x0041cfd3
0x0040698a:	movl %ecx, 0x8(%ebp)
0x0040698d:	movl %ebx, %eax
0x0040698f:	movl %eax, -12(%ebp)
0x00406992:	pushl %edi
0x00406993:	addl %eax, %ecx
0x00406995:	pushl %eax
0x00406996:	pushl %ebx
0x00406997:	call 0x00420c20
0x0040699c:	movl %edx, -4(%ebp)
0x0040699f:	addl %esp, $0x20<UINT8>
0x004069a2:	movb (%ebx,%edi), $0x0<UINT8>
0x004069a6:	movl -12(%ebp), $0xffffffff<UINT32>
0x004069ad:	pushl %edi
0x004069ae:	cmpl %esi, $0x20<UINT8>
0x004069b1:	ja 12
0x004069b3:	pushl %edx
0x004069b4:	pushl %ebx
0x004069b5:	call 0x004061b0
0x0040624a:	leal %edi, (%ecx,%edx)
0x0040624d:	movzbl %edi, -1(%edi,%ebx)
0x00406252:	movl %edi, -1024(%ebp,%edi,4)
0x00406259:	addl %eax, %eax
0x0040625b:	andl %edi, %eax
0x0040625d:	movl %eax, %edi
0x0040625f:	jne 0x00406245
0x004069ba:	addl %esp, $0xc<UINT8>
0x004069bd:	jmp 0x004069ca
0x004069ca:	testl %eax, %eax
0x004069cc:	je 0x004069d7
0x004069ce:	subl %eax, %ebx
0x004069d0:	addl %eax, 0x8(%ebp)
0x004069d3:	movl %esi, %eax
0x004069d5:	jmp 0x004069da
0x004069da:	pushl %ebx
0x004069db:	call 0x0041d3a9
0x004069e0:	movl %eax, -4(%ebp)
0x004069e3:	pushl %eax
0x004069e4:	call 0x0041d3a9
0x004069e9:	addl %esp, $0x8<UINT8>
0x004069ec:	popl %ebx
0x004069ed:	popl %edi
0x004069ee:	movl %eax, %esi
0x004069f0:	popl %esi
0x004069f1:	movl %esp, %ebp
0x004069f3:	popl %ebp
0x004069f4:	ret $0x4<UINT16>

0x00406ca6:	movl %edx, %eax
0x00406ca8:	movl -20(%ebp), %edx
0x00406cab:	cmpl %edx, $0xffffffff<UINT8>
0x00406cae:	je 0x00406e16
0x00406cb4:	cmpl 0x10(%ebp), $0x0<UINT8>
0x00406cb8:	pushl %edi
0x00406cb9:	je 0x00406cd0
0x00406cbb:	xorl %ecx, %ecx
0x00406cbd:	leal %edi, -68(%ebp)
0x00406cc0:	call 0x00406a80
0x00406cc5:	movb -4(%ebp), $0x2<UINT8>
0x00406cc9:	movl %ebx, $0x2<UINT32>
0x00406cce:	jmp 0x00406ced
0x00406ced:	movl %ecx, 0x4(%eax)
0x00406cf0:	movl %edx, (%eax)
0x00406cf2:	movl %eax, %ecx
0x00406cf4:	pushl %edx
0x00406cf5:	leal %ecx, -36(%ebp)
0x00406cf8:	movl -16(%ebp), %ebx
0x00406cfb:	call 0x00406e50
0x00406d00:	xorl %edi, %edi
0x00406d02:	testb %bl, $0x4<UINT8>
0x00406d05:	je 0x00406d26
0x00406d26:	movl -4(%ebp), $0x1<UINT32>
0x00406d2d:	testb %bl, $0x2<UINT8>
0x00406d30:	je 0x00406d48
0x00406d32:	movl %eax, -68(%ebp)
0x00406d35:	andl %ebx, $0xfffffffd<UINT8>
0x00406d38:	movl -16(%ebp), %ebx
0x00406d3b:	cmpl %eax, %edi
0x00406d3d:	je 9
0x00406d3f:	pushl %eax
0x00406d40:	call 0x0041d3a9
0x00406d45:	addl %esp, $0x4<UINT8>
0x00406d48:	movl %edi, 0x14(%ebp)
0x00406d4b:	cmpl %edi, $0xffffffff<UINT8>
0x00406d4e:	jne 0x00406dd6
0x00406d54:	cmpl 0x10(%ebp), $0x0<UINT8>
0x00406d58:	je 0x00406d75
0x00406d5a:	leal %eax, -36(%ebp)
0x00406d5d:	pushl %eax
0x00406d5e:	leal %ecx, -52(%ebp)
0x00406d61:	pushl %ecx
0x00406d62:	movl %ecx, %esi
0x00406d64:	call 0x00406b60
0x00406b60:	pushl %ebp
0x00406b61:	movl %ebp, %esp
0x00406b63:	pushl $0xffffffff<UINT8>
0x00406b65:	pushl $0x430ba9<UINT32>
0x00406b6a:	movl %eax, %fs:0
0x00406b70:	pushl %eax
0x00406b71:	pushl %ecx
0x00406b72:	pushl %esi
0x00406b73:	pushl %edi
0x00406b74:	movl %eax, 0x44609c
0x00406b79:	xorl %eax, %ebp
0x00406b7b:	pushl %eax
0x00406b7c:	leal %eax, -12(%ebp)
0x00406b7f:	movl %fs:0, %eax
0x00406b85:	movl %edi, %ecx
0x00406b87:	movl %esi, 0x8(%ebp)
0x00406b8a:	movl %eax, 0xc(%ebp)
0x00406b8d:	pushl %eax
0x00406b8e:	movl -4(%ebp), $0x0<UINT32>
0x00406b95:	pushl %esi
0x00406b96:	movl -16(%ebp), $0x0<UINT32>
0x00406b9d:	call 0x00406bd0
0x00406ba2:	movl -4(%ebp), $0x0<UINT32>
0x00406ba9:	movl %eax, 0x4(%edi)
0x00406bac:	movl %edi, (%edi)
0x00406bae:	pushl %edi
0x00406baf:	movl %ecx, %esi
0x00406bb1:	movl -16(%ebp), $0x1<UINT32>
0x00406bb8:	call 0x00403540
0x00406bbd:	movl %eax, %esi
0x00406bbf:	movl %ecx, -12(%ebp)
0x00406bc2:	movl %fs:0, %ecx
0x00406bc9:	popl %ecx
0x00406bca:	popl %edi
0x00406bcb:	popl %esi
0x00406bcc:	movl %esp, %ebp
0x00406bce:	popl %ebp
0x00406bcf:	ret

0x00406d69:	addl %esp, $0x8<UINT8>
0x00406d6c:	movb -4(%ebp), $0x4<UINT8>
0x00406d70:	orl %ebx, $0x8<UINT8>
0x00406d73:	jmp 0x00406d8f
0x00406d8f:	movl %ecx, 0x4(%eax)
0x00406d92:	movl %eax, (%eax)
0x00406d94:	pushl %eax
0x00406d95:	movl %eax, %ecx
0x00406d97:	leal %ecx, -36(%ebp)
0x00406d9a:	movl -16(%ebp), %ebx
0x00406d9d:	call 0x00406e50
0x00406da2:	movl -4(%ebp), $0x4<UINT32>
0x00406da9:	testb %bl, $0x10<UINT8>
0x00406dac:	je 0x00406dbc
0x00406dbc:	movl -4(%ebp), $0x1<UINT32>
0x00406dc3:	testb %bl, $0x8<UINT8>
0x00406dc6:	je 0x00406dd6
0x00406dc8:	andl %ebx, $0xfffffff7<UINT8>
0x00406dcb:	leal %ecx, -52(%ebp)
0x00406dce:	movl -16(%ebp), %ebx
0x00406dd1:	call 0x00403390
0x00406dd6:	cmpl 0x10(%ebp), $0x0<UINT8>
0x00406dda:	je 0x00406df8
0x00406ddc:	cmpl %edi, $0x1<UINT8>
0x00406ddf:	jne 0x00406deb
0x00406deb:	movl %edx, 0x4(%esi)
0x00406dee:	addl %edx, -20(%ebp)
0x00406df1:	movl %eax, 0xc(%ebp)
0x00406df4:	xorl %ecx, %ecx
0x00406df6:	jmp 0x00406e11
0x00406e11:	call 0x004033c0
0x004033f0:	movl %edx, (%eax)
0x004033f2:	addl %edx, %ecx
0x004033f4:	movb %dl, (%edx,%esi)
0x004033f7:	jmp 0x004033ff
0x00406e16:	movl %esi, 0x8(%ebp)
0x00406e19:	leal %ecx, -36(%ebp)
0x00406e1c:	pushl %ecx
0x00406e1d:	pushl %esi
0x00406e1e:	call 0x00406bd0
0x00406e23:	movl %eax, -36(%ebp)
0x00406e26:	testl %eax, %eax
0x00406e28:	je 9
0x00406e2a:	pushl %eax
0x00406e2b:	call 0x0041d3a9
0x00406e30:	addl %esp, $0x4<UINT8>
0x00406e33:	movl %eax, %esi
0x00406e35:	movl %ecx, -12(%ebp)
0x00406e38:	movl %fs:0, %ecx
0x00406e3f:	popl %ecx
0x00406e40:	popl %edi
0x00406e41:	popl %esi
0x00406e42:	popl %ebx
0x00406e43:	movl %esp, %ebp
0x00406e45:	popl %ebp
0x00406e46:	ret

0x004110f1:	addl %esp, $0x10<UINT8>
0x004110f4:	movb -4(%ebp), $0x3<UINT8>
0x004110f8:	movl %eax, -120(%ebp)
0x004110fb:	cmpl %eax, %ebx
0x004110fd:	je 9
0x004110ff:	pushl %eax
0x00411100:	call 0x0041d3a9
0x00411105:	addl %esp, $0x4<UINT8>
0x00411108:	movl %eax, (%esi)
0x0041110a:	cmpl %eax, %ebx
0x0041110c:	movl -120(%ebp), %ebx
0x0041110f:	movl -116(%ebp), %ebx
0x00411112:	movl -112(%ebp), %ebx
0x00411115:	jnl 0x00411156
0x00411156:	movl %ecx, -48(%ebp)
0x00411159:	movl %eax, -44(%ebp)
0x0041115c:	pushl %ecx
0x0041115d:	leal %ecx, -32(%ebp)
0x00411160:	call 0x00403540
0x00411165:	leal %edx, -48(%ebp)
0x00411168:	leal %eax, -32(%ebp)
0x0041116b:	pushl %edx
0x0041116c:	pushl %eax
0x0041116d:	movl %edi, %eax
0x0041116f:	call 0x00414f80
0x00414f80:	pushl %ebp
0x00414f81:	movl %ebp, %esp
0x00414f83:	pushl $0xffffffff<UINT8>
0x00414f85:	pushl $0x431420<UINT32>
0x00414f8a:	movl %eax, %fs:0
0x00414f90:	pushl %eax
0x00414f91:	subl %esp, $0x30<UINT8>
0x00414f94:	pushl %ebx
0x00414f95:	pushl %esi
0x00414f96:	movl %eax, 0x44609c
0x00414f9b:	xorl %eax, %ebp
0x00414f9d:	pushl %eax
0x00414f9e:	leal %eax, -12(%ebp)
0x00414fa1:	movl %fs:0, %eax
0x00414fa7:	movl %eax, 0x8(%ebp)
0x00414faa:	pushl %eax
0x00414fab:	leal %ecx, -44(%ebp)
0x00414fae:	pushl %ecx
0x00414faf:	call 0x00406bd0
0x00414fb4:	xorl %eax, %eax
0x00414fb6:	movl -4(%ebp), %eax
0x00414fb9:	movl -28(%ebp), %eax
0x00414fbc:	movl -24(%ebp), %eax
0x00414fbf:	movl -20(%ebp), %eax
0x00414fc2:	movl %ecx, $0x1<UINT32>
0x00414fc7:	movb -4(%ebp), %cl
0x00414fca:	leal %ebx, 0x13(%eax)
0x00414fcd:	leal %esi, -28(%ebp)
0x00414fd0:	movl -16(%ebp), %ecx
0x00414fd3:	movb 0x448860, %al
0x00414fd8:	call 0x004034c0
0x00414fdd:	movl %eax, -24(%ebp)
0x00414fe0:	movl %esi, -28(%ebp)
0x00414fe3:	movw %dx, 0x440584
0x00414fea:	movw (%eax,%esi), %dx
0x00414fee:	movb %cl, 0x440586
0x00414ff4:	movb 0x2(%eax,%esi), %cl
0x00414ff8:	addl %eax, $0x3<UINT8>
0x00414ffb:	movl -24(%ebp), %eax
0x00414ffe:	pushl $0x1<UINT8>
0x00415000:	xorl %ebx, %ebx
0x00415002:	pushl %ebx
0x00415003:	leal %edx, -44(%ebp)
0x00415006:	pushl %edx
0x00415007:	leal %eax, -60(%ebp)
0x0041500a:	pushl %eax
0x0041500b:	leal %ecx, -28(%ebp)
0x0041500e:	movb -4(%ebp), $0x2<UINT8>
0x00415012:	call 0x00406c40
0x00406cd0:	movl %eax, 0x4(%esi)
0x00406cd3:	leal %ecx, (%eax,%edx)
0x00406cd6:	orl %edx, $0xffffffff<UINT8>
0x00406cd9:	leal %edi, -52(%ebp)
0x00406cdc:	call 0x00406a80
0x00406ce1:	movl -4(%ebp), $0x3<UINT32>
0x00406ce8:	movl %ebx, $0x4<UINT32>
0x00406d07:	movl %eax, -52(%ebp)
0x00406d0a:	andl %ebx, $0xfffffffb<UINT8>
0x00406d0d:	movl -16(%ebp), %ebx
0x00406d10:	cmpl %eax, %edi
0x00406d12:	je 9
0x00406d14:	pushl %eax
0x00406d15:	call 0x0041d3a9
0x00406d1a:	addl %esp, $0x4<UINT8>
0x00406d1d:	movl -52(%ebp), %edi
0x00406d20:	movl -48(%ebp), %edi
0x00406d23:	movl -44(%ebp), %edi
0x00406df8:	cmpl %edi, $0x1<UINT8>
0x00406dfb:	jne 0x00406e05
0x00406dfd:	movl %ecx, 0x4(%esi)
0x00406e00:	addl %ecx, -20(%ebp)
0x00406e03:	jmp 0x00406e08
0x00406e08:	movl %eax, 0xc(%ebp)
0x00406e0b:	movl %edx, 0x4(%eax)
0x00406e0e:	subl %edx, -20(%ebp)
0x00415017:	addl %esp, $0x10<UINT8>
0x0041501a:	movb -4(%ebp), $0x3<UINT8>
0x0041501e:	movl %ecx, 0x4(%eax)
0x00415021:	movl %edx, (%eax)
0x00415023:	movl %eax, %ecx
0x00415025:	pushl %edx
0x00415026:	movl %ecx, %edi
0x00415028:	call 0x00406e50
0x0041502d:	movl %eax, -60(%ebp)
0x00415030:	cmpl %eax, %ebx
0x00415032:	je 9
0x00415034:	pushl %eax
0x00415035:	call 0x0041d3a9
0x0041503a:	addl %esp, $0x4<UINT8>
0x0041503d:	movl -60(%ebp), %ebx
0x00415040:	movl -56(%ebp), %ebx
0x00415043:	movl -52(%ebp), %ebx
0x00415046:	movb -4(%ebp), %bl
0x00415049:	cmpl %esi, %ebx
0x0041504b:	je 9
0x0041504d:	pushl %esi
0x0041504e:	call 0x0041d3a9
0x00415053:	addl %esp, $0x4<UINT8>
0x00415056:	cmpl 0x4(%edi), %ebx
0x00415059:	jne 0x00415076
0x00415076:	movl -28(%ebp), %ebx
0x00415079:	movl -24(%ebp), %ebx
0x0041507c:	movl -20(%ebp), %ebx
0x0041507f:	movb -4(%ebp), $0x4<UINT8>
0x00415083:	movb 0x448860, %bl
0x00415089:	movl %ebx, $0x11<UINT32>
0x0041508e:	leal %esi, -28(%ebp)
0x00415091:	movl -16(%ebp), $0x1<UINT32>
0x00415098:	call 0x004034c0
0x0041509d:	movl %eax, -24(%ebp)
0x004150a0:	movb %cl, 0x440590
0x004150a6:	movl %esi, -28(%ebp)
0x004150a9:	movb (%eax,%esi), %cl
0x004150ac:	incl %eax
0x004150ad:	movl -24(%ebp), %eax
0x004150b0:	pushl $0xffffffff<UINT8>
0x004150b2:	pushl $0x0<UINT8>
0x004150b4:	leal %edx, -60(%ebp)
0x004150b7:	pushl %edi
0x004150b8:	pushl %edx
0x004150b9:	leal %ecx, -28(%ebp)
0x004150bc:	movb -4(%ebp), $0x5<UINT8>
0x004150c0:	call 0x00406c40
0x00406d75:	leal %edx, -68(%ebp)
0x00406d78:	pushl %esi
0x00406d79:	pushl %edx
0x00406d7a:	leal %ecx, -36(%ebp)
0x00406d7d:	call 0x00406b60
0x00406d82:	addl %esp, $0x8<UINT8>
0x00406d85:	movl -4(%ebp), $0x5<UINT32>
0x00406d8c:	orl %ebx, $0x10<UINT8>
0x00406dae:	andl %ebx, $0xffffffef<UINT8>
0x00406db1:	leal %ecx, -68(%ebp)
0x00406db4:	movl -16(%ebp), %ebx
0x00406db7:	call 0x00403390
0x00406e05:	movl %ecx, -20(%ebp)
0x004150c5:	addl %esp, $0x10<UINT8>
0x004150c8:	movl %ebx, 0xc(%ebp)
0x004150cb:	movb -4(%ebp), $0x6<UINT8>
0x004150cf:	movl %ecx, 0x4(%eax)
0x004150d2:	movl %eax, (%eax)
0x004150d4:	pushl %eax
0x004150d5:	movl %eax, %ecx
0x004150d7:	movl %ecx, %ebx
0x004150d9:	call 0x00406e50
0x004150de:	movl %eax, -60(%ebp)
0x004150e1:	testl %eax, %eax
0x004150e3:	je 9
0x004150e5:	pushl %eax
0x004150e6:	call 0x0041d3a9
0x004150eb:	addl %esp, $0x4<UINT8>
0x004150ee:	xorl %eax, %eax
0x004150f0:	movl -60(%ebp), %eax
0x004150f3:	movl -56(%ebp), %eax
0x004150f6:	movl -52(%ebp), %eax
0x004150f9:	movb -4(%ebp), %al
0x004150fc:	cmpl %esi, %eax
0x004150fe:	je 9
0x00415100:	pushl %esi
0x00415101:	call 0x0041d3a9
0x00415106:	addl %esp, $0x4<UINT8>
0x00415109:	cmpl 0x4(%ebx), $0x0<UINT8>
0x0041510d:	jne 0x0041511b
0x0041511b:	leal %ecx, -44(%ebp)
0x0041511e:	pushl %ecx
0x0041511f:	leal %edx, -60(%ebp)
0x00415122:	pushl %edx
0x00415123:	movl %ecx, %edi
0x00415125:	call 0x00406b60
0x0041512a:	addl %esp, $0x8<UINT8>
0x0041512d:	movb -4(%ebp), $0x7<UINT8>
0x00415131:	movl %ecx, (%eax)
0x00415133:	movl %eax, 0x4(%eax)
0x00415136:	pushl %ecx
0x00415137:	movl %ecx, %edi
0x00415139:	call 0x00406e50
0x0041513e:	movl %eax, -60(%ebp)
0x00415141:	testl %eax, %eax
0x00415143:	je 9
0x00415145:	pushl %eax
0x00415146:	call 0x0041d3a9
0x0041514b:	addl %esp, $0x4<UINT8>
0x0041514e:	movl %eax, -44(%ebp)
0x00415151:	testl %eax, %eax
0x00415153:	je 9
0x00415155:	pushl %eax
0x00415156:	call 0x0041d3a9
0x0041515b:	addl %esp, $0x4<UINT8>
0x0041515e:	movl %ecx, -12(%ebp)
0x00415161:	movl %fs:0, %ecx
0x00415168:	popl %ecx
0x00415169:	popl %esi
0x0041516a:	popl %ebx
0x0041516b:	movl %esp, %ebp
0x0041516d:	popl %ebp
0x0041516e:	ret

0x00411174:	addl %esp, $0x8<UINT8>
0x00411177:	pushl $0x43ff64<UINT32>
0x0041117c:	movl %eax, %edi
0x0041117e:	call 0x004055c0
0x004055c0:	pushl %ebp
0x004055c1:	movl %ebp, %esp
0x004055c3:	subl %esp, $0x8<UINT8>
0x004055c6:	pushl %ebx
0x004055c7:	movl %ebx, 0x4(%eax)
0x004055ca:	movl %eax, (%eax)
0x004055cc:	pushl %esi
0x004055cd:	movl -8(%ebp), %eax
0x004055d0:	testl %eax, %eax
0x004055d2:	je 234
0x004055d8:	movl %esi, 0x8(%ebp)
0x004055db:	testl %esi, %esi
0x004055dd:	je 223
0x004055e3:	cmpl %eax, %esi
0x004055e5:	jne 0x004055f1
0x004055f1:	testl %ebx, %ebx
0x004055f3:	jne 0x00405618
0x00405618:	leal %ecx, 0x1(%esi)
0x0040561b:	jmp 0x00405620
0x00405620:	movb %al, (%esi)
0x00405622:	incl %esi
0x00405623:	testb %al, %al
0x00405625:	jne 0x00405620
0x00405627:	subl %esi, %ecx
0x00405629:	je -32
0x0040562b:	testl %ebx, %ebx
0x0040562d:	je 143
0x00405633:	leal %eax, 0x1(%esi)
0x00405636:	pushl %edi
0x00405637:	pushl %eax
0x00405638:	call 0x0041cfd3
0x0040563d:	movl %ecx, 0x8(%ebp)
0x00405640:	pushl %esi
0x00405641:	movl %edi, %eax
0x00405643:	pushl %ecx
0x00405644:	pushl %edi
0x00405645:	movl -4(%ebp), %edi
0x00405648:	call 0x00420c20
0x0040564d:	leal %edx, 0x1(%ebx)
0x00405650:	pushl %edx
0x00405651:	movb (%edi,%esi), $0x0<UINT8>
0x00405655:	call 0x0041cfd3
0x0040565a:	movl %edi, %eax
0x0040565c:	movl %eax, -8(%ebp)
0x0040565f:	pushl %ebx
0x00405660:	pushl %eax
0x00405661:	pushl %edi
0x00405662:	call 0x00420c20
0x00405667:	addl %esp, $0x20<UINT8>
0x0040566a:	movb (%edi,%ebx), $0x0<UINT8>
0x0040566e:	movl 0x8(%ebp), $0xffffffff<UINT32>
0x00405675:	pushl %ebx
0x00405676:	cmpl %esi, $0x20<UINT8>
0x00405679:	ja 15
0x0040567b:	movl %ecx, -4(%ebp)
0x0040567e:	pushl %ecx
0x0040567f:	pushl %edi
0x00405680:	call 0x004061b0
0x00405685:	addl %esp, $0xc<UINT8>
0x00405688:	jmp 0x00405698
0x00405698:	testl %eax, %eax
0x0040569a:	je 6
0x0040569c:	subl %eax, %edi
0x0040569e:	movl %esi, %eax
0x004056a0:	jmp 0x004056a5
0x004056a5:	pushl %edi
0x004056a6:	call 0x0041d3a9
0x004056ab:	movl %edx, -4(%ebp)
0x004056ae:	pushl %edx
0x004056af:	call 0x0041d3a9
0x004056b4:	addl %esp, $0x8<UINT8>
0x004056b7:	popl %edi
0x004056b8:	movl %eax, %esi
0x004056ba:	popl %esi
0x004056bb:	popl %ebx
0x004056bc:	movl %esp, %ebp
0x004056be:	popl %ebp
0x004056bf:	ret $0x4<UINT16>

0x00411183:	cmpl %eax, $0xffffffff<UINT8>
0x00411186:	jne 0x004111c5
0x004111c5:	movl %eax, 0x4(%esi)
0x004111c8:	cmpl %eax, %ebx
0x004111ca:	jle 0x00411525
0x00411525:	movl %edx, -32(%ebp)
0x00411528:	movl %esi, -56(%ebp)
0x0041152b:	movl %eax, -28(%ebp)
0x0041152e:	pushl %edx
0x0041152f:	leal %ecx, 0xb0(%esi)
0x00411535:	call 0x00406e50
0x0041153a:	movl %eax, -48(%ebp)
0x0041153d:	pushl %eax
0x0041153e:	movl %eax, -44(%ebp)
0x00411541:	leal %ecx, 0xe0(%esi)
0x00411547:	call 0x00406e50
0x0041154c:	movl %ecx, -48(%ebp)
0x0041154f:	movl %eax, -44(%ebp)
0x00411552:	pushl %ecx
0x00411553:	leal %ecx, 0x100(%esi)
0x00411559:	call 0x00406e50
0x0041155e:	movl %edx, -48(%ebp)
0x00411561:	movl %eax, -44(%ebp)
0x00411564:	pushl %edx
0x00411565:	leal %ecx, 0xc0(%esi)
0x0041156b:	call 0x00406e50
0x00411570:	movl %eax, -48(%ebp)
0x00411573:	pushl %eax
0x00411574:	movl %eax, -44(%ebp)
0x00411577:	leal %ecx, 0xd0(%esi)
0x0041157d:	call 0x00406e50
0x00411582:	movl %ecx, -48(%ebp)
0x00411585:	movl %eax, -44(%ebp)
0x00411588:	pushl %ecx
0x00411589:	leal %ecx, 0xf0(%esi)
0x0041158f:	call 0x00406e50
0x00411594:	movl %eax, -32(%ebp)
0x00411597:	cmpl %eax, %ebx
0x00411599:	je 9
0x0041159b:	pushl %eax
0x0041159c:	call 0x0041d3a9
0x004115a1:	addl %esp, $0x4<UINT8>
0x004115a4:	movl %eax, -48(%ebp)
0x004115a7:	movl -32(%ebp), %ebx
0x004115aa:	movl -28(%ebp), %ebx
0x004115ad:	movl -24(%ebp), %ebx
0x004115b0:	cmpl %eax, %ebx
0x004115b2:	je 9
0x004115b4:	pushl %eax
0x004115b5:	call 0x0041d3a9
0x004115ba:	addl %esp, $0x4<UINT8>
0x004115bd:	movl %eax, $0x1<UINT32>
0x004115c2:	movl %ecx, -12(%ebp)
0x004115c5:	movl %fs:0, %ecx
0x004115cc:	popl %ecx
0x004115cd:	popl %edi
0x004115ce:	popl %esi
0x004115cf:	popl %ebx
0x004115d0:	movl %esp, %ebp
0x004115d2:	popl %ebp
0x004115d3:	ret

0x0041167c:	addl %esp, $0x4<UINT8>
0x0041167f:	testl %eax, %eax
0x00411681:	je 4069
0x00411687:	leal %ecx, -360(%ebp)
0x0041168d:	pushl %edi
0x0041168e:	pushl %ecx
0x0041168f:	call 0x00412980
0x00412980:	pushl %ebp
0x00412981:	movl %ebp, %esp
0x00412983:	pushl $0xffffffff<UINT8>
0x00412985:	pushl $0x431051<UINT32>
0x0041298a:	movl %eax, %fs:0
0x00412990:	pushl %eax
0x00412991:	subl %esp, $0x34<UINT8>
0x00412994:	pushl %ebx
0x00412995:	pushl %esi
0x00412996:	pushl %edi
0x00412997:	movl %eax, 0x44609c
0x0041299c:	xorl %eax, %ebp
0x0041299e:	pushl %eax
0x0041299f:	leal %eax, -12(%ebp)
0x004129a2:	movl %fs:0, %eax
0x004129a8:	xorl %eax, %eax
0x004129aa:	movl -28(%ebp), %eax
0x004129ad:	movl -44(%ebp), %eax
0x004129b0:	movl -40(%ebp), %eax
0x004129b3:	movl -36(%ebp), %eax
0x004129b6:	movl %edi, $0x1<UINT32>
0x004129bb:	movl -4(%ebp), %edi
0x004129be:	leal %ebx, 0x11(%eax)
0x004129c1:	leal %esi, -44(%ebp)
0x004129c4:	movl -32(%ebp), %edi
0x004129c7:	movb 0x448860, %al
0x004129cc:	call 0x004034c0
0x004129d1:	movl %esi, -40(%ebp)
0x004129d4:	movb %al, 0x43faec
0x004129d9:	movl %ebx, -44(%ebp)
0x004129dc:	movb (%ebx,%esi), %al
0x004129df:	addl %esi, %edi
0x004129e1:	movl -40(%ebp), %esi
0x004129e4:	movl -4(%ebp), $0x2<UINT32>
0x004129eb:	jmp 0x004129f0
0x004129f0:	call 46008
0x004129f5:	movl -16(%ebp), %eax
0x004129f8:	fildl -16(%ebp)
0x004129fb:	fdivl 0x440dd0
0x00412a01:	fmull 0x440dc8
0x00412a07:	call 0x0042f8c0
0x0042f8c0:	cmpl 0x449a68, $0x0<UINT8>
0x0042f8c7:	je 0x0042f8f6
0x0042f8f6:	pushl %ebp
0x0042f8f7:	movl %ebp, %esp
0x0042f8f9:	subl %esp, $0x20<UINT8>
0x0042f8fc:	andl %esp, $0xfffffff0<UINT8>
0x0042f8ff:	fld %st0
0x0042f901:	fsts 0x18(%esp)
0x0042f905:	fistpll 0x10(%esp)
0x0042f909:	fildll 0x10(%esp)
0x0042f90d:	movl %edx, 0x18(%esp)
0x0042f911:	movl %eax, 0x10(%esp)
0x0042f915:	testl %eax, %eax
0x0042f917:	je 60
0x0042f919:	fsubp %st1, %st0
0x0042f91b:	testl %edx, %edx
0x0042f91d:	jns 0x0042f93d
0x0042f93d:	fstps (%esp)
0x0042f940:	movl %ecx, (%esp)
0x0042f943:	addl %ecx, $0x7fffffff<UINT32>
0x0042f949:	sbbl %eax, $0x0<UINT8>
0x0042f94c:	movl %edx, 0x14(%esp)
0x0042f950:	sbbl %edx, $0x0<UINT8>
0x0042f953:	jmp 0x0042f969
0x0042f969:	leave
0x0042f96a:	ret

0x00412a0c:	movl %edi, %eax
0x00412a0e:	leal %ecx, -4(%edi)
0x00412a11:	cmpl %ecx, $0x8<UINT8>
0x00412a14:	ja 0x004129f0
0x00412a16:	testl %edi, %edi
0x00412a18:	jle 112
0x00412a1a:	movl %ebx, -36(%ebp)
0x00412a1d:	leal %ecx, (%ecx)
0x00412a20:	decl %edi
0x00412a21:	call 0x0041ddad
0x0041ddad:	call 0x00422ebd
0x0041ddb2:	movl %ecx, 0x14(%eax)
0x0041ddb5:	imull %ecx, %ecx, $0x343fd<UINT32>
0x0041ddbb:	addl %ecx, $0x269ec3<UINT32>
0x0041ddc1:	movl 0x14(%eax), %ecx
0x0041ddc4:	movl %eax, %ecx
0x0041ddc6:	shrl %eax, $0x10<UINT8>
0x0041ddc9:	andl %eax, $0x7fff<UINT32>
0x0041ddce:	ret

0x00412a26:	movl -16(%ebp), %eax
0x00412a29:	fildl -16(%ebp)
0x00412a2c:	fnstcw -14(%ebp)
0x00412a2f:	fdivl 0x440dd0
0x00412a35:	movzwl %eax, -14(%ebp)
0x00412a39:	orl %eax, $0xc00<UINT32>
0x00412a3e:	movl -20(%ebp), %eax
0x00412a41:	fmull 0x440dc8
0x00412a47:	fldcw -20(%ebp)
0x00412a4a:	fistpll -24(%ebp)
0x00412a4d:	movl %edx, -24(%ebp)
0x00412a50:	movl -20(%ebp), %edx
0x00412a53:	fldcw -14(%ebp)
0x00412a56:	cmpl %esi, %ebx
0x00412a58:	jb 0x00412a71
0x00412a71:	movl %eax, -20(%ebp)
0x00412a74:	movb %cl, 0x4404f4(%eax)
0x00412a7a:	movl %edx, -44(%ebp)
0x00412a7d:	movb (%edx,%esi), %cl
0x00412a80:	incl %esi
0x00412a81:	movl -40(%ebp), %esi
0x00412a84:	testl %edi, %edi
0x00412a86:	jg 0x00412a20
0x00412a88:	movl %ebx, %edx
0x00412a8a:	pushl $0x440534<UINT32>
0x00412a8f:	leal %eax, -44(%ebp)
0x00412a92:	pushl %eax
0x00412a93:	leal %ecx, -60(%ebp)
0x00412a96:	pushl %ecx
0x00412a97:	call 0x00405b20
0x00405b20:	pushl %ebp
0x00405b21:	movl %ebp, %esp
0x00405b23:	pushl $0xffffffff<UINT8>
0x00405b25:	pushl $0x430899<UINT32>
0x00405b2a:	movl %eax, %fs:0
0x00405b30:	pushl %eax
0x00405b31:	subl %esp, $0x8<UINT8>
0x00405b34:	pushl %ebx
0x00405b35:	pushl %esi
0x00405b36:	pushl %edi
0x00405b37:	movl %eax, 0x44609c
0x00405b3c:	xorl %eax, %ebp
0x00405b3e:	pushl %eax
0x00405b3f:	leal %eax, -12(%ebp)
0x00405b42:	movl %fs:0, %eax
0x00405b48:	movl %esi, 0x8(%ebp)
0x00405b4b:	movl %eax, 0xc(%ebp)
0x00405b4e:	xorl %edi, %edi
0x00405b50:	pushl %eax
0x00405b51:	movl -4(%ebp), %edi
0x00405b54:	pushl %esi
0x00405b55:	movl -20(%ebp), %edi
0x00405b58:	call 0x00406bd0
0x00405b5d:	movl %ebx, 0x10(%ebp)
0x00405b60:	movl -4(%ebp), %edi
0x00405b63:	movl -20(%ebp), $0x1<UINT32>
0x00405b6a:	cmpl %ebx, %edi
0x00405b6c:	je 111
0x00405b6e:	movl %edx, (%esi)
0x00405b70:	cmpl %ebx, %edx
0x00405b72:	movl %eax, %ebx
0x00405b74:	sete -13(%ebp)
0x00405b78:	leal %edi, 0x1(%eax)
0x00405b7b:	jmp 0x00405b80
0x00405b80:	movb %cl, (%eax)
0x00405b82:	incl %eax
0x00405b83:	testb %cl, %cl
0x00405b85:	jne 0x00405b80
0x00405b87:	subl %eax, %edi
0x00405b89:	movl %edi, %eax
0x00405b8b:	jne 0x00405b9c
0x00405b9c:	movl %eax, 0x8(%esi)
0x00405b9f:	cmpl %edi, %eax
0x00405ba1:	ja 9
0x00405ba3:	movl %ecx, %eax
0x00405ba5:	subl %ecx, 0x4(%esi)
0x00405ba8:	cmpl %edi, %ecx
0x00405baa:	jbe 0x00405bbe
0x00405bbe:	cmpb -13(%ebp), $0x0<UINT8>
0x00405bc2:	je 0x00405bc8
0x00405bc8:	movl %eax, %ebx
0x00405bca:	movl %edx, (%esi)
0x00405bcc:	addl %edx, 0x4(%esi)
0x00405bcf:	pushl %edi
0x00405bd0:	pushl %eax
0x00405bd1:	pushl %edx
0x00405bd2:	call 0x00420c20
0x00405bd7:	addl %esp, $0xc<UINT8>
0x00405bda:	addl 0x4(%esi), %edi
0x00405bdd:	movl %eax, %esi
0x00405bdf:	movl %ecx, -12(%ebp)
0x00405be2:	movl %fs:0, %ecx
0x00405be9:	popl %ecx
0x00405bea:	popl %edi
0x00405beb:	popl %esi
0x00405bec:	popl %ebx
0x00405bed:	movl %esp, %ebp
0x00405bef:	popl %ebp
0x00405bf0:	ret

0x00412a9c:	movl %ecx, 0xc(%ebp)
0x00412a9f:	movb -4(%ebp), $0x3<UINT8>
0x00412aa3:	movl %esi, 0x8(%ebp)
0x00412aa6:	pushl %eax
0x00412aa7:	pushl %esi
0x00412aa8:	call 0x00406b60
0x00412aad:	addl %esp, $0x14<UINT8>
0x00412ab0:	movl %eax, -60(%ebp)
0x00412ab3:	xorl %edi, %edi
0x00412ab5:	cmpl %eax, %edi
0x00412ab7:	je 9
0x00412ab9:	pushl %eax
0x00412aba:	call 0x0041d3a9
0x00412abf:	addl %esp, $0x4<UINT8>
0x00412ac2:	movl -60(%ebp), %edi
0x00412ac5:	movl -56(%ebp), %edi
0x00412ac8:	movl -52(%ebp), %edi
0x00412acb:	cmpl %ebx, %edi
0x00412acd:	je 9
0x00412acf:	pushl %ebx
0x00412ad0:	call 0x0041d3a9
0x00412ad5:	addl %esp, $0x4<UINT8>
0x00412ad8:	movl %eax, %esi
0x00412ada:	movl %ecx, -12(%ebp)
0x00412add:	movl %fs:0, %ecx
0x00412ae4:	popl %ecx
0x00412ae5:	popl %edi
0x00412ae6:	popl %esi
0x00412ae7:	popl %ebx
0x00412ae8:	movl %esp, %ebp
0x00412aea:	popl %ebp
0x00412aeb:	ret

0x00411694:	addl %esp, $0x8<UINT8>
0x00411697:	xorl %ebx, %ebx
0x00411699:	pushl %ebx
0x0041169a:	pushl $0x440208<UINT32>
0x0041169f:	leal %edx, -408(%ebp)
0x004116a5:	pushl %edx
0x004116a6:	movl -4(%ebp), %ebx
0x004116a9:	call 0x004035d0
0x004116ae:	leal %eax, -408(%ebp)
0x004116b4:	pushl %eax
0x004116b5:	leal %ecx, -392(%ebp)
0x004116bb:	pushl %ecx
0x004116bc:	movb -4(%ebp), $0x1<UINT8>
0x004116c0:	call 0x00412980
0x004116c5:	addl %esp, $0x8<UINT8>
0x004116c8:	movb -4(%ebp), $0x2<UINT8>
0x004116cc:	movl %ecx, 0x4(%eax)
0x004116cf:	movl %eax, (%eax)
0x004116d1:	pushl %eax
0x004116d2:	movl %eax, %ecx
0x004116d4:	leal %ecx, -360(%ebp)
0x004116da:	call 0x00403540
0x004116df:	movl %eax, -392(%ebp)
0x004116e5:	cmpl %eax, %ebx
0x004116e7:	je 9
0x004116e9:	pushl %eax
0x004116ea:	call 0x0041d3a9
0x004116ef:	addl %esp, $0x4<UINT8>
0x004116f2:	movb -4(%ebp), %bl
0x004116f5:	movl %eax, -408(%ebp)
0x004116fb:	movl -392(%ebp), %ebx
0x00411701:	movl -388(%ebp), %ebx
0x00411707:	movl -384(%ebp), %ebx
0x0041170d:	cmpl %eax, %ebx
0x0041170f:	je 9
0x00411711:	pushl %eax
0x00411712:	call 0x0041d3a9
0x00411717:	addl %esp, $0x4<UINT8>
0x0041171a:	movl %eax, -360(%ebp)
0x00411720:	movl %ecx, -352(%ebp)
0x00411726:	cmpl %eax, $0x440470<UINT32>
0x0041172b:	sete -344(%ebp)
0x00411732:	cmpl %ecx, $0x7<UINT8>
0x00411735:	jb 13
0x00411737:	movl %edx, %ecx
0x00411739:	subl %edx, -356(%ebp)
0x0041173f:	cmpl %edx, $0x7<UINT8>
0x00411742:	jae 0x00411760
0x00411760:	movl %ecx, %eax
0x00411762:	cmpb -344(%ebp), %bl
0x00411768:	jne 5
0x0041176a:	movl %ecx, $0x440470<UINT32>
0x0041176f:	movl %edx, -356(%ebp)
0x00411775:	addl %eax, %edx
0x00411777:	movl %edx, (%ecx)
0x00411779:	movl (%eax), %edx
0x0041177b:	movw %dx, 0x4(%ecx)
0x0041177f:	movw 0x4(%eax), %dx
0x00411783:	movb %cl, 0x6(%ecx)
0x00411786:	movb 0x6(%eax), %cl
0x00411789:	addl -356(%ebp), $0x7<UINT8>
0x00411790:	movl %eax, -360(%ebp)
0x00411796:	movl %ecx, -352(%ebp)
0x0041179c:	cmpl %eax, $0x440478<UINT32>
0x004117a1:	sete -344(%ebp)
0x004117a8:	cmpl %ecx, $0x5<UINT8>
0x004117ab:	jb 13
0x004117ad:	movl %edx, %ecx
0x004117af:	subl %edx, -356(%ebp)
0x004117b5:	cmpl %edx, $0x5<UINT8>
0x004117b8:	jae 0x004117d6
0x004117d6:	movl %ecx, %eax
0x004117d8:	cmpb -344(%ebp), %bl
0x004117de:	jne 5
0x004117e0:	movl %ecx, $0x440478<UINT32>
0x004117e5:	movl %edx, -356(%ebp)
0x004117eb:	addl %eax, %edx
0x004117ed:	movl %edx, (%ecx)
0x004117ef:	movl (%eax), %edx
0x004117f1:	movb %cl, 0x4(%ecx)
0x004117f4:	movb 0x4(%eax), %cl
0x004117f7:	movl %eax, 0x447650
0x004117fc:	addl -356(%ebp), $0x5<UINT8>
0x00411803:	cltd
0x00411804:	pushl %edx
0x00411805:	pushl %eax
0x00411806:	leal %esi, -408(%ebp)
0x0041180c:	call 0x0041ae90
0x00411811:	addl %esp, $0x8<UINT8>
0x00411814:	leal %esi, 0x10(%edi)
0x00411817:	pushl %ebx
0x00411818:	movl %ecx, %esi
0x0041181a:	movb -4(%ebp), $0x3<UINT8>
0x0041181e:	call 0x004068e0
0x00411823:	pushl %esi
0x00411824:	movl %edx, %eax
0x00411826:	xorl %ecx, %ecx
0x00411828:	leal %edi, -392(%ebp)
0x0041182e:	call 0x00406a80
0x00411833:	movb -4(%ebp), $0x4<UINT8>
0x00411837:	movl %ecx, 0x4(%eax)
0x0041183a:	movl %eax, (%eax)
0x0041183c:	pushl %eax
0x0041183d:	movl %eax, %ecx
0x0041183f:	leal %ecx, -360(%ebp)
0x00411845:	call 0x00403540
0x0041184a:	movl %eax, -392(%ebp)
0x00411850:	cmpl %eax, %ebx
0x00411852:	je 9
0x00411854:	pushl %eax
0x00411855:	call 0x0041d3a9
0x0041185a:	addl %esp, $0x4<UINT8>
0x0041185d:	movb -4(%ebp), %bl
0x00411860:	movl %eax, -408(%ebp)
0x00411866:	movl -392(%ebp), %ebx
0x0041186c:	movl -388(%ebp), %ebx
0x00411872:	movl -384(%ebp), %ebx
0x00411878:	cmpl %eax, %ebx
0x0041187a:	je 9
0x0041187c:	pushl %eax
0x0041187d:	call 0x0041d3a9
0x00411882:	addl %esp, $0x4<UINT8>
0x00411885:	pushl $0x43d068<UINT32>
0x0041188a:	movl %eax, $0x39f<UINT32>
0x0041188f:	leal %edi, -408(%ebp)
0x00411895:	leal %ecx, -188(%ebp)
0x0041189b:	movb -188(%ebp), %bl
0x004118a1:	movl -120(%ebp), %ebx
0x004118a4:	movl -116(%ebp), %ebx
0x004118a7:	movl -112(%ebp), $0x67452301<UINT32>
0x004118ae:	movl -108(%ebp), $0xefcdab89<UINT32>
0x004118b5:	movl -104(%ebp), $0x98badcfe<UINT32>
0x004118bc:	movl -100(%ebp), $0x10325476<UINT32>
0x004118c3:	call 0x00403210
0x004118c8:	movl %edx, $0x10<UINT32>
0x004118cd:	pushl %eax
0x004118ce:	leal %ecx, -8(%edx)
0x004118d1:	leal %edi, -392(%ebp)
0x004118d7:	movb -4(%ebp), $0x5<UINT8>
0x004118db:	call 0x00406a80
0x004118e0:	movb -4(%ebp), $0x6<UINT8>
0x004118e4:	movl %ecx, 0x4(%eax)
0x004118e7:	movl %eax, (%eax)
0x004118e9:	pushl %eax
0x004118ea:	movl %eax, %ecx
0x004118ec:	leal %ecx, -360(%ebp)
0x004118f2:	call 0x00403540
0x004118f7:	movl %eax, -392(%ebp)
0x004118fd:	cmpl %eax, %ebx
0x004118ff:	je 9
0x00411901:	pushl %eax
0x00411902:	call 0x0041d3a9
0x00411907:	addl %esp, $0x4<UINT8>
0x0041190a:	movb -4(%ebp), %bl
0x0041190d:	movl %eax, -408(%ebp)
0x00411913:	movl -392(%ebp), %ebx
0x00411919:	movl -388(%ebp), %ebx
0x0041191f:	movl -384(%ebp), %ebx
0x00411925:	cmpl %eax, %ebx
0x00411927:	je 9
0x00411929:	pushl %eax
0x0041192a:	call 0x0041d3a9
0x0041192f:	addl %esp, $0x4<UINT8>
0x00411932:	leal %edx, -340(%ebp)
0x00411938:	pushl %edx
0x00411939:	movl -340(%ebp), $0x94<UINT32>
0x00411943:	call GetVersionExA@kernel32.dll
GetVersionExA@kernel32.dll: API Node	
0x00411949:	movl %eax, -336(%ebp)
0x0041194f:	pushl %ebx
0x00411950:	pushl %eax
0x00411951:	leal %esi, -408(%ebp)
0x00411957:	call 0x0041ae90
0x0041195c:	addl %esp, $0x8<UINT8>
0x0041195f:	movl %edi, %eax
0x00411961:	movb -4(%ebp), $0x7<UINT8>
0x00411965:	movl %eax, -360(%ebp)
0x0041196b:	movl %ecx, -352(%ebp)
0x00411971:	cmpl %eax, $0x440480<UINT32>
0x00411976:	sete -344(%ebp)
0x0041197d:	cmpl %ecx, $0x7<UINT8>
0x00411980:	jb 13
0x00411982:	movl %edx, %ecx
0x00411984:	subl %edx, -356(%ebp)
0x0041198a:	cmpl %edx, $0x7<UINT8>
0x0041198d:	jae 0x004119ab
0x004119ab:	movl %ecx, %eax
0x004119ad:	cmpb -344(%ebp), %bl
0x004119b3:	jne 5
0x004119b5:	movl %ecx, $0x440480<UINT32>
0x004119ba:	movl %edx, -356(%ebp)
0x004119c0:	addl %eax, %edx
0x004119c2:	movl %edx, (%ecx)
0x004119c4:	movl (%eax), %edx
0x004119c6:	movw %dx, 0x4(%ecx)
0x004119ca:	movw 0x4(%eax), %dx
0x004119ce:	movb %cl, 0x6(%ecx)
0x004119d1:	movb 0x6(%eax), %cl
0x004119d4:	addl -356(%ebp), $0x7<UINT8>
0x004119db:	movl %eax, 0x4(%edi)
0x004119de:	movl %edi, (%edi)
0x004119e0:	pushl %edi
0x004119e1:	leal %ecx, -360(%ebp)
0x004119e7:	call 0x00403540
0x004119ec:	movl %esi, %eax
0x004119ee:	movl %eax, 0x8(%esi)
0x004119f1:	cmpl 0x4(%esi), %eax
0x004119f4:	jb 0x00411a06
0x00411a06:	movl %edx, 0x4(%esi)
0x00411a09:	movl %eax, (%esi)
0x00411a0b:	movb (%edx,%eax), $0x2e<UINT8>
0x00411a0f:	incl 0x4(%esi)
0x00411a12:	movb -4(%ebp), %bl
0x00411a15:	movl %eax, -408(%ebp)
0x00411a1b:	cmpl %eax, %ebx
0x00411a1d:	je 9
0x00411a1f:	pushl %eax
0x00411a20:	call 0x0041d3a9
0x00411a25:	addl %esp, $0x4<UINT8>
0x00411a28:	movl %ecx, -332(%ebp)
0x00411a2e:	pushl %ebx
0x00411a2f:	pushl %ecx
0x00411a30:	leal %esi, -408(%ebp)
0x00411a36:	call 0x0041ae90
0x00411a3b:	addl %esp, $0x8<UINT8>
0x00411a3e:	movb -4(%ebp), $0x8<UINT8>
0x00411a42:	movl %ecx, 0x4(%eax)
0x00411a45:	movl %eax, (%eax)
0x00411a47:	pushl %eax
0x00411a48:	movl %eax, %ecx
0x00411a4a:	leal %ecx, -360(%ebp)
0x00411a50:	call 0x00403540
0x00411a55:	movl %esi, %eax
0x00411a57:	movl %eax, 0x8(%esi)
0x00411a5a:	cmpl 0x4(%esi), %eax
0x00411a5d:	jb 0x00411a6f
0x00411a6f:	movl %edx, 0x4(%esi)
0x00411a72:	movl %eax, (%esi)
0x00411a74:	movb (%edx,%eax), $0x2e<UINT8>
0x00411a78:	incl 0x4(%esi)
0x00411a7b:	movb -4(%ebp), %bl
0x00411a7e:	movl %eax, -408(%ebp)
0x00411a84:	cmpl %eax, %ebx
0x00411a86:	je 9
0x00411a88:	pushl %eax
0x00411a89:	call 0x0041d3a9
0x00411a8e:	addl %esp, $0x4<UINT8>
0x00411a91:	movl %ecx, -328(%ebp)
0x00411a97:	pushl %ebx
0x00411a98:	pushl %ecx
0x00411a99:	leal %esi, -408(%ebp)
0x00411a9f:	call 0x0041ae90
0x00411aa4:	addl %esp, $0x8<UINT8>
0x00411aa7:	movb -4(%ebp), $0x9<UINT8>
0x00411aab:	movl %ecx, 0x4(%eax)
0x00411aae:	movl %eax, (%eax)
0x00411ab0:	pushl %eax
0x00411ab1:	movl %eax, %ecx
0x00411ab3:	leal %ecx, -360(%ebp)
0x00411ab9:	call 0x00403540
0x00411abe:	movb -4(%ebp), %bl
0x00411ac1:	movl %eax, -408(%ebp)
0x00411ac7:	cmpl %eax, %ebx
0x00411ac9:	je 9
0x00411acb:	pushl %eax
0x00411acc:	call 0x0041d3a9
0x00411ad1:	addl %esp, $0x4<UINT8>
0x00411ad4:	movl %esi, 0x43b040
0x00411ada:	pushl $0x9<UINT8>
0x00411adc:	leal %edx, -72(%ebp)
0x00411adf:	pushl %edx
0x00411ae0:	pushl $0x59<UINT8>
0x00411ae2:	pushl $0x800<UINT32>
0x00411ae7:	call GetLocaleInfoA@kernel32.dll
GetLocaleInfoA@kernel32.dll: API Node	
0x00411ae9:	pushl $0x9<UINT8>
0x00411aeb:	leal %eax, -72(%ebp,%eax)
0x00411aef:	pushl %eax
0x00411af0:	pushl $0x5a<UINT8>
0x00411af2:	pushl $0x800<UINT32>
0x00411af7:	movb -1(%eax), $0x2d<UINT8>
0x00411afb:	call GetLocaleInfoA@kernel32.dll
0x00411afd:	movl %ecx, -360(%ebp)
0x00411b03:	movl %eax, -352(%ebp)
0x00411b09:	cmpl %ecx, $0x440488<UINT32>
0x00411b0f:	sete -344(%ebp)
0x00411b16:	cmpl %eax, $0x2<UINT8>
0x00411b19:	jb 13
0x00411b1b:	movl %edx, %eax
0x00411b1d:	subl %edx, -356(%ebp)
0x00411b23:	cmpl %edx, $0x2<UINT8>
0x00411b26:	jae 0x00411b44
0x00411b44:	movl %eax, %ecx
0x00411b46:	cmpb -344(%ebp), %bl
0x00411b4c:	jne 5
0x00411b4e:	movl %eax, $0x440488<UINT32>
0x00411b53:	movw %ax, (%eax)
0x00411b56:	movl %edx, -356(%ebp)
0x00411b5c:	movw (%edx,%ecx), %ax
0x00411b60:	movl %ecx, -360(%ebp)
0x00411b66:	addl -356(%ebp), $0x2<UINT8>
0x00411b6d:	leal %eax, -72(%ebp)
0x00411b70:	cmpl %eax, %ecx
0x00411b72:	sete -344(%ebp)
0x00411b79:	leal %esi, 0x1(%eax)
0x00411b7c:	leal %esp, (%esp)
0x00411b80:	movb %dl, (%eax)
0x00411b82:	incl %eax
0x00411b83:	cmpb %dl, %bl
0x00411b85:	jne 0x00411b80
0x00411b87:	subl %eax, %esi
0x00411b89:	movl %edi, %eax
0x00411b8b:	movl %eax, -352(%ebp)
0x00411b91:	jne 0x00411ba3
0x00411ba3:	cmpl %edi, %eax
0x00411ba5:	ja 12
0x00411ba7:	movl %edx, %eax
0x00411ba9:	subl %edx, -356(%ebp)
0x00411baf:	cmpl %edi, %edx
0x00411bb1:	jbe 0x00411bd0
0x00411bd0:	movl %eax, %ecx
0x00411bd2:	cmpb -344(%ebp), %bl
0x00411bd8:	jne 3
0x00411bda:	leal %eax, -72(%ebp)
0x00411bdd:	pushl %edi
0x00411bde:	pushl %eax
0x00411bdf:	movl %eax, -356(%ebp)
0x00411be5:	addl %eax, %ecx
0x00411be7:	pushl %eax
0x00411be8:	call 0x00420c20
0x00411bed:	addl -356(%ebp), %edi
0x00411bf3:	movl %eax, -360(%ebp)
0x00411bf9:	movl %ecx, -352(%ebp)
0x00411bff:	addl %esp, $0xc<UINT8>
0x00411c02:	cmpl %eax, $0x44048c<UINT32>
0x00411c07:	sete -344(%ebp)
0x00411c0e:	cmpl %ecx, $0x1<UINT8>
0x00411c11:	jb 13
0x00411c13:	movl %edx, %ecx
0x00411c15:	subl %edx, -356(%ebp)
0x00411c1b:	cmpl %edx, $0x1<UINT8>
0x00411c1e:	jae 0x00411c3c
0x00411c3c:	movl %ecx, %eax
0x00411c3e:	cmpb -344(%ebp), %bl
0x00411c44:	jne 5
0x00411c46:	movl %ecx, $0x44048c<UINT32>
0x00411c4b:	movb %cl, (%ecx)
0x00411c4d:	movl %edx, -356(%ebp)
0x00411c53:	movb (%edx,%eax), %cl
0x00411c56:	incl -356(%ebp)
0x00411c5c:	pushl %ebx
0x00411c5d:	pushl $0x440490<UINT32>
0x00411c62:	leal %eax, -392(%ebp)
0x00411c68:	pushl %eax
0x00411c69:	movl -568(%ebp), $0x20<UINT32>
0x00411c73:	call 0x004035d0
0x00411c78:	pushl %ebx
0x00411c79:	pushl $0x440080<UINT32>
0x00411c7e:	leal %ecx, -376(%ebp)
0x00411c84:	pushl %ecx
0x00411c85:	movb -4(%ebp), $0xa<UINT8>
0x00411c89:	call 0x004035d0
0x00411c8e:	pushl $0x1<UINT8>
0x00411c90:	pushl %ebx
0x00411c91:	leal %edx, -392(%ebp)
0x00411c97:	pushl %edx
0x00411c98:	leal %eax, -472(%ebp)
0x00411c9e:	pushl %eax
0x00411c9f:	movb -4(%ebp), $0xb<UINT8>
0x00411ca3:	call 0x0041b070
0x00411ca8:	movl %edi, %eax
0x00411caa:	pushl $0x1<UINT8>
0x00411cac:	pushl %ebx
0x00411cad:	leal %ecx, -376(%ebp)
0x00411cb3:	pushl %ecx
0x00411cb4:	leal %edx, -456(%ebp)
0x00411cba:	pushl %edx
0x00411cbb:	movb -4(%ebp), $0xc<UINT8>
0x00411cbf:	call 0x0041b070
0x00411cc4:	movl %esi, %eax
0x00411cc6:	movl %eax, (%esi)
0x00411cc8:	addl %esp, $0x20<UINT8>
0x00411ccb:	pushl %eax
0x00411ccc:	call GetModuleHandleA@kernel32.dll
0x00411cd2:	cmpl %eax, %ebx
0x00411cd4:	jne 0x00411cdf
0x00411cdf:	movl %edi, (%edi)
0x00411ce1:	pushl %edi
0x00411ce2:	pushl %eax
0x00411ce3:	call GetProcAddress@kernel32.dll
0x00411ce9:	movl %esi, %eax
0x00411ceb:	cmpl %esi, %ebx
0x00411ced:	jne 0x00411cf5
0x00411cf5:	movl %eax, -456(%ebp)
0x00411cfb:	cmpl %eax, %ebx
0x00411cfd:	je 9
0x00411cff:	pushl %eax
0x00411d00:	call 0x0041d3a9
0x00411d05:	addl %esp, $0x4<UINT8>
0x00411d08:	movl %eax, -472(%ebp)
0x00411d0e:	movl -456(%ebp), %ebx
0x00411d14:	movl -452(%ebp), %ebx
0x00411d1a:	movl -448(%ebp), %ebx
0x00411d20:	cmpl %eax, %ebx
0x00411d22:	je 9
0x00411d24:	pushl %eax
0x00411d25:	call 0x0041d3a9
0x00411d2a:	addl %esp, $0x4<UINT8>
0x00411d2d:	movl %eax, -376(%ebp)
0x00411d33:	movl -472(%ebp), %ebx
0x00411d39:	movl -468(%ebp), %ebx
0x00411d3f:	movl -464(%ebp), %ebx
0x00411d45:	cmpl %eax, %ebx
0x00411d47:	je 9
0x00411d49:	pushl %eax
0x00411d4a:	call 0x0041d3a9
0x00411d4f:	addl %esp, $0x4<UINT8>
0x00411d52:	movb -4(%ebp), %bl
0x00411d55:	movl %eax, -392(%ebp)
0x00411d5b:	movl -376(%ebp), %ebx
0x00411d61:	movl -372(%ebp), %ebx
0x00411d67:	movl -368(%ebp), %ebx
0x00411d6d:	cmpl %eax, %ebx
0x00411d6f:	je 9
0x00411d71:	pushl %eax
0x00411d72:	call 0x0041d3a9
0x00411d77:	addl %esp, $0x4<UINT8>
0x00411d7a:	leal %eax, -568(%ebp)
0x00411d80:	pushl %eax
0x00411d81:	movl -392(%ebp), %ebx
0x00411d87:	movl -388(%ebp), %ebx
0x00411d8d:	movl -384(%ebp), %ebx
0x00411d93:	call GlobalMemoryStatus@Kernel32.dll
GlobalMemoryStatus@Kernel32.dll: API Node	
0x00411d95:	xorl %eax, %eax
0x00411d97:	pushl %ebx
0x00411d98:	pushl $0x4404b4<UINT32>
0x00411d9d:	leal %ecx, -408(%ebp)
0x00411da3:	pushl %ecx
0x00411da4:	movb -52(%ebp), %bl
0x00411da7:	movl -51(%ebp), %eax
0x00411daa:	movl -47(%ebp), %eax
0x00411dad:	movl -43(%ebp), %eax
0x00411db0:	movl -39(%ebp), %eax
0x00411db3:	movl -35(%ebp), %eax
0x00411db6:	movl -31(%ebp), %eax
0x00411db9:	movl -27(%ebp), %eax
0x00411dbc:	movw -23(%ebp), %ax
0x00411dc0:	movb -21(%ebp), %al
0x00411dc3:	call 0x004035d0
0x00411dc8:	pushl $0x1<UINT8>
0x00411dca:	pushl %ebx
0x00411dcb:	leal %edx, -408(%ebp)
0x00411dd1:	pushl %edx
0x00411dd2:	leal %eax, -456(%ebp)
0x00411dd8:	pushl %eax
0x00411dd9:	movb -4(%ebp), $0xd<UINT8>
0x00411ddd:	call 0x0041b070
0x00411de2:	leal %ecx, -472(%ebp)
0x00411de8:	pushl $0x4404d0<UINT32>
0x00411ded:	pushl %ecx
0x00411dee:	movl %ecx, %eax
0x00411df0:	movb -4(%ebp), $0xe<UINT8>
0x00411df4:	call 0x004084c0
0x00411df9:	movl %edx, -560(%ebp)
0x00411dff:	movl %eax, (%eax)
0x00411e01:	shrl %edx, $0x14<UINT8>
0x00411e04:	incl %edx
0x00411e05:	pushl %edx
0x00411e06:	pushl %eax
0x00411e07:	leal %eax, -52(%ebp)
0x00411e0a:	pushl %eax
0x00411e0b:	call wsprintfA@user32.dll
wsprintfA@user32.dll: API Node	
0x00411e11:	movl %eax, -472(%ebp)
0x00411e17:	addl %esp, $0x24<UINT8>
0x00411e1a:	cmpl %eax, %ebx
0x00411e1c:	je 9
0x00411e1e:	pushl %eax
0x00411e1f:	call 0x0041d3a9
0x00411e24:	addl %esp, $0x4<UINT8>
0x00411e27:	movl %eax, -456(%ebp)
0x00411e2d:	movl -472(%ebp), %ebx
0x00411e33:	movl -468(%ebp), %ebx
0x00411e39:	movl -464(%ebp), %ebx
0x00411e3f:	cmpl %eax, %ebx
0x00411e41:	je 9
0x00411e43:	pushl %eax
0x00411e44:	call 0x0041d3a9
0x00411e49:	addl %esp, $0x4<UINT8>
0x00411e4c:	movb -4(%ebp), %bl
0x00411e4f:	movl %eax, -408(%ebp)
0x00411e55:	movl -456(%ebp), %ebx
0x00411e5b:	movl -452(%ebp), %ebx
0x00411e61:	movl -448(%ebp), %ebx
0x00411e67:	cmpl %eax, %ebx
0x00411e69:	je 9
0x00411e6b:	pushl %eax
0x00411e6c:	call 0x0041d3a9
0x00411e71:	addl %esp, $0x4<UINT8>
0x00411e74:	movl %edx, -360(%ebp)
0x00411e7a:	leal %ecx, -52(%ebp)
0x00411e7d:	cmpl %ecx, %edx
0x00411e7f:	movl %eax, %ecx
0x00411e81:	sete -344(%ebp)
0x00411e88:	leal %esi, 0x1(%eax)
0x00411e8b:	jmp 0x00411e90
0x00411e90:	movb %cl, (%eax)
0x00411e92:	incl %eax
0x00411e93:	cmpb %cl, %bl
0x00411e95:	jne -7
0x00411e97:	subl %eax, %esi
0x00411e99:	movl %edi, %eax
0x00411e9b:	movl %eax, -352(%ebp)
0x00411ea1:	jne 16
0x00411ea3:	cmpl %edx, %ebx
0x00411ea5:	jne 0x00411eb7
0x00411eb7:	movl %ecx, %eax
0x00411eb9:	subl %ecx, -356(%ebp)
0x00411ebf:	cmpl %edi, %ecx
0x00411ec1:	jbe 0x00411ee0
0x00411ee0:	movl %eax, %edx
0x00411ee2:	cmpb -344(%ebp), %bl
0x00411ee8:	jne 3
0x00411eea:	leal %eax, -52(%ebp)
0x00411eed:	pushl %edi
0x00411eee:	pushl %eax
0x00411eef:	movl %eax, -356(%ebp)
0x00411ef5:	addl %eax, %edx
0x00411ef7:	pushl %eax
0x00411ef8:	call 0x00420c20
0x00411efd:	addl -356(%ebp), %edi
0x00411f03:	addl %esp, $0xc<UINT8>
0x00411f06:	call 0x0041c9a0
0x0041c9a0:	pushl %ebp
0x0041c9a1:	movl %ebp, %esp
0x0041c9a3:	pushl $0xffffffff<UINT8>
0x0041c9a5:	pushl $0x4328ae<UINT32>
0x0041c9aa:	movl %eax, %fs:0
0x0041c9b0:	pushl %eax
0x0041c9b1:	subl %esp, $0x11c<UINT32>
0x0041c9b7:	movl %eax, 0x44609c
0x0041c9bc:	xorl %eax, %ebp
0x0041c9be:	movl -16(%ebp), %eax
0x0041c9c1:	pushl %ebx
0x0041c9c2:	pushl %esi
0x0041c9c3:	pushl %edi
0x0041c9c4:	pushl %eax
0x0041c9c5:	leal %eax, -12(%ebp)
0x0041c9c8:	movl %fs:0, %eax
0x0041c9ce:	xorl %eax, %eax
0x0041c9d0:	movl -224(%ebp), %eax
0x0041c9d6:	movl -220(%ebp), %eax
0x0041c9dc:	movl -216(%ebp), %eax
0x0041c9e2:	movl -4(%ebp), %eax
0x0041c9e5:	movl %edi, $0x1<UINT32>
0x0041c9ea:	leal %ebx, 0x28(%eax)
0x0041c9ed:	leal %esi, -224(%ebp)
0x0041c9f3:	movl -212(%ebp), %edi
0x0041c9f9:	movb 0x448860, %al
0x0041c9fe:	call 0x004034c0
0x0041ca03:	movl %eax, -220(%ebp)
0x0041ca09:	movl %edx, 0x440d88
0x0041ca0f:	movl %ecx, -224(%ebp)
0x0041ca15:	movl (%eax,%ecx), %edx
0x0041ca18:	movl %edx, 0x440d8c
0x0041ca1e:	movl 0x4(%eax,%ecx), %edx
0x0041ca22:	movl %edx, 0x440d90
0x0041ca28:	movl 0x8(%eax,%ecx), %edx
0x0041ca2c:	movl %edx, 0x440d94
0x0041ca32:	movl 0xc(%eax,%ecx), %edx
0x0041ca36:	movl %edx, 0x440d98
0x0041ca3c:	movl 0x10(%eax,%ecx), %edx
0x0041ca40:	movl %edx, 0x440d9c
0x0041ca46:	movl 0x14(%eax,%ecx), %edx
0x0041ca4a:	addl %eax, $0x18<UINT8>
0x0041ca4d:	movl -220(%ebp), %eax
0x0041ca53:	xorl %eax, %eax
0x0041ca55:	movl -4(%ebp), %edi
0x0041ca58:	movl -208(%ebp), %eax
0x0041ca5e:	movl -204(%ebp), %eax
0x0041ca64:	movl -200(%ebp), %eax
0x0041ca6a:	movb -4(%ebp), $0x2<UINT8>
0x0041ca6e:	leal %esi, -208(%ebp)
0x0041ca74:	movl -196(%ebp), %edi
0x0041ca7a:	movb 0x448860, %al
0x0041ca7f:	call 0x004034c0
0x0041ca84:	movl %eax, -204(%ebp)
0x0041ca8a:	movl %edx, 0x440080
0x0041ca90:	movl %ecx, -208(%ebp)
0x0041ca96:	movl (%eax,%ecx), %edx
0x0041ca99:	movl %edx, 0x440084
0x0041ca9f:	movl 0x4(%eax,%ecx), %edx
0x0041caa3:	movl %edx, 0x440088
0x0041caa9:	movl 0x8(%eax,%ecx), %edx
0x0041caad:	movl %edx, 0x44008c
0x0041cab3:	movl 0xc(%eax,%ecx), %edx
0x0041cab7:	movl %edx, 0x440090
0x0041cabd:	movl 0x10(%eax,%ecx), %edx
0x0041cac1:	movl %edx, 0x440094
0x0041cac7:	movl 0x14(%eax,%ecx), %edx
0x0041cacb:	addl %eax, $0x18<UINT8>
0x0041cace:	movl -204(%ebp), %eax
0x0041cad4:	pushl %edi
0x0041cad5:	xorl %ebx, %ebx
0x0041cad7:	pushl %ebx
0x0041cad8:	leal %eax, -224(%ebp)
0x0041cade:	pushl %eax
0x0041cadf:	leal %ecx, -292(%ebp)
0x0041cae5:	pushl %ecx
0x0041cae6:	movb -4(%ebp), $0x3<UINT8>
0x0041caea:	call 0x0041b070
0x0041caef:	movl -228(%ebp), %eax
0x0041caf5:	pushl %edi
0x0041caf6:	pushl %ebx
0x0041caf7:	movl %edx, %esi
0x0041caf9:	pushl %edx
0x0041cafa:	leal %eax, -244(%ebp)
0x0041cb00:	pushl %eax
0x0041cb01:	movb -4(%ebp), $0x4<UINT8>
0x0041cb05:	call 0x0041b070
0x0041cb0a:	movl %esi, %eax
0x0041cb0c:	movl %eax, (%esi)
0x0041cb0e:	addl %esp, $0x20<UINT8>
0x0041cb11:	pushl %eax
0x0041cb12:	call GetModuleHandleA@kernel32.dll
0x0041cb18:	cmpl %eax, %ebx
0x0041cb1a:	jne 0x0041cb25
0x0041cb25:	movl %ecx, -228(%ebp)
0x0041cb2b:	movl %ecx, (%ecx)
0x0041cb2d:	pushl %ecx
0x0041cb2e:	pushl %eax
0x0041cb2f:	call GetProcAddress@kernel32.dll
0x0041cb35:	movl -228(%ebp), %eax
0x0041cb3b:	cmpl %eax, %ebx
0x0041cb3d:	jne 0x0041cb45
0x0041cb45:	movl %eax, -244(%ebp)
0x0041cb4b:	cmpl %eax, %ebx
0x0041cb4d:	je 9
0x0041cb4f:	pushl %eax
0x0041cb50:	call 0x0041d3a9
0x0041cb55:	addl %esp, $0x4<UINT8>
0x0041cb58:	movl %eax, -292(%ebp)
0x0041cb5e:	movl -244(%ebp), %ebx
0x0041cb64:	movl -240(%ebp), %ebx
0x0041cb6a:	movl -236(%ebp), %ebx
0x0041cb70:	cmpl %eax, %ebx
0x0041cb72:	je 9
0x0041cb74:	pushl %eax
0x0041cb75:	call 0x0041d3a9
0x0041cb7a:	addl %esp, $0x4<UINT8>
0x0041cb7d:	movl %eax, -208(%ebp)
0x0041cb83:	movl -292(%ebp), %ebx
0x0041cb89:	movl -288(%ebp), %ebx
0x0041cb8f:	movl -284(%ebp), %ebx
0x0041cb95:	cmpl %eax, %ebx
0x0041cb97:	je 9
0x0041cb99:	pushl %eax
0x0041cb9a:	call 0x0041d3a9
0x0041cb9f:	addl %esp, $0x4<UINT8>
0x0041cba2:	movl %eax, -224(%ebp)
0x0041cba8:	cmpl %eax, %ebx
0x0041cbaa:	je 9
0x0041cbac:	pushl %eax
0x0041cbad:	call 0x0041d3a9
0x0041cbb2:	addl %esp, $0x4<UINT8>
0x0041cbb5:	movl -208(%ebp), %ebx
0x0041cbbb:	movl -204(%ebp), %ebx
0x0041cbc1:	movl -200(%ebp), %ebx
0x0041cbc7:	movl -4(%ebp), $0x5<UINT32>
0x0041cbce:	movb 0x448860, %bl
0x0041cbd4:	movl %ebx, $0x30<UINT32>
0x0041cbd9:	leal %esi, -208(%ebp)
0x0041cbdf:	movl -196(%ebp), %edi
0x0041cbe5:	call 0x004034c0
0x0041cbea:	movl %edx, -208(%ebp)
0x0041cbf0:	movl %esi, -204(%ebp)
0x0041cbf6:	pushl $0x20<UINT8>
0x0041cbf8:	leal %eax, (%esi,%edx)
0x0041cbfb:	pushl $0x440da4<UINT32>
0x0041cc00:	pushl %eax
0x0041cc01:	call 0x00420c20
0x0041cc06:	addl %esi, $0x20<UINT8>
0x0041cc09:	addl %esp, $0xc<UINT8>
0x0041cc0c:	movl -204(%ebp), %esi
0x0041cc12:	xorl %eax, %eax
0x0041cc14:	movl -4(%ebp), $0x6<UINT32>
0x0041cc1b:	movl -224(%ebp), %eax
0x0041cc21:	movl -220(%ebp), %eax
0x0041cc27:	movl -216(%ebp), %eax
0x0041cc2d:	movb -4(%ebp), $0x7<UINT8>
0x0041cc31:	leal %ebx, 0x28(%eax)
0x0041cc34:	leal %esi, -224(%ebp)
0x0041cc3a:	movl -212(%ebp), %edi
0x0041cc40:	movb 0x448860, %al
0x0041cc45:	call 0x004034c0
0x0041cc4a:	movl %eax, -220(%ebp)
0x0041cc50:	movl %edx, 0x440080
0x0041cc56:	movl %ecx, -224(%ebp)
0x0041cc5c:	movl (%eax,%ecx), %edx
0x0041cc5f:	movl %edx, 0x440084
0x0041cc65:	movl 0x4(%eax,%ecx), %edx
0x0041cc69:	movl %edx, 0x440088
0x0041cc6f:	movl 0x8(%eax,%ecx), %edx
0x0041cc73:	movl %edx, 0x44008c
0x0041cc79:	movl 0xc(%eax,%ecx), %edx
0x0041cc7d:	movl %edx, 0x440090
0x0041cc83:	movl 0x10(%eax,%ecx), %edx
0x0041cc87:	movl %edx, 0x440094
0x0041cc8d:	movl 0x14(%eax,%ecx), %edx
0x0041cc91:	addl %eax, $0x18<UINT8>
0x0041cc94:	movl -220(%ebp), %eax
0x0041cc9a:	pushl %edi
0x0041cc9b:	pushl $0x0<UINT8>
0x0041cc9d:	leal %eax, -208(%ebp)
0x0041cca3:	pushl %eax
0x0041cca4:	leal %ecx, -260(%ebp)
0x0041ccaa:	pushl %ecx
0x0041ccab:	movb -4(%ebp), $0x8<UINT8>
0x0041ccaf:	call 0x0041b070
0x0041ccb4:	movl %ebx, %eax
0x0041ccb6:	pushl %edi
0x0041ccb7:	pushl $0x0<UINT8>
0x0041ccb9:	movl %edx, %esi
0x0041ccbb:	pushl %edx
0x0041ccbc:	leal %eax, -276(%ebp)
0x0041ccc2:	pushl %eax
0x0041ccc3:	movb -4(%ebp), $0x9<UINT8>
0x0041ccc7:	call 0x0041b070
0x0041cccc:	movl %esi, %eax
0x0041ccce:	movl %eax, (%esi)
0x0041ccd0:	addl %esp, $0x20<UINT8>
0x0041ccd3:	pushl %eax
0x0041ccd4:	call GetModuleHandleA@kernel32.dll
0x0041ccda:	xorl %edi, %edi
0x0041ccdc:	cmpl %eax, %edi
0x0041ccde:	jne 0x0041cce9
0x0041cce9:	movl %ebx, (%ebx)
0x0041cceb:	pushl %ebx
0x0041ccec:	pushl %eax
0x0041cced:	call GetProcAddress@kernel32.dll
0x0041ccf3:	movl %esi, %eax
0x0041ccf5:	cmpl %esi, %edi
0x0041ccf7:	jne 0x0041ccff
0x0041ccff:	movl %eax, -276(%ebp)
0x0041cd05:	cmpl %eax, %edi
0x0041cd07:	je 9
0x0041cd09:	pushl %eax
0x0041cd0a:	call 0x0041d3a9
0x0041cd0f:	addl %esp, $0x4<UINT8>
0x0041cd12:	movl %eax, -260(%ebp)
0x0041cd18:	movl -276(%ebp), %edi
0x0041cd1e:	movl -272(%ebp), %edi
0x0041cd24:	movl -268(%ebp), %edi
0x0041cd2a:	cmpl %eax, %edi
0x0041cd2c:	je 9
0x0041cd2e:	pushl %eax
0x0041cd2f:	call 0x0041d3a9
0x0041cd34:	addl %esp, $0x4<UINT8>
0x0041cd37:	movl %eax, -224(%ebp)
0x0041cd3d:	movl -260(%ebp), %edi
0x0041cd43:	movl -256(%ebp), %edi
0x0041cd49:	movl -252(%ebp), %edi
0x0041cd4f:	cmpl %eax, %edi
0x0041cd51:	je 9
0x0041cd53:	pushl %eax
0x0041cd54:	call 0x0041d3a9
0x0041cd59:	addl %esp, $0x4<UINT8>
0x0041cd5c:	movl -4(%ebp), $0xffffffff<UINT32>
0x0041cd63:	movl %eax, -208(%ebp)
0x0041cd69:	cmpl %eax, %edi
0x0041cd6b:	je 9
0x0041cd6d:	pushl %eax
0x0041cd6e:	call 0x0041d3a9
0x0041cd73:	addl %esp, $0x4<UINT8>
0x0041cd76:	leal %ecx, -124(%ebp)
0x0041cd79:	pushl %ecx
0x0041cd7a:	call GetSystemTime@Kernel32.dll
GetSystemTime@Kernel32.dll: API Node	
0x0041cd80:	leal %edx, -192(%ebp)
0x0041cd86:	pushl %edx
0x0041cd87:	call GetTimeZoneInformation@Kernel32.dll
GetTimeZoneInformation@Kernel32.dll: API Node	
0x0041cd89:	movl %ecx, -192(%ebp)
0x0041cd8f:	movl %eax, $0x77777777<UINT32>
0x0041cd94:	imull %eax, %ecx
0x0041cd96:	subl %edx, %ecx
0x0041cd98:	sarl %edx, $0x5<UINT8>
0x0041cd9b:	movl %eax, %edx
0x0041cd9d:	shrl %eax, $0x1f<UINT8>
0x0041cda0:	addl %eax, %edx
0x0041cda2:	movl %ecx, -12(%ebp)
0x0041cda5:	movl %fs:0, %ecx
0x0041cdac:	popl %ecx
0x0041cdad:	popl %edi
0x0041cdae:	popl %esi
0x0041cdaf:	popl %ebx
0x0041cdb0:	movl %ecx, -16(%ebp)
0x0041cdb3:	xorl %ecx, %ebp
0x0041cdb5:	call 0x0041d190
0x0041cdba:	movl %esp, %ebp
0x0041cdbc:	popl %ebp
0x0041cdbd:	ret

0x00411f0b:	cltd
0x00411f0c:	pushl %edx
0x00411f0d:	pushl %eax
0x00411f0e:	leal %esi, -408(%ebp)
0x00411f14:	call 0x0041ae90
0x0042a681:	negl %eax
0x0042a683:	adcl %edx, $0x0<UINT8>
0x0042a686:	negl %edx
0x0042a688:	orl -528(%ebp), $0x100<UINT32>
0x0042a7c3:	movb -556(%ebp), $0x2d<UINT8>
0x0042a7ca:	jmp 0x0042a7e4
0x0042a7e4:	movl -560(%ebp), $0x1<UINT32>
0x00411f19:	addl %esp, $0x8<UINT8>
0x00411f1c:	movl %edi, %eax
0x00411f1e:	movb -4(%ebp), $0xf<UINT8>
0x00411f22:	movl %eax, -360(%ebp)
0x00411f28:	movl %ecx, -352(%ebp)
0x00411f2e:	cmpl %eax, $0x4404d4<UINT32>
0x00411f33:	sete -344(%ebp)
0x00411f3a:	cmpl %ecx, $0x7<UINT8>
0x00411f3d:	jb 13
0x00411f3f:	movl %edx, %ecx
0x00411f41:	subl %edx, -356(%ebp)
0x00411f47:	cmpl %edx, $0x7<UINT8>
0x00411f4a:	jae 28
0x00411f4c:	leal %edx, 0x17(%ecx)
0x00411f4f:	cmpl %edx, %ecx
0x00411f51:	jbe 21
0x00411f53:	movl %ebx, %edx
0x00411f55:	leal %esi, -360(%ebp)
0x00411f5b:	call 0x004034c0
0x00411f60:	movl %eax, -360(%ebp)
0x00411f66:	xorl %ebx, %ebx
0x00411f68:	movl %ecx, %eax
0x00411f6a:	cmpb -344(%ebp), %bl
0x00411f70:	jne 5
0x00411f72:	movl %ecx, $0x4404d4<UINT32>
0x00411f77:	movl %edx, -356(%ebp)
0x00411f7d:	addl %eax, %edx
0x00411f7f:	movl %edx, (%ecx)
0x00411f81:	movl (%eax), %edx
0x00411f83:	movw %dx, 0x4(%ecx)
0x00411f87:	movw 0x4(%eax), %dx
0x00411f8b:	movb %cl, 0x6(%ecx)
0x00411f8e:	movb 0x6(%eax), %cl
0x00411f91:	addl -356(%ebp), $0x7<UINT8>
0x00411f98:	movl %eax, 0x4(%edi)
0x00411f9b:	movl %edi, (%edi)
0x00411f9d:	pushl %edi
0x00411f9e:	leal %ecx, -360(%ebp)
0x00411fa4:	call 0x00403540
0x00411fa9:	movl %esi, %eax
0x00411fab:	cmpl (%esi), $0x4404dc<UINT32>
0x00411fb1:	movl %eax, 0x8(%esi)
0x00411fb4:	sete -344(%ebp)
0x00411fbb:	cmpl %eax, $0x1<UINT8>
0x00411fbe:	jb 10
0x00411fc0:	movl %edx, %eax
0x00411fc2:	subl %edx, 0x4(%esi)
0x00411fc5:	cmpl %edx, $0x1<UINT8>
0x00411fc8:	jae 0x00411fda
0x00411fda:	cmpb -344(%ebp), %bl
0x00411fe0:	je 0x00411fe6
0x00411fe6:	movl %eax, $0x4404dc<UINT32>
0x00411feb:	movl %ecx, (%esi)
0x00411fed:	addl %ecx, 0x4(%esi)
0x00411ff0:	movb %dl, (%eax)
0x00411ff2:	movb (%ecx), %dl
0x00411ff4:	incl 0x4(%esi)
0x00411ff7:	movb -4(%ebp), %bl
0x00411ffa:	movl %eax, -408(%ebp)
0x00412000:	cmpl %eax, %ebx
0x00412002:	je 9
0x00412004:	pushl %eax
0x00412005:	call 0x0041d3a9
0x0041200a:	addl %esp, $0x4<UINT8>
0x0041200d:	leal %eax, -360(%ebp)
0x00412013:	pushl %eax
0x00412014:	call 2775
0x00412019:	movl %esi, -440(%ebp)
0x0041201f:	movl %ecx, (%esi)
0x00412021:	movl %eax, 0x4(%esi)
0x00412024:	addl %esp, $0x4<UINT8>
0x00412027:	pushl %ecx
0x00412028:	leal %edi, -436(%ebp)
0x0041202e:	leal %ecx, -188(%ebp)
0x00412034:	call -60969
0x00412039:	movb -4(%ebp), $0x10<UINT8>
0x0041203d:	testb 0x4499f0, $0x1<UINT8>
0x00412044:	jne 84
0x00412046:	orl 0x4499f0, $0x1<UINT8>
0x0041204d:	pushl %ebx
0x0041204e:	pushl $0x43ff44<UINT32>
0x00412053:	pushl $0x4499c0<UINT32>
0x00412058:	movb -4(%ebp), $0x11<UINT8>
0x0041205c:	call -60049
0x00412061:	pushl %ebx
0x00412062:	pushl $0x4401fc<UINT32>
0x00412067:	pushl $0x4499d0<UINT32>
0x0041206c:	movb -4(%ebp), $0x12<UINT8>
0x00412070:	call -60069
0x00412075:	pushl %ebx
0x00412076:	pushl $0x4404e0<UINT32>
0x0041207b:	pushl $0x4499e0<UINT32>
0x00412080:	movb -4(%ebp), $0x13<UINT8>
0x00412084:	call -60089
0x00412089:	pushl $0x439af0<UINT32>
0x0041208e:	call 48165
0x00412093:	addl %esp, $0x4<UINT8>
0x00412096:	movb -4(%ebp), $0x10<UINT8>
0x0041209a:	leal %ecx, 0x20(%esi)
0x0041209d:	movl -416(%ebp), %ebx
0x004120a3:	movl -496(%ebp), %ecx
0x004120a9:	movl -412(%ebp), $0x4499c0<UINT32>
0x004120b3:	jmp 0x004120c6
0x004120c6:	movl %ebx, -412(%ebp)
0x004120cc:	movl %edx, $0x4404e0<UINT32>
0x004120d1:	call 0x00405800
0x00405800:	pushl %esi
0x00405801:	movl %esi, (%ebx)
0x00405803:	cmpl %esi, %edx
0x00405805:	jne 0x0040580b
0x0040580b:	testl %edx, %edx
0x0040580d:	jne 0x00405813
0x00405813:	movl %eax, %edx
0x00405815:	pushl %edi
0x00405816:	leal %edi, 0x1(%eax)
0x00405819:	leal %esp, (%esp)
0x00405820:	movb %cl, (%eax)
0x00405822:	incl %eax
0x00405823:	testb %cl, %cl
0x00405825:	jne 0x00405820
0x00405827:	movl %ecx, 0x4(%ebx)
0x0040582a:	subl %eax, %edi
0x0040582c:	cmpl %ecx, %eax
0x0040582e:	jne 0x004058a8
0x004058a8:	popl %edi
0x004058a9:	xorb %al, %al
0x004058ab:	popl %esi
0x004058ac:	ret

0x004120d6:	testb %al, %al
0x004120d8:	je 0x004121f6
0x004121f6:	xorl %ebx, %ebx
0x004121f8:	movl -376(%ebp), %ebx
0x004121fe:	movl -372(%ebp), %ebx
0x00412204:	movl -368(%ebp), %ebx
0x0041220a:	movb -4(%ebp), $0x17<UINT8>
0x0041220e:	movb 0x448860, %bl
0x00412214:	movl %ebx, $0x10<UINT32>
0x00412219:	leal %esi, -376(%ebp)
0x0041221f:	movl -364(%ebp), $0x1<UINT32>
0x00412229:	call 0x004034c0
0x0041222e:	subl %esp, $0x10<UINT8>
0x00412231:	movl %esi, %esp
0x00412233:	xorl %ebx, %ebx
0x00412235:	movl -536(%ebp), %esp
0x0041223b:	movl -488(%ebp), %esi
0x00412241:	movl (%esi), %ebx
0x00412243:	movl 0x4(%esi), %ebx
0x00412246:	movl 0x8(%esi), %ebx
0x00412249:	movb -4(%ebp), $0x19<UINT8>
0x0041224d:	movl 0xc(%esi), $0x1<UINT32>
0x00412254:	movb 0x448860, %bl
0x0041225a:	movl %eax, (%esi)
0x0041225c:	cmpl %eax, $0x43fad1<UINT32>
0x00412261:	sete -344(%ebp)
0x00412268:	cmpl %eax, %ebx
0x0041226a:	jne 18
0x0041226c:	cmpl 0x8(%esi), $0x10<UINT8>
0x00412270:	jae 12
0x00412272:	movl %ebx, $0x10<UINT32>
0x00412277:	call 0x004034c0
0x0041227c:	xorl %ebx, %ebx
0x0041227e:	cmpb -344(%ebp), %bl
0x00412284:	je 0x0041228a
0x0041228a:	movl %eax, $0x43fad1<UINT32>
0x0041228f:	pushl %ebx
0x00412290:	pushl %eax
0x00412291:	movl %eax, 0x4(%esi)
0x00412294:	addl %eax, (%esi)
0x00412296:	pushl %eax
0x00412297:	call 0x00420c20
0x0041229c:	pushl %ecx
0x0041229d:	movl %esi, %esp
0x0041229f:	movl -488(%ebp), %esp
0x004122a5:	movl -484(%ebp), %esi
0x004122ab:	movl (%esi), %ebx
0x004122ad:	movl 0x4(%esi), %ebx
0x004122b0:	movl 0x8(%esi), %ebx
0x004122b3:	movb -4(%ebp), $0x1b<UINT8>
0x004122b7:	movl 0xc(%esi), $0x1<UINT32>
0x004122be:	movb 0x448860, %bl
0x004122c4:	movl %eax, (%esi)
0x004122c6:	cmpl %eax, $0x43fad1<UINT32>
0x004122cb:	sete -344(%ebp)
0x004122d2:	cmpl %eax, %ebx
0x004122d4:	jne 18
0x004122d6:	cmpl 0x8(%esi), $0x10<UINT8>
0x004122da:	jae 12
0x004122dc:	movl %ebx, $0x10<UINT32>
0x004122e1:	call 0x004034c0
0x004122e6:	xorl %ebx, %ebx
0x004122e8:	cmpb -344(%ebp), %bl
0x004122ee:	je 0x004122f4
0x004122f4:	movl %eax, $0x43fad1<UINT32>
0x004122f9:	movl %ecx, 0x4(%esi)
0x004122fc:	addl %ecx, (%esi)
0x004122fe:	pushl %ebx
0x004122ff:	pushl %eax
0x00412300:	pushl %ecx
0x00412301:	call 0x00420c20
0x00412306:	addl %esp, $0xc<UINT8>
0x00412309:	pushl %ebx
0x0041230a:	pushl %ebx
0x0041230b:	subl %esp, $0x10<UINT8>
0x0041230e:	movl %esi, %esp
0x00412310:	movl -484(%ebp), %esp
0x00412316:	movl -476(%ebp), %esi
0x0041231c:	movl (%esi), %ebx
0x0041231e:	movl 0x4(%esi), %ebx
0x00412321:	movl 0x8(%esi), %ebx
0x00412324:	movb -4(%ebp), $0x1d<UINT8>
0x00412328:	movl 0xc(%esi), $0x1<UINT32>
0x0041232f:	movb 0x448860, %bl
0x00412335:	movl %eax, (%esi)
0x00412337:	cmpl %eax, $0x43fad1<UINT32>
0x0041233c:	sete -344(%ebp)
0x00412343:	cmpl %eax, %ebx
0x00412345:	jne 18
0x00412347:	cmpl 0x8(%esi), $0x10<UINT8>
0x0041234b:	jae 12
0x0041234d:	movl %ebx, $0x10<UINT32>
0x00412352:	call 0x004034c0
0x00412357:	xorl %ebx, %ebx
0x00412359:	cmpb -344(%ebp), %bl
0x0041235f:	je 0x00412365
0x00412365:	movl %eax, $0x43fad1<UINT32>
0x0041236a:	movl %edx, 0x4(%esi)
0x0041236d:	addl %edx, (%esi)
0x0041236f:	pushl %ebx
0x00412370:	pushl %eax
0x00412371:	pushl %edx
0x00412372:	call 0x00420c20
0x00412377:	pushl %ecx
0x00412378:	movl %ecx, %esp
0x0041237a:	movl -476(%ebp), %esp
0x00412380:	movl -480(%ebp), %ecx
0x00412386:	movl (%ecx), %ebx
0x00412388:	movl 0x4(%ecx), %ebx
0x0041238b:	movl 0x8(%ecx), %ebx
0x0041238e:	movb -4(%ebp), $0x1f<UINT8>
0x00412392:	movl 0xc(%ecx), $0x1<UINT32>
0x00412399:	movl %eax, -360(%ebp)
0x0041239f:	pushl %eax
0x004123a0:	movl %eax, -356(%ebp)
0x004123a6:	movb 0x448860, %bl
0x004123ac:	call 0x00406e50
0x004123b1:	movl %ecx, -496(%ebp)
0x004123b7:	movl %edi, -440(%ebp)
0x004123bd:	pushl %ecx
0x004123be:	subl %esp, $0x10<UINT8>
0x004123c1:	movb -4(%ebp), $0x20<UINT8>
0x004123c5:	movl %esi, %esp
0x004123c7:	movl %eax, %edi
0x004123c9:	movl -480(%ebp), %esp
0x004123cf:	call 0x004073c0
0x004073c0:	pushl %ebp
0x004073c1:	movl %ebp, %esp
0x004073c3:	subl %esp, $0x8<UINT8>
0x004073c6:	addl %eax, $0xe0<UINT32>
0x004073cb:	pushl %eax
0x004073cc:	pushl %esi
0x004073cd:	movl -4(%ebp), $0x0<UINT32>
0x004073d4:	call 0x00412790
0x00412790:	pushl %ebp
0x00412791:	movl %ebp, %esp
0x00412793:	pushl $0xffffffff<UINT8>
0x00412795:	pushl $0x431098<UINT32>
0x0041279a:	movl %eax, %fs:0
0x004127a0:	pushl %eax
0x004127a1:	subl %esp, $0x3c<UINT8>
0x004127a4:	pushl %ebx
0x004127a5:	pushl %esi
0x004127a6:	pushl %edi
0x004127a7:	movl %eax, 0x44609c
0x004127ac:	xorl %eax, %ebp
0x004127ae:	pushl %eax
0x004127af:	leal %eax, -12(%ebp)
0x004127b2:	movl %fs:0, %eax
0x004127b8:	movl %eax, 0xc(%ebp)
0x004127bb:	movl %edi, 0x4(%eax)
0x004127be:	xorl %ecx, %ecx
0x004127c0:	xorl %edx, %edx
0x004127c2:	xorl %esi, %esi
0x004127c4:	movl -20(%ebp), %ecx
0x004127c7:	cmpl %edi, %ecx
0x004127c9:	jbe 44
0x004127cb:	movl %ebx, (%eax)
0x004127cd:	leal %ecx, (%ecx)
0x004127d0:	movb %al, (%ebx,%ecx)
0x004127d3:	cmpb %al, $0x40<UINT8>
0x004127d5:	jne 0x004127da
0x004127da:	cmpb %al, $0x23<UINT8>
0x004127dc:	jne 0x004127df
0x004127df:	incl %ecx
0x004127e0:	cmpl %ecx, %edi
0x004127e2:	jb 0x004127d0
0x004127e4:	xorl %ebx, %ebx
0x004127e6:	movl -20(%ebp), %esi
0x004127e9:	movl -16(%ebp), %edx
0x004127ec:	cmpl %esi, %ebx
0x004127ee:	jne 37
0x004127f0:	cmpl %edx, %ebx
0x004127f2:	jne 33
0x004127f4:	movl %eax, 0xc(%ebp)
0x004127f7:	movl %esi, 0x8(%ebp)
0x004127fa:	pushl %eax
0x004127fb:	pushl %esi
0x004127fc:	call 0x00406bd0
0x00412801:	movl %eax, %esi
0x00412803:	movl %ecx, -12(%ebp)
0x00412806:	movl %fs:0, %ecx
0x0041280d:	popl %ecx
0x0041280e:	popl %edi
0x0041280f:	popl %esi
0x00412810:	popl %ebx
0x00412811:	movl %esp, %ebp
0x00412813:	popl %ebp
0x00412814:	ret

0x004073d9:	addl %esp, $0x8<UINT8>
0x004073dc:	movl %eax, %esi
0x004073de:	movl %esp, %ebp
0x004073e0:	popl %ebp
0x004073e1:	ret

0x004123d4:	subl %esp, $0x10<UINT8>
0x004123d7:	movl %ecx, %esp
0x004123d9:	movl -532(%ebp), %esp
0x004123df:	movl -420(%ebp), %ecx
0x004123e5:	movl (%ecx), %ebx
0x004123e7:	movl 0x4(%ecx), %ebx
0x004123ea:	movl 0x8(%ecx), %ebx
0x004123ed:	movb -4(%ebp), $0x22<UINT8>
0x004123f1:	movl %esi, $0x1<UINT32>
0x004123f6:	movl 0xc(%ecx), %esi
0x004123f9:	movb 0x448860, %bl
0x004123ff:	movl %edx, 0xb0(%edi)
0x00412405:	movl %eax, 0xb4(%edi)
0x0041240b:	pushl %edx
0x0041240c:	call 0x00406e50
0x00412411:	subl %esp, $0x10<UINT8>
0x00412414:	movl %ecx, %esp
0x00412416:	movl -420(%ebp), %esp
0x0041241c:	movl -420(%ebp), %ecx
0x00412422:	movl (%ecx), %ebx
0x00412424:	movl 0x4(%ecx), %ebx
0x00412427:	movl 0x8(%ecx), %ebx
0x0041242a:	movl %edx, -412(%ebp)
0x00412430:	movb -4(%ebp), $0x24<UINT8>
0x00412434:	movl 0xc(%ecx), %esi
0x00412437:	movl %eax, 0x4(%edx)
0x0041243a:	movl %edx, (%edx)
0x0041243c:	pushl %edx
0x0041243d:	movb 0x448860, %bl
0x00412443:	call 0x00406e50
0x00412448:	leal %eax, -376(%ebp)
0x0041244e:	pushl %eax
0x0041244f:	movb -4(%ebp), $0x23<UINT8>
0x00412453:	pushl %esi
0x00412454:	movb -4(%ebp), $0x18<UINT8>
0x00412458:	call 0x0040dc30
0x0040dc30:	pushl %ebp
0x0040dc31:	movl %ebp, %esp
0x0040dc33:	pushl $0xffffffff<UINT8>
0x0040dc35:	pushl $0x433541<UINT32>
0x0040dc3a:	movl %eax, %fs:0
0x0040dc40:	pushl %eax
0x0040dc41:	subl %esp, $0x174<UINT32>
0x0040dc47:	movl %eax, 0x44609c
0x0040dc4c:	xorl %eax, %ebp
0x0040dc4e:	movl -16(%ebp), %eax
0x0040dc51:	pushl %ebx
0x0040dc52:	pushl %esi
0x0040dc53:	pushl %edi
0x0040dc54:	pushl %eax
0x0040dc55:	leal %eax, -12(%ebp)
0x0040dc58:	movl %fs:0, %eax
0x0040dc5e:	movl %eax, 0xc(%ebp)
0x0040dc61:	movl %ecx, 0x40(%ebp)
0x0040dc64:	movl -252(%ebp), %eax
0x0040dc6a:	movl -216(%ebp), %ecx
0x0040dc70:	xorl %esi, %esi
0x0040dc72:	movl -4(%ebp), %esi
0x0040dc75:	movl %eax, 0x14(%ebp)
0x0040dc78:	movl %edi, 0x10(%ebp)
0x0040dc7b:	movl %ebx, %eax
0x0040dc7d:	cmpl %eax, %esi
0x0040dc7f:	jbe 0x0040dc98
0x0040dc98:	movl %ebx, 0x24(%ebp)
0x0040dc9b:	movl %edi, 0x20(%ebp)
0x0040dc9e:	cmpl %ebx, %esi
0x0040dca0:	jbe 23
0x0040dca2:	movsbl %eax, (%esi,%edi)
0x0040dca6:	pushl %eax
0x0040dca7:	call 0x0041e899
0x0041e899:	movl %edi, %edi
0x0041e89b:	pushl %ebp
0x0041e89c:	movl %ebp, %esp
0x0041e89e:	cmpl 0x448134, $0x0<UINT8>
0x0041e8a5:	jne 16
0x0041e8a7:	movl %eax, 0x8(%ebp)
0x0041e8aa:	leal %ecx, -65(%eax)
0x0041e8ad:	cmpl %ecx, $0x19<UINT8>
0x0041e8b0:	ja 0x0041e8c3
0x0041e8c3:	popl %ebp
0x0041e8c4:	ret

0x0040dcac:	movb (%esi,%edi), %al
0x0040dcaf:	incl %esi
0x0040dcb0:	addl %esp, $0x4<UINT8>
0x0040dcb3:	cmpl %esi, %ebx
0x0040dcb5:	jb 0x0040dca2
0x0040dcb7:	xorl %esi, %esi
0x0040dcb9:	movl -140(%ebp), %esi
0x0040dcbf:	movl -136(%ebp), %esi
0x0040dcc5:	movl -132(%ebp), %esi
0x0040dccb:	movl %edi, $0x1<UINT32>
0x0040dcd0:	movb -4(%ebp), $0x7<UINT8>
0x0040dcd4:	leal %ebx, 0x12(%edi)
0x0040dcd7:	leal %esi, -140(%ebp)
0x0040dcdd:	movl -128(%ebp), %edi
0x0040dce0:	movb 0x448860, $0x0<UINT8>
0x0040dce7:	call 0x004034c0
0x0040dcec:	movl %eax, -136(%ebp)
0x0040dcf2:	movl %esi, -140(%ebp)
0x0040dcf8:	movw %cx, 0x440584
0x0040dcff:	movw (%eax,%esi), %cx
0x0040dd03:	movb %dl, 0x440586
0x0040dd09:	movb 0x2(%eax,%esi), %dl
0x0040dd0d:	addl %eax, $0x3<UINT8>
0x0040dd10:	movl -136(%ebp), %eax
0x0040dd16:	xorl %ebx, %ebx
0x0040dd18:	pushl %ebx
0x0040dd19:	pushl %edi
0x0040dd1a:	leal %eax, 0x20(%ebp)
0x0040dd1d:	pushl %eax
0x0040dd1e:	leal %ecx, -164(%ebp)
0x0040dd24:	pushl %ecx
0x0040dd25:	leal %ecx, -140(%ebp)
0x0040dd2b:	movb -4(%ebp), $0x8<UINT8>
0x0040dd2f:	call 0x00406c40
0x0040dd34:	addl %esp, $0x10<UINT8>
0x0040dd37:	call 0x00414990
0x00414990:	pushl %edi
0x00414991:	movl %edi, %eax
0x00414993:	movl %ecx, (%edi)
0x00414995:	cmpl %ecx, $0x44029c<UINT32>
0x0041499b:	jne 0x004149a1
0x004149a1:	movl %eax, $0x44029c<UINT32>
0x004149a6:	pushl %esi
0x004149a7:	leal %esi, 0x1(%eax)
0x004149aa:	leal %ebx, (%ebx)
0x004149b0:	movb %dl, (%eax)
0x004149b2:	incl %eax
0x004149b3:	testb %dl, %dl
0x004149b5:	jne 0x004149b0
0x004149b7:	subl %eax, %esi
0x004149b9:	cmpl %eax, 0x4(%edi)
0x004149bc:	jne 0x00414a43
0x00414a43:	popl %esi
0x00414a44:	xorb %al, %al
0x00414a46:	popl %edi
0x00414a47:	ret

0x0040dd3c:	xorl %edx, %edx
0x0040dd3e:	testb %al, %al
0x0040dd40:	movl %eax, -164(%ebp)
0x0040dd46:	setne %dl
0x0040dd49:	movl -240(%ebp), %edx
0x0040dd4f:	cmpl %eax, %ebx
0x0040dd51:	je 9
0x0040dd53:	pushl %eax
0x0040dd54:	call 0x0041d3a9
0x0040dd59:	addl %esp, $0x4<UINT8>
0x0040dd5c:	movl -164(%ebp), %ebx
0x0040dd62:	movl -160(%ebp), %ebx
0x0040dd68:	movl -156(%ebp), %ebx
0x0040dd6e:	cmpl %esi, %ebx
0x0040dd70:	je 9
0x0040dd72:	pushl %esi
0x0040dd73:	call 0x0041d3a9
0x0040dd78:	addl %esp, $0x4<UINT8>
0x0040dd7b:	movl -140(%ebp), %ebx
0x0040dd81:	movl -136(%ebp), %ebx
0x0040dd87:	movl -132(%ebp), %ebx
0x0040dd8d:	movb -4(%ebp), $0x9<UINT8>
0x0040dd91:	movb 0x448860, %bl
0x0040dd97:	movl %ebx, $0x11<UINT32>
0x0040dd9c:	leal %esi, -140(%ebp)
0x0040dda2:	movl -128(%ebp), %edi
0x0040dda5:	call 0x004034c0
0x0040ddaa:	movl %eax, -136(%ebp)
0x0040ddb0:	movb %cl, 0x4401ac
0x0040ddb6:	movl %esi, -140(%ebp)
0x0040ddbc:	movb (%eax,%esi), %cl
0x0040ddbf:	addl %eax, %edi
0x0040ddc1:	movl -136(%ebp), %eax
0x0040ddc7:	xorl %ebx, %ebx
0x0040ddc9:	pushl %ebx
0x0040ddca:	pushl %ebx
0x0040ddcb:	leal %edx, 0x20(%ebp)
0x0040ddce:	pushl %edx
0x0040ddcf:	leal %eax, -164(%ebp)
0x0040ddd5:	pushl %eax
0x0040ddd6:	leal %ecx, -140(%ebp)
0x0040dddc:	movb -4(%ebp), $0xa<UINT8>
0x0040dde0:	call 0x00406c40
0x00406268:	popl %edi
0x00406269:	xorl %eax, %eax
0x0040626b:	popl %ebx
0x0040626c:	movl %esp, %ebp
0x0040626e:	popl %ebp
0x0040626f:	ret

0x004069d7:	movl %esi, -12(%ebp)
0x00406e69:	movl %eax, %ebx
0x00406e6b:	leal %edx, 0x1(%eax)
0x00406e6e:	movl %edi, %edi
0x00406e70:	movb %cl, (%eax)
0x00406e72:	incl %eax
0x00406e73:	testb %cl, %cl
0x00406e75:	jne -7
0x00406e77:	subl %eax, %edx
0x00406e79:	movl %edi, %eax
0x00406e7b:	testl %edi, %edi
0x00406e7d:	je 0x00406e91
0x0040342a:	movl %eax, (%esi)
0x0040342c:	cmpl %eax, %ebx
0x0040342e:	je 0x00403439
0x00403439:	movl (%esi), %ebx
0x0040343b:	movl 0x4(%esi), %ebx
0x0040343e:	movl 0x8(%esi), %ebx
0x00403441:	popl %esi
0x00403442:	popl %ebx
0x00403443:	ret

0x00406e9c:	movl %ebx, $0x10<UINT32>
0x00406ea1:	cmpl 0x8(%esi), %ebx
0x00406ea4:	jae 25
0x00406ea6:	call 0x004034c0
0x00406eab:	popl %edi
0x00406eac:	popl %esi
0x00406ead:	popl %ebx
0x00406eae:	popl %ebp
0x00406eaf:	ret $0x4<UINT16>

0x0040dde5:	movl %eax, (%eax)
0x0040dde7:	pushl %eax
0x0040dde8:	call 0x0041de36
0x0041de36:	movl %edi, %edi
0x0041de38:	pushl %ebp
0x0041de39:	movl %ebp, %esp
0x0041de3b:	popl %ebp
0x0041de3c:	jmp 0x0041de20
0x0041de20:	movl %edi, %edi
0x0041de22:	pushl %ebp
0x0041de23:	movl %ebp, %esp
0x0041de25:	pushl $0xa<UINT8>
0x0041de27:	pushl $0x0<UINT8>
0x0041de29:	pushl 0x8(%ebp)
0x0041de2c:	call 0x004249e7
0x004249e7:	movl %edi, %edi
0x004249e9:	pushl %ebp
0x004249ea:	movl %ebp, %esp
0x004249ec:	xorl %eax, %eax
0x004249ee:	pushl %eax
0x004249ef:	pushl 0x10(%ebp)
0x004249f2:	pushl 0xc(%ebp)
0x004249f5:	pushl 0x8(%ebp)
0x004249f8:	cmpl 0x448134, %eax
0x004249fe:	jne 7
0x00424a00:	pushl $0x446c18<UINT32>
0x00424a05:	jmp 0x00424a08
0x00424a08:	call 0x004247b8
0x004247b8:	movl %edi, %edi
0x004247ba:	pushl %ebp
0x004247bb:	movl %ebp, %esp
0x004247bd:	subl %esp, $0x14<UINT8>
0x004247c0:	pushl %esi
0x004247c1:	pushl %edi
0x004247c2:	pushl 0x8(%ebp)
0x004247c5:	leal %ecx, -20(%ebp)
0x004247c8:	call 0x0041d900
0x0041d976:	movl %ecx, (%eax)
0x0041d978:	movl (%esi), %ecx
0x0041d97a:	movl %eax, 0x4(%eax)
0x0041d97d:	movl 0x4(%esi), %eax
0x004247cd:	movl %eax, 0x10(%ebp)
0x004247d0:	movl %esi, 0xc(%ebp)
0x004247d3:	xorl %edi, %edi
0x004247d5:	cmpl %eax, %edi
0x004247d7:	je 0x004247db
0x004247db:	cmpl %esi, %edi
0x004247dd:	jne 0x0042480b
0x0042480b:	cmpl 0x14(%ebp), %edi
0x0042480e:	je 12
0x00424810:	cmpl 0x14(%ebp), $0x2<UINT8>
0x00424814:	jl -55
0x00424816:	cmpl 0x14(%ebp), $0x24<UINT8>
0x0042481a:	jg -61
0x0042481c:	movl %ecx, -20(%ebp)
0x0042481f:	pushl %ebx
0x00424820:	movb %bl, (%esi)
0x00424822:	movl -4(%ebp), %edi
0x00424825:	leal %edi, 0x1(%esi)
0x00424828:	cmpl 0xac(%ecx), $0x1<UINT8>
0x0042482f:	jle 0x00424848
0x00424848:	movl %edx, 0xc8(%ecx)
0x0042484e:	movzbl %eax, %bl
0x00424851:	movzwl %eax, (%edx,%eax,2)
0x00424855:	andl %eax, $0x8<UINT8>
0x00424858:	testl %eax, %eax
0x0042485a:	je 0x00424861
0x00424861:	cmpb %bl, $0x2d<UINT8>
0x00424864:	jne 0x0042486c
0x0042486c:	cmpb %bl, $0x2b<UINT8>
0x0042486f:	jne 0x00424874
0x00424874:	movl %eax, 0x14(%ebp)
0x00424877:	testl %eax, %eax
0x00424879:	jl 331
0x0042487f:	cmpl %eax, $0x1<UINT8>
0x00424882:	je 322
0x00424888:	cmpl %eax, $0x24<UINT8>
0x0042488b:	jg 313
0x00424891:	testl %eax, %eax
0x00424893:	jne 0x004248bf
0x004248bf:	cmpl %eax, $0x10<UINT8>
0x004248c2:	jne 0x004248d7
0x004248d7:	movl %esi, 0xc8(%ecx)
0x004248dd:	movl %eax, $0xffffffff<UINT32>
0x004248e2:	xorl %edx, %edx
0x004248e4:	divl %eax, 0x14(%ebp)
0x004248e7:	movzbl %ecx, %bl
0x004248ea:	movzwl %ecx, (%esi,%ecx,2)
0x004248ee:	testb %cl, $0x4<UINT8>
0x004248f1:	je 0x004248fb
0x004248fb:	testl %ecx, $0x103<UINT32>
0x00424901:	je 0x00424934
0x00424934:	movl %eax, 0x18(%ebp)
0x00424937:	decl %edi
0x00424938:	testb %al, $0x8<UINT8>
0x0042493a:	jne 32
0x0042493c:	cmpl 0x10(%ebp), $0x0<UINT8>
0x00424940:	je 0x00424945
0x00424945:	andl -4(%ebp), $0x0<UINT8>
0x00424949:	jmp 0x004249a6
0x004249a6:	movl %eax, 0x10(%ebp)
0x004249a9:	testl %eax, %eax
0x004249ab:	je 0x004249af
0x004249af:	testb 0x18(%ebp), $0x2<UINT8>
0x004249b3:	je 0x004249b8
0x004249b8:	cmpb -8(%ebp), $0x0<UINT8>
0x004249bc:	je 0x004249c5
0x004249c5:	movl %eax, -4(%ebp)
0x004249c8:	jmp 0x004249e2
0x004249e2:	popl %ebx
0x004249e3:	popl %edi
0x004249e4:	popl %esi
0x004249e5:	leave
0x004249e6:	ret

0x00424a0d:	addl %esp, $0x14<UINT8>
0x00424a10:	popl %ebp
0x00424a11:	ret

0x0041de31:	addl %esp, $0xc<UINT8>
0x0041de34:	popl %ebp
0x0041de35:	ret

0x0040dded:	movzwl %edi, %ax
0x0040ddf0:	movl %eax, -164(%ebp)
0x0040ddf6:	addl %esp, $0x14<UINT8>
0x0040ddf9:	movl -248(%ebp), %edi
0x0040ddff:	cmpl %eax, %ebx
0x0040de01:	je 9
0x0040de03:	pushl %eax
0x0040de04:	call 0x0041d3a9
0x0040de09:	addl %esp, $0x4<UINT8>
0x0040de0c:	movl -164(%ebp), %ebx
0x0040de12:	movl -160(%ebp), %ebx
0x0040de18:	movl -156(%ebp), %ebx
0x0040de1e:	movb -4(%ebp), $0x6<UINT8>
0x0040de22:	cmpl %esi, %ebx
0x0040de24:	je 9
0x0040de26:	pushl %esi
0x0040de27:	call 0x0041d3a9
0x0040de2c:	addl %esp, $0x4<UINT8>
0x0040de2f:	cmpw %di, %bx
0x0040de32:	jne 28
0x0040de34:	movl %ecx, -240(%ebp)
0x0040de3a:	negl %ecx
0x0040de3c:	sbbl %ecx, %ecx
0x0040de3e:	andl %ecx, $0x16b<UINT32>
0x0040de44:	addl %ecx, $0x50<UINT8>
0x0040de47:	movzwl %edx, %cx
0x0040de4a:	movl -248(%ebp), %edx
0x0040de50:	cmpl 0x70(%ebp), %ebx
0x0040de53:	jne 19
0x0040de55:	movl %eax, 0x4476b4
0x0040de5a:	pushl %eax
0x0040de5b:	movl %eax, 0x4476b8
0x0040de60:	leal %ecx, 0x6c(%ebp)
0x0040de63:	call 0x00406e50
0x0040de68:	cmpl 0x80(%ebp), %ebx
0x0040de6e:	jne 20
0x0040de70:	movl %ecx, 0x4476a4
0x0040de76:	movl %eax, 0x4476a8
0x0040de7b:	pushl %ecx
0x0040de7c:	leal %ecx, 0x7c(%ebp)
0x0040de7f:	call 0x00406e50
0x0040de84:	leal %ecx, -184(%ebp)
0x0040de8a:	call 0x00406a10
0x0040de8f:	cmpl 0x8(%ebp), $0x5<UINT8>
0x0040de93:	movb -4(%ebp), $0xb<UINT8>
0x0040de97:	jne 0x0040e1d6
0x0040e1d6:	call 0x00405790
0x0040e1db:	movl -212(%ebp), %eax
0x0040e1e1:	leal %eax, 0x10(%ebp)
0x0040e1e4:	call 0x004148d0
0x004148d0:	pushl %esi
0x004148d1:	pushl %edi
0x004148d2:	movl %edi, %eax
0x004148d4:	movl %esi, (%edi)
0x004148d6:	cmpl %esi, $0x43ff44<UINT32>
0x004148dc:	jne 0x004148e3
0x004148e3:	movl %eax, $0x43ff44<UINT32>
0x004148e8:	leal %edx, 0x1(%eax)
0x004148eb:	jmp 0x004148f0
0x004148f0:	movb %cl, (%eax)
0x004148f2:	incl %eax
0x004148f3:	testb %cl, %cl
0x004148f5:	jne 0x004148f0
0x004148f7:	movl %ecx, 0x4(%edi)
0x004148fa:	subl %eax, %edx
0x004148fc:	cmpl %ecx, %eax
0x004148fe:	jne 0x00414983
0x00414983:	popl %edi
0x00414984:	movb %al, $0x1<UINT8>
0x00414986:	popl %esi
0x00414987:	ret

0x0040e1e9:	testb %al, %al
0x0040e1eb:	je 207
0x0040e1f1:	pushl %ebx
0x0040e1f2:	pushl $0x4402a4<UINT32>
0x0040e1f7:	leal %eax, -236(%ebp)
0x0040e1fd:	pushl %eax
0x0040e1fe:	call 0x004035d0
0x0040e203:	pushl %ebx
0x0040e204:	pushl $0x43faec<UINT32>
0x0040e209:	leal %ecx, -140(%ebp)
0x0040e20f:	pushl %ecx
0x0040e210:	movb -4(%ebp), $0x13<UINT8>
0x0040e214:	call 0x004035d0
0x0040e219:	leal %ebx, -236(%ebp)
0x0040e21f:	leal %esi, -140(%ebp)
0x0040e225:	leal %eax, 0x44(%ebp)
0x0040e228:	movb -4(%ebp), $0x14<UINT8>
0x0040e22c:	call 0x00405ae0
0x00405ae0:	pushl %edi
0x00405ae1:	movl %edi, %eax
0x00405ae3:	pushl $0x0<UINT8>
0x00405ae5:	movl %eax, %esi
0x00405ae7:	movl %ecx, %edi
0x00405ae9:	call 0x004068e0
0x00405aee:	movl %ecx, %eax
0x00405af0:	cmpl %ecx, $0xffffffff<UINT8>
0x00405af3:	je 33
0x00405af5:	movl %eax, (%ebx)
0x00405af7:	movl %edx, 0x4(%esi)
0x00405afa:	pushl %eax
0x00405afb:	movl %eax, 0x4(%ebx)
0x00405afe:	pushl %edx
0x00405aff:	call 0x00405c70
0x00405c70:	pushl %ebp
0x00405c71:	movl %ebp, %esp
0x00405c73:	movl %edx, 0xc(%ebp)
0x00405c76:	pushl %ebx
0x00405c77:	pushl %esi
0x00405c78:	movl %esi, %eax
0x00405c7a:	movl %eax, (%edi)
0x00405c7c:	movl %ebx, %ecx
0x00405c7e:	cmpl %eax, %edx
0x00405c80:	je 226
0x00405c86:	movl %ecx, 0x4(%edi)
0x00405c89:	cmpl %ebx, %ecx
0x00405c8b:	jae 215
0x00405c91:	testl %edx, %edx
0x00405c93:	jne 0x00405c99
0x00405c99:	testl %esi, %esi
0x00405c9b:	jne 0x00405cab
0x00405cab:	movl %edx, 0x8(%ebp)
0x00405cae:	testl %edx, %edx
0x00405cb0:	jne 0x00405ce6
0x00405ce6:	testl %esi, %esi
0x00405ce8:	jne 0x00405cba
0x00405cba:	leal %eax, (%ebx,%edx)
0x00405cbd:	cmpl %eax, %ecx
0x00405cbf:	jbe 0x00405cc8
0x00405cc8:	cmpl %edx, %esi
0x00405cca:	jne 47
0x00405ccc:	movl %ecx, 0xc(%ebp)
0x00405ccf:	movl %eax, (%edi)
0x00405cd1:	pushl %esi
0x00405cd2:	pushl %ecx
0x00405cd3:	addl %eax, %ebx
0x00405cd5:	pushl %eax
0x00405cd6:	call 0x00420c20
0x00405cdb:	addl %esp, $0xc<UINT8>
0x00405cde:	movl %eax, %edi
0x00405ce0:	popl %esi
0x00405ce1:	popl %ebx
0x00405ce2:	popl %ebp
0x00405ce3:	ret $0x8<UINT16>

0x00405b04:	pushl $0x0<UINT8>
0x00405b06:	movl %eax, %esi
0x00405b08:	movl %ecx, %edi
0x00405b0a:	call 0x004068e0
0x00405b0f:	movl %ecx, %eax
0x00405b11:	cmpl %ecx, $0xffffffff<UINT8>
0x00405b14:	jne 0x00405af5
0x00405b16:	movl %eax, %edi
0x00405b18:	popl %edi
0x00405b19:	ret

0x0040e231:	movl %eax, -140(%ebp)
0x0040e237:	xorl %esi, %esi
0x0040e239:	cmpl %eax, %esi
0x0040e23b:	je 9
0x0040e23d:	pushl %eax
0x0040e23e:	call 0x0041d3a9
0x0040e243:	addl %esp, $0x4<UINT8>
0x0040e246:	movb %bl, $0xb<UINT8>
0x0040e248:	movb -4(%ebp), %bl
0x0040e24b:	movl %eax, -236(%ebp)
0x0040e251:	movl -140(%ebp), %esi
0x0040e257:	movl -136(%ebp), %esi
0x0040e25d:	movl -132(%ebp), %esi
0x0040e263:	cmpl %eax, %esi
0x0040e265:	je 9
0x0040e267:	pushl %eax
0x0040e268:	call 0x0041d3a9
0x0040e26d:	addl %esp, $0x4<UINT8>
0x0040e270:	leal %edx, -236(%ebp)
0x0040e276:	pushl $0x4402a8<UINT32>
0x0040e27b:	pushl %edx
0x0040e27c:	leal %ecx, 0x44(%ebp)
0x0040e27f:	call 0x004084c0
0x0040e284:	addl %esp, $0x8<UINT8>
0x0040e287:	movb -4(%ebp), $0x15<UINT8>
0x0040e28b:	movl %ecx, 0x4(%eax)
0x0040e28e:	movl %eax, (%eax)
0x0040e290:	pushl %eax
0x0040e291:	movl %eax, %ecx
0x0040e293:	leal %ecx, 0x6c(%ebp)
0x0040e296:	call 0x00406e50
0x0040e29b:	movb -4(%ebp), %bl
0x0040e29e:	movl %eax, -236(%ebp)
0x0040e2a4:	cmpl %eax, %esi
0x0040e2a6:	je 9
0x0040e2a8:	pushl %eax
0x0040e2a9:	call 0x0041d3a9
0x0040e2ae:	addl %esp, $0x4<UINT8>
0x0040e2b1:	pushl $0x43fad1<UINT32>
0x0040e2b6:	leal %eax, 0x44(%ebp)
0x0040e2b9:	call 0x00405eb0
0x00403430:	pushl %eax
0x00403431:	call 0x0041d3a9
0x00403436:	addl %esp, $0x4<UINT8>
0x00405efa:	movl %ebx, $0x10<UINT32>
0x00405eff:	cmpl 0x8(%esi), %ebx
0x00405f02:	jae 27
0x00405f04:	call 0x004034c0
0x00405f09:	popl %edi
0x00405f0a:	movl %eax, %esi
0x00405f0c:	popl %esi
0x00405f0d:	popl %ebx
0x00405f0e:	popl %ebp
0x00405f0f:	ret $0x4<UINT16>

0x0040e2be:	xorl %ebx, %ebx
0x0040e2c0:	movl -168(%ebp), %ebx
0x0040e2c6:	leal %ecx, 0x7c(%ebp)
0x0040e2c9:	pushl %ecx
0x0040e2ca:	leal %edx, -300(%ebp)
0x0040e2d0:	pushl %edx
0x0040e2d1:	xorl %edx, %edx
0x0040e2d3:	call 0x0041ad30
0x0040e2d8:	movl %esi, %eax
0x0040e2da:	leal %eax, 0x6c(%ebp)
0x0040e2dd:	pushl %eax
0x0040e2de:	leal %ecx, -268(%ebp)
0x0040e2e4:	pushl %ecx
0x0040e2e5:	xorl %edx, %edx
0x0040e2e7:	movb -4(%ebp), $0x16<UINT8>
0x0040e2eb:	call 0x0041ad30
0x00420c48:	cmpl 0x449a68, $0x0<UINT8>
0x00420c4f:	je 0x00420c67
0x0040e2f0:	movl %edi, %eax
0x0040e2f2:	leal %edx, 0x30(%ebp)
0x0040e2f5:	pushl %edx
0x0040e2f6:	leal %eax, -200(%ebp)
0x0040e2fc:	pushl %eax
0x0040e2fd:	xorl %edx, %edx
0x0040e2ff:	movb -4(%ebp), $0x17<UINT8>
0x0040e303:	call 0x0041ad30
0x0040e308:	movl -220(%ebp), %eax
0x0040e30e:	leal %ecx, 0x20(%ebp)
0x0040e311:	pushl %ecx
0x0040e312:	leal %edx, -164(%ebp)
0x0040e318:	pushl %edx
0x0040e319:	xorl %edx, %edx
0x0040e31b:	movb -4(%ebp), $0x18<UINT8>
0x0040e31f:	call 0x0041ad30
0x0040e324:	movl -204(%ebp), %eax
0x0040e32a:	leal %eax, 0x10(%ebp)
0x0040e32d:	pushl %eax
0x0040e32e:	leal %ecx, -140(%ebp)
0x0040e334:	pushl %ecx
0x0040e335:	xorl %edx, %edx
0x0040e337:	movb -4(%ebp), $0x19<UINT8>
0x0040e33b:	call 0x0041ad30
0x0041ae12:	xorl %eax, %eax
0x0041ae14:	popl %edi
0x0041ae15:	popl %ebp
0x0041ae16:	ret

0x0041ad7c:	pushl %edi
0x0041ad7d:	movl %ecx, $0x4402b4<UINT32>
0x0041ad82:	call 0x00408650
0x004085e6:	testl %ebx, %ebx
0x004085e8:	jne 12
0x004085ea:	movl %ebx, $0x10<UINT32>
0x004085ef:	cmpl 0x8(%esi), %ebx
0x004085f2:	jae 34
0x004085f4:	jmp 0x0040860e
0x0041ad87:	movl %eax, %edi
0x0041ad89:	movl %ecx, -12(%ebp)
0x0041ad8c:	movl %fs:0, %ecx
0x0041ad93:	popl %ecx
0x0041ad94:	popl %edi
0x0041ad95:	popl %esi
0x0041ad96:	movl %esp, %ebp
0x0041ad98:	popl %ebp
0x0041ad99:	ret

0x0040e340:	movl %ecx, 0x68(%ebp)
0x0040e343:	leal %edx, -184(%ebp)
0x0040e349:	pushl %edx
0x0040e34a:	movl %edx, 0x64(%ebp)
0x0040e34d:	pushl %ecx
0x0040e34e:	pushl %edx
0x0040e34f:	leal %ecx, 0x54(%ebp)
0x0040e352:	pushl %ecx
0x0040e353:	movl %ecx, -252(%ebp)
0x0040e359:	leal %edx, 0x44(%ebp)
0x0040e35c:	pushl %edx
0x0040e35d:	movl %edx, -216(%ebp)
0x0040e363:	pushl %ecx
0x0040e364:	movl %ecx, -248(%ebp)
0x0040e36a:	pushl %edx
0x0040e36b:	movl %edx, -240(%ebp)
0x0040e371:	pushl %ecx
0x0040e372:	movl %ecx, -204(%ebp)
0x0040e378:	pushl %esi
0x0040e379:	pushl %edi
0x0040e37a:	pushl %eax
0x0040e37b:	movl %eax, 0x8(%ebp)
0x0040e37e:	pushl %edx
0x0040e37f:	movl %edx, -220(%ebp)
0x0040e385:	pushl %eax
0x0040e386:	movb -4(%ebp), $0x1a<UINT8>
0x0040e38a:	call 0x0040f7e0
0x0040f7e0:	pushl %ebp
0x0040f7e1:	movl %ebp, %esp
0x0040f7e3:	andl %esp, $0xfffffff8<UINT8>
0x0040f7e6:	pushl $0xffffffff<UINT8>
0x0040f7e8:	pushl $0x4321b2<UINT32>
0x0040f7ed:	movl %eax, %fs:0
0x0040f7f3:	pushl %eax
0x0040f7f4:	subl %esp, $0x1e0<UINT32>
0x0040f7fa:	movl %eax, 0x44609c
0x0040f7ff:	xorl %eax, %esp
0x0040f801:	movl 0x1d8(%esp), %eax
0x0040f808:	pushl %ebx
0x0040f809:	pushl %esi
0x0040f80a:	pushl %edi
0x0040f80b:	movl %eax, 0x44609c
0x0040f810:	xorl %eax, %esp
0x0040f812:	pushl %eax
0x0040f813:	leal %eax, 0x1f0(%esp)
0x0040f81a:	movl %fs:0, %eax
0x0040f820:	movl %eax, 0x10(%ebp)
0x0040f823:	movl %edi, 0x24(%ebp)
0x0040f826:	movl 0x40(%esp), %eax
0x0040f82a:	movl %eax, 0x14(%ebp)
0x0040f82d:	movl 0xdc(%esp), %ecx
0x0040f834:	movl %ecx, 0x18(%ebp)
0x0040f837:	movl 0xc8(%esp), %edx
0x0040f83e:	movl %edx, 0x20(%ebp)
0x0040f841:	movl 0xe4(%esp), %eax
0x0040f848:	movl %eax, 0x28(%ebp)
0x0040f84b:	movl 0x48(%esp), %ecx
0x0040f84f:	movl %ecx, 0x2c(%ebp)
0x0040f852:	movl 0x44(%esp), %edx
0x0040f856:	movl %edx, 0x38(%ebp)
0x0040f859:	movl 0xec(%esp), %eax
0x0040f860:	xorl %eax, %eax
0x0040f862:	movl 0xa4(%esp), %ecx
0x0040f869:	movl %ecx, $0x4<UINT32>
0x0040f86e:	movl 0x6c(%esp), %edi
0x0040f872:	movl 0x64(%esp), %edx
0x0040f876:	movl 0x38(%esp), %ecx
0x0040f87a:	movl 0xa8(%esp), %eax
0x0040f881:	movl 0xc0(%esp), %ecx
0x0040f888:	movl 0x68(%esp), %eax
0x0040f88c:	movl 0x4c(%esp), %eax
0x0040f890:	movl 0x28(%esp), %eax
0x0040f894:	movl 0x2c(%esp), %eax
0x0040f898:	movl 0x30(%esp), %eax
0x0040f89c:	movl 0x1f8(%esp), %eax
0x0040f8a3:	leal %ebx, 0x24(%ecx)
0x0040f8a6:	leal %esi, 0x28(%esp)
0x0040f8aa:	movl 0x34(%esp), $0x1<UINT32>
0x0040f8b2:	movb 0x448860, %al
0x0040f8b7:	call 0x004034c0
0x0040f8bc:	movl %eax, 0x2c(%esp)
0x0040f8c0:	movl %edx, 0x4402f8
0x0040f8c6:	movl %ecx, 0x28(%esp)
0x0040f8ca:	movl (%ecx,%eax), %edx
0x0040f8cd:	movl %edx, 0x4402fc
0x0040f8d3:	movl 0x4(%ecx,%eax), %edx
0x0040f8d7:	movl %edx, 0x440300
0x0040f8dd:	movl 0x8(%ecx,%eax), %edx
0x0040f8e1:	movl %edx, 0x440304
0x0040f8e7:	movl 0xc(%ecx,%eax), %edx
0x0040f8eb:	movl %edx, 0x440308
0x0040f8f1:	movl 0x10(%ecx,%eax), %edx
0x0040f8f5:	movl %edx, 0x44030c
0x0040f8fb:	movl 0x14(%ecx,%eax), %edx
0x0040f8ff:	addl %eax, $0x18<UINT8>
0x0040f902:	movl 0x2c(%esp), %eax
0x0040f906:	xorl %eax, %eax
0x0040f908:	movl %ecx, $0x1<UINT32>
0x0040f90d:	movl 0x1f8(%esp), %ecx
0x0040f914:	movl 0x18(%esp), %eax
0x0040f918:	movl 0x1c(%esp), %eax
0x0040f91c:	movl 0x20(%esp), %eax
0x0040f920:	movb 0x1f8(%esp), $0x2<UINT8>
0x0040f928:	leal %esi, 0x18(%esp)
0x0040f92c:	movl 0x24(%esp), %ecx
0x0040f930:	movb 0x448860, %al
0x0040f935:	call 0x004034c0
0x0040f93a:	movl %eax, 0x1c(%esp)
0x0040f93e:	movl %edx, 0x440314
0x0040f944:	movl %ecx, 0x18(%esp)
0x0040f948:	movl (%ecx,%eax), %edx
0x0040f94b:	movl %edx, 0x440318
0x0040f951:	movl 0x4(%ecx,%eax), %edx
0x0040f955:	movl %edx, 0x44031c
0x0040f95b:	movl 0x8(%ecx,%eax), %edx
0x0040f95f:	movl %edx, 0x440320
0x0040f965:	movl 0xc(%ecx,%eax), %edx
0x0040f969:	movl %edx, 0x440324
0x0040f96f:	movl 0x10(%ecx,%eax), %edx
0x0040f973:	movl %edx, 0x440328
0x0040f979:	movl 0x14(%ecx,%eax), %edx
0x0040f97d:	addl %eax, $0x18<UINT8>
0x0040f980:	movl 0x1c(%esp), %eax
0x0040f984:	pushl $0x1<UINT8>
0x0040f986:	pushl $0x0<UINT8>
0x0040f988:	leal %eax, 0x30(%esp)
0x0040f98c:	pushl %eax
0x0040f98d:	leal %ecx, 0x160(%esp)
0x0040f994:	pushl %ecx
0x0040f995:	movb 0x208(%esp), $0x3<UINT8>
0x0040f99d:	call 0x0041b070
0x0040f9a2:	movl %ebx, %eax
0x0040f9a4:	pushl $0x1<UINT8>
0x0040f9a6:	pushl $0x0<UINT8>
0x0040f9a8:	movl %edx, %esi
0x0040f9aa:	pushl %edx
0x0040f9ab:	leal %eax, 0x70(%esp)
0x0040f9af:	pushl %eax
0x0040f9b0:	movb 0x218(%esp), $0x4<UINT8>
0x0040f9b8:	call 0x0041b070
0x0040f9bd:	movl %esi, %eax
0x0040f9bf:	movl %eax, (%esi)
0x0040f9c1:	addl %esp, $0x20<UINT8>
0x0040f9c4:	pushl %eax
0x0040f9c5:	call GetModuleHandleA@kernel32.dll
0x0040f9cb:	testl %eax, %eax
0x0040f9cd:	jne 9
0x0040f9cf:	movl %esi, (%esi)
0x0040f9d1:	pushl %esi
0x0040f9d2:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x0040f9d8:	movl %ebx, (%ebx)
0x0040f9da:	pushl %ebx
0x0040f9db:	pushl %eax
0x0040f9dc:	call GetProcAddress@kernel32.dll
0x0040f9e2:	xorl %esi, %esi
0x0040f9e4:	movl 0xac(%esp), %eax
0x0040f9eb:	cmpl %eax, %esi
0x0040f9ed:	jne 0x0040f9f5
0x0040f9f5:	movl %eax, 0x54(%esp)
0x0040f9f9:	cmpl %eax, %esi
0x0040f9fb:	je 9
0x0040f9fd:	pushl %eax
0x0040f9fe:	call 0x0041d3a9
0x0040fa03:	addl %esp, $0x4<UINT8>
0x0040fa06:	movl %eax, 0x154(%esp)
0x0040fa0d:	movl 0x54(%esp), %esi
0x0040fa11:	movl 0x58(%esp), %esi
0x0040fa15:	movl 0x5c(%esp), %esi
0x0040fa19:	cmpl %eax, %esi
0x0040fa1b:	je 9
0x0040fa1d:	pushl %eax
0x0040fa1e:	call 0x0041d3a9
0x0040fa23:	addl %esp, $0x4<UINT8>
0x0040fa26:	movl %eax, 0x18(%esp)
0x0040fa2a:	movl 0x154(%esp), %esi
0x0040fa31:	movl 0x158(%esp), %esi
0x0040fa38:	movl 0x15c(%esp), %esi
0x0040fa3f:	cmpl %eax, %esi
0x0040fa41:	je 9
0x0040fa43:	pushl %eax
0x0040fa44:	call 0x0041d3a9
0x0040fa49:	addl %esp, $0x4<UINT8>
0x0040fa4c:	movl %eax, 0x28(%esp)
0x0040fa50:	cmpl %eax, %esi
0x0040fa52:	je 9
0x0040fa54:	pushl %eax
0x0040fa55:	call 0x0041d3a9
0x0040fa5a:	addl %esp, $0x4<UINT8>
0x0040fa5d:	xorl %eax, %eax
0x0040fa5f:	movl 0x18(%esp), %eax
0x0040fa63:	movl 0x1c(%esp), %eax
0x0040fa67:	movl 0x20(%esp), %eax
0x0040fa6b:	movl 0x1f8(%esp), $0x5<UINT32>
0x0040fa76:	leal %ebx, 0x30(%eax)
0x0040fa79:	leal %esi, 0x18(%esp)
0x0040fa7d:	movl 0x24(%esp), $0x1<UINT32>
0x0040fa85:	movb 0x448860, %al
0x0040fa8a:	call 0x004034c0
0x0040fa8f:	movl %esi, 0x1c(%esp)
0x0040fa93:	movl %ecx, 0x18(%esp)
0x0040fa97:	pushl $0x20<UINT8>
0x0040fa99:	addl %ecx, %esi
0x0040fa9b:	pushl $0x440330<UINT32>
0x0040faa0:	pushl %ecx
0x0040faa1:	call 0x00420c20
0x0040faa6:	addl %esi, $0x20<UINT8>
0x0040faa9:	addl %esp, $0xc<UINT8>
0x0040faac:	movl 0x1c(%esp), %esi
0x0040fab0:	xorl %eax, %eax
0x0040fab2:	movl 0x1f8(%esp), $0x6<UINT32>
0x0040fabd:	movl 0x28(%esp), %eax
0x0040fac1:	movl 0x2c(%esp), %eax
0x0040fac5:	movl 0x30(%esp), %eax
0x0040fac9:	movb 0x1f8(%esp), $0x7<UINT8>
0x0040fad1:	leal %ebx, 0x28(%eax)
0x0040fad4:	leal %esi, 0x28(%esp)
0x0040fad8:	movl 0x34(%esp), $0x1<UINT32>
0x0040fae0:	movb 0x448860, %al
0x0040fae5:	call 0x004034c0
0x0040faea:	movl %eax, 0x2c(%esp)
0x0040faee:	movl %edx, 0x440314
0x0040faf4:	movl %ecx, 0x28(%esp)
0x0040faf8:	movl (%ecx,%eax), %edx
0x0040fafb:	movl %edx, 0x440318
0x0040fb01:	movl 0x4(%ecx,%eax), %edx
0x0040fb05:	movl %edx, 0x44031c
0x0040fb0b:	movl 0x8(%ecx,%eax), %edx
0x0040fb0f:	movl %edx, 0x440320
0x0040fb15:	movl 0xc(%ecx,%eax), %edx
0x0040fb19:	movl %edx, 0x440324
0x0040fb1f:	movl 0x10(%ecx,%eax), %edx
0x0040fb23:	movl %edx, 0x440328
0x0040fb29:	movl 0x14(%ecx,%eax), %edx
0x0040fb2d:	addl %eax, $0x18<UINT8>
0x0040fb30:	movl 0x2c(%esp), %eax
0x0040fb34:	pushl $0x1<UINT8>
0x0040fb36:	xorl %ebx, %ebx
0x0040fb38:	pushl %ebx
0x0040fb39:	leal %eax, 0x20(%esp)
0x0040fb3d:	pushl %eax
0x0040fb3e:	leal %ecx, 0x110(%esp)
0x0040fb45:	pushl %ecx
0x0040fb46:	movb 0x208(%esp), $0x8<UINT8>
0x0040fb4e:	call 0x0041b070
0x0040fb53:	movl 0x60(%esp), %eax
0x0040fb57:	pushl $0x1<UINT8>
0x0040fb59:	pushl %ebx
0x0040fb5a:	movl %edx, %esi
0x0040fb5c:	pushl %edx
0x0040fb5d:	leal %eax, 0x180(%esp)
0x0040fb64:	pushl %eax
0x0040fb65:	movb 0x218(%esp), $0x9<UINT8>
0x0040fb6d:	call 0x0041b070
0x0040fb72:	movl %esi, %eax
0x0040fb74:	movl %eax, (%esi)
0x0040fb76:	addl %esp, $0x20<UINT8>
0x0040fb79:	pushl %eax
0x0040fb7a:	call GetModuleHandleA@kernel32.dll
0x0040fb80:	cmpl %eax, %ebx
0x0040fb82:	jne 0x0040fb8d
0x0040fb8d:	movl %ecx, 0x50(%esp)
0x0040fb91:	movl %ecx, (%ecx)
0x0040fb93:	pushl %ecx
0x0040fb94:	pushl %eax
0x0040fb95:	call GetProcAddress@kernel32.dll
0x0040fb9b:	movl 0x50(%esp), %eax
0x0040fb9f:	cmpl %eax, %ebx
0x0040fba1:	jne 0x0040fba9
0x0040fba9:	movl %eax, 0x164(%esp)
0x0040fbb0:	cmpl %eax, %ebx
0x0040fbb2:	je 9
0x0040fbb4:	pushl %eax
0x0040fbb5:	call 0x0041d3a9
0x0040fbba:	addl %esp, $0x4<UINT8>
0x0040fbbd:	movl %eax, 0x104(%esp)
0x0040fbc4:	movl 0x164(%esp), %ebx
0x0040fbcb:	movl 0x168(%esp), %ebx
0x0040fbd2:	movl 0x16c(%esp), %ebx
0x0040fbd9:	cmpl %eax, %ebx
0x0040fbdb:	je 9
0x0040fbdd:	pushl %eax
0x0040fbde:	call 0x0041d3a9
0x0040fbe3:	addl %esp, $0x4<UINT8>
0x0040fbe6:	movl %eax, 0x28(%esp)
0x0040fbea:	movl 0x104(%esp), %ebx
0x0040fbf1:	movl 0x108(%esp), %ebx
0x0040fbf8:	movl 0x10c(%esp), %ebx
0x0040fbff:	cmpl %eax, %ebx
0x0040fc01:	je 9
0x0040fc03:	pushl %eax
0x0040fc04:	call 0x0041d3a9
0x0040fc09:	addl %esp, $0x4<UINT8>
0x0040fc0c:	movl %eax, 0x18(%esp)
0x0040fc10:	cmpl %eax, %ebx
0x0040fc12:	je 9
0x0040fc14:	pushl %eax
0x0040fc15:	call 0x0041d3a9
0x0040fc1a:	addl %esp, $0x4<UINT8>
0x0040fc1d:	movl 0x18(%esp), %ebx
0x0040fc21:	movl 0x1c(%esp), %ebx
0x0040fc25:	movl 0x20(%esp), %ebx
0x0040fc29:	movl 0x1f8(%esp), $0xa<UINT32>
0x0040fc34:	movb 0x448860, %bl
0x0040fc3a:	movl %ebx, $0x30<UINT32>
0x0040fc3f:	leal %esi, 0x18(%esp)
0x0040fc43:	movl 0x24(%esp), $0x1<UINT32>
0x0040fc4b:	call 0x004034c0
0x0040fc50:	movl %esi, 0x1c(%esp)
0x0040fc54:	movl %edx, 0x18(%esp)
0x0040fc58:	pushl $0x20<UINT8>
0x0040fc5a:	addl %edx, %esi
0x0040fc5c:	pushl $0x440354<UINT32>
0x0040fc61:	pushl %edx
0x0040fc62:	call 0x00420c20
0x0040fc67:	addl %esi, $0x20<UINT8>
0x0040fc6a:	addl %esp, $0xc<UINT8>
0x0040fc6d:	movl 0x1c(%esp), %esi
0x0040fc71:	xorl %eax, %eax
0x0040fc73:	movl 0x1f8(%esp), $0xb<UINT32>
0x0040fc7e:	movl 0x28(%esp), %eax
0x0040fc82:	movl 0x2c(%esp), %eax
0x0040fc86:	movl 0x30(%esp), %eax
0x0040fc8a:	movb 0x1f8(%esp), $0xc<UINT8>
0x0040fc92:	leal %ebx, 0x28(%eax)
0x0040fc95:	leal %esi, 0x28(%esp)
0x0040fc99:	movl 0x34(%esp), $0x1<UINT32>
0x0040fca1:	movb 0x448860, %al
0x0040fca6:	call 0x004034c0
0x0040fcab:	movl %eax, 0x2c(%esp)
0x0040fcaf:	movl %edx, 0x440314
0x0040fcb5:	movl %ecx, 0x28(%esp)
0x0040fcb9:	movl (%ecx,%eax), %edx
0x0040fcbc:	movl %edx, 0x440318
0x0040fcc2:	movl 0x4(%ecx,%eax), %edx
0x0040fcc6:	movl %edx, 0x44031c
0x0040fccc:	movl 0x8(%ecx,%eax), %edx
0x0040fcd0:	movl %edx, 0x440320
0x0040fcd6:	movl 0xc(%ecx,%eax), %edx
0x0040fcda:	movl %edx, 0x440324
0x0040fce0:	movl 0x10(%ecx,%eax), %edx
0x0040fce4:	movl %edx, 0x440328
0x0040fcea:	movl 0x14(%ecx,%eax), %edx
0x0040fcee:	addl %eax, $0x18<UINT8>
0x0040fcf1:	movl 0x2c(%esp), %eax
0x0040fcf5:	pushl $0x1<UINT8>
0x0040fcf7:	xorl %ebx, %ebx
0x0040fcf9:	pushl %ebx
0x0040fcfa:	leal %eax, 0x20(%esp)
0x0040fcfe:	pushl %eax
0x0040fcff:	leal %ecx, 0x100(%esp)
0x0040fd06:	pushl %ecx
0x0040fd07:	movb 0x208(%esp), $0xd<UINT8>
0x0040fd0f:	call 0x0041b070
0x0040fd14:	movl 0x4c(%esp), %eax
0x0040fd18:	pushl $0x1<UINT8>
0x0040fd1a:	pushl %ebx
0x0040fd1b:	movl %edx, %esi
0x0040fd1d:	pushl %edx
0x0040fd1e:	leal %eax, 0x130(%esp)
0x0040fd25:	pushl %eax
0x0040fd26:	movb 0x218(%esp), $0xe<UINT8>
0x0040fd2e:	call 0x0041b070
0x0040fd33:	movl %esi, %eax
0x0040fd35:	movl %eax, (%esi)
0x0040fd37:	addl %esp, $0x20<UINT8>
0x0040fd3a:	pushl %eax
0x0040fd3b:	call GetModuleHandleA@kernel32.dll
0x0040fd41:	cmpl %eax, %ebx
0x0040fd43:	jne 0x0040fd4e
0x0040fd4e:	movl %ecx, 0x3c(%esp)
0x0040fd52:	movl %ecx, (%ecx)
0x0040fd54:	pushl %ecx
0x0040fd55:	pushl %eax
0x0040fd56:	call GetProcAddress@kernel32.dll
0x0040fd5c:	movl 0xe8(%esp), %eax
0x0040fd63:	cmpl %eax, %ebx
0x0040fd65:	jne 0x0040fd6d
0x0040fd6d:	movl %eax, 0x114(%esp)
0x0040fd74:	cmpl %eax, %ebx
0x0040fd76:	je 9
0x0040fd78:	pushl %eax
0x0040fd79:	call 0x0041d3a9
0x0040fd7e:	addl %esp, $0x4<UINT8>
0x0040fd81:	movl %eax, 0xf4(%esp)
0x0040fd88:	movl 0x114(%esp), %ebx
0x0040fd8f:	movl 0x118(%esp), %ebx
0x0040fd96:	movl 0x11c(%esp), %ebx
0x0040fd9d:	cmpl %eax, %ebx
0x0040fd9f:	je 9
0x0040fda1:	pushl %eax
0x0040fda2:	call 0x0041d3a9
0x0040fda7:	addl %esp, $0x4<UINT8>
0x0040fdaa:	movl %eax, 0x28(%esp)
0x0040fdae:	movl 0xf4(%esp), %ebx
0x0040fdb5:	movl 0xf8(%esp), %ebx
0x0040fdbc:	movl 0xfc(%esp), %ebx
0x0040fdc3:	cmpl %eax, %ebx
0x0040fdc5:	je 9
0x0040fdc7:	pushl %eax
0x0040fdc8:	call 0x0041d3a9
0x0040fdcd:	addl %esp, $0x4<UINT8>
0x0040fdd0:	movl %eax, 0x18(%esp)
0x0040fdd4:	cmpl %eax, %ebx
0x0040fdd6:	je 9
0x0040fdd8:	pushl %eax
0x0040fdd9:	call 0x0041d3a9
0x0040fdde:	addl %esp, $0x4<UINT8>
0x0040fde1:	movl 0x18(%esp), %ebx
0x0040fde5:	movl 0x1c(%esp), %ebx
0x0040fde9:	movl 0x20(%esp), %ebx
0x0040fded:	movl 0x1f8(%esp), $0xf<UINT32>
0x0040fdf8:	movb 0x448860, %bl
0x0040fdfe:	movl %ebx, $0x30<UINT32>
0x0040fe03:	leal %esi, 0x18(%esp)
0x0040fe07:	movl 0x24(%esp), $0x1<UINT32>
0x0040fe0f:	call 0x004034c0
0x0040fe14:	movl %esi, 0x1c(%esp)
0x0040fe18:	movl %edx, 0x18(%esp)
0x0040fe1c:	pushl $0x20<UINT8>
0x0040fe1e:	addl %edx, %esi
0x0040fe20:	pushl $0x440378<UINT32>
0x0040fe25:	pushl %edx
0x0040fe26:	call 0x00420c20
0x0040fe2b:	addl %esi, $0x20<UINT8>
0x0040fe2e:	addl %esp, $0xc<UINT8>
0x0040fe31:	movl 0x1c(%esp), %esi
0x0040fe35:	xorl %eax, %eax
0x0040fe37:	movl 0x1f8(%esp), $0x10<UINT32>
0x0040fe42:	movl 0x28(%esp), %eax
0x0040fe46:	movl 0x2c(%esp), %eax
0x0040fe4a:	movl 0x30(%esp), %eax
0x0040fe4e:	movb 0x1f8(%esp), $0x11<UINT8>
0x0040fe56:	leal %ebx, 0x28(%eax)
0x0040fe59:	leal %esi, 0x28(%esp)
0x0040fe5d:	movl 0x34(%esp), $0x1<UINT32>
0x0040fe65:	movb 0x448860, %al
0x0040fe6a:	call 0x004034c0
0x0040fe6f:	movl %eax, 0x2c(%esp)
0x0040fe73:	movl %edx, 0x440314
0x0040fe79:	movl %ecx, 0x28(%esp)
0x0040fe7d:	movl (%ecx,%eax), %edx
0x0040fe80:	movl %edx, 0x440318
0x0040fe86:	movl 0x4(%ecx,%eax), %edx
0x0040fe8a:	movl %edx, 0x44031c
0x0040fe90:	movl 0x8(%ecx,%eax), %edx
0x0040fe94:	movl %edx, 0x440320
0x0040fe9a:	movl 0xc(%ecx,%eax), %edx
0x0040fe9e:	movl %edx, 0x440324
0x0040fea4:	movl 0x10(%ecx,%eax), %edx
0x0040fea8:	movl %edx, 0x440328
0x0040feae:	movl 0x14(%ecx,%eax), %edx
0x0040feb2:	addl %eax, $0x18<UINT8>
0x0040feb5:	movl 0x2c(%esp), %eax
0x0040feb9:	pushl $0x1<UINT8>
0x0040febb:	xorl %ebx, %ebx
0x0040febd:	pushl %ebx
0x0040febe:	leal %eax, 0x20(%esp)
0x0040fec2:	pushl %eax
0x0040fec3:	leal %ecx, 0x150(%esp)
0x0040feca:	pushl %ecx
0x0040fecb:	movb 0x208(%esp), $0x12<UINT8>
0x0040fed3:	call 0x0041b070
0x0040fed8:	movl 0x4c(%esp), %eax
0x0040fedc:	pushl $0x1<UINT8>
0x0040fede:	pushl %ebx
0x0040fedf:	movl %edx, %esi
0x0040fee1:	pushl %edx
0x0040fee2:	leal %eax, 0x150(%esp)
0x0040fee9:	pushl %eax
0x0040feea:	movb 0x218(%esp), $0x13<UINT8>
0x0040fef2:	call 0x0041b070
0x0040fef7:	movl %esi, %eax
0x0040fef9:	movl %eax, (%esi)
0x0040fefb:	addl %esp, $0x20<UINT8>
0x0040fefe:	pushl %eax
0x0040feff:	call GetModuleHandleA@kernel32.dll
0x0040ff05:	cmpl %eax, %ebx
0x0040ff07:	jne 0x0040ff12
0x0040ff12:	movl %ecx, 0x3c(%esp)
0x0040ff16:	movl %ecx, (%ecx)
0x0040ff18:	pushl %ecx
0x0040ff19:	pushl %eax
0x0040ff1a:	call GetProcAddress@kernel32.dll
0x0040ff20:	movl 0xc4(%esp), %eax
0x0040ff27:	cmpl %eax, %ebx
0x0040ff29:	jne 0x0040ff31
0x0040ff31:	movl %eax, 0x134(%esp)
0x0040ff38:	cmpl %eax, %ebx
0x0040ff3a:	je 9
0x0040ff3c:	pushl %eax
0x0040ff3d:	call 0x0041d3a9
0x0040ff42:	addl %esp, $0x4<UINT8>
0x0040ff45:	movl %eax, 0x144(%esp)
0x0040ff4c:	movl 0x134(%esp), %ebx
0x0040ff53:	movl 0x138(%esp), %ebx
0x0040ff5a:	movl 0x13c(%esp), %ebx
0x0040ff61:	cmpl %eax, %ebx
0x0040ff63:	je 9
0x0040ff65:	pushl %eax
0x0040ff66:	call 0x0041d3a9
0x0040ff6b:	addl %esp, $0x4<UINT8>
0x0040ff6e:	movl %eax, 0x28(%esp)
0x0040ff72:	movl 0x144(%esp), %ebx
0x0040ff79:	movl 0x148(%esp), %ebx
0x0040ff80:	movl 0x14c(%esp), %ebx
0x0040ff87:	cmpl %eax, %ebx
0x0040ff89:	je 9
0x0040ff8b:	pushl %eax
0x0040ff8c:	call 0x0041d3a9
0x0040ff91:	addl %esp, $0x4<UINT8>
0x0040ff94:	movl %eax, 0x18(%esp)
0x0040ff98:	cmpl %eax, %ebx
0x0040ff9a:	je 9
0x0040ff9c:	pushl %eax
0x0040ff9d:	call 0x0041d3a9
0x0040ffa2:	addl %esp, $0x4<UINT8>
0x0040ffa5:	movl 0x18(%esp), %ebx
0x0040ffa9:	movl 0x1c(%esp), %ebx
0x0040ffad:	movl 0x20(%esp), %ebx
0x0040ffb1:	movl 0x1f8(%esp), $0x14<UINT32>
0x0040ffbc:	movb 0x448860, %bl
0x0040ffc2:	movl %ebx, $0x30<UINT32>
0x0040ffc7:	leal %esi, 0x18(%esp)
0x0040ffcb:	movl 0x24(%esp), $0x1<UINT32>
0x0040ffd3:	call 0x004034c0
0x0040ffd8:	movl %esi, 0x1c(%esp)
0x0040ffdc:	movl %edx, 0x18(%esp)
0x0040ffe0:	pushl $0x20<UINT8>
0x0040ffe2:	addl %edx, %esi
0x0040ffe4:	pushl $0x44039c<UINT32>
0x0040ffe9:	pushl %edx
0x0040ffea:	call 0x00420c20
0x0040ffef:	addl %esi, $0x20<UINT8>
0x0040fff2:	addl %esp, $0xc<UINT8>
0x0040fff5:	movl 0x1c(%esp), %esi
0x0040fff9:	xorl %eax, %eax
0x0040fffb:	movl 0x1f8(%esp), $0x15<UINT32>
0x00410006:	movl 0x28(%esp), %eax
0x0041000a:	movl 0x2c(%esp), %eax
0x0041000e:	movl 0x30(%esp), %eax
0x00410012:	movb 0x1f8(%esp), $0x16<UINT8>
0x0041001a:	leal %ebx, 0x28(%eax)
0x0041001d:	leal %esi, 0x28(%esp)
0x00410021:	movl 0x34(%esp), $0x1<UINT32>
0x00410029:	movb 0x448860, %al
0x0041002e:	call 0x004034c0
0x00410033:	movl %eax, 0x2c(%esp)
0x00410037:	movl %edx, 0x440314
0x0041003d:	movl %ecx, 0x28(%esp)
0x00410041:	movl (%eax,%ecx), %edx
0x00410044:	movl %edx, 0x440318
0x0041004a:	movl 0x4(%eax,%ecx), %edx
0x0041004e:	movl %edx, 0x44031c
0x00410054:	movl 0x8(%eax,%ecx), %edx
0x00410058:	movl %edx, 0x440320
0x0041005e:	movl 0xc(%eax,%ecx), %edx
0x00410062:	movl %edx, 0x440324
0x00410068:	movl 0x10(%eax,%ecx), %edx
0x0041006c:	movl %edx, 0x440328
0x00410072:	movl 0x14(%eax,%ecx), %edx
0x00410076:	addl %eax, $0x18<UINT8>
0x00410079:	movl 0x2c(%esp), %eax
0x0041007d:	pushl $0x1<UINT8>
0x0041007f:	xorl %ebx, %ebx
0x00410081:	pushl %ebx
0x00410082:	leal %eax, 0x20(%esp)
0x00410086:	pushl %eax
0x00410087:	leal %ecx, 0xd8(%esp)
0x0041008e:	pushl %ecx
0x0041008f:	movb 0x208(%esp), $0x17<UINT8>
0x00410097:	call 0x0041b070
0x0041009c:	movl 0x4c(%esp), %eax
0x004100a0:	pushl $0x1<UINT8>
0x004100a2:	pushl %ebx
0x004100a3:	movl %edx, %esi
0x004100a5:	pushl %edx
0x004100a6:	leal %eax, 0x140(%esp)
0x004100ad:	pushl %eax
0x004100ae:	movb 0x218(%esp), $0x18<UINT8>
0x004100b6:	call 0x0041b070
0x004100bb:	movl %esi, %eax
0x004100bd:	movl %eax, (%esi)
0x004100bf:	addl %esp, $0x20<UINT8>
0x004100c2:	pushl %eax
0x004100c3:	call GetModuleHandleA@kernel32.dll
0x004100c9:	cmpl %eax, %ebx
0x004100cb:	jne 0x004100d6
0x004100d6:	movl %ecx, 0x3c(%esp)
0x004100da:	movl %ecx, (%ecx)
0x004100dc:	pushl %ecx
0x004100dd:	pushl %eax
0x004100de:	call GetProcAddress@kernel32.dll
0x004100e4:	movl 0x70(%esp), %eax
0x004100e8:	cmpl %eax, %ebx
0x004100ea:	jne 0x004100f2
0x004100f2:	movl %eax, 0x124(%esp)
0x004100f9:	cmpl %eax, %ebx
0x004100fb:	je 9
0x004100fd:	pushl %eax
0x004100fe:	call 0x0041d3a9
0x00410103:	addl %esp, $0x4<UINT8>
0x00410106:	movl %eax, 0xcc(%esp)
0x0041010d:	movl 0x124(%esp), %ebx
0x00410114:	movl 0x128(%esp), %ebx
0x0041011b:	movl 0x12c(%esp), %ebx
0x00410122:	cmpl %eax, %ebx
0x00410124:	je 9
0x00410126:	pushl %eax
0x00410127:	call 0x0041d3a9
0x0041012c:	addl %esp, $0x4<UINT8>
0x0041012f:	movl %eax, 0x28(%esp)
0x00410133:	movl 0xcc(%esp), %ebx
0x0041013a:	movl 0xd0(%esp), %ebx
0x00410141:	movl 0xd4(%esp), %ebx
0x00410148:	cmpl %eax, %ebx
0x0041014a:	je 9
0x0041014c:	pushl %eax
0x0041014d:	call 0x0041d3a9
0x00410152:	addl %esp, $0x4<UINT8>
0x00410155:	movl %eax, 0x18(%esp)
0x00410159:	cmpl %eax, %ebx
0x0041015b:	je 9
0x0041015d:	pushl %eax
0x0041015e:	call 0x0041d3a9
0x00410163:	addl %esp, $0x4<UINT8>
0x00410166:	movl 0x18(%esp), %ebx
0x0041016a:	movl 0x1c(%esp), %ebx
0x0041016e:	movl 0x20(%esp), %ebx
0x00410172:	movl 0x1f8(%esp), $0x19<UINT32>
0x0041017d:	movb 0x448860, %bl
0x00410183:	movl %ebx, $0x30<UINT32>
0x00410188:	leal %esi, 0x18(%esp)
0x0041018c:	movl 0x24(%esp), $0x1<UINT32>
0x00410194:	call 0x004034c0
0x00410199:	movl %edx, 0x18(%esp)
0x0041019d:	movl %esi, 0x1c(%esp)
0x004101a1:	pushl $0x20<UINT8>
0x004101a3:	leal %eax, (%esi,%edx)
0x004101a6:	pushl $0x4403c0<UINT32>
0x004101ab:	pushl %eax
0x004101ac:	call 0x00420c20
0x004101b1:	addl %esi, $0x20<UINT8>
0x004101b4:	addl %esp, $0xc<UINT8>
0x004101b7:	movl 0x1c(%esp), %esi
0x004101bb:	xorl %eax, %eax
0x004101bd:	movl 0x1f8(%esp), $0x1a<UINT32>
0x004101c8:	movl 0x28(%esp), %eax
0x004101cc:	movl 0x2c(%esp), %eax
0x004101d0:	movl 0x30(%esp), %eax
0x004101d4:	movb 0x1f8(%esp), $0x1b<UINT8>
0x004101dc:	leal %ebx, 0x28(%eax)
0x004101df:	leal %esi, 0x28(%esp)
0x004101e3:	movl 0x34(%esp), $0x1<UINT32>
0x004101eb:	movb 0x448860, %al
0x004101f0:	call 0x004034c0
0x004101f5:	movl %eax, 0x2c(%esp)
0x004101f9:	movl %edx, 0x440314
0x004101ff:	movl %ecx, 0x28(%esp)
0x00410203:	movl (%eax,%ecx), %edx
0x00410206:	movl %edx, 0x440318
0x0041020c:	movl 0x4(%eax,%ecx), %edx
0x00410210:	movl %edx, 0x44031c
0x00410216:	movl 0x8(%eax,%ecx), %edx
0x0041021a:	movl %edx, 0x440320
0x00410220:	movl 0xc(%eax,%ecx), %edx
0x00410224:	movl %edx, 0x440324
0x0041022a:	movl 0x10(%eax,%ecx), %edx
0x0041022e:	movl %edx, 0x440328
0x00410234:	movl 0x14(%eax,%ecx), %edx
0x00410238:	addl %eax, $0x18<UINT8>
0x0041023b:	movl 0x2c(%esp), %eax
0x0041023f:	pushl $0x1<UINT8>
0x00410241:	xorl %ebx, %ebx
0x00410243:	pushl %ebx
0x00410244:	leal %eax, 0x20(%esp)
0x00410248:	pushl %eax
0x00410249:	leal %ecx, 0xa0(%esp)
0x00410250:	pushl %ecx
0x00410251:	movb 0x208(%esp), $0x1c<UINT8>
0x00410259:	call 0x0041b070
0x0041025e:	movl 0x4c(%esp), %eax
0x00410262:	pushl $0x1<UINT8>
0x00410264:	pushl %ebx
0x00410265:	movl %edx, %esi
0x00410267:	pushl %edx
0x00410268:	leal %eax, 0xcc(%esp)
0x0041026f:	pushl %eax
0x00410270:	movb 0x218(%esp), $0x1d<UINT8>
0x00410278:	call 0x0041b070
0x0041027d:	movl %esi, %eax
0x0041027f:	movl %eax, (%esi)
0x00410281:	addl %esp, $0x20<UINT8>
0x00410284:	pushl %eax
0x00410285:	call GetModuleHandleA@kernel32.dll
0x0041028b:	cmpl %eax, %ebx
0x0041028d:	jne 0x00410298
0x00410298:	movl %ecx, 0x3c(%esp)
0x0041029c:	movl %ecx, (%ecx)
0x0041029e:	pushl %ecx
0x0041029f:	pushl %eax
0x004102a0:	call GetProcAddress@kernel32.dll
0x004102a6:	movl 0xe0(%esp), %eax
0x004102ad:	cmpl %eax, %ebx
0x004102af:	jne 0x004102b7
0x004102b7:	movl %eax, 0xb0(%esp)
0x004102be:	cmpl %eax, %ebx
0x004102c0:	je 9
0x004102c2:	pushl %eax
0x004102c3:	call 0x0041d3a9
0x004102c8:	addl %esp, $0x4<UINT8>
0x004102cb:	movl %eax, 0x94(%esp)
0x004102d2:	movl 0xb0(%esp), %ebx
0x004102d9:	movl 0xb4(%esp), %ebx
0x004102e0:	movl 0xb8(%esp), %ebx
0x004102e7:	cmpl %eax, %ebx
0x004102e9:	je 9
0x004102eb:	pushl %eax
0x004102ec:	call 0x0041d3a9
0x004102f1:	addl %esp, $0x4<UINT8>
0x004102f4:	movl %eax, 0x28(%esp)
0x004102f8:	movl 0x94(%esp), %ebx
0x004102ff:	movl 0x98(%esp), %ebx
0x00410306:	movl 0x9c(%esp), %ebx
0x0041030d:	cmpl %eax, %ebx
0x0041030f:	je 9
0x00410311:	pushl %eax
0x00410312:	call 0x0041d3a9
0x00410317:	addl %esp, $0x4<UINT8>
0x0041031a:	movl %eax, 0x18(%esp)
0x0041031e:	cmpl %eax, %ebx
0x00410320:	je 9
0x00410322:	pushl %eax
0x00410323:	call 0x0041d3a9
0x00410328:	addl %esp, $0x4<UINT8>
0x0041032b:	movl 0x18(%esp), %ebx
0x0041032f:	movl 0x1c(%esp), %ebx
0x00410333:	movl 0x20(%esp), %ebx
0x00410337:	movl 0x1f8(%esp), $0x1e<UINT32>
0x00410342:	movb 0x448860, %bl
0x00410348:	movl %ebx, $0x28<UINT32>
0x0041034d:	leal %esi, 0x18(%esp)
0x00410351:	movl 0x24(%esp), $0x1<UINT32>
0x00410359:	call 0x004034c0
0x0041035e:	movl %eax, 0x1c(%esp)
0x00410362:	movl %edx, 0x4403e4
0x00410368:	movl %ecx, 0x18(%esp)
0x0041036c:	movl (%ecx,%eax), %edx
0x0041036f:	movl %edx, 0x4403e8
0x00410375:	movl 0x4(%ecx,%eax), %edx
0x00410379:	movl %edx, 0x4403ec
0x0041037f:	movl 0x8(%ecx,%eax), %edx
0x00410383:	movl %edx, 0x4403f0
0x00410389:	movl 0xc(%ecx,%eax), %edx
0x0041038d:	movl %edx, 0x4403f4
0x00410393:	movl 0x10(%ecx,%eax), %edx
0x00410397:	movl %edx, 0x4403f8
0x0041039d:	movl 0x14(%ecx,%eax), %edx
0x004103a1:	addl %eax, $0x18<UINT8>
0x004103a4:	movl 0x1c(%esp), %eax
0x004103a8:	xorl %eax, %eax
0x004103aa:	movl 0x1f8(%esp), $0x1f<UINT32>
0x004103b5:	movl 0x28(%esp), %eax
0x004103b9:	movl 0x2c(%esp), %eax
0x004103bd:	movl 0x30(%esp), %eax
0x004103c1:	movb 0x1f8(%esp), $0x20<UINT8>
0x004103c9:	leal %esi, 0x28(%esp)
0x004103cd:	movl 0x34(%esp), $0x1<UINT32>
0x004103d5:	movb 0x448860, %al
0x004103da:	call 0x004034c0
0x004103df:	movl %eax, 0x2c(%esp)
0x004103e3:	movl %edx, 0x440314
0x004103e9:	movl %ecx, 0x28(%esp)
0x004103ed:	movl (%ecx,%eax), %edx
0x004103f0:	movl %edx, 0x440318
0x004103f6:	movl 0x4(%ecx,%eax), %edx
0x004103fa:	movl %edx, 0x44031c
0x00410400:	movl 0x8(%ecx,%eax), %edx
0x00410404:	movl %edx, 0x440320
0x0041040a:	movl 0xc(%ecx,%eax), %edx
0x0041040e:	movl %edx, 0x440324
0x00410414:	movl 0x10(%ecx,%eax), %edx
0x00410418:	movl %edx, 0x440328
0x0041041e:	movl 0x14(%ecx,%eax), %edx
0x00410422:	addl %eax, $0x18<UINT8>
0x00410425:	movl 0x2c(%esp), %eax
0x00410429:	pushl $0x1<UINT8>
0x0041042b:	pushl $0x0<UINT8>
0x0041042d:	leal %eax, 0x20(%esp)
0x00410431:	pushl %eax
0x00410432:	leal %ecx, 0x80(%esp)
0x00410439:	pushl %ecx
0x0041043a:	movb 0x208(%esp), $0x21<UINT8>
0x00410442:	call 0x0041b070
0x00410447:	movl %ebx, %eax
0x00410449:	pushl $0x1<UINT8>
0x0041044b:	pushl $0x0<UINT8>
0x0041044d:	movl %edx, %esi
0x0041044f:	pushl %edx
0x00410450:	leal %eax, 0xa0(%esp)
0x00410457:	pushl %eax
0x00410458:	movb 0x218(%esp), $0x22<UINT8>
0x00410460:	call 0x0041b070
0x00410465:	movl %esi, %eax
0x00410467:	movl %eax, (%esi)
0x00410469:	addl %esp, $0x20<UINT8>
0x0041046c:	pushl %eax
0x0041046d:	call GetModuleHandleA@kernel32.dll
0x00410473:	testl %eax, %eax
0x00410475:	jne 0x00410480
0x00410480:	movl %ebx, (%ebx)
0x00410482:	pushl %ebx
0x00410483:	pushl %eax
0x00410484:	call GetProcAddress@kernel32.dll
0x0041048a:	xorl %esi, %esi
0x0041048c:	movl 0xf0(%esp), %eax
0x00410493:	cmpl %eax, %esi
0x00410495:	jne 0x0041049d
0x0041049d:	movl %eax, 0x84(%esp)
0x004104a4:	cmpl %eax, %esi
0x004104a6:	je 9
0x004104a8:	pushl %eax
0x004104a9:	call 0x0041d3a9
0x004104ae:	addl %esp, $0x4<UINT8>
0x004104b1:	movl %eax, 0x74(%esp)
0x004104b5:	movl 0x84(%esp), %esi
0x004104bc:	movl 0x88(%esp), %esi
0x004104c3:	movl 0x8c(%esp), %esi
0x004104ca:	cmpl %eax, %esi
0x004104cc:	je 9
0x004104ce:	pushl %eax
0x004104cf:	call 0x0041d3a9
0x004104d4:	addl %esp, $0x4<UINT8>
0x004104d7:	movl %eax, 0x28(%esp)
0x004104db:	movl 0x74(%esp), %esi
0x004104df:	movl 0x78(%esp), %esi
0x004104e3:	movl 0x7c(%esp), %esi
0x004104e7:	cmpl %eax, %esi
0x004104e9:	je 9
0x004104eb:	pushl %eax
0x004104ec:	call 0x0041d3a9
0x004104f1:	addl %esp, $0x4<UINT8>
0x004104f4:	movl 0x1f8(%esp), $0xffffffff<UINT32>
0x004104ff:	movl %eax, 0x18(%esp)
0x00410503:	cmpl %eax, %esi
0x00410505:	je 9
0x00410507:	pushl %eax
0x00410508:	call 0x0041d3a9
0x0041050d:	addl %esp, $0x4<UINT8>
0x00410510:	movl %eax, 0x44(%esp)
0x00410514:	movl %edx, 0x4(%eax)
0x00410517:	cmpl %edx, %esi
0x00410519:	je 49
0x0041051b:	cmpl 0x20(%eax), %esi
0x0041051e:	jne 4
0x00410520:	xorl %esi, %esi
0x00410522:	jmp 0x00410529
0x00410529:	cmpl %edx, $0x1<UINT8>
0x0041052c:	jne 14
0x0041052e:	movl %eax, 0x48(%esp)
0x00410532:	movl %eax, (%eax)
0x00410534:	xorl %ecx, %ecx
0x00410536:	pushl %ecx
0x00410537:	pushl %esi
0x00410538:	pushl %ecx
0x00410539:	pushl %edx
0x0041053a:	jmp 0x00410556
0x00410556:	pushl %eax
0x00410557:	call InternetOpenW@WinINet.dll
InternetOpenW@WinINet.dll: API Node	
0x0041055e:	movl 0x48(%esp), %eax
0x00410562:	movl %eax, 0x8(%ebp)
0x00410565:	cmpl %eax, $0x1<UINT8>
0x00410568:	je 0x0041056f
0x0041056f:	movl %ebx, 0x50(%esp)
0x00410573:	pushl $0x4<UINT8>
0x00410575:	leal %edx, 0x40(%esp)
0x00410579:	pushl %edx
0x0041057a:	pushl $0x6<UINT8>
0x0041057c:	pushl $0x0<UINT8>
0x0041057e:	movl 0x4c(%esp), $0x1d4c0<UINT32>
0x00410586:	call InternetSetOptionW@WinINet.dll
InternetSetOptionW@WinINet.dll: API Node	
0x00410588:	jmp 0x0041058e
0x0041058e:	movl %ecx, 0x48(%esp)
0x00410592:	testl %ecx, %ecx
0x00410594:	je 1825
0x0041059a:	movl %edx, 0x1c(%ebp)
0x0041059d:	movl %eax, 0xdc(%esp)
0x004105a4:	movl %eax, (%eax)
0x004105a6:	pushl $0x1<UINT8>
0x004105a8:	pushl $0x0<UINT8>
0x004105aa:	pushl $0x3<UINT8>
0x004105ac:	pushl $0x0<UINT8>
0x004105ae:	pushl $0x0<UINT8>
0x004105b0:	pushl %edx
0x004105b1:	pushl %eax
0x004105b2:	pushl %ecx
0x004105b3:	call InternetConnectW@WinINet.dll
InternetConnectW@WinINet.dll: API Node	
0x004105ba:	movl 0x68(%esp), %eax
0x004105be:	testl %eax, %eax
0x004105c0:	je 1781
0x004105c6:	movl %eax, 0xc8(%esp)
0x004105cd:	movl %eax, (%eax)
0x004105cf:	movl %ecx, 0x40(%esp)
0x004105d3:	movl %ecx, (%ecx)
0x004105d5:	movl %edx, 0x68(%esp)
0x004105d9:	pushl $0x0<UINT8>
0x004105db:	pushl $0x680200<UINT32>
0x004105e0:	pushl $0x0<UINT8>
0x004105e2:	pushl $0x0<UINT8>
0x004105e4:	pushl $0x440400<UINT32>
0x004105e9:	pushl %eax
0x004105ea:	pushl %ecx
0x004105eb:	pushl %edx
0x004105ec:	call HttpOpenRequestW@WinINet.dll
HttpOpenRequestW@WinINet.dll: API Node	
0x004105f3:	movl 0x4c(%esp), %eax
0x004105f7:	testl %eax, %eax
0x004105f9:	je 1724
0x004105ff:	movl %ecx, 0xe4(%esp)
0x00410606:	movl %eax, 0x4(%ecx)
0x00410609:	movl %ecx, (%ecx)
0x0041060b:	movl %esi, 0x4c(%esp)
0x0041060f:	pushl $0xa0000000<UINT32>
0x00410614:	pushl %eax
0x00410615:	pushl %ecx
0x00410616:	pushl %esi
0x00410617:	call HttpAddRequestHeadersW@WinINet.dll
HttpAddRequestHeadersW@WinINet.dll: API Node	
0x0041061e:	cmpl 0xc(%ebp), $0x0<UINT8>
0x00410622:	je 0x00410640
0x00410640:	movl %ecx, 0x44(%esp)
0x00410644:	movl %eax, 0x30(%ecx)
0x00410647:	testl %eax, %eax
0x00410649:	je 0x00410675
0x00410675:	movl %eax, 0xec(%esp)
0x0041067c:	movl %ecx, 0x4(%eax)
0x0041067f:	testl %ecx, %ecx
0x00410681:	jne 4
0x00410683:	xorl %eax, %eax
0x00410685:	jmp 0x00410689
0x00410689:	pushl %ecx
0x0041068a:	pushl %eax
0x0041068b:	xorl %ebx, %ebx
0x0041068d:	pushl %ebx
0x0041068e:	pushl %ebx
0x0041068f:	pushl %esi
0x00410690:	call HttpSendRequestW@WinINet.dll
HttpSendRequestW@WinINet.dll: API Node	
0x00410697:	cmpl %eax, %ebx
0x00410699:	je 1564
0x0041069f:	movl %eax, 0x4c(%esp)
0x004106a3:	pushl %ebx
0x004106a4:	leal %ecx, 0xc4(%esp)
0x004106ab:	pushl %ecx
0x004106ac:	leal %edx, 0xb0(%esp)
0x004106b3:	pushl %edx
0x004106b4:	pushl $0x20000013<UINT32>
0x004106b9:	pushl %eax
0x004106ba:	call HttpQueryInfoW@WinINet.dll
HttpQueryInfoW@WinINet.dll: API Node	
0x004106c1:	cmpl %eax, %ebx
0x004106c3:	je 1522
0x004106c9:	movl %esi, 0x4c(%esp)
0x004106cd:	leal %ecx, 0x28(%esp)
0x004106d1:	pushl %esi
0x004106d2:	pushl %ecx
0x004106d3:	call 0x00413cd0
0x00413cd0:	pushl %ebp
0x00413cd1:	movl %ebp, %esp
0x00413cd3:	pushl $0xffffffff<UINT8>
0x00413cd5:	pushl $0x431db1<UINT32>
0x00413cda:	movl %eax, %fs:0
0x00413ce0:	pushl %eax
0x00413ce1:	subl %esp, $0x4c<UINT8>
0x00413ce4:	pushl %ebx
0x00413ce5:	pushl %esi
0x00413ce6:	pushl %edi
0x00413ce7:	movl %eax, 0x44609c
0x00413cec:	xorl %eax, %ebp
0x00413cee:	pushl %eax
0x00413cef:	leal %eax, -12(%ebp)
0x00413cf2:	movl %fs:0, %eax
0x00413cf8:	xorl %ebx, %ebx
0x00413cfa:	movl -4(%ebp), %ebx
0x00413cfd:	movl -20(%ebp), %ebx
0x00413d00:	movl %ecx, 0x8(%ebp)
0x00413d03:	call 0x00406a10
0x00413d08:	movl %edi, $0x1<UINT32>
0x00413d0d:	movl -20(%ebp), %edi
0x00413d10:	movl -16(%ebp), %ebx
0x00413d13:	movl -52(%ebp), %ebx
0x00413d16:	movl -48(%ebp), %ebx
0x00413d19:	movl -44(%ebp), %ebx
0x00413d1c:	movl -4(%ebp), %edi
0x00413d1f:	movb 0x448860, %bl
0x00413d25:	leal %ebx, 0x27(%edi)
0x00413d28:	leal %esi, -52(%ebp)
0x00413d2b:	movl -40(%ebp), %edi
0x00413d2e:	call 0x004034c0
0x00413d33:	movl %eax, -48(%ebp)
0x00413d36:	movl %edx, 0x440568
0x00413d3c:	movl %ecx, -52(%ebp)
0x00413d3f:	movl (%eax,%ecx), %edx
0x00413d42:	movl %edx, 0x44056c
0x00413d48:	movl 0x4(%eax,%ecx), %edx
0x00413d4c:	movl %edx, 0x440570
0x00413d52:	movl 0x8(%eax,%ecx), %edx
0x00413d56:	movl %edx, 0x440574
0x00413d5c:	movl 0xc(%eax,%ecx), %edx
0x00413d60:	movl %edx, 0x440578
0x00413d66:	movl 0x10(%eax,%ecx), %edx
0x00413d6a:	movl %edx, 0x44057c
0x00413d70:	movl 0x14(%eax,%ecx), %edx
0x00413d74:	addl %eax, $0x18<UINT8>
0x00413d77:	movl -48(%ebp), %eax
0x00413d7a:	xorl %eax, %eax
0x00413d7c:	movl -4(%ebp), $0x2<UINT32>
0x00413d83:	movl -36(%ebp), %eax
0x00413d86:	movl -32(%ebp), %eax
0x00413d89:	movl -28(%ebp), %eax
0x00413d8c:	movb -4(%ebp), $0x3<UINT8>
0x00413d90:	leal %esi, -36(%ebp)
0x00413d93:	movl -24(%ebp), %edi
0x00413d96:	movb 0x448860, %al
0x00413d9b:	call 0x004034c0
0x00413da0:	movl %eax, -32(%ebp)
0x00413da3:	movl %edx, 0x440314
0x00413da9:	movl %ecx, -36(%ebp)
0x00413dac:	movl (%eax,%ecx), %edx
0x00413daf:	movl %edx, 0x440318
0x00413db5:	movl 0x4(%eax,%ecx), %edx
0x00413db9:	movl %edx, 0x44031c
0x00413dbf:	movl 0x8(%eax,%ecx), %edx
0x00413dc3:	movl %edx, 0x440320
0x00413dc9:	movl 0xc(%eax,%ecx), %edx
0x00413dcd:	movl %edx, 0x440324
0x00413dd3:	movl 0x10(%eax,%ecx), %edx
0x00413dd7:	movl %edx, 0x440328
0x00413ddd:	movl 0x14(%eax,%ecx), %edx
0x00413de1:	addl %eax, $0x18<UINT8>
0x00413de4:	movl -32(%ebp), %eax
0x00413de7:	pushl %edi
0x00413de8:	pushl $0x0<UINT8>
0x00413dea:	leal %eax, -52(%ebp)
0x00413ded:	pushl %eax
0x00413dee:	leal %ecx, -84(%ebp)
0x00413df1:	pushl %ecx
0x00413df2:	movb -4(%ebp), $0x4<UINT8>
0x00413df6:	call 0x0041b070
0x00413dfb:	movl %ebx, %eax
0x00413dfd:	pushl %edi
0x00413dfe:	pushl $0x0<UINT8>
0x00413e00:	movl %edx, %esi
0x00413e02:	pushl %edx
0x00413e03:	leal %eax, -68(%ebp)
0x00413e06:	movb -4(%ebp), $0x5<UINT8>
0x00413e0a:	pushl %eax
0x00413e0b:	call 0x0041b070
0x00413e10:	movl %esi, %eax
0x00413e12:	movl %eax, (%esi)
0x00413e14:	addl %esp, $0x20<UINT8>
0x00413e17:	pushl %eax
0x00413e18:	call GetModuleHandleA@kernel32.dll
0x00413e1e:	testl %eax, %eax
0x00413e20:	jne 0x00413e2b
0x00413e2b:	movl %ebx, (%ebx)
0x00413e2d:	pushl %ebx
0x00413e2e:	pushl %eax
0x00413e2f:	call GetProcAddress@kernel32.dll
0x00413e35:	movl %edi, %eax
0x00413e37:	xorl %esi, %esi
0x00413e39:	cmpl %edi, %esi
0x00413e3b:	jne 0x00413e43
0x00413e43:	movl %eax, -68(%ebp)
0x00413e46:	cmpl %eax, %esi
0x00413e48:	je 9
0x00413e4a:	pushl %eax
0x00413e4b:	call 0x0041d3a9
0x00413e50:	addl %esp, $0x4<UINT8>
0x00413e53:	movl %eax, -84(%ebp)
0x00413e56:	movl -68(%ebp), %esi
0x00413e59:	movl -64(%ebp), %esi
0x00413e5c:	movl -60(%ebp), %esi
0x00413e5f:	cmpl %eax, %esi
0x00413e61:	je 9
0x00413e63:	pushl %eax
0x00413e64:	call 0x0041d3a9
0x00413e69:	addl %esp, $0x4<UINT8>
0x00413e6c:	movl %eax, -36(%ebp)
0x00413e6f:	movl -84(%ebp), %esi
0x00413e72:	movl -80(%ebp), %esi
0x00413e75:	movl -76(%ebp), %esi
0x00413e78:	cmpl %eax, %esi
0x00413e7a:	je 9
0x00413e7c:	pushl %eax
0x00413e7d:	call 0x0041d3a9
0x00413e82:	addl %esp, $0x4<UINT8>
0x00413e85:	movb -4(%ebp), $0x0<UINT8>
0x00413e89:	movl %eax, -52(%ebp)
0x00413e8c:	cmpl %eax, %esi
0x00413e8e:	je 9
0x00413e90:	pushl %eax
0x00413e91:	call 0x0041d3a9
0x00413e96:	addl %esp, $0x4<UINT8>
0x00413e99:	movl %edx, 0xc(%ebp)
0x00413e9c:	pushl %esi
0x00413e9d:	leal %ecx, -16(%ebp)
0x00413ea0:	pushl %ecx
0x00413ea1:	pushl %esi
0x00413ea2:	pushl $0x16<UINT8>
0x00413ea4:	pushl %edx
0x00413ea5:	call HttpQueryInfoA@WinINet.dll
HttpQueryInfoA@WinINet.dll: API Node	
0x00413ea7:	testl %eax, %eax
0x00413ea9:	jne 0x00413eff
0x00413eff:	movl %eax, 0x8(%ebp)
0x00413f02:	movl %ecx, -12(%ebp)
0x00413f05:	movl %fs:0, %ecx
0x00413f0c:	popl %ecx
0x00413f0d:	popl %edi
0x00413f0e:	popl %esi
0x00413f0f:	popl %ebx
0x00413f10:	movl %esp, %ebp
0x00413f12:	popl %ebp
0x00413f13:	ret

0x004106d8:	addl %esp, $0x8<UINT8>
0x004106db:	movl 0x1f8(%esp), $0x23<UINT32>
0x004106e6:	movl %eax, 0xa8(%esp)
0x004106ed:	addl %eax, $0xffffff38<UINT32>
0x004106f2:	cmpl %eax, $0xcf<UINT32>
0x004106f7:	ja 0x00410bf9
0x00410bf9:	movl 0x38(%esp), $0x2<UINT32>
0x00410c01:	movl 0x1f8(%esp), $0xffffffff<UINT32>
0x00410c0c:	movl %eax, 0x28(%esp)
0x00410c10:	cmpl %eax, %ebx
0x00410c12:	je 9
0x00410c14:	pushl %eax
0x00410c15:	call 0x0041d3a9
0x00410c1a:	addl %esp, $0x4<UINT8>
0x00410c1d:	xorl %ebx, %ebx
0x00410c1f:	pushl %ebx
0x00410c20:	pushl $0x440420<UINT32>
0x00410c25:	leal %ecx, 0x20(%esp)
0x00410c29:	pushl %ecx
0x00410c2a:	call 0x004035d0
0x00410c2f:	pushl %ebx
0x00410c30:	pushl $0x440314<UINT32>
0x00410c35:	leal %edx, 0x5c(%esp)
0x00410c39:	pushl %edx
0x00410c3a:	movl 0x204(%esp), $0x33<UINT32>
0x00410c45:	call 0x004035d0
0x00410c4a:	pushl $0x1<UINT8>
0x00410c4c:	pushl %ebx
0x00410c4d:	leal %eax, 0x20(%esp)
0x00410c51:	pushl %eax
0x00410c52:	leal %ecx, 0xa0(%esp)
0x00410c59:	pushl %ecx
0x00410c5a:	movb 0x208(%esp), $0x34<UINT8>
0x00410c62:	call 0x0041b070
0x00410c67:	movl %edi, %eax
0x00410c69:	pushl $0x1<UINT8>
0x00410c6b:	pushl %ebx
0x00410c6c:	leal %edx, 0x6c(%esp)
0x00410c70:	pushl %edx
0x00410c71:	leal %eax, 0xa0(%esp)
0x00410c78:	pushl %eax
0x00410c79:	movb 0x218(%esp), $0x35<UINT8>
0x00410c81:	call 0x0041b070
0x00410c86:	movl %esi, %eax
0x00410c88:	movl %eax, (%esi)
0x00410c8a:	addl %esp, $0x20<UINT8>
0x00410c8d:	pushl %eax
0x00410c8e:	call GetModuleHandleA@kernel32.dll
0x00410c94:	cmpl %eax, %ebx
0x00410c96:	jne 0x00410ca1
0x00410ca1:	movl %ecx, (%edi)
0x00410ca3:	pushl %ecx
0x00410ca4:	pushl %eax
0x00410ca5:	call GetProcAddress@kernel32.dll
0x00410cab:	movl %esi, %eax
0x00410cad:	cmpl %esi, %ebx
0x00410caf:	jne 0x00410e89
0x00410e89:	movl %eax, 0x84(%esp)
0x00410e90:	cmpl %eax, %ebx
0x00410e92:	je 9
0x00410e94:	pushl %eax
0x00410e95:	call 0x0041d3a9
0x00410e9a:	addl %esp, $0x4<UINT8>
0x00410e9d:	movl %eax, 0x94(%esp)
0x00410ea4:	movl 0x84(%esp), %ebx
0x00410eab:	movl 0x88(%esp), %ebx
0x00410eb2:	movl 0x8c(%esp), %ebx
0x00410eb9:	cmpl %eax, %ebx
0x00410ebb:	je 9
0x00410ebd:	pushl %eax
0x00410ebe:	call 0x0041d3a9
0x00410ec3:	addl %esp, $0x4<UINT8>
0x00410ec6:	movl %eax, 0x54(%esp)
0x00410eca:	movl 0x94(%esp), %ebx
0x00410ed1:	movl 0x98(%esp), %ebx
0x00410ed8:	movl 0x9c(%esp), %ebx
0x00410edf:	cmpl %eax, %ebx
0x00410ee1:	je 9
0x00410ee3:	pushl %eax
0x00410ee4:	call 0x0041d3a9
0x00410ee9:	addl %esp, $0x4<UINT8>
0x00410eec:	movl 0x1f8(%esp), $0xffffffff<UINT32>
0x00410ef7:	movl %eax, 0x18(%esp)
0x00410efb:	movl 0x54(%esp), %ebx
0x00410eff:	movl 0x58(%esp), %ebx
0x00410f03:	movl 0x5c(%esp), %ebx
0x00410f07:	cmpl %eax, %ebx
0x00410f09:	je 9
0x00410f0b:	pushl %eax
0x00410f0c:	call 0x0041d3a9
0x00410f11:	addl %esp, $0x4<UINT8>
0x00410f14:	movl %eax, 0x4c(%esp)
0x00410f18:	movl 0x18(%esp), %ebx
0x00410f1c:	movl 0x1c(%esp), %ebx
0x00410f20:	movl 0x20(%esp), %ebx
0x00410f24:	cmpl %eax, %ebx
0x00410f26:	je 3
0x00410f28:	pushl %eax
0x00410f29:	call InternetCloseHandle@WinINet.dll
InternetCloseHandle@WinINet.dll: API Node	
0x00410f2b:	movl %eax, 0x68(%esp)
0x00410f2f:	cmpl %eax, %ebx
0x00410f31:	je 0x00410f36
0x00410f36:	movl %eax, 0x48(%esp)
0x00410f3a:	cmpl %eax, %ebx
0x00410f3c:	je 3
0x00410f3e:	pushl %eax
0x00410f3f:	call InternetCloseHandle@WinINet.dll
0x00410f41:	movl %eax, 0x38(%esp)
0x00410f45:	movl %ecx, 0x1f0(%esp)
0x00410f4c:	movl %fs:0, %ecx
0x00410f53:	popl %ecx
0x00410f54:	popl %edi
0x00410f55:	popl %esi
0x00410f56:	popl %ebx
0x00410f57:	movl %ecx, 0x1d8(%esp)
0x00410f5e:	xorl %ecx, %esp
0x00410f60:	call 0x0041d190
0x0041d19a:	jmp 0x0041f22c
0x0041f22c:	movl %edi, %edi
0x0041f22e:	pushl %ebp
0x0041f22f:	movl %ebp, %esp
0x0041f231:	subl %esp, $0x328<UINT32>
0x0041f237:	movl 0x447ee0, %eax
0x0041f23c:	movl 0x447edc, %ecx
0x0041f242:	movl 0x447ed8, %edx
0x0041f248:	movl 0x447ed4, %ebx
0x0041f24e:	movl 0x447ed0, %esi
0x0041f254:	movl 0x447ecc, %edi
0x0041f25a:	movw 0x447ef8, %ss
0x0041f261:	movw 0x447eec, %cs
0x0041f268:	movw 0x447ec8, %ds
0x0041f26f:	movw 0x447ec4, %es
0x0041f276:	movw 0x447ec0, %fs
0x0041f27d:	movw 0x447ebc, %gs
0x0041f284:	pushfl
0x0041f285:	popl 0x447ef0
0x0041f28b:	movl %eax, (%ebp)
0x0041f28e:	movl 0x447ee4, %eax
0x0041f293:	movl %eax, 0x4(%ebp)
0x0041f296:	movl 0x447ee8, %eax
0x0041f29b:	leal %eax, 0x8(%ebp)
0x0041f29e:	movl 0x447ef4, %eax
0x0041f2a3:	movl %eax, -800(%ebp)
0x0041f2a9:	movl 0x447e30, $0x10001<UINT32>
0x0041f2b3:	movl %eax, 0x447ee8
0x0041f2b8:	movl 0x447de4, %eax
0x0041f2bd:	movl 0x447dd8, $0xc0000409<UINT32>
0x0041f2c7:	movl 0x447ddc, $0x1<UINT32>
0x0041f2d1:	movl %eax, 0x44609c
0x0041f2d6:	movl -808(%ebp), %eax
0x0041f2dc:	movl %eax, 0x4460a0
0x0041f2e1:	movl -804(%ebp), %eax
0x0041f2e7:	call IsDebuggerPresent@kernel32.dll
IsDebuggerPresent@kernel32.dll: API Node	
0x0041f2ed:	movl 0x447e28, %eax
0x0041f2f2:	pushl $0x1<UINT8>
0x0041f2f4:	call 0x0042845b
0x0042845b:	andl 0x449a48, $0x0<UINT8>
0x00428462:	ret

0x0041f2f9:	popl %ecx
0x0041f2fa:	pushl $0x0<UINT8>
0x0041f2fc:	call SetUnhandledExceptionFilter@kernel32.dll
0x0041f302:	pushl $0x43b6a4<UINT32>
0x0041f307:	call UnhandledExceptionFilter@kernel32.dll
UnhandledExceptionFilter@kernel32.dll: API Node	
0x0041f30d:	cmpl 0x447e28, $0x0<UINT8>
0x0041f314:	jne 8
0x0041f316:	pushl $0x1<UINT8>
0x0041f318:	call 0x0042845b
0x0041f31d:	popl %ecx
0x0041f31e:	pushl $0xc0000409<UINT32>
0x0041f323:	call GetCurrentProcess@kernel32.dll
0x0041f329:	pushl %eax
0x0041f32a:	call TerminateProcess@kernel32.dll
TerminateProcess@kernel32.dll: API Node	
0x0041f330:	leave
0x0041a313:	pushl %edi
0x0041a314:	pushl %edx
0x0041a315:	call 0x00418380
0x00418380:	pushl %ebp
0x00418381:	movl %ebp, %esp
0x00418383:	pushl $0xffffffff<UINT8>
0x00418385:	pushl $0x432b09<UINT32>
0x0041838a:	movl %eax, %fs:0
0x00418390:	pushl %eax
0x00418391:	pushl %ecx
0x00418392:	pushl %esi
0x00418393:	movl %eax, 0x44609c
0x00418398:	xorl %eax, %ebp
0x0041839a:	pushl %eax
0x0041839b:	leal %eax, -12(%ebp)
0x0041839e:	movl %fs:0, %eax
0x004183a4:	movl %esi, 0x8(%ebp)
0x004183a7:	movl %eax, 0xc(%ebp)
0x004183aa:	pushl %eax
0x004183ab:	movl -4(%ebp), $0x0<UINT32>
0x004183b2:	pushl %esi
0x004183b3:	movl -16(%ebp), $0x0<UINT32>
0x004183ba:	call 0x00406bd0
0x004183bf:	movl -4(%ebp), $0x0<UINT32>
0x004183c6:	movl %ecx, %esi
0x004183c8:	movl -16(%ebp), $0x1<UINT32>
0x004183cf:	call 0x00417f90
0x00417f90:	pushl %ebp
0x00417f91:	movl %ebp, %esp
0x00417f93:	pushl $0xffffffff<UINT8>
0x00417f95:	pushl $0x431b29<UINT32>
0x00417f9a:	movl %eax, %fs:0
0x00417fa0:	pushl %eax
0x00417fa1:	subl %esp, $0x194<UINT32>
0x00417fa7:	movl %eax, 0x44609c
0x00417fac:	xorl %eax, %ebp
0x00417fae:	movl -16(%ebp), %eax
0x00417fb1:	pushl %ebx
0x00417fb2:	pushl %esi
0x00417fb3:	pushl %edi
0x00417fb4:	pushl %eax
0x00417fb5:	leal %eax, -12(%ebp)
0x00417fb8:	movl %fs:0, %eax
0x00417fbe:	movl %esi, %ecx
0x00417fc0:	movl -316(%ebp), %esi
0x00417fc6:	call 0x00405790
0x00417fcb:	xorl %ebx, %ebx
0x00417fcd:	cmpl %esi, %ebx
0x00417fcf:	je 566
0x00417fd5:	cmpl 0x4(%esi), %ebx
0x00417fd8:	je 557
0x00417fde:	movl %eax, (%esi)
0x00417fe0:	cmpb (%eax), $0x25<UINT8>
0x00417fe3:	jne 0x0041820b
0x0041820b:	pushl %ebx
0x0041820c:	pushl $0x440b3c<UINT32>
0x00418211:	leal %eax, -296(%ebp)
0x00418217:	pushl %eax
0x00418218:	call 0x004035d0
0x0041821d:	pushl %ebx
0x0041821e:	pushl $0x440080<UINT32>
0x00418223:	leal %ecx, -312(%ebp)
0x00418229:	pushl %ecx
0x0041822a:	movl -4(%ebp), $0xa<UINT32>
0x00418231:	call 0x004035d0
0x00418236:	pushl $0x1<UINT8>
0x00418238:	pushl %ebx
0x00418239:	leal %edx, -296(%ebp)
0x0041823f:	pushl %edx
0x00418240:	leal %eax, -348(%ebp)
0x00418246:	pushl %eax
0x00418247:	movb -4(%ebp), $0xb<UINT8>
0x0041824b:	call 0x0041b070
0x00418250:	movl %edi, %eax
0x00418252:	pushl $0x1<UINT8>
0x00418254:	pushl %ebx
0x00418255:	leal %ecx, -312(%ebp)
0x0041825b:	pushl %ecx
0x0041825c:	leal %edx, -332(%ebp)
0x00418262:	pushl %edx
0x00418263:	movb -4(%ebp), $0xc<UINT8>
0x00418267:	call 0x0041b070
0x0041826c:	movl %esi, %eax
0x0041826e:	movl %eax, (%esi)
0x00418270:	addl %esp, $0x20<UINT8>
0x00418273:	pushl %eax
0x00418274:	call GetModuleHandleA@kernel32.dll
0x0041827a:	cmpl %eax, %ebx
0x0041827c:	jne 0x00418287
0x00418287:	movl %edi, (%edi)
0x00418289:	pushl %edi
0x0041828a:	pushl %eax
0x0041828b:	call GetProcAddress@kernel32.dll
0x00418291:	movl %esi, %eax
0x00418293:	cmpl %esi, %ebx
0x00418295:	jne 0x0041829d
0x0041829d:	movl %eax, -332(%ebp)
0x004182a3:	cmpl %eax, %ebx
0x004182a5:	je 9
0x004182a7:	pushl %eax
0x004182a8:	call 0x0041d3a9
0x004182ad:	addl %esp, $0x4<UINT8>
0x004182b0:	movl %eax, -348(%ebp)
0x004182b6:	movl -332(%ebp), %ebx
0x004182bc:	movl -328(%ebp), %ebx
0x004182c2:	movl -324(%ebp), %ebx
0x004182c8:	cmpl %eax, %ebx
0x004182ca:	je 9
0x004182cc:	pushl %eax
0x004182cd:	call 0x0041d3a9
0x004182d2:	addl %esp, $0x4<UINT8>
0x004182d5:	movl %eax, -312(%ebp)
0x004182db:	movl -348(%ebp), %ebx
0x004182e1:	movl -344(%ebp), %ebx
0x004182e7:	movl -340(%ebp), %ebx
0x004182ed:	cmpl %eax, %ebx
0x004182ef:	je 9
0x004182f1:	pushl %eax
0x004182f2:	call 0x0041d3a9
0x004182f7:	addl %esp, $0x4<UINT8>
0x004182fa:	movl -4(%ebp), $0xffffffff<UINT32>
0x00418301:	movl %eax, -296(%ebp)
0x00418307:	movl -312(%ebp), %ebx
0x0041830d:	movl -308(%ebp), %ebx
0x00418313:	movl -304(%ebp), %ebx
0x00418319:	cmpl %eax, %ebx
0x0041831b:	je 9
0x0041831d:	pushl %eax
0x0041831e:	call 0x0041d3a9
0x00418323:	addl %esp, $0x4<UINT8>
0x00418326:	movl %edi, -316(%ebp)
0x0041832c:	movl %eax, (%edi)
0x0041832e:	pushl $0x104<UINT32>
0x00418333:	leal %ecx, -280(%ebp)
0x00418339:	pushl %ecx
0x0041833a:	pushl %eax
0x0041833b:	movl -296(%ebp), %ebx
0x00418341:	movl -292(%ebp), %ebx
0x00418347:	movl -288(%ebp), %ebx
0x0041834d:	call GetShortPathNameA@Kernel32.dll
GetShortPathNameA@Kernel32.dll: API Node	
0x0041834f:	testl %eax, %eax
0x00418351:	je 14
0x00418353:	leal %edx, -280(%ebp)
0x00418359:	pushl %edx
0x0041835a:	movl %eax, %edi
0x0041835c:	call 0x00405eb0
0x00418361:	movl %ecx, -12(%ebp)
0x00418364:	movl %fs:0, %ecx
0x0041836b:	popl %ecx
0x0041836c:	popl %edi
0x0041836d:	popl %esi
0x0041836e:	popl %ebx
0x0041836f:	movl %ecx, -16(%ebp)
0x00418372:	xorl %ecx, %ebp
0x00418374:	call 0x0041d190
0x00418379:	movl %esp, %ebp
0x0041837b:	popl %ebp
0x0041837c:	ret

0x004183d4:	movl %eax, %esi
0x004183d6:	movl %ecx, -12(%ebp)
0x004183d9:	movl %fs:0, %ecx
0x004183e0:	popl %ecx
0x004183e1:	popl %esi
0x004183e2:	movl %esp, %ebp
0x004183e4:	popl %ebp
0x004183e5:	ret

0x0041a31a:	addl %esp, $0x8<UINT8>
0x0041a31d:	movl -4(%ebp), $0x0<UINT32>
0x0041a324:	movl %eax, (%eax)
0x0041a326:	movl %ecx, -16(%ebp)
0x0041a329:	movl %edx, (%ecx)
0x0041a32b:	pushl %eax
0x0041a32c:	movl %eax, 0x50(%edx)
0x0041a32f:	pushl %ecx
0x0041a330:	call 0x00000000
0x00000000:	addb (%eax), %al
0x00433260:	movl %edx, 0x8(%esp)
0x00433264:	leal %eax, 0xc(%edx)
0x00433267:	movl %ecx, -76(%edx)
0x0043326a:	xorl %ecx, %eax
0x0043326c:	call 0x0041d190
0x00433271:	movl %eax, $0x443c84<UINT32>
0x00433276:	jmp 0x0042f55d
0x0042f55d:	pushl %ebp
0x0042f55e:	movl %ebp, %esp
0x0042f560:	subl %esp, $0x8<UINT8>
0x0042f563:	pushl %ebx
0x0042f564:	pushl %esi
0x0042f565:	pushl %edi
0x0042f566:	cld
0x0042f567:	movl -4(%ebp), %eax
0x0042f56a:	xorl %eax, %eax
0x0042f56c:	pushl %eax
0x0042f56d:	pushl %eax
0x0042f56e:	pushl %eax
0x0042f56f:	pushl -4(%ebp)
0x0042f572:	pushl 0x14(%ebp)
0x0042f575:	pushl 0x10(%ebp)
0x0042f578:	pushl 0xc(%ebp)
0x0042f57b:	pushl 0x8(%ebp)
0x0042f57e:	call 0x0043053e
0x0043053e:	movl %edi, %edi
0x00430540:	pushl %ebp
0x00430541:	movl %ebp, %esp
0x00430543:	pushl %ebx
0x00430544:	pushl %esi
0x00430545:	pushl %edi
0x00430546:	call 0x00422ebd
0x0043054b:	cmpl 0x20c(%eax), $0x0<UINT8>
0x00430552:	movl %eax, 0x18(%ebp)
0x00430555:	movl %ecx, 0x8(%ebp)
0x00430558:	movl %edi, $0xe06d7363<UINT32>
0x0043055d:	movl %esi, $0x1fffffff<UINT32>
0x00430562:	movl %ebx, $0x19930522<UINT32>
0x00430567:	jne 32
0x00430569:	movl %edx, (%ecx)
0x0043056b:	cmpl %edx, %edi
0x0043056d:	je 26
0x0043056f:	cmpl %edx, $0x80000026<UINT32>
0x00430575:	je 18
0x00430577:	movl %edx, (%eax)
0x00430579:	andl %edx, %esi
0x0043057b:	cmpl %edx, %ebx
0x0043057d:	jb 10
0x0043057f:	testb 0x20(%eax), $0x1<UINT8>
0x00430583:	jne 0x0043061c
0x0043061c:	xorl %eax, %eax
0x0043061e:	incl %eax
0x0043061f:	popl %edi
0x00430620:	popl %esi
0x00430621:	popl %ebx
0x00430622:	popl %ebp
0x00430623:	ret

0x0042f583:	addl %esp, $0x20<UINT8>
0x0042f586:	movl -8(%ebp), %eax
0x0042f589:	popl %edi
0x0042f58a:	popl %esi
0x0042f58b:	popl %ebx
0x0042f58c:	movl %eax, -8(%ebp)
0x0042f58f:	movl %esp, %ebp
0x0042f591:	popl %ebp
0x0042f592:	ret

0x00000002:	addb (%eax), %al
0x00000004:	addb (%eax), %al
0x00000006:	addb (%eax), %al
0x00000008:	addb (%eax), %al
0x0000000a:	addb (%eax), %al
0x0000000c:	addb (%eax), %al
0x0000000e:	addb (%eax), %al
0x00000010:	addb (%eax), %al
0x00000012:	addb (%eax), %al
0x00000014:	addb (%eax), %al
0x00000016:	addb (%eax), %al
0x00000018:	addb (%eax), %al
0x0000001a:	addb (%eax), %al
0x0000001c:	addb (%eax), %al
0x0000001e:	addb (%eax), %al
0x00000020:	addb (%eax), %al
0x00000022:	addb (%eax), %al
0x00000024:	addb (%eax), %al
0x00000026:	addb (%eax), %al
0x00000028:	addb (%eax), %al
0x0000002a:	addb (%eax), %al
0x0000002c:	addb (%eax), %al
0x0000002e:	addb (%eax), %al
0x00000030:	addb (%eax), %al
0x00000032:	addb (%eax), %al
0x00000034:	addb (%eax), %al
0x00000036:	addb (%eax), %al
0x00000038:	addb (%eax), %al
0x0000003a:	addb (%eax), %al
0x0000003c:	addb (%eax), %al
0x0000003e:	addb (%eax), %al
0x00000040:	addb (%eax), %al
0x00000042:	addb (%eax), %al
0x00000044:	addb (%eax), %al
0x00000046:	addb (%eax), %al
0x00000048:	addb (%eax), %al
0x0000004a:	addb (%eax), %al
0x0000004c:	addb (%eax), %al
0x0000004e:	addb (%eax), %al
0x00000050:	addb (%eax), %al
0x00000052:	addb (%eax), %al
0x00000054:	addb (%eax), %al
0x00000056:	addb (%eax), %al
0x00000058:	addb (%eax), %al
0x0000005a:	addb (%eax), %al
0x0000005c:	addb (%eax), %al
0x0000005e:	addb (%eax), %al
0x00000060:	addb (%eax), %al
0x00000062:	addb (%eax), %al
0x00000064:	addb (%eax), %al
0x00000066:	addb (%eax), %al
0x00000068:	addb (%eax), %al
0x0000006a:	addb (%eax), %al
0x0000006c:	addb (%eax), %al
0x0000006e:	addb (%eax), %al
0x00000070:	addb (%eax), %al
0x00000072:	addb (%eax), %al
0x00000074:	addb (%eax), %al
0x00000076:	addb (%eax), %al
0x00000078:	addb (%eax), %al
0x0000007a:	addb (%eax), %al
0x0000007c:	addb (%eax), %al
0x0000007e:	addb (%eax), %al
0x00000080:	addb (%eax), %al
0x00000082:	addb (%eax), %al
0x00000084:	addb (%eax), %al
0x00000086:	addb (%eax), %al
0x00000088:	addb (%eax), %al
0x0000008a:	addb (%eax), %al
0x0000008c:	addb (%eax), %al
0x0000008e:	addb (%eax), %al
0x00000090:	addb (%eax), %al
0x00000092:	addb (%eax), %al
0x00000094:	addb (%eax), %al
0x00000096:	addb (%eax), %al
0x00000098:	addb (%eax), %al
0x0000009a:	addb (%eax), %al
0x0000009c:	addb (%eax), %al
0x0000009e:	addb (%eax), %al
0x000000a0:	addb (%eax), %al
0x000000a2:	addb (%eax), %al
0x000000a4:	addb (%eax), %al
0x000000a6:	addb (%eax), %al
0x000000a8:	addb (%eax), %al
0x000000aa:	addb (%eax), %al
0x000000ac:	addb (%eax), %al
0x000000ae:	addb (%eax), %al
0x000000b0:	addb (%eax), %al
0x000000b2:	addb (%eax), %al
0x000000b4:	addb (%eax), %al
0x000000b6:	addb (%eax), %al
0x000000b8:	addb (%eax), %al
0x000000ba:	addb (%eax), %al
0x000000bc:	addb (%eax), %al
0x000000be:	addb (%eax), %al
0x000000c0:	addb (%eax), %al
0x000000c2:	addb (%eax), %al
0x000000c4:	addb (%eax), %al
0x000000c6:	addb (%eax), %al
0x000000c8:	addb (%eax), %al
